package com.member.model;

public class MemberDTO {
	private String userid;
	private String name;
	private String pwd;
	private String email;
	private String phone;
	private String addr;
	private String detailAddr;
	private String extraAddr;
	private int admin;
	
	public String getDetailAddr() {
		return detailAddr == null ? "" : detailAddr.trim();
	}
	public void setDetailAddr(String detailAddr) {
		this.detailAddr = detailAddr;
	}
	public String getExtraAddr() {
		return extraAddr == null ? "" : extraAddr.trim();
	}
	public void setExtraAddr(String extraAddr) {
		this.extraAddr = extraAddr;
	}
	public String getUserid() {
		return userid == null ? "" : userid.trim();
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name == null ? "" : name.trim();
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd == null ? "" : pwd.trim();
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email == null ? "" : email.trim();
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone == null ? "" : phone.trim();
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddr() {
		return addr == null ? "" : addr.trim();
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	

}
