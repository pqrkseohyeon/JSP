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

import com.sun.org.apache.regexp.internal.recompile;

public class BoardDAOImpl implements BoardDAO{
	//DB셋팅
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
	
	//commentList
	public ArrayList<CommentDTO> commentList(int bnum){
		Connection con = null;
		Statement st =  null;
		ResultSet rs = null;
		ArrayList<CommentDTO> arr = new ArrayList<CommentDTO>();
		try {
			con=getConnection();
			String sql="select*from commenttable where bnum="+bnum;
			st=con.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				CommentDTO comment = new CommentDTO();
				comment.setBnum(rs.getInt("bnum"));
				comment.setCnum(rs.getInt("cnum"));
				comment.setMsg(rs.getString("msg"));
				comment.setRegdate(rs.getString("regdate"));
				comment.setUserid(rs.getString("userid"));
				arr.add(comment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, st,rs);
		}
		return arr;
	}
	
	//commentInsert
	public void commentInsert(CommentDTO comment) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con=getConnection();
			String sql = "INSERT INTO commenttable (cnum, userid, msg, regdate, bnum) VALUES (commenttable_seq.nextval,?, ?, sysdate, ?)";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, comment.getUserid());
			ps.setString(2, comment.getMsg());
			ps.setInt(3, comment.getBnum());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con,ps);
		}
		
	}
	
	//추가하기
	@Override
	public void boardSave(BoardDTO board) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con=getConnection();
			String sql = "INSERT INTO tbl_board (num, writer, content, subject, reg_date, readcount) VALUES (tbl_board_seq.nextval, ?, ?, ?, sysdate, 0)";
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getWriter());
			ps.setString(2, board.getContent());
			ps.setString(3, board.getSubject());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con,ps);
		}
		
	}
	//전체보기 검색 포함 페이징
	@Override
	public ArrayList<BoardDTO> boardList(String field, String word,int startRow, int endRow){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<BoardDTO> arr = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		try {
			con = getConnection();
			if(word.equals("")) {
				sb.append("select * from");
				sb.append(" (select aa.*,rownum rn from");
				sb.append(" (select * from tbl_board order by num desc)aa");
				sb.append(" where rownum<=?) where rn >=?");
				
				
			}else {
				sb.append("select * from");
				sb.append(" (select aa.*,rownum rn from");
				sb.append(" (select * from tbl_board where "+field +" like '%"+word+"%'");
				sb.append(" order by num desc)aa");
				sb.append(" where rownum<=?) where rn >=?");	
			}
			ps = con.prepareStatement(sb.toString());
			ps.setInt(1, endRow);
			ps.setInt(2, startRow);
		
			rs = ps.executeQuery();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setReg_date(rs.getString("reg_date"));
				dto.setReadcount(rs.getInt("readcount"));
				arr.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, ps, rs);
		}
		return arr;
	}
	//상세보기
	@Override
	public BoardDTO boardfindById(int num) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		BoardDTO dto = null;
		
		try {
			con = getConnection();
			st = con.createStatement();
			st.execute("update tbl_board set readcount = readcount+1 where num ="+num);
			String sql = "SELECT * FROM tbl_board WHERE num="+num;
			rs = st.executeQuery(sql);
			if(rs.next()) {
				dto = new BoardDTO();
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setReg_date(rs.getString("reg_date"));
				dto.setReadcount(rs.getInt("readcount"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con,st,rs);
		}
		
		return dto;
	}
	//수정하기
	@Override
	public void boardUpdate(BoardDTO board) {

		
	}
	//삭제하기
	@Override
	public int boardDelete(int num) {
		Connection con = null;
		Statement st = null;
		int flag = 0;
		
		try {
			try {
				con = getConnection();
			} catch (Exception e) {
				e.printStackTrace();
		}
			String sql = "DELETE FROM btl_board WHERE num ='"+num+"'";
			st = con.createStatement();
			st.execute(sql);
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, null);
		}
		return flag;
	}
	//회원수-포함x
	@Override
	public int boardCount() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int count = 0;
		
		
		try { 
			con = getConnection();
			st = con.createStatement();
			String sql ="select count(*) from tbl_board";
			rs = st.executeQuery(sql);
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}

		return count;
	}
	//회원수 - 포함
	@Override
	public int boardCount(String field, String word) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int count = 0;
		String sql = "";
		
		try {
			con = getConnection();
			st = con.createStatement();
			if(word.equals("")) {
				 sql = "SELECT COUNT(*) FROM tbl_board";
			}else {
				sql = "SELECT COUNT(*) FROM tbl_board where "+field+" like '%"+word+"%'";
			}
			rs = st.executeQuery(sql);
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, st, rs);
		}
		return count;
	}
	
	//닫기
	private void closeConnection(Connection con, PreparedStatement ps) {
		try {
			if(ps!=null) ps.close();
			if(con!=null) con.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	private void closeConnection(Connection con, Statement st, ResultSet rs) {
		try {
			if(rs!=null) rs.close();
			if(st!=null) st.close();
			if(con!=null) con.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
