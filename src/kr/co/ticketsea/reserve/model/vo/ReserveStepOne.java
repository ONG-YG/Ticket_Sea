package kr.co.ticketsea.reserve.model.vo;

import java.util.ArrayList;

public class ReserveStepOne {
	
	private int showNo;									//공연번호
	private String showTitle;							//공연명
	private String showPoster;							//공연 포스터 파일명
	private ArrayList<PerformSchedule> psList;			//공연 일정 목록
	private String startDate;							//공연 시작일
	private String endDate;								//공연 종료일
	
	
	public ReserveStepOne() {
		super();
	}
	public ReserveStepOne(int showNo, String showTitle, String showPoster, ArrayList<PerformSchedule> psList,
			String startDate, String endDate) {
		super();
		this.showNo = showNo;
		this.showTitle = showTitle;
		this.showPoster = showPoster;
		this.psList = psList;
		this.startDate = startDate;
		this.endDate = endDate;
	}



	public int getShowNo() {
		return showNo;
	}
	public void setShowNo(int showNo) {
		this.showNo = showNo;
	}
	public String getShowTitle() {
		return showTitle;
	}
	public void setShowTitle(String showTitle) {
		this.showTitle = showTitle;
	}
	public String getShowPoster() {
		return showPoster;
	}
	public void setShowPoster(String showPoster) {
		this.showPoster = showPoster;
	}
	public ArrayList<PerformSchedule> getPsList() {
		return psList;
	}
	public void setPsList(ArrayList<PerformSchedule> psList) {
		this.psList = psList;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
	
}
