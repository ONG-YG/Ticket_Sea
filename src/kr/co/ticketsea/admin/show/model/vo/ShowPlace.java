package kr.co.ticketsea.admin.show.model.vo;

public class ShowPlace {
	private int th_no;
	private String th_name;
	private String th_lct;
	
	public ShowPlace() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ShowPlace(int th_no, String th_name, String th_lct) {
		super();
		this.th_no = th_no;
		this.th_name = th_name;
		this.th_lct = th_lct;
	}
	public int getTh_no() {
		return th_no;
	}
	public void setTh_no(int th_no) {
		this.th_no = th_no;
	}
	public String getTh_name() {
		return th_name;
	}
	public void setTh_name(String th_name) {
		this.th_name = th_name;
	}
	public String getTh_lct() {
		return th_lct;
	}
	public void setTh_lct(String th_lct) {
		this.th_lct = th_lct;
	}
	
	
}
