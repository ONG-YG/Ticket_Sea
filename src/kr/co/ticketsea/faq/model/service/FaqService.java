package kr.co.ticketsea.faq.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.co.ticketsea.common.JDBCTemplate;
import kr.co.ticketsea.faq.model.dao.FaqDao;
import kr.co.ticketsea.faq.model.vo.Faq;
import kr.co.ticketsea.faq.model.vo.PageData;

public class FaqService {
	
	public PageData faqAllList(int currentPage) {
		Connection conn = JDBCTemplate.getConnection();
		
		// 2개값을 저장하는 변수 생성 (게시물의 개수, navi의 개수)	
		int recordCountPerPage = 10; //게시물의 개수
		int naviCountPerPage = 5; //navi의 개수
		
		
		// Service에서 DAO를 호출 (2번의 DAO를 호출)
		// 1. 현재 페이지의 게시물 리스트 요청
		// 2. 현재 페이지를 중심으로 만들어지는 navi 리스트 요청
		
		
		ArrayList<Faq> list = new FaqDao().getCurrentPage(conn,currentPage,recordCountPerPage);
		String pageNavi = new FaqDao().getPageNavi(conn,currentPage,recordCountPerPage,naviCountPerPage);
		PageData pd = null;
		
		
		if(!list.isEmpty() && !pageNavi.isEmpty())
		{
			pd = new PageData();
			pd.setList(list);
			pd.setPageNavi(pageNavi);
		}
		
		JDBCTemplate.close(conn);
		
		return pd;
			
		
	}

	public Faq selectOneFaq(int boardF_no) {
		//하나의 공지사항을 읽으면 공지사항의 내용과 댓글을 가져와야 함
		
		
		Connection conn = JDBCTemplate.getConnection();
		
		//공지사항 가져오는 메소드
		Faq faq = new FaqDao().selectOneFaq(conn,boardF_no);
		
		
		//리턴을 하기 위하여 공지사항과 댓글을 저장하는 VO를 생성하여 저장
		
		if(faq != null) {
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return faq;
		
		
	}

	public int deleteFaq(int boardF_no) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new FaqDao().deleteFaq(conn,boardF_no);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
		
	}

	public int faqUpdate(int boardF_no, String boardF_title, String boardF_contents, String userId) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new FaqDao().faqUpdate(conn,boardF_no,boardF_title,boardF_contents,userId);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int insertFaq(String category, String title, String contents) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new FaqDao().insertFaq(conn,category,title,contents);
		
		if(result>0)
		{
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
			
		return result;
	}

	public PageData searchList(String keyword, int currentPage) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		// 2개값을 저장하는 변수 생성 (게시물의 개수, navi의 개수)	
		int recordCountPerPage = 10; //게시물의 개수
		int naviCountPerPage = 5; //navi의 개수
		
		
		// Service에서 DAO를 호출 (2번의 DAO를 호출)
		// 1. 현재 페이지의 게시물 리스트 요청
		// 2. 현재 페이지를 중심으로 만들어지는 navi 리스트 요청
		
		
		ArrayList<Faq> list = new FaqDao().getSearchCurrentPage(conn,currentPage,recordCountPerPage,keyword);
		String pageNavi = new FaqDao().getSearchPageNavi(conn,currentPage,recordCountPerPage,naviCountPerPage,keyword);
		PageData pd = null;
		
		
		if(!list.isEmpty() && !pageNavi.isEmpty())
		{
			pd = new PageData();
			pd.setList(list);
			pd.setPageNavi(pageNavi);
		}
		
		JDBCTemplate.close(conn);
		
		return pd;
		
	}

}
