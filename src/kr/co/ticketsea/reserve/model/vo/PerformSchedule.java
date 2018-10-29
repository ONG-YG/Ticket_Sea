package kr.co.ticketsea.reserve.model.vo;

import java.sql.Date;

public class PerformSchedule {
	
	private int performSchNo;		//공연회차번호
	private Date performSchDate;	//공연일
	private int performSchCnt;		//공연회차
	private int showNo;				//공연번호
	private String performTime;		//공연시간
	private int availableSeat;		//해당회차 잔여석 수
	
	
	
	public PerformSchedule() {
		super();
	}
	public PerformSchedule(int performSchNo, Date performSchDate, int performSchCnt, int showNo, String performTime,
			int availableSeat) {
		super();
		this.performSchNo = performSchNo;
		this.performSchDate = performSchDate;
		this.performSchCnt = performSchCnt;
		this.showNo = showNo;
		this.performTime = performTime;
		this.availableSeat = availableSeat;
	}
	
	
	
	public int getPerformSchNo() {
		return performSchNo;
	}
	public void setPerformSchNo(int performSchNo) {
		this.performSchNo = performSchNo;
	}
	public Date getPerformSchDate() {
		return performSchDate;
	}
	public void setPerformSchDate(Date performSchDate) {
		this.performSchDate = performSchDate;
	}
	public int getPerformSchCnt() {
		return performSchCnt;
	}
	public void setPerformSchCnt(int performSchCnt) {
		this.performSchCnt = performSchCnt;
	}
	public int getShowNo() {
		return showNo;
	}
	public void setShowNo(int showNo) {
		this.showNo = showNo;
	}
	public String getPerformTime() {
		return performTime;
	}
	public void setPerformTime(String performTime) {
		this.performTime = performTime;
	}
	public int getAvailableSeat() {
		return availableSeat;
	}
	public void setAvailableSeat(int availableSeat) {
		this.availableSeat = availableSeat;
	}
	
	
	
	
	@Override
	public String toString() {
		
		String ps =  "---------perform schedule-------\n"
						+ performSchNo + "\n"
						+ performSchDate + "\n"
						+ performSchCnt + "\n"
						+ showNo + "\n"
						+ performTime + "\n"
						+ availableSeat + "\n"
						+ "---------------------------------\n";
		
		return ps;
	}
	
	
	
}
