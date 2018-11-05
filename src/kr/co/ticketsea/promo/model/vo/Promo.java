package kr.co.ticketsea.promo.model.vo;

import java.sql.Date;
import java.sql.Timestamp;

public class Promo {
	private int boardP_no;
	private String boardP_writer;
	private String boardP_category;
	private String boardP_title;
	private String boardP_artist;
	private String boardP_contents;
	private String boardP_location;
	private Date boardP_date;
	private int boardP_hit;
	private char boardP_active;
	
	private String boardP_fileName;
	private String boardP_filePath;
	private long boardP_fileSize;
	private Timestamp boardP_uploadTime;
	public int getBoardP_no() {
		return boardP_no;
	}
	public void setBoardP_no(int boardP_no) {
		this.boardP_no = boardP_no;
	}
	public String getBoardP_writer() {
		return boardP_writer;
	}
	public void setBoardP_writer(String boardP_writer) {
		this.boardP_writer = boardP_writer;
	}
	public String getBoardP_category() {
		return boardP_category;
	}
	public void setBoardP_category(String boardP_category) {
		this.boardP_category = boardP_category;
	}
	public String getBoardP_title() {
		return boardP_title;
	}
	public void setBoardP_title(String boardP_title) {
		this.boardP_title = boardP_title;
	}
	public String getBoardP_artist() {
		return boardP_artist;
	}
	public void setBoardP_artist(String boardP_artist) {
		this.boardP_artist = boardP_artist;
	}
	public String getBoardP_contents() {
		return boardP_contents;
	}
	public void setBoardP_contents(String boardP_contents) {
		this.boardP_contents = boardP_contents;
	}
	public String getBoardP_location() {
		return boardP_location;
	}
	public void setBoardP_location(String boardP_location) {
		this.boardP_location = boardP_location;
	}
	public Date getBoardP_date() {
		return boardP_date;
	}
	public void setBoardP_date(Date boardP_date) {
		this.boardP_date = boardP_date;
	}
	public int getBoardP_hit() {
		return boardP_hit;
	}
	public void setBoardP_hit(int boardP_hit) {
		this.boardP_hit = boardP_hit;
	}
	public char getBoardP_active() {
		return boardP_active;
	}
	public void setBoardP_active(char boardP_active) {
		this.boardP_active = boardP_active;
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
	public Promo(int boardP_no, String boardP_writer, String boardP_category, String boardP_title, String boardP_artist,
			String boardP_contents, String boardP_location, Date boardP_date, int boardP_hit, char boardP_active,
			String boardP_fileName, String boardP_filePath, long boardP_fileSize, Timestamp boardP_uploadTime) {
		super();
		this.boardP_no = boardP_no;
		this.boardP_writer = boardP_writer;
		this.boardP_category = boardP_category;
		this.boardP_title = boardP_title;
		this.boardP_artist = boardP_artist;
		this.boardP_contents = boardP_contents;
		this.boardP_location = boardP_location;
		this.boardP_date = boardP_date;
		this.boardP_hit = boardP_hit;
		this.boardP_active = boardP_active;
		this.boardP_fileName = boardP_fileName;
		this.boardP_filePath = boardP_filePath;
		this.boardP_fileSize = boardP_fileSize;
		this.boardP_uploadTime = boardP_uploadTime;
	}
	public Promo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
