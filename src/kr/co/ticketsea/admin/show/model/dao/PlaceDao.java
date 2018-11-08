package kr.co.ticketsea.admin.show.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import kr.co.ticketsea.common.JDBCTemplate;

public class PlaceDao {

	public int insertPlace(Connection conn,String th_name, String th_lct) {
		PreparedStatement pstmt=null;
		int result=0;
		
		String query="insert into THEATER_L values(PL_DB.nextval,?,?)";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, th_name);
			pstmt.setString(2, th_lct);
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

}
