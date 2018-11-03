package kr.co.ticketsea.admin.show.model.vo;

import java.sql.Date;

public class MiniShow {
	
	private int ms_no; //소규모공연번호
	private String ms_name; //공연명
	private String ms_userId; //작성자 아이디
	private String ms_artists; //출연아티스트
	private String ms_place; //공연장소
	private Date ms_wrtdate; //작성일
	private Date ms_st_date; //시작일
	private Date ms_ed_date; //종료일
	private String ms_ct; //공연 장르
	private String ms_intd; //공연소개
	private String ms_poster;
	private String ms_state; //승인상태
	public MiniShow() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MiniShow(int ms_no, String ms_name, String ms_userId, String ms_artists, String ms_place, Date ms_wrtdate,
			Date ms_st_date, Date ms_ed_date, String ms_ct, String ms_intd, String ms_poster, String ms_state) {
		super();
		this.ms_no = ms_no;
		this.ms_name = ms_name;
		this.ms_userId = ms_userId;
		this.ms_artists = ms_artists;
		this.ms_place = ms_place;
		this.ms_wrtdate = ms_wrtdate;
		this.ms_st_date = ms_st_date;
		this.ms_ed_date = ms_ed_date;
		this.ms_ct = ms_ct;
		this.ms_intd = ms_intd;
		this.ms_poster = ms_poster;
		this.ms_state = ms_state;
	}
	public int getMs_no() {
		return ms_no;
	}
	public void setMs_no(int ms_no) {
		this.ms_no = ms_no;
	}
	public String getMs_name() {
		return ms_name;
	}
	public void setMs_name(String ms_name) {
		this.ms_name = ms_name;
	}
	public String getMs_userId() {
		return ms_userId;
	}
	public void setMs_userId(String ms_userId) {
		this.ms_userId = ms_userId;
	}
	public String getMs_artists() {
		return ms_artists;
	}
	public void setMs_artists(String ms_artists) {
		this.ms_artists = ms_artists;
	}
	public String getMs_place() {
		return ms_place;
	}
	public void setMs_place(String ms_place) {
		this.ms_place = ms_place;
	}
	public Date getMs_wrtdate() {
		return ms_wrtdate;
	}
	public void setMs_wrtdate(Date ms_wrtdate) {
		this.ms_wrtdate = ms_wrtdate;
	}
	public Date getMs_st_date() {
		return ms_st_date;
	}
	public void setMs_st_date(Date ms_st_date) {
		this.ms_st_date = ms_st_date;
	}
	public Date getMs_ed_date() {
		return ms_ed_date;
	}
	public void setMs_ed_date(Date ms_ed_date) {
		this.ms_ed_date = ms_ed_date;
	}
	public String getMs_ct() {
		return ms_ct;
	}
	public void setMs_ct(String ms_ct) {
		this.ms_ct = ms_ct;
	}
	public String getMs_intd() {
		return ms_intd;
	}
	public void setMs_intd(String ms_intd) {
		this.ms_intd = ms_intd;
	}
	public String getMs_poster() {
		return ms_poster;
	}
	public void setMs_poster(String ms_poster) {
		this.ms_poster = ms_poster;
	}
	public String getMs_state() {
		return ms_state;
	}
	public void setMs_state(String ms_state) {
		this.ms_state = ms_state;
	}
	
	
	
}
