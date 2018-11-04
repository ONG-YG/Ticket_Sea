package kr.co.ticketsea.admin.reserve.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.co.ticketsea.admin.reserve.model.dao.AdReserveDao;
import kr.co.ticketsea.admin.reserve.model.vo.Reserve;
import kr.co.ticketsea.admin.reserve.model.vo.ReservePageData;
import kr.co.ticketsea.common.JDBCTemplate;

public class AdReserveService {

	public ReservePageData reserveAllList(int currentPage) {
		Connection conn = JDBCTemplate.getConnection();
		
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;
		
		ArrayList<Reserve> list = new AdReserveDao().getcurrentPage(conn, currentPage, recordCountPerPage);
		return null;
	}

}
