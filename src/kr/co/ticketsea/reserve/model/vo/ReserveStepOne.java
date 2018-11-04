package kr.co.ticketsea.reserve.model.vo;

import java.util.ArrayList;

public class ReserveStepOne {
	
	private int showNo;							//공연번호
	private String showTitle;					//공연명
	private String showPoster;					//공연 포스터 파일명
	private ArrayList<PerformSchedule> psList;	//공연 일정 목록
	
	
	
	
	public ReserveStepOne() {
		super();
	}
	public ReserveStepOne(int showNo, String showTitle, String showPoster, ArrayList<PerformSchedule> psList) {
		super();
		this.showNo = showNo;
		this.showTitle = showTitle;
		this.showPoster = showPoster;
		this.psList = psList;
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
	
	
		
	
	
}
