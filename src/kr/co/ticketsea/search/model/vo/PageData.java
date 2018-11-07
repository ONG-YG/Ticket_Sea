package kr.co.ticketsea.search.model.vo;

import java.util.ArrayList;

public class PageData {

	private ArrayList<Search> list;
	private String pageNavi;
	public PageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PageData(ArrayList<Search> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}
	public ArrayList<Search> getList() {
		return list;
	}
	public void setList(ArrayList<Search> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
	
	
}
