package kr.co.ticketsea.promo.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.co.ticketsea.common.JDBCTemplate;
import kr.co.ticketsea.notice.model.dao.NoticeDao;
import kr.co.ticketsea.notice.model.vo.Notice;
import kr.co.ticketsea.promo.model.dao.PromoDao;
import kr.co.ticketsea.promo.model.vo.Promo;
import kr.co.ticketsea.promo.model.vo.PromoData;
import kr.co.ticketsea.qna.model.dao.QnaDao;
import kr.co.ticketsea.promo.model.vo.*;

public class PromoService {

	public PageData promoAllList(int currentPage) {
		
		Connection conn = JDBCTemplate.getConnection();
	
	// 2개값을 저장하는 변수 생성 (게시물의 개수, navi의 개수)	
	int recordCountPerPage = 12; //게시물의 개수
	int naviCountPerPage = 5; //navi의 개수
	
	
	// Service에서 DAO를 호출 (2번의 DAO를 호출)
	// 1. 현재 페이지의 게시물 리스트 요청
	// 2. 현재 페이지를 중심으로 만들어지는 navi 리스트 요청
	
	
	ArrayList<Promo> list = new PromoDao().getCurrentPage(conn,currentPage,recordCountPerPage);
	String pageNavi = new PromoDao().getPageNavi(conn,currentPage,recordCountPerPage,naviCountPerPage);
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

	public PromoData selectOnePromo(int boardP_no) {
		//하나의 공지사항을 읽으면 공지사항의 내용과 댓글을 가져와야 함
		
		
		Connection conn = JDBCTemplate.getConnection();
		
		//공지사항 가져오는 메소드
		Promo promo = new PromoDao().selectOnePromo(conn,boardP_no);
		ArrayList<Comment> list = new PromoDao().selectComments(conn,boardP_no);
		
		//리턴을 하기 위하여 공지사항과 댓글을 저장하는 VO를 생성하여 저장
		PromoData pd = null;
		if(promo!=null) {
			pd = new PromoData();
			pd.setList(list);
			pd.setPromo(promo);
			}
		
		JDBCTemplate.close(conn);
		
		return pd;
		
		
	}

	public int insertPromo(String title, String category, String contents, String artist, String location, String userId) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new PromoDao().insertPromo(conn,title,category,contents,artist,location,userId);
		
		if(result>0)
		{
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
			
		return result;
	}

	public int insertComment(int promoNo, String contents, String userId) {
		
		Connection conn = JDBCTemplate.getConnection();
		int result = new PromoDao().insertComment(conn,promoNo,contents,userId);
		
		if(result>0)
		{
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int deletePromo(int boardP_no, String userId) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new PromoDao().deletePromo(conn,boardP_no,userId);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
		
	}

}
