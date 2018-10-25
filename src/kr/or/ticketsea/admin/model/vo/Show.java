package kr.or.ticketsea.admin.model.vo;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Show {
	private int m_show_no;
	private int th_no;
	private String sc_code;
	private String show_name;
	private String show_st_date;
	private String show_ed_date;
	private String artists;
	private String show_grd;
	private int show_run;
	
	public Show() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Show(int m_show_no, int th_no, String sc_code, String show_name, String show_st_date, String show_ed_date,
			String artists, String show_grd, int show_run) {
		super();
		this.m_show_no = m_show_no;
		this.th_no = th_no;
		this.sc_code = sc_code;
		this.show_name = show_name;
		this.artists = artists;
		this.show_grd = show_grd;
		this.show_run = show_run;
		this.show_st_date=show_st_date;
		this.show_ed_date=show_ed_date;
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
	
	
	
}
