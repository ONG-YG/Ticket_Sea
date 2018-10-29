package kr.co.ticketsea.mypage.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.co.ticketsea.common.JDBCTemplate;

public class MypageDao {

	public void selectMusical(Connection conn, int memberNo) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = "select bk_no from book_inf where member_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, memberNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		
	}

}
