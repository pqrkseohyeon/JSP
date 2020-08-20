package Login;

public class LogIn_Chk {
	public LogIn_Chk() {

	}

	public void LogInList_Chk() {

	}

	public boolean LogInList_Chk(String sID, String sPW) {
		// TODO Auto-generated method stub
		for (int i = 0; i < Member.MemeverVec.size(); i++) {
			if (Member.MemeverVec.get(i).getSid().equals(sID) && Member.MemeverVec.get(i).getSpw().equals(sPW)) {
				return true;
			}
		}
		return false;
	}
}
