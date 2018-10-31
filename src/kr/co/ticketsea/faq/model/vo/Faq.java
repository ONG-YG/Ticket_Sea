package kr.co.ticketsea.faq.model.vo;

public class Faq {
	private int boardF_no;
	private String boardF_category;
	private String boardF_title;
	private String boardF_contents;
	
	public int getBoardF_no() {
		return boardF_no;
	}
	public void setBoardF_no(int boardF_no) {
		this.boardF_no = boardF_no;
	}
	public String getBoardF_category() {
		return boardF_category;
	}
	public void setBoardF_category(String boardF_category) {
		this.boardF_category = boardF_category;
	}
	public String getBoardF_title() {
		return boardF_title;
	}
	public void setBoardF_title(String boardF_title) {
		this.boardF_title = boardF_title;
	}
	public String getBoardF_contents() {
		return boardF_contents;
	}
	public void setBoardF_contents(String boardF_contents) {
		this.boardF_contents = boardF_contents;
	}
	public Faq(int boardF_no, String boardF_category, String boardF_title, String boardF_contents) {
		super();
		this.boardF_no = boardF_no;
		this.boardF_category = boardF_category;
		this.boardF_title = boardF_title;
		this.boardF_contents = boardF_contents;
	}
	public Faq() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
