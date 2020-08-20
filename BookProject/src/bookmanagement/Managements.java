package bookmanagement;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;

//도서관리 탭
public class Managements  extends BookManagements implements ActionListener, ItemListener {
	//도서관리 탭을 선택하면 나타나는 화면
	MyFrame frame2 = new MyFrame();
	//도서 추가, 수정, 삭제, 취소 버튼 생성
	JButton insert = new JButton("도서 추가");
	JButton update = new JButton("도서 수정");
	JButton delete = new JButton("도서 삭제");
	JButton cancel = new JButton("취소");
	JComboBox comb = new JComboBox();
	private JLabel select = new JLabel("도서 선택");
	private final JTextArea mInfo = new JTextArea();
	
	//도서관리 화면 구성
	public Managements() {
		frame2.setTitle("도서관리");
		frame2.setSize(331, 285);
		frame2.setLocation(550, 350);	
		frame2.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		
		//setBounds(가로위치, 세로위치, 가로길이, 세로길이) : 위치와 크기를 설정
		comb.setBounds(12, 39, 183, 21);
		comb.addItem("도서를 선택하세요");
		for(int i=0;i<list.size(); i++) {
			comb.addItem(list.get(i).getCode() + "." + list.get(i).getName()+". "
					  +list.get(i).getAuthor()+ ". "+ list.get(i).getDay());
		}
		//JFrame을 상속받은 클래스에서 add를 사용하려면 getContentPane()을 사용해서 부착시켜야한다.
		frame2.getContentPane().add(comb); //콤보박스를 도서관리 프레임에 부착
		
		//도서수정 버튼
		update.setBounds(207, 40, 97, 32);
		frame2.getContentPane().add(update); // 도서 수정 버튼을 도서관리 프레임에 부착
		
		//도서삭제 버튼
		delete.setBounds(207, 82, 97, 32);
		frame2.getContentPane().add(delete);
		
		//취소 버튼
		cancel.setBounds(207,166,97,32);
		frame2.getContentPane().add(cancel);
		
		//JLabel 도서선택 프레임2에 부착
		select.setBounds(12, 14, 57, 15);
		frame2.getContentPane().add(select);
		
		mInfo.setEditable(false);
		mInfo.setBounds(12, 80, 183, 152);
		frame2.getContentPane().add(mInfo);
		
		insert.setBounds(207, 124, 97, 32);
		frame2.getContentPane().add(insert);
		
		insert.addActionListener(this);
		insert.setBackground(new Color(230,230,250));
		update.addActionListener(this);
		update.setBackground(new Color(230,230,250));
		delete.addActionListener(this);
		delete.setBackground(new Color(230,230,250));
		cancel.addActionListener(this);
		cancel.setBackground(new Color(230,230,250));
		comb.addItemListener(this);
		comb.setBackground(new Color(230,230,250));
		
		frame2.setVisible(true);
	}
	
	
	@Override
	public void itemStateChanged(ItemEvent ie) {
		if(comb.getSelectedIndex()!=0) { //콤보상자, 단일 선택 목록상자에서 선택된 목록의 위치를 얻어온다. 선택된 목록이 없으면 -1을 리턴
			int select = comb.getSelectedIndex()-1;
			mInfo.setText("도서코드 : "+list.get(select).getCode() + "\n도서명 : "+list.get(select).getName() 
					+ "\n저자 성별 : " +list.get(select).getGender() + "\n 도서 저자명 : "+list.get(select).getAuthor()
					+ "\n 도서 출판일 : " + list.get(select).getDay());	
		}else {
			mInfo.setText(null);
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==update) { //이벤트를 발생시킨 객체의 위치값을 가져온다.
			int select = comb.getSelectedIndex();
			Update ed = new Update(select);
			frame2.dispose();
		}else if(e.getSource()==delete) {
			int select = comb.getSelectedIndex()-1;
			list.remove(select);
			comb.removeItemAt(select+1);
			
		}else if(e.getSource()==insert) {
			frame2.dispose();
			BookRegister mr = new BookRegister();
		}else if(e.getSource()==cancel) {
			frame2.dispose();
			super.MaStart(); //MemberManagements에 있는걸 부른다.
		}
	}
}
