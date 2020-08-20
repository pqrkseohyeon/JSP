package bookmanagement;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

												//이벤트 추가를 위해 implements ActonListener를 상속받는다.
public class BookRegister extends BookManagements implements ActionListener {
	//extends(상속) 부모클래스명 :  부모의 메소드를 그대로 사용할 수 있으며, 
	//오버라이딩 할 필요없이 부모에 구현되어 있는 것을 직접 사용 가능하다.
	//부모클래스로부터 상속받은 자원도 가지고 있고 자기자신만의 자원도 가지고 있어서, 부모클래스를 포함하는 형태로 그려진다.
	
	private JTextField name;
	private JTextField day;
	private JTextField author;
	private JTextField code;
	
	
	JButton btn1 = new JButton("등록");
	JButton btn2 = new JButton("취소");
	
	MyFrame frame = new MyFrame();
	
	JRadioButton female;
	JRadioButton male;
	ButtonGroup bg = new ButtonGroup();
	
	public BookRegister() {
		
		frame.setTitle("도서등록");
		frame.setSize(500, 330);
		frame.setLocation(500, 300);//자신의 화면에서 해당 윈도우를 나타낼 위치를 지정하게 해준다.	
		//JFrame에 정의 : System의 exit 메소드를 사용해 어플리케이션을 종료,
		//윈도우창 종료 시 프로세스까지 깔끔하게 닫게 해준다.
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		
		//도서등록에서 왼쪽에 있는 라벨들
		JLabel laCode = new JLabel("도서 코드 :");
		laCode.setBounds(27, 16, 90, 30); // 절대 위치와 크기지정, setBounds(x, y, w, h);
		frame.getContentPane().add(laCode); // frame에 부착시켜준다.
		
		JLabel laName = new JLabel("도서 이름 :");
		laName.setBounds(207, 16, 90, 30);
		frame.getContentPane().add(laName);
		
		JLabel laAuthor = new JLabel("도서 저자 :");
		laAuthor.setBounds(27, 56, 90, 30);
		frame.getContentPane().add(laAuthor);
		
		JLabel laDay = new JLabel("도서 출판일 :");
		laDay.setBounds(27, 96, 90, 30);
		frame.getContentPane().add(laDay);
		
		
		
		//입력받는 텍스트필드
		name = new JTextField();
		name.setColumns(10); // 최대 입력갯수를 설정
		name.setBounds(280, 21, 82, 21);
		frame.getContentPane().add(name);
		
		author = new JTextField();
		author.setColumns(10);
		author.setBounds(97, 61, 62, 21);
		frame.getContentPane().add(author);
		
		day = new JTextField();
		day.setColumns(10);
		day.setBounds(123, 101, 144, 21);
		frame.getContentPane().add(day);
		
		btn1.setBounds(97,232, 97, 40);
		frame.getContentPane().add(btn1);
		
		btn2.setBounds(280, 232, 97, 40);
		frame.getContentPane().add(btn2);
		
		code = new JTextField();
		code.setColumns(10);
		code.setBounds(97, 21, 62, 21);
		if(list.size()!=0) {
			code.setText((Integer.parseInt(list.get(list.size()-1).getCode())+1)+"");
		}else {
			code.setText(1001+ "");
		}
		frame.getContentPane().add(code);
		
		JLabel laGender = new JLabel("저자 성별 : ");
		laGender.setBounds(207, 56, 90, 30);
		frame.getContentPane().add(laGender);
		
		male = new JRadioButton("남");
		male.setBounds(283, 60, 45, 23);
		
		female = new JRadioButton("여");
		female.setBounds(331, 60, 45, 23);
		
		bg.add(male); // 버튼그룹에 부착
		bg.add(female);
		
		frame.getContentPane().add(male);
		frame.getContentPane().add(female);
		
		frame.setVisible(true);
		
		btn1.addActionListener(this);
		btn1.setBackground(new Color(230,230,250));
		btn2.addActionListener(this);
		btn2.setBackground(new Color(230,230,250));
	}
	//ActionEvent는 버튼을 눌렀거나, 입력창에서 Enter키를 입력하는 액션에 관한 이벤트,
	//이 이벤트를 사용하기 위해서는 ActionLisner 인터페이스를 구현해야한다.
		
