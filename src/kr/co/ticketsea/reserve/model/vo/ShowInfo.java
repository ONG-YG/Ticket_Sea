package kr.co.ticketsea.reserve.model.vo;

import java.sql.Date;

public class ShowInfo {
	
	private int m_show_no;
	private int th_no;
	private String sc_code;
	private String m_show_name;
	private Date m_show_st_date;
	private Date m_show_ed_date;
	private int bk_comm;
	private String m_show_poster;
	
	
	
	
	public ShowInfo() {
		super();
	}
	public ShowInfo(int m_show_no, int th_no, String sc_code, String m_show_name, Date m_show_st_date,
			Date m_show_ed_date, int bk_comm, String m_show_poster) {
		super();
		this.m_show_no = m_show_no;
		this.th_no = th_no;
		this.sc_code = sc_code;
		this.m_show_name = m_show_name;
		this.m_show_st_date = m_show_st_date;
		this.m_show_ed_date = m_show_ed_date;
		this.bk_comm = bk_comm;
		this.m_show_poster = m_show_poster;
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
	public String getM_show_name() {
		return m_show_name;
	}
	public void setM_show_name(String m_show_name) {
		this.m_show_name = m_show_name;
	}
	public Date getM_show_st_date() {
		return m_show_st_date;
	}
	public void setM_show_st_date(Date m_show_st_date) {
		this.m_show_st_date = m_show_st_date;
	}
	public Date getM_show_ed_date() {
		return m_show_ed_date;
	}
	public void setM_show_ed_date(Date m_show_ed_date) {
		this.m_show_ed_date = m_show_ed_date;
	}
	public int getBk_comm() {
		return bk_comm;
	}
	public void setBk_comm(int bk_comm) {
		this.bk_comm = bk_comm;
	}
	public String getM_show_poster() {
		return m_show_poster;
	}
	public void setM_show_poster(String m_show_poster) {
		this.m_show_poster = m_show_poster;
	}
	
	
	@Override
	public String toString() {
		
		String showInfo = "---------show-------\n"
							+ m_show_no + "\n"
							+ th_no + "\n"
							+ sc_code + "\n"
							+ m_show_name + "\n"
							+ m_show_st_date + "\n"
							+ m_show_ed_date + "\n"
							+ bk_comm + "\n"
							+ m_show_poster + "\n"
							+ "--------------------\n";
		
		return showInfo;
	}
	
	
}
