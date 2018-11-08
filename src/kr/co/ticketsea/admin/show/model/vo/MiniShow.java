package kr.co.ticketsea.admin.show.model.vo;

import java.sql.Date;

public class MiniShow {
	
	private int boardp_no; //소규모공연번호
	private String boardp_title; //공연명
	private String boardp_writer; //작성자 아이디
	private String boardp_artist; //출연아티스트
	private String boardp_location; //공연장소
	private Date boardp_date; //공연일
	private int boardp_price; //공연가격
	private String boardp_category; //공연 장르
	private String boardp_contents; //공연소개
	private String boardp_filename; //공연 포스터
	private char boardp_active; //승인상태
	public MiniShow() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MiniShow(int boardp_no, String boardp_title, String boardp_writer, String boardp_artist,
			String boardp_location, Date boardp_date, int boardp_price, String boardp_category, String boardp_contents,
			String boardp_filename, char boardp_active) {
		super();
		this.boardp_no = boardp_no;
		this.boardp_title = boardp_title;
		this.boardp_writer = boardp_writer;
		this.boardp_artist = boardp_artist;
		this.boardp_location = boardp_location;
		this.boardp_date = boardp_date;
		this.boardp_price = boardp_price;
		this.boardp_category = boardp_category;
		this.boardp_contents = boardp_contents;
		this.boardp_filename = boardp_filename;
		this.boardp_active = boardp_active;
	}
	public int getBoardp_no() {
		return boardp_no;
	}
	public void setBoardp_no(int boardp_no) {
		this.boardp_no = boardp_no;
	}
	public String getBoardp_title() {
		return boardp_title;
	}
	public void setBoardp_title(String boardp_title) {
		this.boardp_title = boardp_title;
	}
	public String getBoardp_writer() {
		return boardp_writer;
	}
	public void setBoardp_writer(String boardp_writer) {
		this.boardp_writer = boardp_writer;
	}
	public String getBoardp_artist() {
		return boardp_artist;
	}
	public void setBoardp_artist(String boardp_artist) {
		this.boardp_artist = boardp_artist;
	}
	public String getBoardp_location() {
		return boardp_location;
	}
	public void setBoardp_location(String boardp_location) {
		this.boardp_location = boardp_location;
	}
	public Date getBoardp_date() {
		return boardp_date;
	}
	public void setBoardp_date(Date boardp_date) {
		this.boardp_date = boardp_date;
	}
	public int getBoardp_price() {
		return boardp_price;
	}
	public void setBoardp_price(int boardp_price) {
		this.boardp_price = boardp_price;
	}
	public String getBoardp_category() {
		return boardp_category;
	}
	public void setBoardp_category(String boardp_category) {
		this.boardp_category = boardp_category;
	}
	public String getBoardp_contents() {
		return boardp_contents;
	}
	public void setBoardp_contents(String boardp_contents) {
		this.boardp_contents = boardp_contents;
	}
	public String getBoardp_filename() {
		return boardp_filename;
	}
	public void setBoardp_filename(String boardp_filename) {
		this.boardp_filename = boardp_filename;
	}
	public char getBoardp_active() {
		return boardp_active;
	}
	public void setBoardp_active(char boardp_active) {
		this.boardp_active = boardp_active;
	}
	
	
	
	
}
