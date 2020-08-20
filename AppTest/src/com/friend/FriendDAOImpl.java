package com.friend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FriendDAOImpl implements FriendDAO{
	String url, user, pwd; //멤버변수 만들어주기
	
	//생성자 -> 디비 연결
	public FriendDAOImpl() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			url="jdbc:oracle:thin:@localhost:1521:xe";
			user="scott";
			pwd="1234";
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//추가
	public void friendInsert(Friend f) {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con=DriverManager.getConnection(url,user,pwd);
			String sql="INSERT INTO friend VALUES(friend_seq.nextval,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, f.getName());
			ps.setString(2, f.getBirth());
			ps.setString(3, f.getPhone());
			ps.setString(4, f.getAddr());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con,ps);
		}
	}
	
	//전체보기
	public ArrayList<Friend> friendView() {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		ArrayList<Friend>arr=new ArrayList<Friend>();
		
		try {
			con=DriverManager.getConnection(url,user,pwd);
			String sql="SELECT * FROM friend ORDER BY num desc";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				Friend f=new Friend();
				f.setNum(rs.getInt("num"));
				f.setName(rs.getString("name"));
				f.setBirth(rs.getString("birth"));
				f.setPhone(rs.getString("phone"));
				f.setAddr(rs.getString("addr"));
				arr.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arr;
	}

	//상세보기
		public Friend friendDetail(int num) {
			Connection con=null;
			Statement st=null;
			ResultSet rs=null;
			Friend f=null;
			try {
				con=DriverManager.getConnection(url, user, pwd);
				String sql="SELECT *FROM friend WHERE num="+num;
				st=con.createStatement();
				rs=st.executeQuery(sql);
				if(rs.next()) {
					f=new Friend();
					f.setNum(rs.getInt("num"));
					f.setName(rs.getString("name"));
					f.setBirth(rs.getString("birth"));
					f.setPhone(rs.getString("phone"));
					f.setAddr(rs.getString("addr"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeConnection(con, st, rs);
			}
			return f;
		}
		
	//수정
	public void friendUpdate(Friend f) {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			String sql="UPDATE friend SET name=?, birth=?,"
					+ "phone=?,addr=? WHERE num=?";
			con=DriverManager.getConnection(url,user,pwd);
			ps=con.prepareStatement(sql);
			ps.setString(1, f.getName());
			ps.setString(2, f.getBirth());
			ps.setString(3, f.getPhone());
			ps.setString(4, f.getAddr());
			ps.setInt(5, f.getNum());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}
	}

	//삭제
	public void friendDelete(int num) {
		Connection con=null;
		Statement st=null;
		try {
			con=DriverManager.getConnection(url, user, pwd);
			String sql="DELETE FROM friend WHERE num="+num;
			st=con.createStatement();
			st.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, null);
		}
	}

	//검색
	public ArrayList<Friend> friendSearch(String key, String word) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		ArrayList<Friend>arr=new ArrayList<Friend>();
		try {
			con=DriverManager.getConnection(url,user,pwd);
			st=con.createStatement();
			String sql="SELECT * FROM friend WHERE "+key+" LIKE '%"+word+"%'";
			rs=st.executeQuery(sql);
			while(rs.next()) {
				Friend f=new Friend();
				f.setNum(rs.getInt("num"));
				f.setName(rs.getString("name"));
				f.setBirth(rs.getString("birth"));
				f.setPhone(rs.getString("phone"));
				f.setAddr(rs.getString("addr"));
				arr.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}
		return arr;
	}
	
	//닫기(종료)메소드
	public void closeConnection(Connection con, Statement st, ResultSet rs) {
			try {
				if(rs!=null) rs.close();
				if(con!=null) con.close();
				if(st!=null) st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public void closeConnection(Connection con, PreparedStatement ps) {
			try {
				if(con!=null) con.close();
				if(ps!=null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	@Override
	public Friend FriendDetail(int num) {
		// TODO Auto-generated method stub
		return null;
	}
}

