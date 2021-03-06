package kr.co.ticketsea.admin.show.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.co.ticketsea.admin.show.model.dao.MiniShowDao;
import kr.co.ticketsea.admin.show.model.vo.MiniPgData;
import kr.co.ticketsea.admin.show.model.vo.MiniShow;
import kr.co.ticketsea.common.JDBCTemplate;

public class MiniShowService {

	public MiniPgData showWaitList(int currentPage) {
		Connection conn = JDBCTemplate.getConnection();
		
		// 2개의 값을 저장하는 변수 생성 (게시물의 개수, navi의 개수)
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;
		
		//service에서 DAO를 호출 (2번의 DAO를 호출)
		
		//1. 현재 페이지의 게시물 리스트 요청
		ArrayList<MiniShow> list = new MiniShowDao().getcurrentPage(conn, currentPage, recordCountPerPage);
		
		
		//2. 현재 페이지를 중심으로 만들어지는 navi 리스트 요청
		String pageNavi = new MiniShowDao().getPageNavi(conn,currentPage,recordCountPerPage,naviCountPerPage);
		
		MiniPgData mpd = null;
		
		if(!list.isEmpty() && !pageNavi.isEmpty()) {
			mpd= new MiniPgData();
			mpd.setList(list);
			mpd.setPageNavi(pageNavi);
		}
		JDBCTemplate.close(conn);
		
		
		return mpd;
		
	}
	
	//승인된 소규모 공연 테이블 리스트
	public MiniPgData showApproveList(int currentPage) {
		Connection conn = JDBCTemplate.getConnection();
		
		// 2개의 값을 저장하는 변수 생성 (게시물의 개수, navi의 개수)
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;
		
		//service에서 DAO를 호출 (2번의 DAO를 호출)
		
		//1. 현재 페이지의 게시물 리스트 요청
		ArrayList<MiniShow> list = new MiniShowDao().getApcurrentPage(conn, currentPage, recordCountPerPage);
		
		
		//2. 현재 페이지를 중심으로 만들어지는 navi 리스트 요청
		String pageNavi = new MiniShowDao().getApPageNavi(conn,currentPage,recordCountPerPage,naviCountPerPage);
		
		MiniPgData mpd = null;
		
		if(!list.isEmpty() && !pageNavi.isEmpty()) {
			mpd= new MiniPgData();
			mpd.setList(list);
			mpd.setPageNavi(pageNavi);
		}
		JDBCTemplate.close(conn);
		
		
		return mpd;
	}

	public MiniShow selectApShow(int msNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		MiniShow ms= new MiniShowDao().selectApShow(conn,msNo);
		
		JDBCTemplate.close(conn);
		
		return ms;
		
	}

	//소규모 공연 승인하는 로직
	public int miniShowApprove(int msNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result=new MiniShowDao().miniShowApprove(conn,msNo);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int updateApMiniShow(MiniShow ms) {
		Connection conn = JDBCTemplate.getConnection();
		int result=new MiniShowDao().updateApMiniShow(conn,ms);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public MiniShow selectWtShow(int msNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		MiniShow ms= new MiniShowDao().selectWtShow(conn,msNo);
		
		JDBCTemplate.close(conn);
		
		return ms;
	}

	public int refuseMiniShow(int msNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MiniShowDao().refuseMiniShow(conn,msNo);
		JDBCTemplate.close(conn);
		
		return result;
	}


}
