package kr.co.ticketsea.reserve.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.co.ticketsea.common.JDBCTemplate;
import kr.co.ticketsea.reserve.model.dao.ReserveDao;
import kr.co.ticketsea.reserve.model.vo.PerformSchedule;

public class ReserveService {
	
	public int dbTest() {
		Connection conn = JDBCTemplate.getConnection();
		int result = new ReserveDao().dbTest(conn);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public ArrayList<PerformSchedule> selectAllPerformSchedule(int showNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<PerformSchedule> psList = new ReserveDao().selectAllPerformSchedule(conn, showNo);		
		
		JDBCTemplate.close(conn);		
		
		return psList;
	}

	public int selectOnePerformSchedule(int showNo, String date_sel, String cnt_sel) {
		Connection conn = JDBCTemplate.getConnection();
		
		int performSchNo = new ReserveDao().selectOnePerformSchedule(conn, showNo, date_sel, cnt_sel);
		
		JDBCTemplate.close(conn);
		
		return performSchNo;
	}

	public ArrayList<Integer> selectReservedSeats(int performSchNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Integer> reserved_seats = new ReserveDao().selectReservedSeats(conn, performSchNo);
		
		JDBCTemplate.close(conn);
		
		return reserved_seats;
	}
}
