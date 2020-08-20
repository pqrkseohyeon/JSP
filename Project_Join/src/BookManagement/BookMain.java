package BookManagement;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import FileIO.FileIO;
import Handling.Handling;
import Login.LoginMain;
import Login.LoginMain.Status;
import Login.Member;

public class BookMain extends JFrame {

	private static JPanel contentPane;
	// 리스트 클리어 / 삽입 / 수정 / 삭제 인터페이스
	private static Handling handling = null;

	private static JLabel lblNewLabel_Title = new JLabel("");
	private static int iselectSearchIndex = 0;

	private JTextField textField_Search;

	private JButton btnLogIn = new JButton("로그인"); // 오른쪽 상단 버튼 구역 // 로그인 체크

	public ArrayList<String> array_combox = new ArrayList<String>();

	private JTable table = new JTable(model);
	private JPanel panel_2 = new JPanel();
	private JLabel lbListSize = null; // 총 리스트 개수 노출

	// 왼쪽 리스트 목록
	private static JLabel[] Label = new JLabel[6];
	private static JTextField[] textField = new JTextField[6];

	private static Vector<String> comlumn = new Vector<String>(); // 행
	public static DefaultTableModel model = new DefaultTableModel(comlumn, 0) {
		public boolean isCellEditable(int row, int column) {// 테이블직접수정X
			return false;
			// TODO Auto-generated method stub
		}
	};

	public BookMain() { // 생성자

	}

