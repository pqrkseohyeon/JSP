package BookManagement;

import java.util.ArrayList;

public class Customer {
	private String scustomernum; // 고객번호
	private String scustomerName; // 이름
	private String scustomerId; // 아이디
	private String scustomerPw; // 비밀번호
	private String scustomerPhone; // 전화번호
	private String scustomeremail;// 이메일

	public Customer() {

	}

	public Customer(String scustomernum, String scustomerName, String scustomerId, String scustomerPw,
			String scustomerPhone, String scustomeremail) {
		this.scustomernum = scustomernum;
		this.scustomerName = scustomerName;
		this.scustomerId = scustomerId;
		this.scustomerPw = scustomerPw;
		this.scustomerPhone = scustomerPhone;
		this.scustomeremail = scustomeremail;
	}

	String getScustomernum() {
		return this.scustomernum;
	}

	String getScustomerName() {
		return this.scustomerName;
	}

	String getScustomerId() {
		return this.scustomerId;
	}

	String getScustomerPw() {
		return this.scustomerPw;
	}

	String getScustomerPhone() {
		return this.scustomerPhone;
	}

	String getScustomeremail() {
		return this.scustomeremail;
	}

	public ArrayList<String> RetrunList() {
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("고객번호");
		arrayList.add("이름");
		arrayList.add("아이디");
		arrayList.add("비밀번호");
		arrayList.add("전화번호");
		arrayList.add("이메일");
		return arrayList;
	}
}
