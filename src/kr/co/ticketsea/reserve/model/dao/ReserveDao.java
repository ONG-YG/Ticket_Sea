package kr.co.ticketsea.reserve.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.ticketsea.admin.reserve.model.vo.Reserve;
import kr.co.ticketsea.admin.show.model.vo.Show;
import kr.co.ticketsea.common.JDBCTemplate;

public class ReserveDao {
	
	public int dbTest(Connection conn, String temp) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "INSERT INTO TEST VALUES(?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, temp);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		
		return result;
	}

	
}
