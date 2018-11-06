package kr.co.ticketsea.admin.show.model.service;

import java.sql.Connection;

import kr.co.ticketsea.admin.show.model.dao.FileDao;
import kr.co.ticketsea.admin.show.model.vo.FileData;
import kr.co.ticketsea.common.JDBCTemplate;


public class FileService {
	public int uploadFile(FileData fd) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new FileDao().uploadFile(conn,fd);
		
		if(result>0)
		{
			JDBCTemplate.commit(conn);
		}
		else
		{
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);	
		return result;
	}
}
