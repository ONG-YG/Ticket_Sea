package kr.co.ticketsea.admin.reserve.model.vo;

import java.util.ArrayList;



public class ReservePageData {
	private ArrayList<Reserve> list;
	private String pageNavi;
	
	
	public ReservePageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReservePageData(ArrayList<Reserve> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}
	public ArrayList<Reserve> getList() {
		return list;
	}
	public void setList(ArrayList<Reserve> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
	
	
}
