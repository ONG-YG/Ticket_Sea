package kr.co.ticketsea.mypage.model.vo;

import java.sql.Date;

public class MyReserveList {
	private int showNo;
	private Date showDate;
	private String showName;
	public MyReserveList(int showNo, Date showDate, String showName) {
		super();
		this.showNo = showNo;
		this.showDate = showDate;
		this.showName = showName;
	}
	public MyReserveList() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getShowNo() {
		return showNo;
	}
	public void setShowNo(int showNo) {
		this.showNo = showNo;
	}
	public Date getShowDate() {
		return showDate;
	}
	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}
	public String getShowName() {
		return showName;
	}
	public void setShowName(String showName) {
		this.showName = showName;
	}
	
	
}
