package db.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class QueryBean {

	Connection conn;
	Statement stmt;
	ResultSet rs;

	public QueryBean() {

		conn = null;
		stmt = null;
		rs = null;

	}

	public void getConnection() {

		try {

			conn = DBConnection.getConnection();

		} catch (Exception e1) {

			e1.printStackTrace();
		}

		try {

			stmt = conn.createStatement();

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	public void closeConnection() {

		if (stmt != null) {

			try {

				stmt.close();

			} catch (Exception e) {

				e.printStackTrace();

			}

		}

		if (conn != null) {

			try {
				conn.close();

			} catch (Exception e) {

				e.printStackTrace();

			}

		}
	}

	public ArrayList getUserInfo(String id) throws Exception {

		StringBuffer sb = new StringBuffer();

		sb.append("SELECT");
		sb.append("   U_ID, U_NAME, U_PHONE, U_GRADE, WRITE_TIME ");
		sb.append(" FROM ");
		sb.append("   USER_INFO_SAMPLE");
		
		sb.append("   where ");
		sb.append("   					u_id like '%"+id+"%' ");
		
		sb.append(" ORDER BY");
		sb.append("     WRITE_TIME");

		System.out.println(sb.toString());
		
		rs = stmt.executeQuery(sb.toString());

		ArrayList res = new ArrayList();
		while (rs.next()) {

			res.add(rs.getString(1));
			res.add(rs.getString(2));
			res.add(rs.getString(3));
			res.add(rs.getString(4));
			res.add(rs.getString(5));
			
		}
		
		return res;
	}
	
	public int insertUserInfo(String id, String name, String phone, String grade) throws Exception {

		StringBuffer sb = new StringBuffer();
		PreparedStatement pstmt = null;
		
		int result = 0;

		sb.append(" INSERT INTO "); 
		sb.append("							USER_INFO_SAMPLE "); 
		sb.append("						(U_ID, U_NAME, U_PHONE, U_GRADE, WRITE_TIME) "); 
		sb.append(" VALUES ");
		sb.append("					('"+id+"','"+name+"','"+phone+"', '"+grade+"',sysdate)  ");


		System.out.println(sb.toString());
		
		pstmt = conn.prepareStatement(sb.toString());
		
//		rs = stmt.executeQuery(sb.toString());
		
		result = pstmt.executeUpdate();
		
		return result;
	}
	
	public int updateUserInfo(String id, String name, String phone, String grade) throws Exception {

		StringBuffer sb = new StringBuffer();
		PreparedStatement pstmt = null;
		
		int result = 0;

		sb.append(" UPDATE "); 
		sb.append("							USER_INFO_SAMPLE "); 
		sb.append(" SET ");
		sb.append("						U_NAME = ?,  U_PHONE=?,  U_GRADE=?,  WRITE_TIME=sysdate "); 
		sb.append(" WHERE ");
		sb.append("					U_ID = ? ");


		System.out.println(sb.toString());
		
		pstmt = conn.prepareStatement(sb.toString());
		pstmt.clearParameters();
		pstmt.setString(1,  name);
		pstmt.setString(2,  phone);
		pstmt.setString(3,  grade);
		pstmt.setString(4,  id);
		
//		rs = stmt.executeQuery(sb.toString());
		
		result = pstmt.executeUpdate();
		
		return result;
	}
	
	public int deleteUserInfo(String id) throws Exception {

		StringBuffer sb = new StringBuffer();
		PreparedStatement pstmt = null;
		
		int result = 0;

		sb.append(" DELETE "); 
		sb.append("					FROM");
		sb.append("									USER_INFO_SAMPLE ");
		sb.append(" WHERE ");
		sb.append("					U_ID = ? ");

		System.out.println(sb.toString());
		
		pstmt = conn.prepareStatement(sb.toString());
		pstmt.clearParameters();
		pstmt.setString(1,  id);
		
//		rs = stmt.executeQuery(sb.toString());
		
		result = pstmt.executeUpdate();
		
		return result;
	}

}
