package kr.co.ticketsea.show.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.ticketsea.common.JDBCTemplate;
import kr.co.ticketsea.show.model.vo.Comment;
import kr.co.ticketsea.show.model.vo.Show;

public class ShowDao {

	public ArrayList<Show> getCurrentPage(Connection conn, int currentPage, int recordCountPerPage) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int start = currentPage * recordCountPerPage - (recordCountPerPage-1);
		
		
		
		int end = currentPage * recordCountPerPage;
		
		
		String query = "select * from (select show.*,row_number() " + 
				"over(order by show_no desc) AS num " + 
				"from show) where num between ? and ?";
		
		ArrayList<Show> list = new ArrayList<Show>();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) 
			{
				Show s = new Show();
				s.setShow_no(rset.getInt("show_no"));
				s.setShow_title(rset.getString("show_title"));
				s.setShow_filePath(rset.getString("show_filepath"));
				
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
		
		String query = "select count(*) AS TOTALCOUNT FROM show";
		
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
				show.setShow_artist(rset.getString("show_artist"));
				show.setShow_category(rset.getString("show_category"));
				show.setShow_contents(rset.getString("show_contents"));
				show.setShow_writer(rset.getString("show_writer"));
				show.setShow_endDate(rset.getDate("show_endDate"));
				show.setShow_fileName(rset.getString("show_fileName"));
				show.setShow_filePath(rset.getString("show_filePath"));
				show.setShow_fileSize(rset.getLong("show_fileSize"));
				show.setShow_grade(rset.getString("show_grade"));
				show.setShow_location(rset.getString("show_location"));
				show.setShow_no(rset.getInt("show_no"));
				show.setShow_price(rset.getInt("show_price"));
				show.setShow_startDate(rset.getDate("show_startDate"));
				show.setShow_subTitle(rset.getString("show_subTitle"));
				show.setShow_time(rset.getInt("show_time"));
				show.setShow_title(rset.getString("show_title"));
				show.setShow_uploadTime(rset.getTimestamp("show_uploadTime"));
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

}
