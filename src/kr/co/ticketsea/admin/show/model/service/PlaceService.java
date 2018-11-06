package kr.co.ticketsea.admin.show.model.service;

import java.sql.Connection;

import kr.co.ticketsea.admin.show.model.dao.PlaceDao;
import kr.co.ticketsea.common.JDBCTemplate;

public class PlaceService {

	public int insertPlace(String th_name, String th_lct) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		
		int result=new PlaceDao().insertPlace(conn,th_name, th_lct);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}

}
