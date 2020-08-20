package Login;

public class Join_Chk {
	public Join_Chk() {

	}

	public boolean RepetiteID(String sid) // 아이디 중복 확인
	{
		for (int i = 0; i < Member.MemeverVec.size(); i++) {
			if (Member.MemeverVec.get(i).getSid().equals(sid)) {
				return false;
			}
		}
		return true;
	}

	public boolean PasswordLengthChk(String spw) {
		// 비밀번호 길이 체크"※ 문자, 숫자로 8자 이상"
		if (spw.length() < 8)
			return false;
		else
			return true;
	}

	public boolean PasswordMatchChk(String spw, String sRepw) {
		// 비밀번호 입력 후 그 비밀번호와 재확인 비밀번호가 일치하는지 확인
		if (spw.equals(sRepw))
			return true;
		else
			return false;
	}
}
