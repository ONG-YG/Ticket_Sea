package kr.or.ticketsea.admin.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.jsp.tagext.PageData;

import kr.or.iei.common.JDBCTemplate;
import kr.or.ticketsea.admin.model.dao.ShowDao;
import kr.or.ticketsea.admin.model.vo.Show;

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
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;
		
		ArrayList<Show> list = new ShowDao().getcurrentPage(conn, currentPage, recordCountPerPage);
		return null;
	}
	

}
