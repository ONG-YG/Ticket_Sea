package kr.co.ticketsea.admin.member.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.co.ticketsea.member.model.vo.*;
import kr.co.ticketsea.admin.member.model.dao.AdMemberDao;
import kr.co.ticketsea.admin.member.model.vo.*;
import kr.co.ticketsea.common.JDBCTemplate;

public class AdMemberService {

	public MemberPageData memberAllList(int currentPage) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		//게시물 개수, navi 개수의 값을 저장하기 위한 변수 생성
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;
		
		//1. 현재 페이지의 게시물 리스트 요청
		ArrayList<Member> list = new AdMemberDao().getCurrentPage(conn,currentPage,recordCountPerPage);
		
		String pageNavi = new AdMemberDao().getPageNavi(conn,currentPage,recordCountPerPage,naviCountPerPage);
	
		MemberPageData pd =null;
		
		if(!list.isEmpty() && !pageNavi.isEmpty()) {
			pd= new MemberPageData();
			pd.setList(list);
			pd.setPageNavi(pageNavi);
		}
		JDBCTemplate.close(conn);
		
		return pd;
	}

	public Member selectOneMember(int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		Member m = new AdMemberDao().selectOneMember(conn,memberNo);
		
		JDBCTemplate.close(conn);
		return m;
		
	}

	public int updateMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		int result=new AdMemberDao().updateMember(conn, m);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
		
	}
	public int deleteMember(int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new AdMemberDao().deleteMember(conn,memberNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}

	public MemberPageData searchList(String keyword, int currentPage) {
		
		Connection conn= JDBCTemplate.getConnection();
		
		// 2개의 값을 저장하는 변수 생성 (게시물의 개수, navi의 개수)
		
		int recordCountPerPage  = 10; //게시물의 개수
		int naviCountPerPage = 5; //navi의 개수
		
		//service에서 DAO를 호출 (2번의 DAO를 호출)
		
		//1. 현재 페이지의 게시물 리스트 요청
		ArrayList<Member> list=new AdMemberDao().getSearchCurrentPage(conn,currentPage,recordCountPerPage,keyword);
		
		//2. 현재 페이지를 중심으로 만들어지는 navi 리스트 요청
		String pageNavi = new AdMemberDao().getSearchPageNavi(conn,currentPage,recordCountPerPage,naviCountPerPage,keyword);
		
		MemberPageData mpd =null;
		
		if(!list.isEmpty() && !pageNavi.isEmpty()) {
			mpd= new MemberPageData();
			mpd.setList(list);
			mpd.setPageNavi(pageNavi);
		}
		JDBCTemplate.close(conn);
		
		return mpd;
	}


}
