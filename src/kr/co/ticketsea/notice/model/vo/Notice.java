package kr.co.ticketsea.notice.model.vo;

import java.sql.Date;

public class Notice {
	private int boardN_no;
	private String boardN_category;
	private String boardN_title;
	private String boardN_contents;
	private Date boardN_date;
	private int boardN_hit;
	public int getBoardN_no() {
		return boardN_no;
	}
	public void setBoardN_no(int boardN_no) {
		this.boardN_no = boardN_no;
	}
	public String getBoardN_category() {
		return boardN_category;
	}
	public void setBoardN_category(String boardN_category) {
		this.boardN_category = boardN_category;
	}
	public String getBoardN_title() {
		return boardN_title;
	}
	public void setBoardN_title(String boardN_title) {
		this.boardN_title = boardN_title;
	}
	public String getBoardN_contents() {
		return boardN_contents;
	}
	public void setBoardN_contents(String boardN_contents) {
		this.boardN_contents = boardN_contents;
	}
	public Date getBoardN_date() {
		return boardN_date;
	}
	public void setBoardN_date(Date boardN_date) {
		this.boardN_date = boardN_date;
	}
	public int getBoardN_hit() {
		return boardN_hit;
	}
	public void setBoardN_hit(int boardN_hit) {
		this.boardN_hit = boardN_hit;
	}
	public Notice(int boardN_no, String boardN_category, String boardN_title, String boardN_contents, Date boardN_date,
			int boardN_hit) {
		super();
		this.boardN_no = boardN_no;
		this.boardN_category = boardN_category;
		this.boardN_title = boardN_title;
		this.boardN_contents = boardN_contents;
		this.boardN_date = boardN_date;
		this.boardN_hit = boardN_hit;
	}
	public Notice() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
