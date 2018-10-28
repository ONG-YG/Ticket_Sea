package kr.co.ticketsea.reserve.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.co.ticketsea.admin.reserve.model.vo.Reserve;
import kr.co.ticketsea.admin.reserve.model.vo.ReservePageData;
import kr.co.ticketsea.admin.show.model.dao.ShowDao;
import kr.co.ticketsea.admin.show.model.vo.Show;
import kr.co.ticketsea.common.JDBCTemplate;
import kr.co.ticketsea.reserve.model.dao.ReserveDao;

public class ReserveService {
	
	public int dbTest(String temp) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ReserveDao().dbTest(conn, temp);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public ReservePageData reserveAllList(int currentPage) {
		Connection conn=JDBCTemplate.getConnection();
		
		// 2개의 값을 저장하는 변수 생성 (게시물의 개수, navi의 개수)
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;
		
		//service에서 DAO를 호출 (2번의 DAO를 호출)
		
		//1. 현재 페이지의 게시물 리스트 요청
		ArrayList<Reserve> list = new ReserveDao().getcurrentPage(conn, currentPage, recordCountPerPage);
		
			
		return null;
	}
}
