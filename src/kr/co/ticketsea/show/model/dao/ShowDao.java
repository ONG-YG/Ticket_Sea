package kr.co.ticketsea.show.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import kr.co.ticketsea.common.JDBCTemplate;
import kr.co.ticketsea.show.model.vo.Comment;
import kr.co.ticketsea.admin.show.model.vo.Show;

public class ShowDao {

	public ArrayList<Show> getCurrentPage(Connection conn, int currentPage, int recordCountPerPage) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int start = currentPage * recordCountPerPage - (recordCountPerPage-1);
		
		
		
		int end = currentPage * recordCountPerPage;
		
		
		String query = "select * from (select musical_l.*,row_number() " + 
				"over(order by m_show_no desc) AS num " + 
				"from musical_l) where num between ? and ?";
		
		ArrayList<Show> list = new ArrayList<Show>();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) 
			{
				Show s = new Show();
				s.setM_show_no(rset.getInt("m_show_no"));
				s.setShow_name(rset.getString("m_show_name"));
				s.setShow_poster(rset.getString("m_show_poster"));
				s.setSc_code(rset.getString("sc_code"));
				s.setShow_dtInfo(rset.getString("m_show_dtInfo"));
				
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
	public String getPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int recordTotalCount = 0; 
		
		String query = "select count(*) AS TOTALCOUNT FROM musical_l";
		
		 try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				recordTotalCount = rset.getInt("TOTALCOUNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		 
		
		 int pageToTalCount = 0; 
		 
		 
		 if(recordTotalCount%recordCountPerPage !=0)
		 {
			 pageToTalCount = recordTotalCount / recordCountPerPage + 1;
		 }
		 else 
		 {
			 pageToTalCount = recordTotalCount / recordCountPerPage;
		 }
		 
		 
		 if(currentPage<1)
		 {
			 currentPage = 1;
		 }
		 else if(currentPage>pageToTalCount)
		 {
			 currentPage = pageToTalCount;
		 }
		 
		int startNavi = ((currentPage-1)/naviCountPerPage)*naviCountPerPage+1; 
		
		
		int endNavi = startNavi + naviCountPerPage -1;
		
		
		if(endNavi > pageToTalCount)
		{
			endNavi = pageToTalCount;
		}
	
		boolean needPrev = true;
		boolean needNext = true;
		
		if(startNavi==1) { needPrev = false;}
		if(endNavi==pageToTalCount) { needNext = false;}
		
		
		
		StringBuilder sb = new StringBuilder();
		
	
		if(needPrev==true)
		{
			sb.append("<a href='/showList.do?currentPage="+(startNavi-1)+"'> < </a>");
		}
		
		
		for(int i=startNavi; i<=endNavi;i++){
			if(i==currentPage)
			{
				
				sb.append("<a href='/showList.do?currentPage="+i+"'><B style='font-size:30px'>"+i+"</B></a> ");
				
			}
			else
			{
				
				sb.append("<a href='/showList.do?currentPage="+i+"'>"+i+"</a> ");
			}
		}
		
		if(needNext) 
		{
			sb.append("<a href='/showList.do?currentPage="+(endNavi+1)+"'> > </a>");
		}
		
		return sb.toString();		
		
	}
	public Show selectOneShow(Connection conn, int show_no) {
		
		//upHitShow(conn, show_no);
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Show show = null;
		
		String query = "select * from musical_l where m_show_no = ?";
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, show_no);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				show = new Show();
				show.setArtists(rset.getString("m_artists"));
				show.setSc_code(rset.getString("sc_code"));
				show.setBk_comm(rset.getInt("BK_COMM"));
				show.setM_show_no(rset.getInt("m_show_no"));
				show.setShow_dtInfo(rset.getString("m_show_dtinfo"));
				show.setShow_st_date(rset.getString("m_show_st_date"));
				show.setShow_ed_date(rset.getString("m_show_ed_date"));
				show.setShow_grd(rset.getString("m_show_grd"));
				show.setShow_name(rset.getString("m_show_name"));
				show.setShow_poster(rset.getString("m_show_poster"));
				show.setShow_run(rset.getInt("m_show_run"));
				//show.setTh_name(rset.getString("m_show_ed_date")); // 확인
				show.setTh_no(rset.getInt("th_no"));
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
	public ArrayList<Comment> selectComments(Connection conn, int show_no) {
	
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from showCOMMENT "
				+ "where show_no = ?";
		
		ArrayList<Comment> list = new ArrayList<Comment>();
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, show_no);
			
			rset = pstmt.executeQuery();
			
			while(rset.next())
			{
				Comment co = new Comment();
				co.setCommentNo(rset.getInt("showComment"));
				co.setShowNo(rset.getInt("show_no"));
				co.setContents(rset.getString("showComment_contents"));
				co.setUserId(rset.getString("showComment_writer"));
				co.setRegDate(rset.getDate("showComment_date"));
				
				list.add(co);
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}
	public int insertComment(Connection conn, int showNo, String contents, String userId) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "insert into showcomment values(SEQ_showcomment.NEXTVAL,?,?,?,SYSDATE)";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, showNo);
			pstmt.setString(2, contents);
			pstmt.setString(3, userId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		
		return result;
	}
	public int insertShow(Connection conn, String userId, String category, String title, String subTitle, String artist,
			String contents, String location, int time, String grade, int price, String fileName, String fullFilePath,
			long fileSize, Timestamp uploadTime) {
		
			PreparedStatement pstmt = null;
			int result = 0;
			
			String query = "insert into show values(seq_show_no.NEXTVAL,?,?,?,?,?,?,?,sysdate,sysdate,?,?,?,?,?,?,?)";
			
			try {
				pstmt = conn.prepareStatement(query);
				
				pstmt.setString(1, userId);
				pstmt.setString(2, category);
				pstmt.setString(3, title);
				pstmt.setString(4, subTitle);
				pstmt.setString(5, artist);
				pstmt.setString(6, contents);
				pstmt.setString(7, location);
				pstmt.setInt(8, time);
				pstmt.setString(9, grade);
				pstmt.setInt(10, price);
				
				
				pstmt.setString(11, fileName);
				pstmt.setString(12, fullFilePath);
				pstmt.setLong(13,  fileSize);
				pstmt.setTimestamp(14, uploadTime);
				
				result = pstmt.executeUpdate();
			
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(pstmt);
			}
			
			return result;
		}
	public int deleteComment(Connection conn, int m_show_no, int showcomment) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "delete from showcomment where show_no=? and showcomment=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, m_show_no);
			pstmt.setInt(2, showcomment);
			
			result = pstmt.executeUpdate();
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	/*public void upHitShow(Connection conn, int m_show_no) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "update musical_l set m_show_hit = m_show_hit+1 where m_show_no = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, m_show_no);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
	}*/
	
}
