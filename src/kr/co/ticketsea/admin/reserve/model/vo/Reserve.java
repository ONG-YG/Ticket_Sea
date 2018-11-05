package kr.co.ticketsea.admin.reserve.model.vo;

import java.sql.Date;

public class Reserve {
	//예매정보
	private int bk_no; //예매번호
	private int member_no; //예매한 회원 번호
	private String bk_stat_cd; //예매 상태코드
	private Date bk_date; //예매일
	private int tot_price; //총 결제 금액
	private String bk_pay_type; //결제방법
	//회원정보
	private String member_id; // 회원아이디
	private String member_name; //회원번호
	//예매상태코드
	private String bk_stat_name; //예매 상태 코드 
	//티켓정보
	private String tk_no; //티켓 번호
	private int ps_no; //공연회차번호
	private int seat_no; //좌석번호
	//좌석정보
	private String seat_grd; //좌석등급
	private String seat_title; //좌석 타이틀
	//공연회차 정보
	private Date ps_date; //공연일
	private int ps_cnt; //공연회차
	private String ps_time; //공연시간
	//공연정보
	private int show_no; //공연번호
	private String show_name; //공연명
	
	public Reserve() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reserve(int bk_no, int member_no, String bk_stat_cd, Date bk_date, int tot_price, String bk_pay_type,
			String member_id, String member_name, String bk_stat_name, String tk_no, int ps_no, int seat_no,
			String seat_grd, String seat_title, Date ps_date, int ps_cnt, String ps_time, int show_no,
			String show_name) {
		super();
		this.bk_no = bk_no;
		this.member_no = member_no;
		this.bk_stat_cd = bk_stat_cd;
		this.bk_date = bk_date;
		this.tot_price = tot_price;
		this.bk_pay_type = bk_pay_type;
		this.member_id = member_id;
		this.member_name = member_name;
		this.bk_stat_name = bk_stat_name;
		this.tk_no = tk_no;
		this.ps_no = ps_no;
		this.seat_no = seat_no;
		this.seat_grd = seat_grd;
		this.seat_title = seat_title;
		this.ps_date = ps_date;
		this.ps_cnt = ps_cnt;
		this.ps_time = ps_time;
		this.show_no = show_no;
		this.show_name = show_name;
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

	public Date getBk_date() {
		return bk_date;
	}

	public void setBk_date(Date bk_date) {
		this.bk_date = bk_date;
	}

	public int getTot_price() {
		return tot_price;
	}

	public void setTot_price(int tot_price) {
		this.tot_price = tot_price;
	}

	public String getBk_pay_type() {
		return bk_pay_type;
	}

	public void setBk_pay_type(String bk_pay_type) {
		this.bk_pay_type = bk_pay_type;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public String getBk_stat_name() {
		return bk_stat_name;
	}

	public void setBk_stat_name(String bk_stat_name) {
		this.bk_stat_name = bk_stat_name;
	}

	public String getTk_no() {
		return tk_no;
	}

	public void setTk_no(String tk_no) {
		this.tk_no = tk_no;
	}

	public int getPs_no() {
		return ps_no;
	}

	public void setPs_no(int ps_no) {
		this.ps_no = ps_no;
	}

	public int getSeat_no() {
		return seat_no;
	}

	public void setSeat_no(int seat_no) {
		this.seat_no = seat_no;
	}

	public String getSeat_grd() {
		return seat_grd;
	}

	public void setSeat_grd(String seat_grd) {
		this.seat_grd = seat_grd;
	}

	public String getSeat_title() {
		return seat_title;
	}

	public void setSeat_title(String seat_title) {
		this.seat_title = seat_title;
	}

	public Date getPs_date() {
		return ps_date;
	}

	public void setPs_date(Date ps_date) {
		this.ps_date = ps_date;
	}

	public int getPs_cnt() {
		return ps_cnt;
	}

	public void setPs_cnt(int ps_cnt) {
		this.ps_cnt = ps_cnt;
	}

	public String getPs_time() {
		return ps_time;
	}

	public void setPs_time(String ps_time) {
		this.ps_time = ps_time;
	}

	public int getShow_no() {
		return show_no;
	}

	public void setShow_no(int show_no) {
		this.show_no = show_no;
	}

	public String getShow_name() {
		return show_name;
	}

	public void setShow_name(String show_name) {
		this.show_name = show_name;
	}
	
	
	
 }
