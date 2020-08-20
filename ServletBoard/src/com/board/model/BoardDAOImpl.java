package com.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;





public class BoardDAOImpl implements BoardDAO  {
	
	
	//디비셋팅
private static BoardDAOImpl instance = new BoardDAOImpl();
	
	public static BoardDAOImpl getInstance() {
		return instance;
	}
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/board");
		return ds.getConnection();	
	}
	
	//회원수-검색x
	public int boardCount() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int count=0;
		
		try {
			con=getConnection();
			st=con.createStatement();
			String sql="select count(*) from tbl_board";
			rs=st.executeQuery(sql);
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, st,rs);
		}
		return count;
	}
	
	//회원수-검색포함
	public int boardCount(String field, String word) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int count=0;
		String sql="";
		
		try {
			con=getConnection();
			st=con.createStatement();
			
			if(word.equals("")) {
				sql="select count(*) from tbl_board";
			}else {
				sql="select count(*) from tbl_board where "+field+ " like '%"+word+"%'";
			}
			
			rs=st.executeQuery(sql);
			if(rs.next()) {
				count=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, st,rs);
		}
		return count;
	}
	

	
	
	//추가
	@Override
	public void boardSave(BoardDTO board) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con=getConnection();
			String sql="insert into tbl_board values(tbl_board_seq.nextval,?,?,?,sysdate,0)";
			ps=con.prepareStatement(sql);
			
			ps.setString(1, board.getWriter());
			ps.setString(2, board.getContent());
			ps.setString(3, board.getSubject());
	
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection(con, ps);
		}
		
	}


	//전체보기 검색
	public ArrayList<BoardDTO> boardSearch(String field, String word){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<BoardDTO>arr = new ArrayList<>();
		
		try {
			con=getConnection();
			String sql="select*from tbl_board where "+field+" like'%"+word+"%'";
			
			st=con.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setWriter(rs.getString("writer"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setReg_date(rs.getString("reg_date"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection(con, st,rs);
		}
		return arr;
	}
	
	//전체보기  페이징
		public ArrayList<BoardDTO>boardList(int startRow, int endRow){
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			ArrayList<BoardDTO> arr = new ArrayList<>();
			
			try {
				con=getConnection();	
						
				StringBuilder sb = new StringBuilder();
				sb.append("select*from");
				sb.append(" (select aa.*,rownum rn from");
				sb.append(" (select*from tbl_board order by num desc)aa");
				sb.append(" where rownum<=?) where rn>=?");
				
				
				ps=con.prepareStatement(sb.toString());
				ps.setInt(1, endRow);
				ps.setInt(2, startRow);
				rs=ps.executeQuery();
				while(rs.next()) {
					BoardDTO bo = new BoardDTO();
					bo.setNum(rs.getInt("num"));
					bo.setWriter(rs.getString("writer"));
					bo.setContent(rs.getString("content"));
					bo.setSubject(rs.getString("subject"));
					bo.setReg_date(rs.getString("reg_date"));
					bo.setReadcount(rs.getInt("readcount"));
					arr.add(bo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeConnection(con, ps,rs);
			}
			return arr;				
		}
	
	//전체보기 검색 포함 페이징
	public ArrayList<BoardDTO>boardList(String field, String word, int startRow, int endRow){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ArrayList<BoardDTO> arr = new ArrayList<>();
		
		try {
			con=getConnection();	
					
			StringBuilder sb = new StringBuilder();
			sb.append("select*from");
			sb.append(" (select aa.*,rownum rn from");
			sb.append(" (select*from tbl_board where "+field+" like'%"+word+"%'");
			sb.append(" order by num desc)aa");
			sb.append(" where rownum<=?) where rn>=?");
			
			
			
			ps=con.prepareStatement(sb.toString());
			ps.setInt(1, endRow);
			ps.setInt(2, startRow);
			rs=ps.executeQuery();
			while(rs.next()) {
				BoardDTO bo = new BoardDTO();
				bo.setNum(rs.getInt("num"));
				bo.setWriter(rs.getString("writer"));
				bo.setContent(rs.getString("content"));
				bo.setSubject(rs.getString("subject"));
				bo.setReg_date(rs.getString("reg_date"));
				bo.setReadcount(rs.getInt("readcount"));
				arr.add(bo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, ps,rs);
		}
		return arr;				
	}

	@Override
	public BoardDTO boardfindById(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void boardUpdate(BoardDTO board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void boardDelete(int num) {
		// TODO Auto-generated method stub
		
	}
	
	
	//닫기
		private void closeConnection(Connection con, PreparedStatement ps) {
				
				try {
					if(ps!=null) ps.close();
					if(con!=null) con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		private void closeConnection(Connection con, Statement st, ResultSet rs) {
			
				try {
					if(rs!=null) rs.close();
					if(st!=null) st.close();
					if(con!=null) con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

}
