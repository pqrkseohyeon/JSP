package bookmanagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Update extends BookManagements implements ActionListener {
	MyFrame frame3 = new MyFrame();
	 
	JButton bCode = new JButton("코드번호");
	JButton bName = new JButton("도서명");
	JButton bGender = new JButton("저자 성별");
	JButton bAuthor = new JButton("작가명");
	JButton bDay = new JButton("출판일");
	JButton bConfirm = new JButton("확인");
	JButton bCancel = new JButton("취소");
	
	private JTextField tName;
	private JTextField tGen;
	private JTextField tCode;
	private JTextField tAut;
	private JTextField tDay;
	int index;
	String code=null, name=null, selectGen=null, author=null, day=null;
	public Update(int index) {
		//setEnabled(false) : 비활성화 시켜준다. true하면 다시 활성화
		frame3.getContentPane().setEnabled(false); 
		frame3.setTitle("수정");
		frame3.setSize(489, 305);
		frame3.setLocation(550, 350);
		frame3.setDefaultCloseOperation(frame3.EXIT_ON_CLOSE);
		
		this.index = index - 1;
		JLabel lbl = new JLabel("도서 수정");
		lbl.setFont(new Font("굴림",Font.BOLD, 15));
		lbl.setForeground(Color.BLUE);
		lbl.setBounds(12, 5, 70,23);
		frame3.getContentPane().add(lbl);
		
		bCode.setBounds(295, 43, 105, 25);
		frame3.getContentPane().add(bCode);
		
		bName.setBounds(295, 73, 105, 25);
		frame3.getContentPane().add(bName);
		
		bGender.setBounds(295, 103, 115, 25);
		frame3.getContentPane().add(bGender);

		bAuthor.setBounds(295, 133, 105, 25);
		frame3.getContentPane().add(bAuthor);
		
		bDay.setBounds(295, 163, 105, 25);
		frame3.getContentPane().add(bDay);
		
		bConfirm.setBounds(61, 222, 163, 25);
		frame3.getContentPane().add(bConfirm);
		
		bCancel.setBounds(218, 222, 183, 25); 
		frame3.getContentPane().add(bCancel);
		
		JLabel jCode = new JLabel("코드번호: ");
		jCode.setBounds(12, 43, 67, 15);
		frame3.getContentPane().add(jCode);
		
		JLabel jName = new JLabel("도서명 : ");
		jName.setBounds(12, 73, 57, 15);
		frame3.getContentPane().add(jName);
		
		JLabel jGen = new JLabel("저자 성별 : ");
		jGen.setBounds(12, 103, 67, 15);
		frame3.getContentPane().add(jGen);
		
		JLabel jAut = new JLabel("저자명: ");
		jAut.setBounds(12, 133, 67,15);
		frame3.getContentPane().add(jAut);
		
		JLabel jDay = new JLabel("출판일: ");
		jDay.setBounds(12, 163, 57, 15);
		frame3.getContentPane().add(jDay);
		
		tCode = new JTextField();
		tCode.setEditable(false);
		tCode.setBounds(70, 43, 120, 21);
		frame3.getContentPane().add(tCode);
		tCode.setColumns(10);
		
		tName = new JTextField();
		tName.setEditable(false);
		tName.setBounds(70, 73, 208, 21);
		frame3.getContentPane().add(tName);
		tName.setColumns(10);
		
		tGen = new JTextField();
		tGen.setEditable(false);
		tGen.setColumns(10);
		tGen.setBounds(80, 103, 50, 21);
		frame3.getContentPane().add(tGen);
		
		tAut = new JTextField();
		tAut.setEditable(false);
		tAut.setColumns(10);
		tAut.setBounds(60, 130, 218, 21);
		frame3.getContentPane().add(tAut);
		
		tDay = new JTextField();
		tDay.setEditable(false);
		tDay.setColumns(10);
		tDay.setBounds(60, 160, 80, 21);
		frame3.getContentPane().add(tDay);
		
		tCode.setText(list.get(this.index).getCode());
		tName.setText(list.get(this.index).getName());
		tGen.setText(list.get(this.index).getGender());
		tAut.setText(list.get(this.index).getAuthor());
		tDay.setText(list.get(this.index).getDay());
		
		frame3.setVisible(true);
		bName.addActionListener(this);
		bName.setBackground(new Color(230,230,250));
		bGender.addActionListener(this);
		bGender.setBackground(new Color(230,230,250));
		bConfirm.addActionListener(this);
		bConfirm.setBackground(new Color(230,230,250));
		bCancel.addActionListener(this);
		bCancel.setBackground(new Color(230,230,250));
		bCode.addActionListener(this);
		bCode.setBackground(new Color(230,230,250));
		bAuthor.addActionListener(this);
		bAuthor.setBackground(new Color(230,230,250));
		bDay.addActionListener(this);
		bDay.setBackground(new Color(230,230,250));
			
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int check;
		if(e.getSource()==bCode) { //코드번호 버튼을 눌렀을 때 
			check = JOptionPane.showConfirmDialog(this, "수정하시겠습니까?\n"+
					"도서코드 : "+tCode.getText(), "메시지" , JOptionPane.INFORMATION_MESSAGE);
			if(check==0) {
				code=JOptionPane.showInputDialog("수정할 도서코드를 입력하세요", list.get(this.index).getCode());
				tCode.setText(code);
				if(code==null) {
					tCode.setText(list.get(this.index).getCode());			
				}
			}
						
		}else if(e.getSource() == bName){
			check = JOptionPane.showConfirmDialog(this, "수정하시겠습니까?\n" + 
					"도서명 : " + tName.getText() ,"메시지", JOptionPane.INFORMATION_MESSAGE );
			if(check ==0){
				name = JOptionPane.showInputDialog("수정할 도서이름을 입력하세요", list.get(this.index).getName());
				tName.setText(name);
				if(name == null){
					tName.setText(list.get(this.index).getName());
				}
		}
	}else if(e.getSource() == bGender){
		check = JOptionPane.showConfirmDialog(this, "수정하시겠습니까?\n" + 
				"작가 성별 : " + bGender.getText() ,"메시지", JOptionPane.INFORMATION_MESSAGE );
		if(check ==0){
			String[] str = {"남자", "여자"};
			selectGen = (String)JOptionPane.showInputDialog(this, "수정할 성별을 입력하세요\n" , "메시지",
					JOptionPane.INFORMATION_MESSAGE,	null, str, str[0]);
			tGen.setText(selectGen);
			if(selectGen == null){
				tGen.setText(list.get(this.index).getGender());
			}
		}
	}else if(e.getSource() == bAuthor){
		check = JOptionPane.showConfirmDialog(this, "수정하시겠습니까?\n" + 
				"저자명 : " + bAuthor.getText() ,"메시지", JOptionPane.INFORMATION_MESSAGE );
		if(check ==0){
			author = JOptionPane.showInputDialog("수정할 저자이름를 입력하세요", list.get(this.index).getAuthor());
			tAut.setText(author);
			if(author == null){
				bAuthor.setText(list.get(this.index).getAuthor());
			}
		}
	}else if(e.getSource() == bDay){
		check = JOptionPane.showConfirmDialog(this, "수정하시겠습니까?\n" + 
				"출판일 : " + tDay.getText() ,"메시지", JOptionPane.INFORMATION_MESSAGE );
		if(check ==0){
			day = JOptionPane.showInputDialog("수정할 출판일을 입력하세요", list.get(this.index).getDay());
			tDay.setText(day);
			if(day == null){
				tDay.setText(list.get(this.index).getDay());
			}
		}
	}
	if(e.getSource()==bConfirm) {
		if(code != null) {
			list.get(this.index).setCode(this.code);
		}
		if(name!=null) {
			list.get(this.index).setName(this.name);
		}
		if(selectGen != null) {
			list.get(this.index).setGender(this.selectGen);
		}
		if(author != null) {
			list.get(this.index).setAuthor(this.author);
		}
		if(day!=null) {
			list.get(this.index).setDay(this.day);
		}
		frame3.dispose();
		Managements m = new Managements();
	}else if(e.getSource()==bCancel) {
		frame3.dispose();
		Managements m = new Managements();
	}
		
	}

}
