package kr.co.ticketsea.faq.model.vo;

import java.util.ArrayList;

import kr.co.ticketsea.faq.model.vo.Faq;
import kr.co.ticketsea.qna.model.vo.Qna;

public class PageData {
	private ArrayList<Faq> list;
	private String pageNavi;
	
	public PageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PageData(ArrayList<Faq> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}
	
	public ArrayList<Faq> getList() {
		return list;
	}
	
	public void setList(ArrayList<Faq> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
	
}
