package com.ssafy.finalbe.model;

public class NewsDto {
	private int no;
	private String title, press, days, link;
	
	public NewsDto() {}

	
	
	public NewsDto(int no, String title, String press, String days, String link) {
		super();
		this.no = no;
		this.title = title;
		this.press = press;
		this.days = days;
		this.link = link;
	}



	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}



	@Override
	public String toString() {
		return "NewsInfo [no=" + no + ", title=" + title + ", press=" + press + ", days=" + days + ", link=" + link
				+ "]";
	}
	
	
	
}
