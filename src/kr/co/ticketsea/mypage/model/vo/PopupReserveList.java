package kr.co.ticketsea.mypage.model.vo;

import java.sql.Date;

public class PopupReserveList {

	private String num;			// 나열 번호
	private String ticketNo;	// 티켓 번호
	private int psNo;			// 공연회차번호
	private Date psDate;		// 공연일
	private int psCnt;			// 공연회차
	private String psTime;		// 공연시간
	private int th1SeatNo;		// 좌석번호
	private String th1SeatGrd;	// 좌석등급
	private String seatTitle;	// 좌석title
	private int mShowNo;		// 공연번호
	private String mShowName;	// 공연명
	private int mShowRun;		// 공연관람시간
	
	public PopupReserveList(String num, String ticketNo, int psNo, Date psDate, int psCnt, String psTime, int th1SeatNo,
			String th1SeatGrd, String seatTitle, int mShowNo, String mShowName, int mShowRun) {
		super();
		this.num = num;
		this.ticketNo = ticketNo;
		this.psNo = psNo;
		this.psDate = psDate;
		this.psCnt = psCnt;
		this.psTime = psTime;
		this.th1SeatNo = th1SeatNo;
		this.th1SeatGrd = th1SeatGrd;
		this.seatTitle = seatTitle;
		this.mShowNo = mShowNo;
		this.mShowName = mShowName;
		this.mShowRun = mShowRun;
	}
	public PopupReserveList() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getTicketNo() {
		return ticketNo;
	}
	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}
	public int getPsNo() {
		return psNo;
	}
	public void setPsNo(int psNo) {
		this.psNo = psNo;
	}
	public Date getPsDate() {
		return psDate;
	}
	public void setPsDate(Date psDate) {
		this.psDate = psDate;
	}
	public int getPsCnt() {
		return psCnt;
	}
	public void setPsCnt(int psCnt) {
		this.psCnt = psCnt;
	}
	public String getPsTime() {
		return psTime;
	}
	public void setPsTime(String psTime) {
		this.psTime = psTime;
	}
	public int getTh1SeatNo() {
		return th1SeatNo;
	}
	public void setTh1SeatNo(int th1SeatNo) {
		this.th1SeatNo = th1SeatNo;
	}
	public String getTh1SeatGrd() {
		return th1SeatGrd;
	}
	public void setTh1SeatGrd(String th1SeatGrd) {
		this.th1SeatGrd = th1SeatGrd;
	}
	public String getSeatTitle() {
		return seatTitle;
	}
	public void setSeatTitle(String seatTitle) {
		this.seatTitle = seatTitle;
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
	public int getmShowRun() {
		return mShowRun;
	}
	public void setmShowRun(int mShowRun) {
		this.mShowRun = mShowRun;
	}
	
	
	
}
