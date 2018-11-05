package kr.co.ticketsea.mypage.model.vo;

import java.sql.Date;

public class ReserveList {
	private int psNo;
	private String bkNo;
	private int memberNo;
	private int mShowNo;
	private String mShowName;
	private Date bkDate;
	private String bkStatCd;
	public ReserveList(int psNo, String bkNo, int memberNo, int mShowNo, String mShowName, Date bkDate,
			String bkStatCd) {
		super();
		this.psNo = psNo;
		this.bkNo = bkNo;
		this.memberNo = memberNo;
		this.mShowNo = mShowNo;
		this.mShowName = mShowName;
		this.bkDate = bkDate;
		this.bkStatCd = bkStatCd;
	}
	public ReserveList() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getPsNo() {
		return psNo;
	}
	public void setPsNo(int psNo) {
		this.psNo = psNo;
	}
	public String getBkNo() {
		return bkNo;
	}
	public void setBkNo(String bkNo) {
		this.bkNo = bkNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getmShowNo() {
		return mShowNo;
	}
	public void setmShowNo(int mShowNo) {
		this.mShowNo = mShowNo;
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
	
	
}
