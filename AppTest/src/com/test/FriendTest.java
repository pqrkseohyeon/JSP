package com.test;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import com.friend.Friend;
import com.friend.FriendDAOImpl;

public class FriendTest {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "scott";
	private static final String PW = "1234";
	//DB 연동 테스트
	@Test
	public void connectionTest() throws Exception {
		Class.forName(DRIVER); //DB연결
		try(Connection con= DriverManager.getConnection(URL,USER,PW)){
			System.out.println(con);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//추가 테스트
	@Test
	public void insertTest() {
		FriendDAOImpl dao = new FriendDAOImpl();
		Friend f = new Friend();
		f.setAddr("부산 서면");
		f.setBirth("1988-09-09");
		f.setName("홍길동");
		f.setPhone("010-1111-2222");
		dao.friendInsert(f);
	}
	
	
	
	//상세보기 테스트
	@Test
	public void detailTest() {
		FriendDAOImpl dao = new FriendDAOImpl();
		assertEquals("홍길동",dao.friendDetail(17).getName());
	}
	
	//수정 테스트
	@Test
	public void updateTest() {
		FriendDAOImpl dao = new FriendDAOImpl();
		Friend f = new Friend();
		f.setName("강감찬");
		f.setNum(17);
		dao.friendUpdate(f);		
		
		
	}
	
	//삭제테스트
	@Test
	public void deleteTest() {
		FriendDAOImpl dao = new FriendDAOImpl();
		dao.friendDelete(17);
		
	}

}
