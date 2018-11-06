package kr.co.ticketsea.mypage.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.co.ticketsea.common.JDBCTemplate;
import kr.co.ticketsea.member.model.vo.Member;
import kr.co.ticketsea.mypage.model.dao.MypageDao;
import kr.co.ticketsea.mypage.model.vo.PopupReserveList;
import kr.co.ticketsea.mypage.model.vo.ReserveList;
import kr.co.ticketsea.mypage.model.vo.ReservePageData;


public class MypageService {

	
	// [예매확인.취소] 페이지의 페이징처리 포함 서비스 메소드
	public ReservePageData reserveAllList(int currentPage, int memberNo) {
		
		Connection conn = JDBCTemplate.getConnection();
			
		int recordCountPerPage = 5; //게시물의 개수
		int naviCountPerPage = 5; //navi의 개수
		
		
		// Service에서 DAO를 호출 (2번의 DAO를 호출)
		// 1. 현재 페이지의 게시물 리스트 요청
		ArrayList<ReserveList> list = new MypageDao().getCurrentPage(conn,currentPage,recordCountPerPage,memberNo);
		
		// 2. 현재 페이지를 중심으로 만들어지는 navi 리스트 요청
		String pageNavi = new MypageDao().getPageNavi(conn,currentPage,recordCountPerPage,naviCountPerPage,memberNo);
		
		// 메소드에 담아서 넣기
		ReservePageData pd = null;
		
		if(!list.isEmpty() && !pageNavi.isEmpty())
		{
			pd = new ReservePageData();
			pd.setList(list);
			pd.setPageNavi(pageNavi);
		}
		
		JDBCTemplate.close(conn);
		
		return pd;		
		
	}

	public Member memberUpdate(int memberNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		// Dao(select문 : member_no 로 id,phone,addr,email을 담은 객체를 가져옴)
		Member updateList = new MypageDao().memberUpdate(conn, memberNo);
		
		JDBCTemplate.close(conn);
		
		return updateList;
		
	}

	public int updateMemberConfirm(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		int result=new MypageDao().updateMemberConfirm(conn, m);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
		
	}

	public ReservePageData popupReserveList(int currentPage, String bkNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int recordCountPerPage = 5; //게시물의 개수
		int naviCountPerPage = 5; //navi의 개수
				
		// Service에서 DAO를 호출 (2번의 DAO를 호출)
		// 1. 현재 페이지의 게시물 리스트 요청
		ArrayList<PopupReserveList> pList = new MypageDao().popupReserveList(conn,currentPage,recordCountPerPage,bkNo);
		
		// 2. 현재 페이지를 중심으로 만들어지는 navi 리스트 요청
		String pageNavi = new MypageDao().getPopupPageNavi(conn,currentPage,recordCountPerPage,naviCountPerPage,bkNo);
		
		// 메소드에 담아서 넣기
		ReservePageData pd = null;
		
		
		
		if(!pList.isEmpty() && !pageNavi.isEmpty())
		{
			pd = new ReservePageData();
			pd.setpList(pList);
			pd.setPageNavi(pageNavi);
		}
		
		JDBCTemplate.close(conn);
		
		return pd;	
	}

}
