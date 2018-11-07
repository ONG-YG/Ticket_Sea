package kr.co.ticketsea.admin.reserve.model.vo;

import java.sql.Date;
import java.util.ArrayList;

import kr.co.ticketsea.reserve.model.vo.SelectedSeat;

public class ReserveApInfo {
	private String bk_no; //예매번호
	private int m_show_no; //공연번호
	private String m_show_name; //공연명
	private Date ps_date; // 공연일
	private int ps_cnt; //공연회차 
	private String ps_time; //공연시간
	private ArrayList<SelectedSeat> seatInfo;
	private int ps_no; //공연회차번호
	private int member_no; 
	private String member_id;
	private String member_name;
	private Date bk_date; //예매일
	private int bk_tot_price; //총결제금액
	private String bk_phone; //예매자핸드폰번호
	private String bk_email; //예매자이메일
	private String bk_stat_name; //예매상태
	public ReserveApInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReserveApInfo(String bk_no, int m_show_no, String m_show_name, Date ps_date, int ps_cnt, String ps_time,
			ArrayList<SelectedSeat> seatInfo, int ps_no, int member_no, String member_id, String member_name,
			Date bk_date, int bk_tot_price, String bk_phone, String bk_email, String bk_stat_name) {
		super();
		this.bk_no = bk_no;
		this.m_show_no = m_show_no;
		this.m_show_name = m_show_name;
		this.ps_date = ps_date;
		this.ps_cnt = ps_cnt;
		this.ps_time = ps_time;
		this.seatInfo = seatInfo;
		this.ps_no = ps_no;
		this.member_no = member_no;
		this.member_id = member_id;
		this.member_name = member_name;
		this.bk_date = bk_date;
		this.bk_tot_price = bk_tot_price;
		this.bk_phone = bk_phone;
		this.bk_email = bk_email;
		this.bk_stat_name = bk_stat_name;
	}
	public String getBk_no() {
		return bk_no;
	}
	public void setBk_no(String bk_no) {
		this.bk_no = bk_no;
	}
	public int getM_show_no() {
		return m_show_no;
	}
	public void setM_show_no(int m_show_no) {
		this.m_show_no = m_show_no;
	}
	public String getM_show_name() {
		return m_show_name;
	}
	public void setM_show_name(String m_show_name) {
		this.m_show_name = m_show_name;
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
	public ArrayList<SelectedSeat> getSeatInfo() {
		return seatInfo;
	}
	public void setSeatInfo(ArrayList<SelectedSeat> seatInfo) {
		this.seatInfo = seatInfo;
	}
	public int getPs_no() {
		return ps_no;
	}
	public void setPs_no(int ps_no) {
		this.ps_no = ps_no;
	}
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
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
	public Date getBk_date() {
		return bk_date;
	}
	public void setBk_date(Date bk_date) {
		this.bk_date = bk_date;
	}
	public int getBk_tot_price() {
		return bk_tot_price;
	}
	public void setBk_tot_price(int bk_tot_price) {
		this.bk_tot_price = bk_tot_price;
	}
	public String getBk_phone() {
		return bk_phone;
	}
	public void setBk_phone(String bk_phone) {
		this.bk_phone = bk_phone;
	}
	public String getBk_email() {
		return bk_email;
	}
	public void setBk_email(String bk_email) {
		this.bk_email = bk_email;
	}
	public String getBk_stat_name() {
		return bk_stat_name;
	}
	public void setBk_stat_name(String bk_stat_name) {
		this.bk_stat_name = bk_stat_name;
	}
	
	
}
