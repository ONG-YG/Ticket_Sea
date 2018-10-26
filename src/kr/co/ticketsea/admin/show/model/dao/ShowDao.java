package kr.co.ticketsea.admin.show.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.ticketsea.admin.show.model.vo.Show;
import kr.co.ticketsea.common.JDBCTemplate;

public class ShowDao {

	public int insertShow(Connection conn, Show s) {
		PreparedStatement pstmt= null;
		int result = 0;
		String query = "insert into musical_l values(SHOW_DB.nextval,10000,'MSC',?,?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, s.getShow_name());
			pstmt.setString(2, s.getShow_st_date());
			pstmt.setString(3, s.getShow_ed_date());
			pstmt.setString(4, s.getArtists());
			pstmt.setString(5, s.getShow_grd());
			pstmt.setInt(6, s.getShow_run());
			
			result = pstmt.executeUpdate();
			System.out.println(result);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
		
	}

	public ArrayList<Show> getcurrentPage(Connection conn, int currentPage, int recordCountPerPage) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		
		//시작 게시물 계산
		int start = currentPage*recordCountPerPage-(recordCountPerPage-1);
		
		//끝 게시물 계산
		int end = currentPage* recordCountPerPage;
		
		String query = "select * from (select row_number() over(order by m_show_no desc) num, musical_l.* from musical_l) where num between ? and ?";
		
		ArrayList<Show> list = new ArrayList<Show>();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rset =  pstmt.executeQuery();
			
			while(rset.next()) {
				Show s = new Show();
				s.setM_show_no(rset.getInt("m_show_no"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
