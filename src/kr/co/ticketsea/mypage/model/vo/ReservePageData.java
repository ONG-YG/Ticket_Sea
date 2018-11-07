package kr.co.ticketsea.mypage.model.vo;

import java.util.ArrayList;

public class ReservePageData {
	private ArrayList<ReserveList> list;
	private ArrayList<PopupReserveList> pList;
	private String pageNavi;
	public ReservePageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReservePageData(ArrayList<ReserveList> list, ArrayList<PopupReserveList> pList, String pageNavi) {
		super();
		this.list = list;
		this.pList = pList;
		this.pageNavi = pageNavi;
	}
	public ArrayList<ReserveList> getList() {
		return list;
	}
	public void setList(ArrayList<ReserveList> list) {
		this.list = list;
	}
	public ArrayList<PopupReserveList> getpList() {
		return pList;
	}
	public void setpList(ArrayList<PopupReserveList> pList) {
		this.pList = pList;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
	
	
}