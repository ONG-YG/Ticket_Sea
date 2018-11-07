package kr.co.ticketsea.reserve.model.vo;


public class ReserveSession {
	
	private int memberNo;			//회원번호
	private int currStat;			//현재 진행단계 (DateCnt-1 / ReserveSeat-2 / ReserveConfirm-3)
	private int progNo;				//예매진행번호
	private String progTime;		//예매시작시간(step3진입 시점)
	private long bkNo;				//예매번호
	private String memberId;		//회원아이디
	private String memberPwd;		//회원비밀번호
	
	
	
	public ReserveSession() {
		this.progTime = null;
	}
	public ReserveSession(int memberNo, int currStat, int progNo, String progTime, long bkNo, String memberId,
			String memberPwd) {
		super();
		this.memberNo = memberNo;
		this.currStat = currStat;
		this.progNo = progNo;
		this.progTime = progTime;
		this.bkNo = bkNo;
		this.memberId = memberId;
		this.memberPwd = memberPwd;
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
	public String getProgTime() {
		return progTime;
	}
	public void setProgTime(String progTime) {
		this.progTime = progTime;
	}
	public long getBkNo() {
		return bkNo;
	}
	public void setBkNo(long bkNo) {
		this.bkNo = bkNo;
	}
	
	
	
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPwd() {
		return memberPwd;
	}
	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}
	@Override
	public String toString() {
		String resvSession = "---------reserveSession-------\n"
				+ memberNo + "\n"
				+ currStat + "\n"
				+ progNo + "\n"
				+ progTime + "\n"
				+ bkNo + "\n"
							+ "-----------------------------\n\n\n";

		return resvSession;
	}
	
}
