package bookmanagement;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

//첫 화면
public class BookManagements extends MyFrame {
	static ArrayList <Books> list = new ArrayList<Books>();
	MyFrame frame = new MyFrame();
	JFileChooser f = new JFileChooser(); // JFileChooser는 윈도우의 파일열기, 저장과 같은 형태의 Dialog를 사용할 수 있다.
	FileNameExtensionFilter ff; // 자바 열기창 , 추가로 한번에 확장자 필터링 가능
	// 자바창 왼쪽에 file -> open하면 파일 찾을 수 있게 하는 버튼
	
	Image backImg;
	
	public BookManagements() {
		//JMenuBar를 생성하고, 그위에 JMenu를 부착시키고, JMenu 위에 JMenuItem을 부착시킨다.
		JMenuBar mb = new JMenuBar(); //메뉴바 생성
		frame.setJMenuBar(mb); // 여러개의 메뉴를 붙이는 바, 프레임에 부착된다.
		
		JMenu m1 = new JMenu("파일"); // 여러개의 메뉴 아이템을 가진다. //JMenu를 JMenuBar에 붙인다.(add)
		mb.add(m1); //메뉴바에 부착
		
		JMenuItem MItem1 = new JMenuItem("새로하기"); // JMenuitem을 생성하여, JMenu에 붙인다.
		m1.add(MItem1); // 메뉴에 메뉴아이템 부착
		
		JMenuItem MItem2 = new JMenuItem("불러오기");
		m1.add(MItem2); 
		
		JMenuItem MItem3 = new JMenuItem("저장하기");
		m1.add(MItem3);
		
		JMenuItem MItem4 = new JMenuItem("종료하기");
		m1.add(MItem4);
		
		MItem4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		//불러오기를 메뉴 선택했을 때 파일 유형에서 텍스트파일을 받아 오게 하는 것
		ff = new FileNameExtensionFilter("텍스트파일(.txt)", "txt"); // 필터링될 확장자
		
		MItem2.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				f.setCurrentDirectory(new File("./src/")); // 기본 Path의 경로 설정 (src) // 현재 사용 디렉토리 지정
				f.addChoosableFileFilter(ff); // 파일 필터를 추가
				int file = f.showOpenDialog(null); // ShowOpenDialog 열기용 창을 만든다. 
				if(file != f.APPROVE_OPTION) { //반환값 int는 JFileChooser.APPROVE_OPTION 값을 반환한다.
					return;
				}
				
				String filePath = f.getSelectedFile().getPath();
				if(filePath != null) {
					fileLoad(filePath);
			}
							
		}

	});
		
	// 저장하기 메뉴
		MItem3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 // GetCurrentDirectory() 함수를 이용해서 작업 디렉토리 경로를 알아오고,
				// SetCurrentdirectory() 현재 사용 디렉토리를 지정한다.
				f.setCurrentDirectory(new File("./src/")); 
				f.setFileSelectionMode(f.FILES_AND_DIRECTORIES); // 파일 선택 모드를 정한다. //파일과 디렉토리 모두 가능
				f.addChoosableFileFilter(ff); // 파일 필터 추가
				
				int file = f.showSaveDialog(null); // ShowOpenDialog 저장용 창을 만든다.
				if(file == f.APPROVE_OPTION) { // 사용자가 창을 강제로 닫았거나, 취소 버튼을 누른 경우
					String type = ff.getDescription().substring(6, 10);
					String savepathname = f.getSelectedFile().getAbsolutePath()+type; // getAbsolutePath 입력된 절대경로 그대로를 표현한다.
					fileSave(savepathname);	
				}else {
					return;
				}
			}	
		});
		
		// 종료하기 메뉴
		MItem4.addActionListener(new ActionListener() {
				@Override
			public void actionPerformed(ActionEvent e) {
				list.clear();
			}
		});
		
		//도서 관리 프로그램 이미지 
		JLabel label = new JLabel(new ImageIcon("./src/bookmanagement/book2.jpg")); // JLabel 컴포넌트에 텍스트와 이미지를 모두 넣을 수 있다.
																// getText와 setText(String str)을 이용해서 텍스트 설정
		label.setBounds(20, 15, 431, 380);
		frame.getContentPane().add(label); //contentPane 일반적인 컴포넌트를 가질 수 있는 패널	
	}
	
	//Main에 있는 MaStart()
	public void MaStart() {
		frame.setTitle("도서관리 프로그램");
	
		frame.setSize(500, 550);
		frame.setLocation(500, 300);
		//x 버튼을 누르면 종료해준다.
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true); //사용자 눈에 보이게 해준다.
		
		//도서등록 버튼
		JButton bt1 = new JButton("도서등록");
		bt1.setBackground(new Color(173,216,230));
		bt1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				// 다른 class인 MemberResister를 생성한다.
				BookRegister mr = new BookRegister();
			}
		});
		bt1.setBounds(22, 410, 133, 49);
		frame.getContentPane().add(bt1);
		
		//도서관리 버튼
		JButton bt2 = new JButton("도서관리");
		bt2.setBackground(new Color(173,216,230));
		bt2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();//창이 닫긴다.
				//다른 class인 Managements를 생성한다.
				Managements mg = new Managements();
				
			}
		});
		bt2.setBounds(174, 410, 133, 49);
		frame.getContentPane().add(bt2);// 프레임으로부터 ContentPane을 얻는 것,  add 메소드를 호출하면, 컴포넌트를 올릴 수 있다.
		
		// 도서검색 버튼
		JButton bt3 = new JButton("도서검색");
		bt3.setBackground(new Color(173,216,230));
		bt3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				//다른 class인 MemberSearch를 생성한다.
				BookSearch ms = new BookSearch();				
			}
		});
		bt3.setBounds(324, 410, 133, 49);
		frame.getContentPane().add(bt3);
	
	}
	
	// 파일 불러오기
	public void fileLoad(String path) {
		// 파일로부터 바이트 단위의 입력을 받는 클래스
		FileInputStream fis = null; 
		//InputStream을 통해 데이터를 읽어 들여 문자 단위로 읽어 들이는 방식으로 변형한다는 의미
		InputStreamReader isr = null; 
		//scanner보다 사용하기 불편하지만, 많은 양의 데이터를 입력받을 경우 사용한다.
		BufferedReader bfr = null;
		//문자열을 지정된 구분자로 나눈다. 구분자는 토큰으로 간주되지 않는다.
		StringTokenizer st = null;
		
		try {
			list.clear();
			fis = new FileInputStream(path);
			isr = new InputStreamReader(fis);
			bfr = new BufferedReader(isr);
			String str = null;
			//read()는 파일을 처음부터 끝까지 읽을 때 사용,
			//readline() 파일을 한 줄 씩 읽을 때 사용
			while((str = bfr.readLine())!= null) {
				Books b = new Books();
				st = new StringTokenizer(str,",");// ,로 구분
				b.setCode(st.nextToken());
				b.setName(st.nextToken());
				b.setAuthor(st.nextToken());
				b.setGender(st.nextToken());
				b.setDay(st.nextToken());
				list.add(b); // add로  list에 부착
				
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fis.close();
			}catch(IOException e ) {
				e.printStackTrace();
			}
		}		
	}
	
	//파일저장
	public void fileSave(String path) {
		// 문자 기반 스트림으로 텍스트 데이터를 파일에 저장할 때 사용, 문자 단위로 저장하므로 텍스트만 저장 가능
		FileWriter fw = null; 
		try {
			fw = new FileWriter(path);
			for(int i=0; i<list.size();i++) {
				fw.write(list.get(i).getCode());
				fw.write(",");
				fw.write(list.get(i).getName());
				fw.write(",");
				fw.write(list.get(i).getAuthor());
				fw.write(",");
				fw.write(list.get(i).getGender());
				fw.write(",");
				fw.write(list.get(i).getDay());
				fw.write(",");
				fw.write("\r\n"); // 줄바꿈
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				fw.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
	}


}
