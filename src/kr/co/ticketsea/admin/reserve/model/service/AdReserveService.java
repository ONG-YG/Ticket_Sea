package kr.co.ticketsea.admin.reserve.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.co.ticketsea.admin.reserve.model.dao.AdReserveDao;
import kr.co.ticketsea.admin.reserve.model.vo.Reserve;
import kr.co.ticketsea.admin.reserve.model.vo.ReserveApInfo;
import kr.co.ticketsea.admin.reserve.model.vo.ReservePageData;
import kr.co.ticketsea.common.JDBCTemplate;
import kr.co.ticketsea.reserve.model.vo.SelectedSeat;

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

	public ReserveApInfo selectOneReserve(String reserveNo) {
		Connection conn=JDBCTemplate.getConnection();
		
		ReserveApInfo rs=new AdReserveDao().selectOneReserve(conn,reserveNo);
		
		System.out.println(rs.getSeatInfo());
		JDBCTemplate.close(conn);
		
		return rs;
		
	}

	public ArrayList<SelectedSeat> reserveSeat(String reserveNo) {
		Connection conn=JDBCTemplate.getConnection();
		ArrayList<SelectedSeat> seatList=new AdReserveDao().seatList(conn,reserveNo);
		JDBCTemplate.close(conn);
		return seatList;
	}

	public int reserveUpdate(String bk_no,String phone, String email) {
		Connection conn=JDBCTemplate.getConnection();
		int result=new AdReserveDao().reserveUpdate(conn,bk_no,phone,email);
		
		if(result>0) {
			JDBCTemplate.commit(conn);;
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int deleteReserve(String bkNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new AdReserveDao().deleteReserve(conn,bkNo);
		if(result>0) {
			JDBCTemplate.commit(conn);;
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	

}
