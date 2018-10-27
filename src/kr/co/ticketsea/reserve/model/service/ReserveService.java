package kr.co.ticketsea.reserve.model.service;

import java.sql.Connection;

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
}
