package Login;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import FileIO.FileIO;
import Login.LoginMain.Status;

@SuppressWarnings("serial")
public class Join extends JFrame {

	private JPanel contentPane;
	private JTextField textField_Id;
	private JPasswordField textField_PW;
	private JPasswordField textField_PwChk;
	private JTextField textField_Name;
	private JTextField textField_Birth;
	private JTextField textField_Phone;

	private JLabel Label_pwinfo;
	private JButton button;
	private boolean brepetiteChk = false; // 아이디 중복 체크

	private void Reset(JTextField textField_Id, JTextField textField_PW, JTextField textField_PwChk,
			JTextField textField_Name, JTextField textField_Birth, JTextField textField_Phone) {
		textField_Id.setText(null);
		textField_PW.setText(null);
		textField_PwChk.setText(null);
		textField_Name.setText(null);
		textField_Birth.setText(null);
		textField_Phone.setText(null);
	}

	public Join() {
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField_Id = new JTextField();
		textField_Id.setBounds(110, 23, 155, 21);
		contentPane.add(textField_Id);
		textField_Id.setColumns(10);

		textField_PW = new JPasswordField();
		textField_PW.setColumns(10);
		textField_PW.setBounds(110, 54, 155, 21);
		contentPane.add(textField_PW);

		textField_PwChk = new JPasswordField();
		textField_PwChk.setColumns(10);
		textField_PwChk.setBounds(110, 85, 155, 21);
		contentPane.add(textField_PwChk);

		textField_Name = new JTextField();
		textField_Name.setColumns(10);
		textField_Name.setBounds(110, 119, 155, 21);
		contentPane.add(textField_Name);

		textField_Birth = new JTextField();
		textField_Birth.setColumns(10);
		textField_Birth.setBounds(110, 150, 155, 21);
		contentPane.add(textField_Birth);

		textField_Phone = new JTextField();
		textField_Phone.setColumns(10);
		textField_Phone.setBounds(110, 181, 155, 21);
		contentPane.add(textField_Phone);

		setForeground(Color.WHITE);
		setBackground(Color.PINK);
		setTitle("\uD68C\uC6D0 \uAC00\uC785");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 459, 268);

		JLabel lblId = new JLabel("ID");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setBounds(30, 26, 57, 15);
		contentPane.add(lblId);

		JLabel lblPw = new JLabel("PW");
		lblPw.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPw.setBounds(30, 57, 57, 15);
		contentPane.add(lblPw);

		JLabel lblPwOk = new JLabel("PW OK");
		lblPwOk.setBackground(Color.GRAY);
		lblPwOk.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPwOk.setBounds(30, 88, 57, 15);
		contentPane.add(lblPwOk);

		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(30, 122, 57, 15);
		contentPane.add(lblName);

		JLabel lblBirth = new JLabel("Birth");
		lblBirth.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBirth.setBounds(30, 153, 57, 15);
		contentPane.add(lblBirth);

		Label_pwinfo = new JLabel("※ 문자, 숫자로 8자 이상");
		Label_pwinfo.setBounds(277, 58, 145, 15);
		contentPane.add(Label_pwinfo);

		button = new JButton("중복 확인");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Join_Chk joinchk = new Join_Chk();
				if (!textField_Id.getText().equals("")) {
					brepetiteChk = joinchk.RepetiteID(textField_Id.getText());
					if (!brepetiteChk)
						LoginMain.ShowMessage("이미 있는 아이디 입니다.\n사용이 불가능합니다.");
					else
						LoginMain.ShowMessage("사용이 가능한 아이디 입니다.");
				} else {
					LoginMain.ShowMessage("아이디를 입력해주세요.");
				}
				// 아이디를 받아들여 중복 체크하기
			}
		});
		JButton button_1 = new JButton("완료");
		button_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				boolean bPasswordLengthChk = false; // 비밀번호 길이 체크
				boolean bPasswordMatchChk = false; // 비밀 번호 일치 확인

				Join_Chk joinchk = new Join_Chk();

				bPasswordLengthChk = joinchk.PasswordLengthChk(textField_PW.getText()); // 비밀 번호 길이 체크
				bPasswordMatchChk = joinchk.PasswordMatchChk(textField_PW.getText(), textField_PwChk.getText());
				if (textField_Name.getText().equals("") || textField_Birth.getText().equals("")
						|| textField_Phone.getText().equals("")) {
					// 한칸이라도 빈값이 있으면,
					LoginMain.ShowMessage("빈값 없이 모두 입력해주세요.");
					return;
				}
				if (!bPasswordLengthChk) {
					LoginMain.ShowMessage("비밀 번호는 문자, 숫자로 8자 이상로 입력해주세요.");
					return;
				}
				if (!bPasswordMatchChk) {
					LoginMain.ShowMessage("비밀번호가 일치하지 않습니다. 비밀번호를 다시 확인해주세요.");
					return;
				}
				if (!brepetiteChk) {
					LoginMain.ShowMessage("아이디 중복 체크해주세요.");
					return;
				}
				LoginMain.ShowMessage("회원 가입 되었습니다. 로그인 해주세요");
				Member.MemeverVec.addElement(new Member(LoginMain.SetCodeNumring(), textField_Id.getText(), textField_PW.getText(),
						textField_Name.getText(), textField_Birth.getText(), textField_Phone.getText()));
				FileIO.SaveToFile("Member.txt");
				Reset(textField_Id, textField_PW, textField_PwChk, textField_Name, textField_Birth, textField_Phone);

				LoginMain.join.setVisible(false);
				LoginMain.login.setVisible(true);
			}
		});

		button.setBounds(277, 23, 145, 21);
		contentPane.add(button);
		button_1.setBounds(277, 181, 145, 21);
		contentPane.add(button_1);

		JLabel label = new JLabel("핸드폰 번호");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(19, 184, 79, 15);
		contentPane.add(label);
	}


}
