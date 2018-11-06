package kr.co.ticketsea.reserve.model.vo;

public class SelectedSeat {
	
	private int seatNo;
	private String seatGrd;
	private int seatPrice;
	private String seatTitle;
	private String seatGrdColor;
	
	
	
	public SelectedSeat() {
		super();
	}
	public SelectedSeat(int seatNo, String seatGrd, int seatPrice, String seatTitle, String seatGrdColor) {
		super();
		this.seatNo = seatNo;
		this.seatGrd = seatGrd;
		this.seatPrice = seatPrice;
		this.seatTitle = seatTitle;
		this.seatGrdColor = seatGrdColor;
	}
	
	
	
	
	
	public int getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}
	public String getSeatGrd() {
		return seatGrd;
	}
	public void setSeatGrd(String seatGrd) {
		this.seatGrd = seatGrd;
	}
	public int getSeatPrice() {
		return seatPrice;
	}
	public void setSeatPrice(int seatPrice) {
		this.seatPrice = seatPrice;
	}
	public String getSeatTitle() {
		return seatTitle;
	}
	public void setSeatTitle(String seatTitle) {
		this.seatTitle = seatTitle;
	}
	public String getSeatGrdColor() {
		return seatGrdColor;
	}
	public void setSeatGrdColor(String seatGrdColor) {
		this.seatGrdColor = seatGrdColor;
	}
	
	
	
	
	
	
	
	@Override
	public String toString() {
		String showInfo = "---------selectedSeat-------\n"
				+ seatNo + " / "
				+ seatGrd + " / "
				+ seatPrice + " / "
				+ seatTitle + "\n"

						+ "----------------------------\n";

		return showInfo;
	}
	
}
