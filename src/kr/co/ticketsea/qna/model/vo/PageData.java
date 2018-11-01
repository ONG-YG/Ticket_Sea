package kr.co.ticketsea.qna.model.vo;

import java.util.ArrayList;

public class PageData {
	private ArrayList<Qna> list;
	private String pageNavi;
	
	public PageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PageData(ArrayList<Qna> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}
	
	public ArrayList<Qna> getList() {
		return list;
	}
	public void setList(ArrayList<Qna> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
	
}
