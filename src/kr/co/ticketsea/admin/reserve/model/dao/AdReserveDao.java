package kr.co.ticketsea.admin.reserve.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.ticketsea.admin.reserve.model.vo.Reserve;
import kr.co.ticketsea.admin.show.model.vo.Show;
import kr.co.ticketsea.common.JDBCTemplate;

public class AdReserveDao {

	public ArrayList<Reserve> getcurrentPage(Connection conn, int currentPage, int recordCountPerPage) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		
		//시작 게시물 계산
		int start = currentPage*recordCountPerPage-(recordCountPerPage-1);
		
		//끝 게시물 계산
		int end = currentPage* recordCountPerPage;
		
		String query = "select * from (select * from (select row_number() over(order by bk_no desc) num, BOOK_INF.* from BOOK_INF) where num between ? and ?)b left join member m on b.member_no=m.member_no;";
		
		ArrayList<Show> list = new ArrayList<Show>();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rset =  pstmt.executeQuery();
			
			while(rset.next()) {
				Reserve r = new Reserve();
				r.setBk_no(rset.getInt("bk_no"));
				r.setMember_no(rset.getInt("member_no"));
			
				list.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

}
