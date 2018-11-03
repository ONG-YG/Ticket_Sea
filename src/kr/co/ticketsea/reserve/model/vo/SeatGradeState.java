package kr.co.ticketsea.reserve.model.vo;

public class SeatGradeState {
	
	private String th1_seat_grd;
	private int th1_seat_prc;
	private int availableSeatCnt;
	private String grd_color;
	
	
	
	public SeatGradeState() {
		super();
	}
	public SeatGradeState(String th1_seat_grd, int th1_seat_prc, int availableSeatCnt, String grd_color) {
		super();
		this.th1_seat_grd = th1_seat_grd;
		this.th1_seat_prc = th1_seat_prc;
		this.availableSeatCnt = availableSeatCnt;
		this.grd_color = grd_color;
	}




	public String getTh1_seat_grd() {
		return th1_seat_grd;
	}
	public void setTh1_seat_grd(String th1_seat_grd) {
		this.th1_seat_grd = th1_seat_grd;
	}
	public int getTh1_seat_prc() {
		return th1_seat_prc;
	}
	public void setTh1_seat_prc(int th1_seat_prc) {
		this.th1_seat_prc = th1_seat_prc;
	}
	public int getAvailableSeatCnt() {
		return availableSeatCnt;
	}
	public void setAvailableSeatCnt(int availableSeatCnt) {
		this.availableSeatCnt = availableSeatCnt;
	}
	public String getGrd_color() {
		return grd_color;
	}
	public void setGrd_color(String grd_color) {
		this.grd_color = grd_color;
	}
	
	
	
	
	
	
	
	
	@Override
	public String toString() {
		String seatGrdStat = "\n---------seatGrdStat-------\n"
				+ th1_seat_grd + "\n"
				+ th1_seat_prc + "\n"
				+ availableSeatCnt + "\n"
							+ "---------------------------\n";

		return seatGrdStat;
	}
	
}
