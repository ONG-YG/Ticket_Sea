package kr.co.ticketsea.show.model.service;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;

import kr.co.ticketsea.common.JDBCTemplate;
import kr.co.ticketsea.show.model.vo.Comment;
import kr.co.ticketsea.show.model.dao.ShowDao;
import kr.co.ticketsea.show.model.vo.PageData;
import kr.co.ticketsea.show.model.vo.ShowData;
import kr.co.ticketsea.admin.show.model.vo.Show;

public class ShowService {

	public PageData showAllList(int currentPage) {
		
		Connection conn = JDBCTemplate.getConnection();
	
	// 2개값을 저장하는 변수 생성 (게시물의 개수, navi의 개수)	
	int recordCountPerPage = 12; //게시물의 개수
	int naviCountPerPage = 5; //navi의 개수
	
	
	// Service에서 DAO를 호출 (2번의 DAO를 호출)
	// 1. 현재 페이지의 게시물 리스트 요청
	// 2. 현재 페이지를 중심으로 만들어지는 navi 리스트 요청
	
	
	ArrayList<Show> list = new ShowDao().getCurrentPage(conn,currentPage,recordCountPerPage);
	String pageNavi = new ShowDao().getPageNavi(conn,currentPage,recordCountPerPage,naviCountPerPage);
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

	public ShowData selectOneShow(int show_no) {
		//하나의 공지사항을 읽으면 공지사항의 내용과 댓글을 가져와야 함
		
		
		Connection conn = JDBCTemplate.getConnection();
		
		//공지사항 가져오는 메소드
		Show show = new ShowDao().selectOneShow(conn,show_no);
		ArrayList<Comment> list = new ShowDao().selectComments(conn,show_no);
		
		//리턴을 하기 위하여 공지사항과 댓글을 저장하는 VO를 생성하여 저장
		ShowData sd = null;
		if(show!=null) {
			sd = new ShowData();
			sd.setList(list);
			sd.setShow(show);
			}
		
		JDBCTemplate.close(conn);
		
		return sd;
		
		
	}

	public int insertComment(int showNo, String contents, String userId) {
		
		Connection conn = JDBCTemplate.getConnection();
		int result = new ShowDao().insertComment(conn,showNo,contents,userId);
		
		if(result>0)
		{
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int deleteComment(int m_show_no) {
		
		Connection conn = JDBCTemplate.getConnection();
		int result = new ShowDao().deleteComment(conn,m_show_no);
		
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
