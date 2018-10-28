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

	public ArrayList<Reserve> getcurrentPage(Connection conn, int currentPage, int recordCountPerPage) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		
		//시작 게시물 계산
		int start = currentPage*recordCountPerPage-(recordCountPerPage-1);
		
		//끝 게시물 계산
		int end = currentPage* recordCountPerPage;
		
		String query = "select * from member m right outer join (select * from (select row_number() over(order by bk_no desc) num, BOOK_INF.* from BOOK_INF) where num between 1 and 5) b on m.MEMBER_NO = b.member_no;";
		
		ArrayList<Reserve> list = new ArrayList<Reserve>();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rset =  pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return null;
	}
}
