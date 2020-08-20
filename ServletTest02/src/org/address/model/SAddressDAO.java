package org.address.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class SAddressDAO {
	private static SAddressDAO instance = new SAddressDAO();
	
	public static SAddressDAO getInstance() {
		return instance;
	}
	
	//디비연결
	private Connection getConnection() throws Exception{
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/member");
		
		return ds.getConnection();
	}
	//우편번호 검색
	public ArrayList<ZipcodeDTO> zipSearch(String dong){
		Connection con =null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<ZipcodeDTO>arr = new ArrayList<>();	
		try {
			con=getConnection();
			String sql="select*from zipcode where dong like '%"+dong+"%'";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				ZipcodeDTO zip = new ZipcodeDTO();
				zip.setBunji(rs.getString("bunji"));
				zip.setDong(rs.getString("dong"));
				zip.setGugun(rs.getString("gugun"));
				zip.setSido(rs.getString("sido"));
				zip.setZipcode(rs.getString("zipcode"));
				arr.add(zip);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, st, rs);
		}
		return arr;	
		
	}
	
	
	//수정
	public void addressUpdate(SAddressDTO dto) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = getConnection();
			String sql = "update address set name=?,zipcode=?,addr=?,tel=? where num=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getZipcode());
			ps.setString(3, dto.getAddr());
			ps.setString(4, dto.getTel());
			ps.setLong(5, dto.getNum());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, ps);
		}
	}
	
	//주소록 갯수 구하기
	public int addressCount() {
		Connection con =null;
		Statement st = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			con=getConnection();
			String sql = "select count(*) from address";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			if(rs.next()) {
				count=rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection(con, st, rs);
		}
		return count;
	}
	//검색 개수
		public int searchCount(String field, String word) {
			Connection con =null;
			Statement st = null;
			ResultSet rs = null;
			int count = 0;
			
			try {
				con=getConnection();
				String sql = "select count(*) from address where "+field+" like '%"+word+"%'";
				st=con.createStatement();
				rs=st.executeQuery(sql);
				if(rs.next()) {
					count=rs.getInt(1);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeConnection(con, st, rs);
			}
			return count;
		}
	
	//추가
	public void insertAddress(SAddressDTO ad) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = getConnection();
			String sql = "insert into address values(address_seq.nextval,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, ad.getName());
			ps.setString(2, ad.getZipcode());
			ps.setString(3, ad.getAddr());
			ps.setString(4, ad.getTel());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, ps);
		}
	}
	
	//전체보기 검색아님
	public ArrayList<SAddressDTO>addressList(){
		Connection con =null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<SAddressDTO>arr = new ArrayList<>();	
		try {
			con=getConnection();
			String sql="select*from address";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				SAddressDTO ad = new SAddressDTO();
				ad.setAddr(rs.getString("addr"));
				ad.setName(rs.getString("name"));
				ad.setNum(rs.getLong("num"));
				ad.setTel(rs.getString("tel"));
				ad.setZipcode(rs.getString("Zipcode"));
				arr.add(ad);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, st, rs);
		}
		return arr;	
	}
	
	//전체보기 검색
	public ArrayList<SAddressDTO> addressSearch(String field, String word){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<SAddressDTO>arr = new ArrayList<>();
		
		try {
			con=getConnection();
			String sql="select*from address where "+field+" like'%"+word+"%'";
			
			st=con.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				SAddressDTO dto = new SAddressDTO();
				dto.setAddr(rs.getString("addr"));
				dto.setName(rs.getString("name"));
				dto.setNum(rs.getLong("num"));
				dto.setTel(rs.getString("tel"));
				dto.setZipcode(rs.getString("Zipcode"));
				arr.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, st,rs);
		}
		return arr;
	}
	
	//상세보기
	public SAddressDTO addressDetail(int num) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		SAddressDTO dto = null;
		
		try {
			con=getConnection();
			st = con.createStatement();
			String sql = "select * from address where num="+num;	
			rs = st.executeQuery(sql);
			if(rs.next()) {
				dto = new SAddressDTO();
				dto.setNum(rs.getInt("num"));
				dto.setAddr(rs.getString("addr"));
				dto.setName(rs.getString("name"));
				dto.setNum(rs.getLong("num"));
				dto.setTel(rs.getString("tel"));
				dto.setZipcode(rs.getString("zipcode"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, st,rs);
		}
		return dto;
		}
	
	//삭제
	public void addressDelete(int num) {
		Connection con = null;
		Statement st = null;
		
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "delete from address where num ="+num;	
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, st,null);
		}
		
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
