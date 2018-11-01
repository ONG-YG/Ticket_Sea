package kr.co.ticketsea.qna.model.vo;

import java.sql.Date;

public class Qna {
	private int boardQ_no;
	private String boardQ_writer;
	private String boardQ_title;
	private String boardQ_contents;
	private Date boardQ_date;
	private int boardQ_hit;
	public int getBoardQ_no() {
		return boardQ_no;
	}
	public void setBoardQ_no(int boardQ_no) {
		this.boardQ_no = boardQ_no;
	}
	public String getBoardQ_writer() {
		return boardQ_writer;
	}
	public void setBoardQ_writer(String boardQ_writer) {
		this.boardQ_writer = boardQ_writer;
	}
	public String getBoardQ_title() {
		return boardQ_title;
	}
	public void setBoardQ_title(String boardQ_title) {
		this.boardQ_title = boardQ_title;
	}
	public String getBoardQ_contents() {
		return boardQ_contents;
	}
	public void setBoardQ_contents(String boardQ_contents) {
		this.boardQ_contents = boardQ_contents;
	}
	public Date getBoardQ_date() {
		return boardQ_date;
	}
	public void setBoardQ_date(Date boardQ_date) {
		this.boardQ_date = boardQ_date;
	}
	public int getBoardQ_hit() {
		return boardQ_hit;
	}
	public void setBoardQ_hit(int boardQ_hit) {
		this.boardQ_hit = boardQ_hit;
	}
	public Qna(int boardQ_no, String boardQ_writer, String boardQ_title, String boardQ_contents, Date boardQ_date,
			int boardQ_hit) {
		super();
		this.boardQ_no = boardQ_no;
		this.boardQ_writer = boardQ_writer;
		this.boardQ_title = boardQ_title;
		this.boardQ_contents = boardQ_contents;
		this.boardQ_date = boardQ_date;
		this.boardQ_hit = boardQ_hit;
	}
	public Qna() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	
}
