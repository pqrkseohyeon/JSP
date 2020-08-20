package com.exam;

public class ScoreBean3 {
	private String name;
	private int kor;
	private int eng;
	private int math;
	

	//생성자
	public ScoreBean3(String name, int kor, int eng, int math) {
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}
	//getter
	public String getName() {
		return name;
	}
	public int getKor() {
		return kor;
	}
	public int getEng() {
		return eng;
	}
	public int getMath() {
		return math;
	}
	//총점
		public int getTot() {
			return kor+eng+math;
		}
		//평균
		public int getAvg() {
			return(kor+eng+math)/3;
		}
		//학점
		public String getGrade() {
			String grade="";
			switch((kor+eng+math)/3/10){
			case 10:
			case 9:grade="A학점";break;
			case 8:grade="B학점";break;
			case 7:grade="C학점";break;
			default: grade="F학점";
			}
			return grade;
		}
	

	

}
