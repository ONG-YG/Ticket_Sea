package kr.co.ticketsea.mypage.model.vo;

public class PromoMgr {
	private String num;
	private int boardP_no;
	private String boardP_writer;
	private String boardP_title;
	private String boardP_category;
	private String boardP_active;
	public PromoMgr(String num, int boardP_no, String boardP_writer, String boardP_title, String boardP_category,
			String boardP_active) {
		super();
		this.num = num;
		this.boardP_no = boardP_no;
		this.boardP_writer = boardP_writer;
		this.boardP_title = boardP_title;
		this.boardP_category = boardP_category;
		this.boardP_active = boardP_active;
	}
	public PromoMgr() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
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
	public String getBoardP_title() {
		return boardP_title;
	}
	public void setBoardP_title(String boardP_title) {
		this.boardP_title = boardP_title;
	}
	public String getBoardP_category() {
		return boardP_category;
	}
	public void setBoardP_category(String boardP_category) {
		this.boardP_category = boardP_category;
	}
	public String getBoardP_active() {
		return boardP_active;
	}
	public void setBoardP_active(String boardP_active) {
		this.boardP_active = boardP_active;
	}
	
	
}
