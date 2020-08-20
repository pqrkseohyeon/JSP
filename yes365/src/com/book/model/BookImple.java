package com.book.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

public class BookImple {

	// DB연결
	private static BookImple instance = new BookImple();

	public static BookImple getInstance() {
		return instance;
	}

	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/member");
		return ds.getConnection();
	}

	// 책 등록
	public int bookInsert(BookDTO vo) {
		Connection con = null;
		PreparedStatement ps = null;
		int flag = 0;

		try {
			con = getConnection();
			String sql = "insert into book(num,title,author,publisher,p_date,price,uploadFile,info) values(book_seq.nextval,?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);

			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getAuthor());
			ps.setString(3, vo.getPublisher());
			ps.setString(4, vo.getP_date());
			ps.setInt(5, vo.getPrice());
			ps.setString(6, vo.getUploadFile());
			ps.setString(7, vo.getInfo());
			flag = ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}
		return flag;
	}
	//도서목록 클릭시 전체보기
	public ArrayList<BookDTO> bookList(){
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		ArrayList<BookDTO> arr =new ArrayList<BookDTO>();
		
		try {
			con=getConnection();
			String sql="select * from book";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				BookDTO dto = new BookDTO();
				dto.setNum(rs.getInt("num"));
				dto.setTitle(rs.getString("title"));
				dto.setAuthor(rs.getString("author"));
				dto.setPublisher(rs.getString("publisher"));
				dto.setP_date(rs.getString("p_date"));
				dto.setPrice(rs.getInt("price"));
				dto.setUploadFile(rs.getString("uploadFile"));
				dto.setInfo(rs.getString("info"));
				arr.add(dto);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection(con, st,rs);
		}
		return arr;
	}

	  
	  //책 전체보기 - 검색X
	public ArrayList<BookDTO> bookList(int startRow, int endRow){
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<BookDTO>arr=new ArrayList<BookDTO>();	
		try {
			con=getConnection();
			StringBuilder sb = new StringBuilder();
			sb.append("select * from");
			sb.append(" (select aa.*, rownum rn from");
			sb.append(" (select * from book order by num desc) aa");
			sb.append(" where rownum<=?) where rn>=?");
			ps=con.prepareStatement(sb.toString());
			ps.setInt(1, endRow);
			ps.setInt(2, startRow);
			rs=ps.executeQuery();
			while(rs.next()) {
				BookDTO dto = new BookDTO();
				dto.setNum(rs.getInt("num"));
				dto.setTitle(rs.getString("title"));
				dto.setAuthor(rs.getString("author"));
				dto.setPublisher(rs.getString("publisher"));
				dto.setP_date(rs.getString("p_date"));
				dto.setPrice(rs.getInt("price"));
				dto.setUploadFile(rs.getString("uploadFile"));
				dto.setInfo(rs.getString("info"));
				arr.add(dto);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection(con, ps,rs);
		}
		return arr;
		
	}
	//책 전체보기 - 검색 o
	public ArrayList<BookDTO> bookList(String field, String word,int startRow, int endRow){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<BookDTO> arr = new ArrayList<BookDTO>();
		
		try {
			con=getConnection();
			StringBuilder sb = new StringBuilder();
			sb.append("select*from");
			sb.append(" (select aa.*,rownum rn from");
			sb.append(" (select*from book where upper ("+field+") like upper ('%"+word+"%')");
			sb.append(" order by num desc) aa");
			sb.append(" where rownum<=?) where rn>=?");
			ps=con.prepareStatement(sb.toString());
			ps.setInt(1, endRow);
			ps.setInt(2, startRow);
			rs=ps.executeQuery();
			while(rs.next()) {
				BookDTO dto = new BookDTO();
				dto.setNum(rs.getInt("num"));
				dto.setTitle(rs.getString("title"));
				dto.setAuthor(rs.getString("author"));
				dto.setPublisher(rs.getString("publisher"));
				dto.setP_date(rs.getString("p_date"));
				dto.setPrice(rs.getInt("price"));
				dto.setUploadFile(rs.getString("uploadfile"));
				dto.setInfo(rs.getString("info"));
				arr.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, ps,rs);
		}
		return arr;
		
	}
	
	//책 갯수 출력
	public int getCount(String field, String word) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int count=0;
		String sql="";
		
		try {
			con=getConnection();
			st=con.createStatement();
			if(word.equals("")) {
				sql="select count(*) from book";
			}else {
				sql="select count(*) from book where upper("+field+") like upper ('%"+word+"%')";
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
	
	//책 상세보기
	public BookDTO bookView(int num) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		BookDTO dto = null;
		
		try {
			con=getConnection();
			st=con.createStatement();
			String sql="select*from book where num="+num;
			rs=st.executeQuery(sql);
			if(rs.next()) {
				dto=new BookDTO();
				dto.setNum(rs.getInt("num"));
				dto.setTitle(rs.getString("title"));
				dto.setAuthor(rs.getString("author"));
				dto.setPublisher(rs.getString("publisher"));
				dto.setP_date(rs.getString("p_date"));
				dto.setPrice(rs.getInt("price"));
				dto.setUploadFile(rs.getString("uploadFile"));
				dto.setInfo(rs.getString("info"));
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection(con, st,rs);
		}
		return dto;
	}
	
	//도서 정보 수정
	public int bookUpdate(BookDTO vo) {
		Connection con = null;
		PreparedStatement ps = null;
		int flag=0;
		
		try {
			con=getConnection();
			String sql="update book set title=?,author=?,publisher=?,p_date=?,price=?,uploadFile=?,info=? where num=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getAuthor());
			ps.setString(3, vo.getPublisher());
			ps.setString(4, vo.getP_date());
			ps.setInt(5, vo.getPrice());
			ps.setString(6, vo.getUploadFile());
			ps.setString(7, vo.getInfo());
			ps.setInt(8, vo.getNum());
			flag=ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection(con, ps);
		}
		return flag;
	}
	
	//삭제
	public void bookDelete(int num) {
		Connection con = null;
		Statement st = null;
		
		try {
			con=getConnection();
			String sql="delete from book where num='"+num+"'";
			st=con.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, st,null);
		}
	}
	
	
	
	
	
	
	// 닫기
	private void closeConnection(Connection con, PreparedStatement ps) {

		try {
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void closeConnection(Connection con, Statement st, ResultSet rs) {

		try {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
