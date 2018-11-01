package kr.co.ticketsea.notice.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.co.ticketsea.common.JDBCTemplate;
import kr.co.ticketsea.notice.model.dao.NoticeDao;
import kr.co.ticketsea.notice.model.vo.Notice;
import kr.co.ticketsea.notice.model.vo.PageData;

public class NoticeService {
	
public PageData noticeAllList(int currentPage) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		// 2개값을 저장하는 변수 생성 (게시물의 개수, navi의 개수)	
		int recordCountPerPage = 10; //게시물의 개수
		int naviCountPerPage = 5; //navi의 개수
		
		
		// Service에서 DAO를 호출 (2번의 DAO를 호출)
		// 1. 현재 페이지의 게시물 리스트 요청
		// 2. 현재 페이지를 중심으로 만들어지는 navi 리스트 요청
		
		
		ArrayList<Notice> list = new NoticeDao().getCurrentPage(conn,currentPage,recordCountPerPage);
		String pageNavi = new NoticeDao().getPageNavi(conn,currentPage,recordCountPerPage,naviCountPerPage);
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
	

	public int insertNotice(String category, String title, String contents) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new NoticeDao().insertNotice(conn,category,title,contents);
		
		if(result>0)
		{
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
			
		return result;
	}

	public Notice selectOneNotice(int boardN_no) {
		//하나의 공지사항을 읽으면 공지사항의 내용과 댓글을 가져와야 함
		
		
		Connection conn = JDBCTemplate.getConnection();
		
		//공지사항 가져오는 메소드
		Notice notice = new NoticeDao().selectOneNotice(conn,boardN_no);
		
		
		//리턴을 하기 위하여 공지사항과 댓글을 저장하는 VO를 생성하여 저장
		
		if(notice != null) {
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return notice;
		
		
	}

	public int deleteNotice(int boardN_no) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new NoticeDao().deleteNotice(conn,boardN_no);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
		
	}

	public int noticeUpdate(int boardN_no, String boardN_title, String boardN_contents) {
Connection conn = JDBCTemplate.getConnection();
		
		int result = new NoticeDao().noticeUpdate(conn,boardN_no,boardN_title,boardN_contents);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}
}
