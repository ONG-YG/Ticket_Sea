package kr.co.ticketsea.admin.member.model.vo;

import java.util.ArrayList;

import kr.co.ticketsea.member.model.vo.*;

public class MemberPageData {

	private ArrayList<Member> list;
	private String pageNavi;
	
	
	public MemberPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberPageData(ArrayList<Member> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}
	public ArrayList<Member> getList() {
		return list;
	}
	public void setList(ArrayList<Member> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
}
