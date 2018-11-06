package kr.co.ticketsea.show.model.vo;

import java.util.ArrayList;

public class PageData {
	private ArrayList<Show> list;
	private String pageNavi;
	
	public PageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PageData(ArrayList<Show> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}
	
	public ArrayList<Show> getList() {
		return list;
	}
	public void setList(ArrayList<Show> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
	
}
