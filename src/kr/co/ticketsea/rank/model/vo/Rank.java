package kr.co.ticketsea.rank.model.vo;

public class Rank {

	
	private String poster;
	private String subject;
	private int showNo;
	
	
	public Rank() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Rank(String poster, String subject, int showNo) {
		super();
		this.poster = poster;
		this.subject = subject;
		this.showNo = showNo;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getShowNo() {
		return showNo;
	}
	public void setShowNo(int showNo) {
		this.showNo = showNo;
	}
	
	
	
	
}
