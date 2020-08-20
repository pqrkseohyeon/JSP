package Login;

import java.util.UUID;
import java.util.Vector;

import javax.swing.JOptionPane;

import BookManagement.Book;
import BookManagement.BookMain;

public class LoginMain {

	public enum Status { // Tab 상황 구분하기 위해 Enum 선언
		BOOK, CUSTOMER, EMPLOYEE, PUBLISHER, DELIVERY, ORDER, SUPPLY, DEPARTMENT, POSITION, BOOKCLASS
	};

	public static Status NowStatus = Status.BOOK; // 현 tab 상황 저장 //기본값 book

	public static FindId findId = new FindId(); // 아이디 찾기
	public static FindPw findpw = new FindPw(); // 비밀 번호 찾기
	public static Join join = new Join(); // 회원 가입
	public static Login login = new Login(); // 로그인 화면 (가장 기본 main)

	public static BookMain bookmain = new BookMain(); // 책 관련 매니저
	public static boolean bLogInStatus = false; // 현 상황이로그인 된 상태인지 체크해주는 변수

	public static void main(String[] args) {
		bookmain.Book_Main();
		bookmain.setVisible(true);
	}

	public static void ShowMessage(String str) {
		JOptionPane.showMessageDialog(null, str);
	}

	public static String SetCodeNumring() { // 코드 넘버 랜덤으로 받아주는 메소드 || 책을 경우, 앞에 B를 / 사람일 경우, 앞에 M
		String str = "";
		if (LoginMain.NowStatus == Status.BOOK)
			str = "B";
		else if (LoginMain.NowStatus == Status.CUSTOMER)
			str = "M";
		str += UUID.randomUUID().toString().replaceFirst("-", "").substring(0, 6);
		return str;
		// 총 길이가 32자리가 나오기 때문에 길이 지정
		// 내가 디폴트로 잡은 값에서 추가로 6자리가 랜덤으로 받아져 작성됨
	}

	public static Vector<?> ReturnVec() {
		switch (LoginMain.NowStatus) {
		case BOOK:
			return Book.BookVec;
		case CUSTOMER:
			return Member.MemeverVec;
		default:
			return Book.BookVec;
		}
	}
}
