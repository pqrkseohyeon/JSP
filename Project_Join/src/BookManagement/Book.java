package BookManagement;

import java.util.Vector;

public class Book {

	public static Vector<Book> BookVec = new Vector<Book>(30);
	private String sbooknum; // 도서 번호
	private String sbookName; // 도서명
	private String sbookWriter; // 저자
	private String sbookBorn; // 제작년도
	private String sbookCost; // 가격
	private String sbookCondition;// 대여 현황

	public Book(String sbooknum, String sbookName, String sbookWriter, String sbookBorn, String sbookCost,
			String sbookCondition) {
		this.sbooknum = sbooknum;
		this.sbookName = sbookName;
		this.sbookWriter = sbookWriter;
		this.sbookBorn = sbookBorn;
		this.sbookCost = sbookCost;
		this.sbookCondition = sbookCondition;
	}

	public Book() {
		// TODO Auto-generated constructor stub
	}

	public String getSbooknum() {
		return this.sbooknum;
	}

	public String getSbookName() {
		return this.sbookName;
	}

	public String getSbookWriter() {
		return this.sbookWriter;
	}

	public String getSbookBorn() {
		return this.sbookBorn;
	}

	public String getSbookCost() {
		return this.sbookCost;
	}

	public String getSbookCondition() {
		return this.sbookCondition;
	}

}
