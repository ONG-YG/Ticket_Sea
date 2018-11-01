package kr.co.ticketsea.admin.show.model.vo;

import java.util.ArrayList;

public class MiniPgData {
	private ArrayList<MiniShow> list;
	private String pageNavi;
	public MiniPgData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MiniPgData(ArrayList<MiniShow> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}
	public ArrayList<MiniShow> getList() {
		return list;
	}
	public void setList(ArrayList<MiniShow> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
	
}
