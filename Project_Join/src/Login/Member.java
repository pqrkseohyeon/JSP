package Login;

import java.util.ArrayList;
import java.util.Vector;

public class Member {
	public static Vector<Member> MemeverVec = new Vector<Member>(30);

	private String scustomernum; // 고객번호
	private String sid; // 아이디
	private String spw; // 비밀번호

	private String sName; // 이름
	private String sBirth; // 생년월일
	private String sPhone; // 폰번호

	public Member() {

	}

	public Member(String sid, String spw) {
		this.sid = sid;
		this.spw = spw;
	}

	public Member(String scustomernum, String sid, String spw, String sName, String sBirth, String sPhone) {
		this.scustomernum = scustomernum;
		this.sid = sid;
		this.spw = spw;
		this.sName = sName;
		this.sBirth = sBirth;
		this.sPhone = sPhone;
	}

	public String getCustomernum() {
		return this.scustomernum;
	}

	public String getName() {
		return this.sName;
	}

	public String getBirth() {
		return this.sBirth;
	}

	public String getPhone() {
		return this.sPhone;
	}

	public String getSid() {
		return this.sid;
	}

	public String getSpw() {
		return this.spw;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public void setSpw(String spw) {
		this.spw = spw;
	}

	public void setMemberInfo(String sid, String spw, String sName, String sBirth, String sPhone) {
		this.sid = sid;
		this.spw = spw;
		this.sName = sName;
		this.sBirth = sBirth;
		this.sPhone = sPhone;
	}

	public ArrayList<String> RetrunList() {
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("고객번호");
		arrayList.add("이름");
		arrayList.add("아이디");
		arrayList.add("비밀번호");
		arrayList.add("전화번호");
		return arrayList;
	}
}
