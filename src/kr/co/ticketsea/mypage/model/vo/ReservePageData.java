package kr.co.ticketsea.mypage.model.vo;

import java.util.ArrayList;

// mypage 관련 페이징 data
public class ReservePageData {
	private ArrayList<ReserveList> list;
	private ArrayList<PopupReserveList> pList;
	private String pageNavi;
	private ArrayList<ReviewMgr> reviewList;
	private ArrayList<QnaMgr> qnaList;
	public ReservePageData(ArrayList<ReserveList> list, ArrayList<PopupReserveList> pList, String pageNavi,
			ArrayList<ReviewMgr> reviewList, ArrayList<QnaMgr> qnaList) {
		super();
		this.list = list;
		this.pList = pList;
		this.pageNavi = pageNavi;
		this.reviewList = reviewList;
		this.qnaList = qnaList;
	}
	public ReservePageData() {
		super();
		// TODO Auto-generated constructor stub
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
	public ArrayList<ReviewMgr> getReviewList() {
		return reviewList;
	}
	public void setReviewList(ArrayList<ReviewMgr> reviewList) {
		this.reviewList = reviewList;
	}
	public ArrayList<QnaMgr> getQnaList() {
		return qnaList;
	}
	public void setQnaList(ArrayList<QnaMgr> qnaList) {
		this.qnaList = qnaList;
	}
	
		
}