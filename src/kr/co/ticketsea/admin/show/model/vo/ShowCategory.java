package kr.co.ticketsea.admin.show.model.vo;

public class ShowCategory {
	
	private String sc_code;
	private String sc_name;
	
	public ShowCategory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ShowCategory(String sc_code, String sc_name) {
		super();
		this.sc_code = sc_code;
		this.sc_name = sc_name;
	}
	public String getSc_code() {
		return sc_code;
	}
	public void setSc_code(String sc_code) {
		this.sc_code = sc_code;
	}
	public String getSc_name() {
		return sc_name;
	}
	public void setSc_name(String sc_name) {
		this.sc_name = sc_name;
	}
	
}
