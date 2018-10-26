package kr.co.ticketsea.admin.show.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.jsp.tagext.PageData;

import kr.co.ticketsea.admin.show.model.dao.ShowDao;
import kr.co.ticketsea.admin.show.model.vo.Show;
import kr.co.ticketsea.common.JDBCTemplate;

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
