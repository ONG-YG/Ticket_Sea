package kr.co.ticketsea.promo.model.vo;

import java.sql.Date;

public class Comment {
	private int commentNo;
	private int promoNo;
	private String contents;
	private String userId;
	private Date regDate;
	
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public int getPromoNo() {
		return promoNo;
	}
	public void setPromoNo(int promoNo) {
		this.promoNo = promoNo;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Comment(int commentNo, int promoNo, String contents, String userId, Date regDate) {
		super();
		this.commentNo = commentNo;
		this.promoNo = promoNo;
		this.contents = contents;
		this.userId = userId;
		this.regDate = regDate;
	}
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	
}









