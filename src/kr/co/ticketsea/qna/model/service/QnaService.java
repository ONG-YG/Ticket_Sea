package kr.co.ticketsea.qna.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.co.ticketsea.common.JDBCTemplate;
import kr.co.ticketsea.faq.model.dao.FaqDao;
import kr.co.ticketsea.qna.model.dao.QnaDao;
import kr.co.ticketsea.qna.model.vo.*;
import kr.co.ticketsea.qna.model.vo.PageData;
import kr.co.ticketsea.qna.model.dao.QnaDao;
import kr.co.ticketsea.qna.model.vo.Qna;

public class QnaService {

	public PageData qnaAllList(int currentPage) {
		Connection conn = JDBCTemplate.getConnection();
		
		// 2개값을 저장하는 변수 생성 (게시물의 개수, navi의 개수)	
		int recordCountPerPage = 10; //게시물의 개수
		int naviCountPerPage = 5; //navi의 개수
		
		
		// Service에서 DAO를 호출 (2번의 DAO를 호출)
		// 1. 현재 페이지의 게시물 리스트 요청
		// 2. 현재 페이지를 중심으로 만들어지는 navi 리스트 요청
		
		
		ArrayList<Qna> list = new QnaDao().getCurrentPage(conn,currentPage,recordCountPerPage);
		String pageNavi = new QnaDao().getPageNavi(conn,currentPage,recordCountPerPage,naviCountPerPage);
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

	public Qna selectOneQna(int boardQ_no) {
		//하나의 공지사항을 읽으면 공지사항의 내용과 댓글을 가져와야 함
		
		
		Connection conn = JDBCTemplate.getConnection();
		
		//공지사항 가져오는 메소드
		Qna qna = new QnaDao().selectOneQna(conn,boardQ_no);
		
		
		//리턴을 하기 위하여 공지사항과 댓글을 저장하는 VO를 생성하여 저장
		
		if(qna != null) {
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return qna;
		
		
	}

	public int deleteQna(int boardQ_no) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new QnaDao().deleteQna(conn,boardQ_no);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
		
	}

	public int insertQna(String title, String contents, String userId) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new QnaDao().insertQna(conn,title,contents,userId);
		
		if(result>0)
		{
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
			
		return result;
	}

}
