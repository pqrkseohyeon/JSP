package bookmanagement;


import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

//도서검색 -> 전체도서보기
public class AllSearch extends BookManagements {
	MyFrame frame5 = new MyFrame();
	private JTable table1;
	
	public AllSearch() {
		frame5.setTitle("전체도서");
		frame5.setSize(700, 448);
		frame5.setLocation(350, 250);
		frame5.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame5.getContentPane().setLayout(null);
		
		JTable table = null;
		String[] column = {"코드", "도서명","저자성별","작가명","출판일"};
		
		Object[][] obj = new Object[list.size()][7];
		for(int i=0; i<list.size();i++){
				obj[i][0] = list.get(i).getCode();
				obj[i][1] = list.get(i).getName();
				obj[i][2] = list.get(i).getGender();
				obj[i][3] = list.get(i).getAuthor();
				obj[i][4] = list.get(i).getDay();
		}
		
		table= new JTable(obj,column);
		table.getColumn("코드").setPreferredWidth(50);
		table.getColumn("도서명").setPreferredWidth(50);
		table.getColumn("저자성별").setPreferredWidth(50);
		table.getColumn("작가명").setPreferredWidth(50);
		table.getColumn("출판일").setPreferredWidth(50);
		
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = table.getColumnModel();
		for(int i=0; i<tcm.getColumnCount();i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		}
		
		table.setSize(660, 387);
		
		JScrollPane scrp = new JScrollPane(table);
		scrp.setSize(660, 387);
		scrp.setLocation(12, 10);
		scrp.setPreferredSize(new Dimension(369, 203));
		frame5.getContentPane().add(scrp);
		
		frame5.setVisible(true);
		
	}
}
