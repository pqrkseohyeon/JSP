package BookManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import Login.LoginMain;
import Login.LoginMain.Status;

public class ButtonHandler implements ActionListener {
	// 버튼 눌렀을 때 액션
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String bt = e.getActionCommand(); // 강제 형변환
		BookMain bookmain = new BookMain();
		bookmain.model.setNumRows(0);
		// 기존에 생성되어 있는 행을 0으로 초기화해 리스트 삭제
		if (bt.equals("Book")) {
			LoginMain.NowStatus = Status.BOOK;
		} else if (bt.equals("Customer")) {
			if (!LoginMain.bLogInStatus) {
				LoginMain.NowStatus = Status.BOOK;
				LoginMain.ShowMessage("로그인 후 이용이 가능합니다.");
			} else {
				LoginMain.NowStatus = Status.CUSTOMER;
			}
		} else {
			LoginMain.ShowMessage("준비중입니다.");
			LoginMain.NowStatus = Status.EMPLOYEE;
			return;
		}
		bookmain.SetChangeable();
		// else if(bt.equals("Employee"))
		// else if(bt.equals("Publisher"))
		// else if(bt.equals("Delivery"))
		// else if(bt.equals("Supply"))
		// else if(bt.equals("Department"))
		// else if(bt.equals("Position"))
		// else if(bt.equals("Book_Class"))
	}

	public String run() {
		String str = "";
		try {
			while (true) {
				int hour1, hour2 = 0;
				int min1, min2 = 0;
				int sec1, sec2 = 0;

				Calendar rightNow = Calendar.getInstance(); // 현재시간을 입력받는다 이 때 Calendar 를 사용할 수 있다

				// 시간을 받기 위해 이런 형식을 취해줘야 제대로 받는다.

				int second_d = rightNow.get(Calendar.SECOND);
				int minute_d = rightNow.get(Calendar.MINUTE);
				int hour_d = rightNow.get(Calendar.HOUR);

				if (hour_d > 9) {
					hour1 = hour_d / 10;
					hour2 = hour_d % 10;
				} else {
					hour1 = 0;
					hour2 = hour_d;
				}
				if (minute_d > 9) {
					min1 = minute_d / 10;
					min2 = minute_d % 10;
				} else {
					min1 = 0;
					min2 = minute_d;
				}
				if (second_d > 9) {
					sec1 = second_d / 10;
					sec2 = second_d % 10;
				} else {
					sec1 = 0;
					sec2 = second_d;
				}
				str = hour1 + "" + hour2 + "" + min2 + "" + min1 + "" + sec1 + "" + sec2 + "";
				System.out.println(str);
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			System.out.println("error");
		}
		return str;
	}

}
