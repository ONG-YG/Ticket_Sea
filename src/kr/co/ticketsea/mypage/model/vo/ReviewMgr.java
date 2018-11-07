package kr.co.ticketsea.mypage.model.vo;

import java.sql.Date;

public class ReviewMgr {
	private int num;						// 고유 번호
	private String mShowName;				// 공연명
	private String showcommentContents;		// 댓글내용
	private Date showcommentDate;			// 댓글 작성 일
	
	public ReviewMgr(int num, String mShowName, String showcommentContents, Date showcommentDate) {
		super();
		this.num = num;
		this.mShowName = mShowName;
		this.showcommentContents = showcommentContents;
		this.showcommentDate = showcommentDate;
	}
	
	
	public ReviewMgr() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getmShowName() {
		return mShowName;
	}
	public void setmShowName(String mShowName) {
		this.mShowName = mShowName;
	}
	public String getShowcommentContents() {
		return showcommentContents;
	}
	public void setShowcommentContents(String showcommentContents) {
		this.showcommentContents = showcommentContents;
	}
	public Date getShowcommentDate() {
		return showcommentDate;
	}
	public void setShowcommentDate(Date showcommentDate) {
		this.showcommentDate = showcommentDate;
	}
}
