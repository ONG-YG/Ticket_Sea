package kr.co.ticketsea.mypage.model.vo;

import java.util.ArrayList;

public class ReservePageData {
	private ArrayList<ReserveList> list;
	private String pageNavi;
	public ReservePageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReservePageData(ArrayList<ReserveList> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}
	public ArrayList<ReserveList> getList() {
		return list;
	}
	public void setList(ArrayList<ReserveList> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
	
	
	
}
