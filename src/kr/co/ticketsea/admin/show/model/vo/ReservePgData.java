package kr.co.ticketsea.admin.show.model.vo;
import java.util.ArrayList;

import kr.co.ticketsea.admin.reserve.model.vo.Reserve;
public class ReservePgData {
	private ArrayList<Reserve> list;
	private String pageNavi;
	public ReservePgData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReservePgData(ArrayList<Reserve> list, String pageNavi) {
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