	//버튼이 클릭되면 호출되는 actionPerFormed() 메서드를 오버라이딩
	@Override
	public void actionPerformed(ActionEvent e) { //이벤트 발생시 호출되는 메서드 //이벤트가 일어나면 실행해야할 코드를 넣는다.
		String memGender = null;
		boolean StringCheck = true;
		if(e.getSource()==btn1) { // e.getSource()는 이벤트가 발생한 객체를 리턴
			//getText로 텍스트를 가져오면, 데이터 형태가 String이 아니라서 String 형태로 바꿔줘야한다.
			//그것을 String 형식으로 바꿔줘야한다.
			String boCode = code.getText(); //입력한 텍스트 가져오기
			String boName = name.getText();
			String boAuthor = author.getText();
			String boDay = day.getText();
			
			if(male.isSelected()) {
				memGender = "남자";
			}else if(female.isSelected()) { // 체크박스 선택
				memGender = "여자";
			}
			
			if(boCode.equals("")){ //도서코드가 공백이면 메시지 다이얼로그 띄운다.
				//기본 알림창(null,"내용",String title, Icon)
				//메시지 다이얼로그 , 단순 메시지를 출력하는 다이얼로그
				JOptionPane.showMessageDialog(this, "도서 코드를 입력해 주세요", "메시지", JOptionPane.INFORMATION_MESSAGE);	
			}else if(boName.equals("")) {
				JOptionPane.showMessageDialog(this, "도서명을 입력해 주세요", "메시지", JOptionPane.INFORMATION_MESSAGE);			
			}else if(boAuthor.equals("")) {
				JOptionPane.showMessageDialog(this, "도서 작가명을 입력해 주세요", "메시지", JOptionPane.INFORMATION_MESSAGE);
				
			}else if(memGender.equals("")){
				JOptionPane.showMessageDialog(this, "작가의 성별를 입력해 주세요", "메시지", JOptionPane.INFORMATION_MESSAGE);
				
			}else if(boDay.equals("")) {
				JOptionPane.showMessageDialog(this, "도서의 출판일을 입력해 주세요", "메시지", JOptionPane.INFORMATION_MESSAGE);
			}else {
				for(int i=0; i<list.size();i++) {
					if(boCode.equals(list.get(i).getCode())) {
						//코드를 일일이 검사해 동일한 코드가 있으면, 아래와 같은 메세지다이얼로그 호출
						JOptionPane.showMessageDialog(this, "동일한 도서코드가 있습니다.","메세지", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				if(!integerOrNot(boCode)) {
					JOptionPane.showMessageDialog(this, "도서 코드는 문자를 입력할 수 없습니다","메세지", JOptionPane.INFORMATION_MESSAGE );
				}else if(!integerOrNot(boDay)) {
					JOptionPane.showMessageDialog(this, "출판일에는 문자를 입력할 수 없습니다." , "메세지" , JOptionPane.INFORMATION_MESSAGE);
				}else {
					// 확인 다이얼로그
					// - 사용자로부터 Yes/No 응답을 입력받는 다이얼로그
					int check = JOptionPane.showConfirmDialog(this, "입력한 내용이 맞습니까?\n"+"도서 코드 : "+boCode+
							"\n도서명 : "+boName+"\n도서 작가명 : "+boAuthor+"\n출판일 : "+boDay,"메세지", JOptionPane.INFORMATION_MESSAGE);
				if(check==0) {
					Books b = new Books();
					b.setCode(boCode);
					b.setName(boName);
					b.setGender(memGender);					
					b.setAuthor(boAuthor);
					b.setDay(boDay);
					list.add(b);
					
					JOptionPane.showMessageDialog(this, "도서가 등록되었습니다.", "메세지", JOptionPane.INFORMATION_MESSAGE);
					int check2 = JOptionPane.showConfirmDialog(this, "계속 입력하시겠습니까?");
					if(check2==0) {
						code.setText(Integer.parseInt(list.get(list.size()-1).getCode()) +1 + "");
						name.setText(null);
						bg.clearSelection();						
						author.setText(null);
						day.setText(null);
						
					}else if(check2==1) {
						frame.setVisible(false);
						super.MaStart(); // 부모 클래스인 MemberManagements의 생성자를 호출
						}
					}							
				}
			}					
		}else if(e.getSource()==btn2) { //이벤트를 발생시킨 객체의 위치값을 가져온다.
			frame.dispose();
			super.MaStart();
		}
			
	}

	private boolean integerOrNot(String strData) { // 입력값이 숫자인지 문자인지 판별
		char[] charData = strData.toCharArray(); //toCharArry()메소드는 String형 변수 문자열을 char형 배열로 바꾼다.
		boolean check=true;
		while(check) {
			for(int i=0;i<charData.length;i++) {
				if(!Character.isDigit(charData[i])) {
					check = !check;
					break;
				}
			}
			break;
		} return check;
	}
	
 }
