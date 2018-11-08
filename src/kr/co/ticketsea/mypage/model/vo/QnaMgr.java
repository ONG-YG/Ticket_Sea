package kr.co.ticketsea.mypage.model.vo;

import java.sql.Date;

public class QnaMgr {
	
	private String num;				// 번호
	private String boardqTitle;		// 제목
	private int boardqHit;			// 조회수
	private Date boardqDate;		// 작성일
	
	
	public QnaMgr(String num, String boardqTitle, int boardqHit, Date boardqDate) {
		super();
		this.num = num;
		this.boardqTitle = boardqTitle;
		this.boardqHit = boardqHit;
		this.boardqDate = boardqDate;
	}
	
	
	
	public QnaMgr() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getBoardqTitle() {
		return boardqTitle;
	}
	public void setBoardqTitle(String boardqTitle) {
		this.boardqTitle = boardqTitle;
	}
	public int getBoardqHit() {
		return boardqHit;
	}
	public void setBoardqHit(int boardqHit) {
		this.boardqHit = boardqHit;
	}
	public Date getBoardqDate() {
		return boardqDate;
	}
	public void setBoardqDate(Date boardqDate) {
		this.boardqDate = boardqDate;
	}
}
