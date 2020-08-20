package bookmanagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

//도서검색 탭
public class BookSearch extends BookManagements implements ActionListener {
	MyFrame frame5 = new MyFrame();
	JButton sCode = new JButton("도서 코드");
	JButton sName = new JButton("도서 이름");
	JButton sAll = new JButton("전체 도서보기");
	JButton sAuthor = new JButton("도서 저자");
	JButton sDay = new JButton("도서 출판일");
	JButton sCancel = new JButton("취소");
	JTextArea ta = new JTextArea();
	
	public BookSearch(){
		frame5.setTitle("도서검색");
		frame5.setSize(505, 400);
		frame5.setLocation(550, 350);
		frame5.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	
		JLabel lblNewLabel = new JLabel("검색");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		lblNewLabel.setBounds(354, 3, 43, 32);
		frame5.getContentPane().add(lblNewLabel);
		
		sCode.setBounds(354, 40, 95, 32);
		frame5.getContentPane().add(sCode);
		
		sName.setBounds(354, 82, 95, 32);
		frame5.getContentPane().add(sName);
		
		sAuthor.setBounds(354, 124, 95, 32);
		frame5.getContentPane().add(sAuthor);
		
		sAll.setBounds(354, 208, 120, 32);
		frame5.getContentPane().add(sAll);
		
		sDay.setBounds(354,166, 110, 32);
		frame5.getContentPane().add(sDay);
		
		sCancel.setBounds(354, 250, 95, 32);
		frame5.getContentPane().add(sCancel);
		
		ta.setEditable(false);
		ta.setBounds(12, 9, 331, 270);
		frame5.getContentPane().add(ta);
		
		JList list1 = new JList();
		list1.setBounds(242, 48, 1, 1);
		frame5.getContentPane().add(list1);
		
		frame5.setVisible(true);
		
		sCode.addActionListener(this);
		sCode.setBackground(new Color(230,230,250));
		sName.addActionListener(this);
		sName.setBackground(new Color(230,230,250));
		sAll.addActionListener(this);
		sAll.setBackground(new Color(230,230,250));
		sCancel.addActionListener(this);
		sCancel.setBackground(new Color(230,230,250));
		sAuthor.addActionListener(this);
		sAuthor.setBackground(new Color(230,230,250));
		sDay.addActionListener(this);
		sDay.setBackground(new Color(230,230,250));
	}
	
	public void actionPerformed(ActionEvent e) {
		int dataCnt=1;
		String[] nameCollection = null;
		String nameCode =null;
		if(e.getSource() == sCode){
			String code = JOptionPane.showInputDialog("검색할 코드을 입력하세요");
			if(code == null){
				return;
			}
			if(list.size() != 0){
				for(int i=0; i<list.size(); i++){
					if(code.equals(list.get(i).getCode())){
						ta.setText("도서 코드 : "+list.get(i).getCode() + 
								"\n\n도서명 : "+list.get(i).getName() +
								"\n\n작가명 : "+list.get(i).getAuthor() +
								"\n\n저자성별 : " + list.get(i).getGender() +
								"\n\n출판일: "+ list.get(i).getDay());
								
						break;
					}
					dataCnt++;
				}
				if(dataCnt == list.size()+1){
					JOptionPane.showMessageDialog(this, "일치하는 도서가 없습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
				}	
			}else{
				JOptionPane.showMessageDialog(this, "등록된 도서가 없습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
			}
		}else if(e.getSource() == sName){
			int cnt = 0;
			String name = JOptionPane.showInputDialog("검색할 도서명을 입력하세요");
			if(name == null){
				return;
			}
			if (list.size() != 0) {
				for (int i = 0; i < list.size(); i++) {
					if (name.equals(list.get(i).getName())) {
						cnt++;
					}
				}
				if (cnt > 1) {
					nameCollection = new String[cnt];
					int namecnt = 0;
					for (int i = 0; i < list.size(); i++) {
						if (name.equals(list.get(i).getName())) {
							nameCollection[namecnt] = list.get(i).getCode()
									+ ". " + list.get(i).getName() + " "
									+ list.get(i).getAuthor() + " "
									+ list.get(i).getDay();
							namecnt++;
						}
					}
					nameCode = (String) JOptionPane.showInputDialog(this,
							"같은 이름이 존재합니다.\n", "메시지",
							JOptionPane.INFORMATION_MESSAGE, null,
							nameCollection, nameCollection[0]);
					if (nameCode == null) {
						return;
					}
					nameCode = nameCode.substring(0, 4);
					for (int i = 0; i < list.size(); i++) {
						if ((nameCode.equals(list.get(i).getCode()))) {
							ta.setText("도서 코드 : "+list.get(i).getCode() + 
									"\n\n도서명 : "+list.get(i).getName() +
									"\n\n작가명 : "+list.get(i).getAuthor() +
									"\n\n작가성별 : " + list.get(i).getGender() +
									"\n\n출판일: "+ list.get(i).getDay());
						}
					}
				} else {
					for (int i = 0; i < list.size(); i++) {
						if (name.equals(list.get(i).getName())) {
							ta.setText("도서 코드 : "+list.get(i).getCode() + 
									"\n\n도서명 : "+list.get(i).getName() +
									"\n\n작가명 : "+list.get(i).getAuthor() +
									"\n\n작가성별 : " + list.get(i).getGender() +
									"\n\n출판일: "+ list.get(i).getDay());
							break;
						}
						dataCnt++;
					}
					if(dataCnt == list.size()+1){
						JOptionPane.showMessageDialog(this, "일치하는 도서가 없습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}else{
				JOptionPane.showMessageDialog(this, "등록된 도서가 없습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
			}
		}else if(e.getSource() == sAuthor){
			String author = JOptionPane.showInputDialog("검색할 작가명을 입력하세요");
			if(author == null){
				return;
			}
			if (list.size() != 0) {
				for (int i = 0; i < list.size(); i++) {
					if (author.equals(list.get(i).getAuthor())) {
						ta.setText("도서 코드 : "+list.get(i).getCode() + 
								"\n\n도서명 : "+list.get(i).getName() +
								"\n\n작가명 : "+list.get(i).getAuthor() +
								"\n\n작가성별 : " + list.get(i).getGender() +
								"\n\n출판일: "+ list.get(i).getDay());
						break;
					}
					dataCnt++;
				}
				if(dataCnt == list.size()+1){
					JOptionPane.showMessageDialog(this, "일치하는 도서가 없습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
				}
			} else{
				JOptionPane.showMessageDialog(this, "등록된 도서가 없습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
			}
		}else if(e.getSource() == sAll){
			//전체 도서 검색
			AllSearch al = new AllSearch();
		}else if(e.getSource() == sCancel){
			frame5.dispose();
			super.MaStart();
		}else if(e.getSource() == sDay){
			String day = JOptionPane.showInputDialog("검색할 출판일을 입력하세요");
			if(day == null){
				return;
			}if(list.size() != 0){
				for(int i=0; i<list.size(); i++){
					if(day.equals(list.get(i).getDay())){
						ta.setText("도서 코드 : "+list.get(i).getCode() + 
								"\n\n도서명 : "+list.get(i).getName() +
								"\n\n작가명 : "+list.get(i).getAuthor() + 
								"\n\n작가성별 : " + list.get(i).getGender() +
								"\n\n출판일: "+ list.get(i).getDay());
								
						break;
					}
					dataCnt++;
				}
				if(dataCnt == list.size()+1){
					JOptionPane.showMessageDialog(this, "일치하는 도서가 없습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
				}	
			}else{
				JOptionPane.showMessageDialog(this, "등록된 도서가 없습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}