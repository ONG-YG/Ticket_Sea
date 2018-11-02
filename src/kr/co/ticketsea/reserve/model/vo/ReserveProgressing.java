package kr.co.ticketsea.reserve.model.vo;

import java.sql.Date;
import java.util.ArrayList;

public class ReserveProgressing {
	
	private int psNo;									//공연회차번호
	private int showNo;									//공연번호
	private String showTitle;							//공연명
	private String showPoster;							//공연 포스터 파일명
	private String theaterName;							//공연장명
	private Date psDate;								//공연일
	private int showCnt;								//공연회차
	private String showTime;							//공연시간
	private ArrayList<Integer> reservedSeatList;		//예매완료 좌석 목록
	private ArrayList<Integer> progSeatList;			//예매진행 중 좌석 목록
	private ArrayList<SeatGradeState> seatGrdStList;	//등급별 좌석가격 및 잔여석
	private String memberName;							//예매자명
	private long bkNo;									//예매번호
	private ArrayList<SelectedSeat> selSeatList;		//선택좌석 목록
	private String phone;								//연락처
	private String email;								//메일
	private int commission;								//수수료
	private int ticketPrice;							//티켓가격 총합
	private int totalPrice;								//총 결제 금액
	
	
	
	public ReserveProgressing() {
		this.psNo = -1;
		this.showNo = -1;
		this.showCnt = -1;
		this.bkNo = -1;
		this.commission = -1;
		this.ticketPrice = -1;
		this.totalPrice = -1;
	}
	public ReserveProgressing(int psNo, int showNo, String showTitle, String showPoster, String theaterName,
			Date psDate, int showCnt, String showTime, ArrayList<Integer> reservedSeatList,
			ArrayList<Integer> progSeatList, ArrayList<SeatGradeState> seatGrdSt, String memberName, int bkNo,
			ArrayList<SelectedSeat> selSeatList, String phone, String email, int commission, int ticketPrice,
			int totalPrice) {
		super();
		this.psNo = psNo;
		this.showNo = showNo;
		this.showTitle = showTitle;
		this.showPoster = showPoster;
		this.theaterName = theaterName;
		this.psDate = psDate;
		this.showCnt = showCnt;
		this.showTime = showTime;
		this.reservedSeatList = reservedSeatList;
		this.progSeatList = progSeatList;
		this.seatGrdStList = seatGrdSt;
		this.memberName = memberName;
		this.bkNo = bkNo;
		this.selSeatList = selSeatList;
		this.phone = phone;
		this.email = email;
		this.commission = commission;
		this.ticketPrice = ticketPrice;
		this.totalPrice = totalPrice;
	}
	
	
	
	
	public int getPsNo() {
		return psNo;
	}
	public void setPsNo(int psNo) {
		this.psNo = psNo;
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
	public String getTheaterName() {
		return theaterName;
	}
	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}
	public Date getPsDate() {
		return psDate;
	}
	public void setPsDate(Date psDate) {
		this.psDate = psDate;
	}
	public int getShowCnt() {
		return showCnt;
	}
	public void setShowCnt(int showCnt) {
		this.showCnt = showCnt;
	}
	public String getShowTime() {
		return showTime;
	}
	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}
	public ArrayList<Integer> getReservedSeatList() {
		return reservedSeatList;
	}
	public void setReservedSeatList(ArrayList<Integer> reservedSeatList) {
		this.reservedSeatList = reservedSeatList;
	}
	public ArrayList<Integer> getProgSeatList() {
		return progSeatList;
	}
	public void setProgSeatList(ArrayList<Integer> progSeatList) {
		this.progSeatList = progSeatList;
	}
	public ArrayList<SeatGradeState> getSeatGrdSt() {
		return seatGrdStList;
	}
	public void setSeatGrdSt(ArrayList<SeatGradeState> seatGrdSt) {
		this.seatGrdStList = seatGrdSt;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public long getBkNo() {
		return bkNo;
	}
	public void setBkNo(long bkNo) {
		this.bkNo = bkNo;
	}
	public ArrayList<SelectedSeat> getSelSeatList() {
		return selSeatList;
	}
	public void setSelecSeatList(ArrayList<SelectedSeat> selSeatList) {
		this.selSeatList = selSeatList;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getCommission() {
		return commission;
	}
	public void setCommission(int commission) {
		this.commission = commission;
	}
	public int getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice() {
		if(!this.selSeatList.isEmpty()) {
			int sum=0;
			for (SelectedSeat seat :this.selSeatList) {
				sum +=seat.getSeatPrice();
			}
			this.ticketPrice = sum;
		}
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice() {
		if(this.commission!=-1 && this.ticketPrice!=-1) {
			this.totalPrice = this.ticketPrice + commission;
		}
	}
	
	
	
	
	@Override
	public String toString() {

		String reservProg = "---------reservProg-------\n"
							+ psNo + "\n"
							+ showNo + "\n"
							+ showTitle + "\n"
							+ showPoster + "\n"
							+ theaterName + "\n"
							+ psDate + "\n"
							+ showCnt + "\n"
							+ showTime + "\n"
							+ "---reservSeats---\n" + reservedSeatList + "\n---\n"
							+ "---progSeats---\n" + progSeatList + "\n---\n"
							+ seatGrdStList + "\n"
							+ memberName + "\n"
							+ bkNo + "\n"
							+ selSeatList + "\n"
							+ phone + "\n"
							+ email + "\n"
							+ commission + "\n"
							+ ticketPrice + "\n"
							+ totalPrice + "\n"
							+ "-------------------------\n";
		
		
		return reservProg;
	}
	
	
	
}
