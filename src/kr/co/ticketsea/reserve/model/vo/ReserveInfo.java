package kr.co.ticketsea.reserve.model.vo;

import java.sql.Date;

public class ReserveInfo {
	
	private int bk_no;
	private int member_no;
	private String bk_stat_cd;
	private String bk_date;
	private int bk_tot_price;
	private int bk_comm;
	private String bk_pay_type;
	
	
	
	public ReserveInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReserveInfo(int bk_no, int member_no, String bk_stat_cd, String bk_date, int bk_tot_price, int bk_comm,
			String bk_pay_type) {
		super();
		this.bk_no = bk_no;
		this.member_no = member_no;
		this.bk_stat_cd = bk_stat_cd;
		this.bk_date = bk_date;
		this.bk_tot_price = bk_tot_price;
		this.bk_comm = bk_comm;
		this.bk_pay_type = bk_pay_type;
	}
	
	
	
	
	public int getBk_no() {
		return bk_no;
	}
	public void setBk_no(int bk_no) {
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
	public String getBk_date() {
		return bk_date;
	}
	public void setBk_date(String bk_date) {
		this.bk_date = bk_date;
	}
	public int getBk_tot_price() {
		return bk_tot_price;
	}
	public void setBk_tot_price(int bk_tot_price) {
		this.bk_tot_price = bk_tot_price;
	}
	public int getBk_comm() {
		return bk_comm;
	}
	public void setBk_comm(int bk_comm) {
		this.bk_comm = bk_comm;
	}
	public String getBk_pay_type() {
		return bk_pay_type;
	}
	public void setBk_pay_type(String bk_pay_type) {
		this.bk_pay_type = bk_pay_type;
	}
	
	
	
	
	
	
	
	
}
