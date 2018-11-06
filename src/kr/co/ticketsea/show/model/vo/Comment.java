package kr.co.ticketsea.show.model.vo;

import java.sql.Date;

public class Comment {
	private int commentNo;
	private int showNo;
	private String contents;
	private String userId;
	private Date regDate;
	
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public int getShowNo() {
		return showNo;
	}
	public void setShowNo(int showNo) {
		this.showNo = showNo;
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
	public Comment(int commentNo, int showNo, String contents, String userId, Date regDate) {
		super();
		this.commentNo = commentNo;
		this.showNo = showNo;
		this.contents = contents;
		this.userId = userId;
		this.regDate = regDate;
	}
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}









