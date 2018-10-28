package kr.co.ticketsea.reserve.model.vo;

import java.sql.Date;

public class ReserveSession {
	
	private int memberNo;		//회원번호
	private int currStat;		//현재 진행단계
	private int progNo;			//예매진행번호
	private Date progTime;		//예매시작시간(step3진입 시점)
	
	
	
	public ReserveSession() {
		this.progTime = null;
	}
	public ReserveSession(int memberNo, int currStat, int progNo, Date progTime) {
		super();
		this.memberNo = memberNo;
		this.currStat = currStat;
		this.progNo = progNo;
		this.progTime = progTime;
	}
	
	
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getCurrStat() {
		return currStat;
	}
	public void setCurrStat(int currStat) {
		this.currStat = currStat;
	}
	public int getProgNo() {
		return progNo;
	}
	public void setProgNo(int progNo) {
		this.progNo = progNo;
	}
	public Date getProgTime() {
		return progTime;
	}
	public void setProgTime(Date progTime) {
		this.progTime = progTime;
	}
	
	
	
	
	
	@Override
	public String toString() {
		String resvSession = "---------reserveSession-------\n"
				+ memberNo + "\n"
				+ currStat + "\n"
				+ progNo + "\n"
				+ progTime + "\n"
				+ "--------------------\n";

		return resvSession;
	}
	
}
