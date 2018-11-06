package kr.co.ticketsea.admin.show.model.service;

import java.sql.Connection;
import java.util.ArrayList;


import kr.co.ticketsea.admin.show.model.dao.ShowDao;
import kr.co.ticketsea.common.JDBCTemplate;
import kr.co.ticketsea.admin.show.model.vo.*;

public class ShowService {

	public int insertShow(Show s) {
		Connection conn=JDBCTemplate.getConnection();
		int result = new ShowDao().insertShow(conn,s);

		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}

	public PageData showAllList(int currentPage) {
		
		Connection conn=JDBCTemplate.getConnection();
		
		// 2개의 값을 저장하는 변수 생성 (게시물의 개수, navi의 개수)
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;
		
		//service에서 DAO를 호출 (2번의 DAO를 호출)
		
		//1. 현재 페이지의 게시물 리스트 요청
		ArrayList<Show> list = new ShowDao().getcurrentPage(conn, currentPage, recordCountPerPage);
		
		for(Show s:list) {
			System.out.println("게시물개수"+s.getM_show_no());
		}
		//2. 현재 페이지를 중심으로 만들어지는 navi 리스트 요청
		String pageNavi = new ShowDao().getPageNavi(conn,currentPage,recordCountPerPage,naviCountPerPage);
		System.out.println("navi개수"+pageNavi);
		PageData pd =null;
		
		if(!list.isEmpty() && !pageNavi.isEmpty()) {
			pd= new PageData();
			pd.setList(list);
			pd.setPageNavi(pageNavi);
		}
		JDBCTemplate.close(conn);
		
		return pd;
	}

	public Show selectOneShow(int showNo) {
		//하나의 공연 정보를 가져와야함
		
		Connection conn=JDBCTemplate.getConnection();
		
		//공연정보 가져오는 메소드
		Show show= new ShowDao().selectOneShow(conn,showNo);
		
		JDBCTemplate.close(conn);
		
		return show;
	}
	
	//장소정보 가져오는 리스트
	
	public ArrayList<ShowPlace> showPlaceList() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<ShowPlace> list =  new ShowDao().showPlaceList(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	public ArrayList<ShowCategory> showCategoryList() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<ShowCategory> list = new ShowDao().showCategoryList(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	public int deleteShow(int showNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ShowDao().deleteShow(conn,showNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}
	


	

}
