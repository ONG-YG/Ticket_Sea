package kr.co.ticketsea.promo.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.co.ticketsea.common.JDBCTemplate;
import kr.co.ticketsea.notice.model.vo.Notice;
import kr.co.ticketsea.promo.model.vo.Show;

public class ShowDao {

	public Show selectOneShow(Connection conn, int show_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Show show = null;

		String query = "select * from show where show_no = ?";
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, show_no);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				show = new Show();
				
				show.setShow_title(rset.getString("show_title"));
				show.setShow_subTitle(rset.getString("show_subTitle"));
				show.setShow_location(rset.getString("show_location"));
				show.setShow_subTitle(rset.getString("show_subTitle"));
				show.setShow_date(rset.getDate("show_date"));
				show.setShow_time(rset.getInt("show_time"));
				show.setShow_grade(rset.getString("show_grade"));
				show.setShow_subTitle(rset.getString("show_subTitle"));
				show.setShow_price(rset.getInt("show_price"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return show;
	}
	}

