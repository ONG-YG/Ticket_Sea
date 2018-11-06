package kr.co.ticketsea.mypage.model.vo;

import java.sql.Date;

public class MyReserveList {
	private int showNo;
	private Date showDate;
	private String showName;
	private int count; // 매수
	private int tableNum; // view에 나열되는 번호
	public MyReserveList(int showNo, Date showDate, String showName, int count, int tableNum) {
		super();
		this.showNo = showNo;
		this.showDate = showDate;
		this.showName = showName;
		this.count = count;
		this.tableNum = tableNum;
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getTableNum() {
		return tableNum;
	}
	public void setTableNum(int tableNum) {
		this.tableNum = tableNum;
	}
	
	
	
}