package kr.co.ticketsea.reserve.model.vo;

//import java.sql.Date;

public class ReserveSession {
	
	private int memberNo;			//회원번호
	private int currStat;			//현재 진행단계 (DateCnt-1 / ReserveSeat-2 / ReserveConfirm-3)
	//private ReserveProgressing rp; 	//예매데이터
	private int progNo;				//예매진행번호
	private String progTime;		//예매시작시간(step3진입 시점)
	
	
	
	public ReserveSession() {
		this.progTime = null;
	}
	public ReserveSession(int memberNo, int currStat, int progNo, String progTime) {
		super();
		this.memberNo = memberNo;
		this.currStat = currStat;
		this.progNo = progNo;
		this.progTime = progTime;
	}
//	public ReserveSession(int memberNo, int currStat, ReserveProgressing rp, int progNo, String progTime) {
//		super();
//		this.memberNo = memberNo;
//		this.currStat = currStat;
//		this.rp = rp;
//		this.progNo = progNo;
//		this.progTime = progTime;
//	}
	
	
	

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
//	public ReserveProgressing getRp() {
//		return rp;
//	}
//	public void setRp(ReserveProgressing rp) {
//		this.rp = rp;
//	}
	public int getProgNo() {
		return progNo;
	}
	public void setProgNo(int progNo) {
		this.progNo = progNo;
	}
	public String getProgTime() {
		return progTime;
	}
	public void setProgTime(String progTime) {
		this.progTime = progTime;
	}
	
	
	
	
	
	@Override
	public String toString() {
		String resvSession = "---------reserveSession-------\n"
				+ memberNo + "\n"
				+ currStat + "\n"
				+ progNo + "\n"
				+ progTime + "\n"
							+ "-----------------------------\n";

		return resvSession;
	}
	
}
