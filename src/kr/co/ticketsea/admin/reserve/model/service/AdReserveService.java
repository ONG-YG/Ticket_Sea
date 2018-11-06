package kr.co.ticketsea.admin.reserve.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.co.ticketsea.admin.reserve.model.dao.AdReserveDao;
import kr.co.ticketsea.admin.reserve.model.vo.Reserve;
import kr.co.ticketsea.admin.reserve.model.vo.ReservePageData;
import kr.co.ticketsea.common.JDBCTemplate;

public class AdReserveService {
	
	public ReservePageData reserveAllList(int currentPage) {
		Connection conn=JDBCTemplate.getConnection();
		
		// 2개의 값을 저장하는 변수 생성 (게시물의 개수, navi의 개수)
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;
		
		//1. 현재 페이지의 게시물 리스트 요청
		ArrayList<Reserve> list = new AdReserveDao().getcurrentPage(conn, currentPage, recordCountPerPage);
		
		//2. 현재 페이지를 중심으로 만들어지는 navi 리스트 요청
		
		String pageNavi = new AdReserveDao().getPageNavi(conn,currentPage,recordCountPerPage,naviCountPerPage);
		
		ReservePageData rpg = null;
		
		if(!list.isEmpty() && !pageNavi.isEmpty()) {
			rpg=new ReservePageData();
			rpg.setList(list);
			rpg.setPageNavi(pageNavi);
		}
		JDBCTemplate.close(conn);
		
		return rpg;
	}

	

}
