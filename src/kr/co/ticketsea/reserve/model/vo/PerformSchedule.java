package kr.co.ticketsea.reserve.model.vo;

import java.sql.Date;

public class PerformSchedule {
	
	private int performSchNo;
	private Date performSchDate;
	private int performSchCnt;
	private int showNo;
	
	
	
	public PerformSchedule() {
		super();
	}
	public PerformSchedule(int performSchNo, Date performSchDate, int performSchCnt, int showNo) {
		super();
		this.performSchNo = performSchNo;
		this.performSchDate = performSchDate;
		this.performSchCnt = performSchCnt;
		this.showNo = showNo;
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
	
	
	
	
	
	
	
	
}
