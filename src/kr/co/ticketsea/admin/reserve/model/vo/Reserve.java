package kr.co.ticketsea.admin.reserve.model.vo;

import java.sql.Date;

public class Reserve {
	private String bk_no; //예매번호
	private int member_no; //예매한 회원 번호
	private String bk_stat_cd; //예매 상태코드
	private Date bk_date; //예매일
	private int bk_tk_price; //티켓 합계 금액
	private int bk_tot_price; //총 결제 금액
	private String bk_pay_type; //결제방법
	
	public Reserve() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Reserve(String bk_no, int member_no, String bk_stat_cd, Date bk_date, int bk_tk_price, int bk_tot_price,
			String bk_pay_type) {
		super();
		this.bk_no = bk_no;
		this.member_no = member_no;
		this.bk_stat_cd = bk_stat_cd;
		this.bk_date = bk_date;
		this.bk_tk_price = bk_tk_price;
		this.bk_tot_price = bk_tot_price;
		this.bk_pay_type = bk_pay_type;
	}
	public String getBk_no() {
		return bk_no;
	}
	public void setBk_no(String bk_no) {
		this.bk_no = bk_no;
	}
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public String getBk_stat_cd() {
		return bk_stat_cd;
	}
	public void setBk_stat_cd(String bk_stat_cd) {
		this.bk_stat_cd = bk_stat_cd;
	}
	public Date getBk_date() {
		return bk_date;
	}
	public void setBk_date(Date bk_date) {
		this.bk_date = bk_date;
	}
	public int getBk_tk_price() {
		return bk_tk_price;
	}
	public void setBk_tk_price(int bk_tk_price) {
		this.bk_tk_price = bk_tk_price;
	}
	public int getBk_tot_price() {
		return bk_tot_price;
	}
	public void setBk_tot_price(int bk_tot_price) {
		this.bk_tot_price = bk_tot_price;
	}
	public String getBk_pay_type() {
		return bk_pay_type;
	}
	public void setBk_pay_type(String bk_pay_type) {
		this.bk_pay_type = bk_pay_type;
	}
	
	
	
}
