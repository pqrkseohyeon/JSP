package com.book.model;

public class BookDTO {
	private int num;
	private String title;
	private String author;
	private String publisher;
	private String p_date;
	private int price;
	private String uploadFile;
	private String info;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title == null ? "" : title.trim();
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author == null ? "" : author.trim();
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher == null ? "" : publisher.trim();
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getP_date() {
		return p_date == null ? "" : p_date.trim();
	}
	public void setP_date(String p_date) {
		this.p_date = p_date;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getUploadFile() {
		return uploadFile == null ? "" : uploadFile.trim();
	}
	public void setUploadFile(String uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getInfo() {
		return info == null ? "" : info.trim();
	}
	public void setInfo(String info) {
		this.info = info;
	}
	

	
	
}
