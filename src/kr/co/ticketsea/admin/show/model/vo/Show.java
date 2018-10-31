package kr.co.ticketsea.admin.show.model.vo;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Show {
	private int m_show_no;
	private int th_no;
	private String th_name;  //정보 불러올때 공연장소 번호가 아니라 이름으로 불러오기 위해
	private String sc_code;
	private String show_name;
	private String show_st_date;
	private String show_ed_date;
	private String artists;
	private String show_grd;
	private int show_run;
	private int bk_comm;
	private String show_poster;
	private String show_dtInfo;
	
	public Show() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Show(int m_show_no, int th_no, String th_name, String sc_code, String show_name, String show_st_date,
			String show_ed_date, String artists, String show_grd, int show_run, int bk_comm, String show_poster,
			String show_dtInfo) {
		super();
		this.m_show_no = m_show_no;
		this.th_no = th_no;
		this.th_name = th_name;
		this.sc_code = sc_code;
		this.show_name = show_name;
		this.show_st_date = show_st_date;
		this.show_ed_date = show_ed_date;
		this.artists = artists;
		this.show_grd = show_grd;
		this.show_run = show_run;
		this.bk_comm = bk_comm;
		this.show_poster = show_poster;
		this.show_dtInfo = show_dtInfo;
	}
	
	public int getM_show_no() {
		return m_show_no;
	}
	public void setM_show_no(int m_show_no) {
		this.m_show_no = m_show_no;
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
	public String getSc_code() {
		return sc_code;
	}
	public void setSc_code(String sc_code) {
		this.sc_code = sc_code;
	}
	public String getShow_name() {
		return show_name;
	}
	public void setShow_name(String show_name) {
		this.show_name = show_name;
	}
	public String getShow_st_date() {
		return show_st_date;
	}
	public void setShow_st_date(String show_st_date) {
		this.show_st_date = show_st_date;
	}
	public String getShow_ed_date() {
		return show_ed_date;
	}
	public void setShow_ed_date(String show_ed_date) {
		this.show_ed_date = show_ed_date;
	}
	public String getArtists() {
		return artists;
	}
	public void setArtists(String artists) {
		this.artists = artists;
	}
	public String getShow_grd() {
		return show_grd;
	}
	public void setShow_grd(String show_grd) {
		this.show_grd = show_grd;
	}
	public int getShow_run() {
		return show_run;
	}
	public void setShow_run(int show_run) {
		this.show_run = show_run;
	}
	public int getBk_comm() {
		return bk_comm;
	}
	public void setBk_comm(int bk_comm) {
		this.bk_comm = bk_comm;
	}
	public String getShow_poster() {
		return show_poster;
	}
	public void setShow_poster(String show_poster) {
		this.show_poster = show_poster;
	}
	public String getShow_dtInfo() {
		return show_dtInfo;
	}
	public void setShow_dtInfo(String show_dtInfo) {
		this.show_dtInfo = show_dtInfo;
	}
	
	
	
}