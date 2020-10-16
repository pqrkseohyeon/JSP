package db.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class QueryBean {

	Connection conn; // 연결 객체
	Statement stmt; // 질의 객체
	ResultSet rs; // 결과 객체
	
	public QueryBean() {
		conn = null;
		stmt = null;
		rs = null;
		
	}
	
	public void getConnection() // 연결 하기
	{
		try {
			conn = DBConnection.getConnection();
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
		try
		{
			stmt=conn.createStatement();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnection() // 연결 끊기
	{
		if (stmt != null) 
		{
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (conn != null) 
		{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	
	public ArrayList getUserInfo(String strUser) throws Exception
	{
		StringBuffer sb = new StringBuffer();
		
		sb.append(" SELECT");
		sb.append("     U_ID, U_NAME, U_PHONE, U_GRADE, WRITE_TIME ");
		sb.append(" FROM  ");
		sb.append("     USER_INFO_SAMPLE ");
		sb.append(" WHERE  ");
		sb.append("      U_ID like '%" + strUser + "%'  ");
		sb.append(" ORDER BY  ");
		sb.append("     WRITE_TIME");
		sb.append(" DESC ");
		
		rs = stmt.executeQuery(sb.toString());
		
		ArrayList res = new ArrayList();
		while (rs.next())
		{
			res.add(rs.getString(1));
			res.add(rs.getString(2));
			res.add(rs.getString(3));
			res.add(rs.getString(4));
			res.add(rs.getString(5));
		}
		System.out.println(sb.toString());
		return res;
		
	}
	
	public int setUserInfo(String id, String name, String phone, String grade)
	{
		int result =0;
		
		StringBuffer sb = new StringBuffer();
		PreparedStatement pstmt = null;
		
		sb.append(" INSERT INTO ");
		sb.append("    USER_INFO_SAMPLE (U_ID, U_NAME, U_PHONE, U_GRADE, WRITE_TIME) ");
		sb.append(" VALUES ");
//		sb.append("    (?,?,?,?, sysdate_)");
		sb.append("    ('" + id + "', '"+name+"', '"+phone+"','"+grade+"',sysdate)");
		
		System.out.println(sb.toString());
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.clearParameters();
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, phone);
			pstmt.setString(4, grade);
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
		public int deleteUserInfo(String id) throws Exception
		{
			int result = 0;
			
			PreparedStatement pstmt = null;
			
			StringBuffer sb = new StringBuffer();
			
			sb.append(" DELETE ");
			sb.append("     FROM ");
			sb.append("        USER_INFO_SAMPLE ");
			sb.append("     WHERE ");
			sb.append("        U_ID = ? ");
			
			try
			{
				pstmt = conn.prepareStatement(sb.toString());
				pstmt.clearParameters();
				pstmt.setString(1, id);
				
				result = pstmt.executeUpdate();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				try 
				{
					if(pstmt != null)
					{
						pstmt.close();
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
			return result;
			
		}
		
		
	
}
