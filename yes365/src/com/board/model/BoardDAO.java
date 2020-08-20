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

import com.book.model.BookImple;


public class BoardDAO {
		// DB연결
		private static BoardDAO instance = new BoardDAO();
		public static BoardDAO getInstance() {
			return instance;
		}
		private Connection getConnection() throws Exception {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup("jdbc/member");
			return ds.getConnection();
		}
		
		//리뷰등록
		public int boardInsert(BoardDTO vo) {
			Connection con=null;
			PreparedStatement ps=null;
			int flag=0;
			
			try {
				con=getConnection();
				String sql="INSERT INTO book_board(bnum, userid, subject, content, reg_date, "
						+ "readcount, num)"
						+ "VALUES(book_board_seq.nextval,?,?,?,sysdate,0,?)";
				ps=con.prepareStatement(sql);
				ps.setString(1, vo.getUserid());
				ps.setString(2, vo.getSubject());
				ps.setString(3, vo.getContent());
				ps.setInt(4, vo.getBnum());
				flag=ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeConnection(con, ps);
			}
			return flag;
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
				if(st!=null) st.close();
				if(con!=null) con.close();
				if(rs!=null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}

}
