package com.member;

import java.util.ArrayList;

public interface MemberDAO {
	//추가
	public void memberInsert(MemberVO vo);
	//전체보기
	public ArrayList<MemberVO> memberList();
	//수정//수정할내용 매개변수
	public int memberUpdate(MemberVO vo);
	//상세보기//리턴값 
	public MemberVO memberView(String userid);
	//삭제
	public void memberDel(String userid);
	//아이디체크(중복체크)
	public String idCheck(String userid);
	

}