	public void Book_Main() {// 디자인
		// 처음 1회 실행
		FileIO.SetFileInputList("Book.txt");
		FileIO.SetFileInputList("Member.txt");
		// 초기에 생성자와 동시에 파일 만들기

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1052, 677);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// 왼쪽 리스트 틀
		CreateLeftList();
		// ------------------- 테이블 리스트 -------------------
		SetChangeable();
		// SetChangeable();
		// ------------------ 중앙 상단 -------------------
		// 현재 선택되어 있는 리스트 종류 이름 노출
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 2)));
		panel.setBounds(12, 10, 855, 36);
		contentPane.add(panel);

		setTitle(lblNewLabel_Title);
		lblNewLabel_Title.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_Title.setVerticalAlignment(SwingConstants.TOP);
		panel.add(lblNewLabel_Title);
		// -------------- 오른쪽 버튼 리스트 -------------- //
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 2))); // 테두리 검정색으로 사이즈 2만큼

		panel_3.setBounds(879, 54, 145, 575);
		contentPane.add(panel_3);

		ArrayList<JButton> btnNewButton_Right = new ArrayList<JButton>();
		btnNewButton_Right.add(new JButton("Book"));
		btnNewButton_Right.add(new JButton("Customer"));
		btnNewButton_Right.add(new JButton("Employee"));
		btnNewButton_Right.add(new JButton("Publisher"));
		btnNewButton_Right.add(new JButton("Delivery"));
		btnNewButton_Right.add(new JButton("Order"));
		btnNewButton_Right.add(new JButton("Supply"));
		btnNewButton_Right.add(new JButton("Department"));
		btnNewButton_Right.add(new JButton("Position"));
		btnNewButton_Right.add(new JButton("Book_Class"));

		ButtonHandler handler = new ButtonHandler(); // 버튼 이벤트 처리 후 작업할 수 있도록

		for (int i = 0; i < btnNewButton_Right.size(); i++) { //
			btnNewButton_Right.get(i).setPreferredSize(new Dimension(125, 51));
			btnNewButton_Right.get(i).addActionListener(handler);

			panel_3.add(btnNewButton_Right.get(i));
		}

		// -------------- 왼쪽 하단 버튼 -------------- //
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(12, 504, 140, 125);
		panel_5.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 2)));

		contentPane.add(panel_5);
		JButton btnNewButton_Clear = new JButton("Clear");
		JButton btnNewButton_Insert = new JButton("Insert");
		JButton btnNewButton_Update = new JButton("Update");
		JButton btnNewButton_Delete = new JButton("Delete");

		btnNewButton_Clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				handling.Clear(); // 빈값으로 초기화
				for (int i = 0; i < textField.length; i++)
					textField[i].setText("");
			}
		});
		btnNewButton_Insert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				handling.Insert();
			}
		});
		btnNewButton_Update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				handling.Update();
			}
		});
		btnNewButton_Delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				handling.Delete();
			}
		});

		btnNewButton_Clear.setPreferredSize(new Dimension(120, 23));
		btnNewButton_Insert.setPreferredSize(new Dimension(120, 23));
		btnNewButton_Update.setPreferredSize(new Dimension(120, 23));
		btnNewButton_Delete.setPreferredSize(new Dimension(120, 23));

		panel_5.add(btnNewButton_Clear);
		panel_5.add(btnNewButton_Insert);
		panel_5.add(btnNewButton_Update);
		panel_5.add(btnNewButton_Delete);

		JPanel panel_1 = new JPanel(); // 오른쪽 상단 버튼 구역
		panel_1.setBounds(879, 10, 145, 36);
		panel_1.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 2)));

		contentPane.add(panel_1);

		btnLogIn.setPreferredSize(new Dimension(125, 25));
		btnLogIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SetLoginLabel();
			}
		});
		panel_1.add(btnLogIn);
	}

	// 오른쪽 상단 버튼 처리 값 세팅

	public void SetLoginLabel() {// 로그인 버튼 누르면 실행해야하는 상황 Setting
		// TODO Auto-generated method stub
		if (!LoginMain.bLogInStatus) {
			btnLogIn.setText("로그인");
			LoginMain.login.setVisible(true);
		} else {
			btnLogIn.setText("로그아웃");
			if (LoginMain.NowStatus == Status.CUSTOMER) {
				LoginMain.NowStatus = Status.BOOK;
				SetChangeable();
			}
			LoginMain.bLogInStatus = false;
			Login();// 로그인 상황이면 로그아웃 후, 다시한번 화면갱신
		}
	}

	public void Login() { // 로그인 상태이면 로그아웃이라는 문구 노출
		// TODO Auto-generated method stub
		if (!LoginMain.bLogInStatus) {
			btnLogIn.setText("로그인");
		} else {
			btnLogIn.setText("로그아웃");
		}
	}

	private JComboBox<String> SetComboBoxList() { // 콤보 박스 세팅
		// TODO Auto-generated method stub
		JComboBox<String> comboBox = new JComboBox<String>(new Vector<String>(array_combox));
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub;
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
				iselectSearchIndex = cb.getSelectedIndex();
				System.out.println(iselectSearchIndex);
			}
		});
		return comboBox;
	}

	public void SetChangeable() // 초기 리스트 값 세팅
	{
		ArrayList<String> arrayList = null;

		// model.setColumnCount(0);
		model.setNumRows(0);
		// if (model.getRowCount() != 0) {// 초기화를 안해줄 경우, 데이터 값이 계속해서 쌓이기 때문에 초기화
		// for (int i = 0; i < model.getRowCount(); i++)
		// model.removeRow(i);
		// }

		setTitle(lblNewLabel_Title); // 최상단 이름 바뀌는 것

		switch (LoginMain.NowStatus) {
		case BOOK:
			arrayList = BookInfoList();// 왼쪽 리스트
			array_combox = BookInfoList(); // 콤보 박스 리스트
			lbListSize = new JLabel();

			break;
		case CUSTOMER:
			arrayList = MemeberInfoList();// 왼쪽 리스트80
			array_combox = MemeberInfoList(); // 콤보 박스 리스트

			lbListSize = new JLabel(" >> 리스트 총 개수 : " + Member.MemeverVec.size());
			break;
		case EMPLOYEE: // 직원
			break;
		default:
			break;
		}
		Handling();
		handling.Clear(); // 왼쪽 선택한 리스트의 정보 초기화

		SetLeftList(arrayList); // 왼쪽 목록
		comlumn.clear();
		comlumn.addElement(arrayList.get(0));
		comlumn.addElement(arrayList.get(1));
		comlumn.addElement(arrayList.get(2));
		comlumn.addElement(arrayList.get(3));
		comlumn.addElement(arrayList.get(4));
		comlumn.addElement(arrayList.get(5));

		model.setColumnIdentifiers(comlumn); // 선택한 목록 값 갱신 메소드
		SetComboBox(); // 콤보박스 리스트s

		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int iselectRow = 0; // 보여지는 리스트 중 선택된 리스트의 열
				iselectRow = table.getSelectedRow();
				SetLeftListInfo(iselectRow); // 좌측에 보이는 리스트에 선택된 열의 값 세팅해주기
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		table.setBounds(150, 100, 200, 200);
		table.setPreferredScrollableViewportSize(new Dimension(700, 350)); // 테이블
		table.repaint();// 셀의 너비를 조정, 글자 정렬 // 이미지 다시 그려주기

		JScrollPane listJs = new JScrollPane(table); // 스크롤
		
		switch (LoginMain.NowStatus) {
		case BOOK:
			for (int i = 0; i < Book.BookVec.size(); i++)
				addBook(Book.BookVec.elementAt(i));
			break;
		case CUSTOMER:
			for (int i = 0; i < Member.MemeverVec.size(); i++)
				addMember(Member.MemeverVec.elementAt(i));
			lbListSize = new JLabel(" >> 리스트 총 개수 : " + Member.MemeverVec.size());
			break;
		case EMPLOYEE: // 직원
			break;
		default:
			break;
		}
		panel_2.setLayout(new BorderLayout());
		panel_2.setBounds(164, 100, 703, 529);
		panel_2.add(listJs, BorderLayout.CENTER);
		panel_2.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 2)));
		panel_2.add(lbListSize, BorderLayout.AFTER_LAST_LINE);

		contentPane.add(panel_2);
	}

	private void RefreshList() { // 리스트가 삭제되거나 수정될 때 값을 고치기 위함
		switch (LoginMain.NowStatus) {
		case BOOK:
			addBook(Book.BookVec.elementAt(Book.BookVec.size()));
			lbListSize = new JLabel(" >> 리스트 총 개수 : " + Book.BookVec.size());
			break;
		case CUSTOMER:
			addMember(Member.MemeverVec.elementAt(Member.MemeverVec.size()));
			lbListSize = new JLabel(" >> 리스트 총 개수 : " + Member.MemeverVec.size());
			break;
		case EMPLOYEE: // 직원
			break;
		default:
			break;
		}
	}

	public String gettextField_Search() { // 검색어 받기
		return textField_Search.getText();
	}

	public ArrayList<String> BookInfoList() // 책 리스트의 열을 담당
	{
		ArrayList<String> arrayList = null;
		arrayList = new ArrayList<String>();
		arrayList.clear();
		arrayList.add("도서 번호");
		arrayList.add("도서명");
		arrayList.add("저자");
		arrayList.add("제작년도");
		arrayList.add("가격");
		arrayList.add("대여현황");
		return arrayList;
	}

	public static void addBook(Book book) { // Book 정보 가져오기
		Object obj[] = new Object[6];
		obj[0] = book.getSbooknum();
		obj[1] = book.getSbookName();
		obj[2] = book.getSbookWriter();
		obj[3] = book.getSbookBorn();
		obj[4] = book.getSbookCost();
		obj[5] = book.getSbookCondition();
		model.addRow(obj);
	}

	public ArrayList<String> MemeberInfoList() // 책 리스트의 열을 담당
	{
		ArrayList<String> arrayList = null;
		arrayList = new ArrayList<String>();
		arrayList.clear();
		arrayList.add("고객번호");
		arrayList.add("아이디");
		arrayList.add("비밀번호");
		arrayList.add("이름");
		arrayList.add("생년월일");
		arrayList.add("연락처");
		return arrayList;
	}

	public static void addMember(Member member) { // Member 정보 가져오기
		Object obj[] = new Object[6];
		obj[0] = member.getCustomernum();
		obj[1] = member.getSid();
		obj[2] = member.getSpw();
		obj[3] = member.getName();
		obj[4] = member.getBirth();
		obj[5] = member.getPhone();
		model.addRow(obj);
	}

	private void SetComboBox() { // 콤보 박스 및 정중앙 상단에 위치해있는 부분
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1)));
		panel_4.setBounds(164, 54, 703, 36);
		contentPane.add(panel_4);

		Book book = new Book();

		JComboBox<String> comboBox = SetComboBoxList();
		// 이 한줄이면 arrayLsit에 포함되어
		// 있는 문자열 수만큼 만들어짐

		comboBox.setEditable(false); // 글을 수정할 수 없게 만들어 주는 메소드
		comboBox.addActionListener(comboBox);
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		panel_4.add(comboBox);

		textField_Search = new JTextField();
		panel_4.add(textField_Search);
		textField_Search.setColumns(20); // 라벨 사이즈 조정

		JButton btnNewButton = new JButton("Searchs");
		panel_4.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("검색리스트 삭제");
		panel_4.add(btnNewButton_1);
	}

	private void setTitle(JLabel lblNewLabel) { // 제일 상단에 이름 세팅
		// TODO Auto-generated method stub
		String str = "";
		switch (LoginMain.NowStatus) {
		case BOOK:
			str = "책 목록";
			break;
		case CUSTOMER:
			str = "회원 정보";
			break;
		default:
			str = "준비중";
			break;
		}
		str += "입니다.";
		lblNewLabel.setText(str);
	}

	private void SetLeftList(ArrayList<String> arraylist) { // 좌측에 있는 데이터 정보
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 54, 140, 440);
		panel_1.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 2)));
		contentPane.add(panel_1);

		for (int i = 0; i < Label.length; i++) { // panel에 추가해주고,
			// setColumns : 열의 수를 설정해, TextField레이아웃을 무효
			Label[i].setText(arraylist.get(i));
			panel_1.add(Label[i]);
			panel_1.add(textField[i]);
			textField[i].setColumns(10);
		}
		textField[0].setEnabled(false); // 수정 불가능
	}

	private void CreateLeftList() { // 좌측 리스트 원하는 개수만큼 만들어주기
		for (int i = 0; i < Label.length; i++) {
			Label[i] = new JLabel();
			textField[i] = new JTextField();
		}
	}

	private void Handling() { // 좌측하단 버튼 리스트
		handling = new Handling() {

			@Override
			public void Clear() {
				// TODO Auto-generated method stub
				for (int i = 0; i < textField.length; i++) {
					textField[i].setText("");
				}
			}

			@Override
			public void Insert() {
				// TODO Auto-generated method stub
				for (int i = 0; i < textField.length; i++) {
					if (textField[i].getText().equals("")) {
						LoginMain.ShowMessage("빈값이 존재합니다. 모든 값을 채워주세요.");
						return;
					}
				}
				if (!InsertUpadateChk(textField[0].getText())) {
					LoginMain.ShowMessage("기존 데이터 값이 존재합니다. 기존값 변경을 원하시면 Update를, 새로 추가를 원하시면 Clear 후, Insert 해주세요.");
					return;
				}
				// 여기서 체크하기 업데이트인지 아니면 추가 인지
				switch (LoginMain.NowStatus) {
				case BOOK:
					Book book = new Book(LoginMain.SetCodeNumring(), textField[1].getText(), textField[2].getText(),
							textField[3].getText(), textField[4].getText(), textField[5].getText());
					Book.BookVec.addElement(book);
					FileIO.SaveToFile("Book.txt");
					break;
				case CUSTOMER:
					Member member = new Member(LoginMain.SetCodeNumring(), textField[1].getText(),
							textField[2].getText(), textField[3].getText(), textField[4].getText(),
							textField[5].getText());
					Member.MemeverVec.addElement(member);
					FileIO.SaveToFile("Member.txt");
					break;
				default:
					break;
				}
				SetChangeable();
			}

			@Override
			public void Update() {
				// TODO Auto-generated method stub
				// 코드번호가 있을 경우, 코드 번호 외에 모든 값 수정 가능
				for (int i = 0; i < Member.MemeverVec.size(); i++) {
					if (Member.MemeverVec.get(i).getCustomernum().equals(textField[0].getText())) {
						Member.MemeverVec.get(i).setMemberInfo(textField[1].getText(), textField[2].getText(),
								textField[3].getText(), textField[4].getText(), textField[5].getText());
					}
				}
			}

			@Override
			public void Delete() {
				boolean blocatedData = false; // 삭제정보가 정확한지에 대한 체크 | 모든 데이터 값이 맞게 입력 되어있는지 확인
				int delectindex = 0;
				// TODO Auto-generated method stub
				// 다녀와서 벡터 리턴하는 메소드 만들기
				switch (LoginMain.NowStatus) {
				case BOOK:
					for (int i = 0; i < Book.BookVec.size(); i++) {
						if (Book.BookVec.get(i).getSbooknum().equals(textField[0].getText())
								&& Book.BookVec.get(i).getSbookName().equals(textField[1].getText())
								&& Book.BookVec.get(i).getSbookWriter().equals(textField[2].getText())
								&& Book.BookVec.get(i).getSbookBorn().equals(textField[3].getText())
								&& Book.BookVec.get(i).getSbookCost().equals(textField[4].getText())
								&& Book.BookVec.get(i).getSbookCondition().equals(textField[5].getText())) {
							blocatedData = true;
							delectindex = i;
							break;
						}
					}
					if (!blocatedData) {
						LoginMain.ShowMessage("삭제할 정보가 정확하지 않습니다. 다시 확인 후 진행해주세요.");
					} else {
						Book.BookVec.remove(delectindex); // 해당 리스트 삭제 후 갱신까지
						SetChangeable();
						LoginMain.ShowMessage("삭제되었습니다.");
						// 여기부터 하기
						FileIO.SaveToFile("Book.txt");
					}
					break;
				case CUSTOMER:
					for (int i = 0; i < Member.MemeverVec.size(); i++) {
						if (Member.MemeverVec.get(i).getCustomernum().equals(textField[0].getText())
								&& Member.MemeverVec.get(i).getSid().equals(textField[1].getText())
								&& Member.MemeverVec.get(i).getSpw().equals(textField[2].getText())
								&& Member.MemeverVec.get(i).getName().equals(textField[3].getText())
								&& Member.MemeverVec.get(i).getBirth().equals(textField[4].getText())
								&& Member.MemeverVec.get(i).getPhone().equals(textField[5].getText())) {
							blocatedData = true;
							delectindex = i;
							break;
						}
					}
					if (!blocatedData) {
						LoginMain.ShowMessage("삭제할 정보가 정확하지 않습니다. 다시 확인 후 진행해주세요.");
					} else {
						Member.MemeverVec.remove(delectindex); // 해당 리스트 삭제 후 갱신까지
						SetChangeable();
						LoginMain.ShowMessage("삭제되었습니다.");
						FileIO.SaveToFile("Member.txt");
						// 여기부터 하기
					}
					break;
				default:
					break;
				}

			}
		};

	}

	private void SetLeftListInfo(int iselectRow) // 좌측에 보이는 리스트에 선택된 열의 값 세팅해주기
	{
		switch (LoginMain.NowStatus) {
		case BOOK:
			textField[0].setText(Book.BookVec.get(iselectRow).getSbooknum());
			textField[1].setText(Book.BookVec.get(iselectRow).getSbookName());
			textField[2].setText(Book.BookVec.get(iselectRow).getSbookWriter());
			textField[3].setText(Book.BookVec.get(iselectRow).getSbookBorn());
			textField[4].setText(Book.BookVec.get(iselectRow).getSbookCost());
			textField[5].setText(Book.BookVec.get(iselectRow).getSbookCondition());
			break;
		case CUSTOMER:
			textField[0].setText(Member.MemeverVec.get(iselectRow).getCustomernum());
			textField[1].setText(Member.MemeverVec.get(iselectRow).getSid());
			textField[2].setText(Member.MemeverVec.get(iselectRow).getSpw());
			textField[3].setText(Member.MemeverVec.get(iselectRow).getName());
			textField[4].setText(Member.MemeverVec.get(iselectRow).getBirth());
			textField[5].setText(Member.MemeverVec.get(iselectRow).getPhone());
			break;
		default:
			break;
		}
	}

	private boolean InsertUpadateChk(String str) { // 현재 저장하려고 하는 정보가 기존에 있는 정보를 변경하는지에 대한 체크
		boolean bChk = true;
		switch (LoginMain.NowStatus) {
		case BOOK:
			for (int i = 0; i < Book.BookVec.size(); i++) {
				if (str.equals(Book.BookVec.get(i).getSbooknum())) {
					bChk = false;
					break;
				}
			}
			break;
		case CUSTOMER:
			for (int i = 0; i < Member.MemeverVec.size(); i++) {
				str.equals(Member.MemeverVec.get(i).getCustomernum());
				bChk = false;
				break;
			}
			break;
		default:
			break;
		}
		return bChk;

	}
}
