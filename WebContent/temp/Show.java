package kr.co.ticketsea.show.model.vo;

import java.sql.Date;
import java.sql.Timestamp;

public class Show {
	private int show_no;
	private String show_category; // 
	private String show_writer;
	private String show_title;
	private String show_subTitle;
	private String show_artist;
	private String show_contents;
	private String show_location;
	private Date show_startDate;
	private Date show_endDate;
	private int show_time;
	private String show_grade;
	private int show_price;
	
	private String show_fileName;
	private String show_filePath;
	private long show_fileSize;
	private Timestamp show_uploadTime;
	public int getShow_no() {
		return show_no;
	}
	public void setShow_no(int show_no) {
		this.show_no = show_no;
	}
	public String getShow_category() {
		return show_category;
	}
	public void setShow_category(String show_category) {
		this.show_category = show_category;
	}
	public String getShow_writer() {
		return show_writer;
	}
	public void setShow_writer(String show_writer) {
		this.show_writer = show_writer;
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
	public String getShow_artist() {
		return show_artist;
	}
	public void setShow_artist(String show_artist) {
		this.show_artist = show_artist;
	}
	public String getShow_contents() {
		return show_contents;
	}
	public void setShow_contents(String show_contents) {
		this.show_contents = show_contents;
	}
	public String getShow_location() {
		return show_location;
	}
	public void setShow_location(String show_location) {
		this.show_location = show_location;
	}
	public Date getShow_startDate() {
		return show_startDate;
	}
	public void setShow_startDate(Date show_startDate) {
		this.show_startDate = show_startDate;
	}
	public Date getShow_endDate() {
		return show_endDate;
	}
	public void setShow_endDate(Date show_endDate) {
		this.show_endDate = show_endDate;
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
	public String getShow_fileName() {
		return show_fileName;
	}
	public void setShow_fileName(String show_fileName) {
		this.show_fileName = show_fileName;
	}
	public String getShow_filePath() {
		return show_filePath;
	}
	public void setShow_filePath(String show_filePath) {
		this.show_filePath = show_filePath;
	}
	public long getShow_fileSize() {
		return show_fileSize;
	}
	public void setShow_fileSize(long show_fileSize) {
		this.show_fileSize = show_fileSize;
	}
	public Timestamp getShow_uploadTime() {
		return show_uploadTime;
	}
	public void setShow_uploadTime(Timestamp show_uploadTime) {
		this.show_uploadTime = show_uploadTime;
	}
	public Show(int show_no, String show_category, String show_writer, String show_title, String show_subTitle,
			String show_artist, String show_contents, String show_location, Date show_startDate, Date show_endDate,
			int show_time, String show_grade, int show_price, String show_fileName, String show_filePath,
			long show_fileSize, Timestamp show_uploadTime) {
		super();
		this.show_no = show_no;
		this.show_category = show_category;
		this.show_writer = show_writer;
		this.show_title = show_title;
		this.show_subTitle = show_subTitle;
		this.show_artist = show_artist;
		this.show_contents = show_contents;
		this.show_location = show_location;
		this.show_startDate = show_startDate;
		this.show_endDate = show_endDate;
		this.show_time = show_time;
		this.show_grade = show_grade;
		this.show_price = show_price;
		this.show_fileName = show_fileName;
		this.show_filePath = show_filePath;
		this.show_fileSize = show_fileSize;
		this.show_uploadTime = show_uploadTime;
	}
	public Show() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
}
