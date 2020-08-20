package bookmanagement;
//private로 선언되어 있는 클래스의 멤버변수에 접근하기 위하여 사용
//setter
//1. 접근이 가능한 public을 선언한 후,
//2. String 타입의 어떠한 값을 리턴한다.
//getter
//해당 클래스 안에 있는 멤버변수의 값을 리턴하는 것이 전부이다.
public class Books {
	String Code;
	String Gender;
	String name;
	String Author;
	String Day;
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return Author;
	}
	public void setAuthor(String author) {
		Author = author;
	}
	public String getDay() {
		return Day;
	}
	public void setDay(String day) {
		Day = day;
	}

	



}
