package kr.co.ticketsea.promo.model.vo;

import java.sql.Date;
import java.sql.Timestamp;

public class Show {
	private int show_no;
	private String show_title;
	private String show_subTitle;
	private String show_location;
	private Date show_date;
	private int show_time;
	private String show_grade;
	private int show_price;
	
	private String boardP_fileName;
	private String boardP_filePath;
	private long boardP_fileSize;
	private Timestamp boardP_uploadTime;
	public int getShow_no() {
		return show_no;
	}
	public void setShow_no(int show_no) {
		this.show_no = show_no;
	}
	public String getShow_title() {
		return show_title;
	}
	public void setShow_title(String show_title) {
		this.show_title = show_title;
	}
	public String getShow_subTitle() {
		return show_subTitle;
	}
	public void setShow_subTitle(String show_subTitle) {
		this.show_subTitle = show_subTitle;
	}
	public String getShow_location() {
		return show_location;
	}
	public void setShow_location(String show_location) {
		this.show_location = show_location;
	}
	public Date getShow_date() {
		return show_date;
	}
	public void setShow_date(Date show_date) {
		this.show_date = show_date;
	}
	public int getShow_time() {
		return show_time;
	}
	public void setShow_time(int show_time) {
		this.show_time = show_time;
	}
	public String getShow_grade() {
		return show_grade;
	}
	public void setShow_grade(String show_grade) {
		this.show_grade = show_grade;
	}
	public int getShow_price() {
		return show_price;
	}
	public void setShow_price(int show_price) {
		this.show_price = show_price;
	}
	public String getBoardP_fileName() {
		return boardP_fileName;
	}
	public void setBoardP_fileName(String boardP_fileName) {
		this.boardP_fileName = boardP_fileName;
	}
	public String getBoardP_filePath() {
		return boardP_filePath;
	}
	public void setBoardP_filePath(String boardP_filePath) {
		this.boardP_filePath = boardP_filePath;
	}
	public long getBoardP_fileSize() {
		return boardP_fileSize;
	}
	public void setBoardP_fileSize(long boardP_fileSize) {
		this.boardP_fileSize = boardP_fileSize;
	}
	public Timestamp getBoardP_uploadTime() {
		return boardP_uploadTime;
	}
	public void setBoardP_uploadTime(Timestamp boardP_uploadTime) {
		this.boardP_uploadTime = boardP_uploadTime;
	}
	public Show(int show_no, String show_title, String show_subTitle, String show_location, Date show_date,
			int show_time, String show_grade, int show_price, String boardP_fileName, String boardP_filePath,
			long boardP_fileSize, Timestamp boardP_uploadTime) {
		super();
		this.show_no = show_no;
		this.show_title = show_title;
		this.show_subTitle = show_subTitle;
		this.show_location = show_location;
		this.show_date = show_date;
		this.show_time = show_time;
		this.show_grade = show_grade;
		this.show_price = show_price;
		this.boardP_fileName = boardP_fileName;
		this.boardP_filePath = boardP_filePath;
		this.boardP_fileSize = boardP_fileSize;
		this.boardP_uploadTime = boardP_uploadTime;
	}
	public Show() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
