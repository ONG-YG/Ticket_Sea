package kr.co.ticketsea.mypage.model.vo;

import java.sql.Date;

public class ReserveList {
	
	private String mShowName;
	private Date bkDate;
	private String bkStatCd;
	private int num;
	private String bkNo;
	public ReserveList(String mShowName, Date bkDate, String bkStatCd, int num, String bkNo) {
		super();
		this.mShowName = mShowName;
		this.bkDate = bkDate;
		this.bkStatCd = bkStatCd;
		this.num = num;
		this.bkNo = bkNo;
	}
	public ReserveList() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getmShowName() {
		return mShowName;
	}
	public void setmShowName(String mShowName) {
		this.mShowName = mShowName;
	}
	public Date getBkDate() {
		return bkDate;
	}
	public void setBkDate(Date bkDate) {
		this.bkDate = bkDate;
	}
	public String getBkStatCd() {
		return bkStatCd;
	}
	public void setBkStatCd(String bkStatCd) {
		this.bkStatCd = bkStatCd;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getBkNo() {
		return bkNo;
	}
	public void setBkNo(String bkNo) {
		this.bkNo = bkNo;
	}
	
	
	
}