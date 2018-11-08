package kr.co.ticketsea.rank.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.ticketsea.common.JDBCTemplate;
import kr.co.ticketsea.rank.model.vo.Rank;

public class RankDao {

	public ArrayList<Rank> newestShow(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Rank> list = new ArrayList<Rank>();
		String query = "select * from (select musical_l.*,row_number() over(order by m_show_st_date desc) as num from musical_l) where num between 1 and 5";
		
		try {
			pstmt=conn.prepareStatement(query);
			rset= pstmt.executeQuery();
			
			while(rset.next())
			{
				Rank r = new Rank();
				r.setPoster(rset.getString("m_show_poster"));
				r.setShowNo(rset.getInt("m_show_no"));
				r.setSubject(rset.getString("m_show_name"));
				
				list.add(r);
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
