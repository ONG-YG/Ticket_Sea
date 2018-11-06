package kr.co.ticketsea.admin.reserve.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		
		String query = "select * " + 
				"from(select row_number() over(order by bk_no desc)num,a.* from(" + 
				"select distinct bk_no, a.member_no,member_id,m_show_name,bk_date " + 
				"from(select member_no,m_show_name,bk_date,bk_no " + 
				"from (select bk_no,member_no,bk_date,m_show_no,a.ps_no " + 
				"from(select a.bk_no,member_no,bk_date,ps_no " + 
				"from book_inf a, bk_s_l b " + 
				"where a.bk_no=b.bk_no)a,perf_sch b " + 
				"where a.ps_no=b.ps_no)a,musical_l b " + 
				"where a.m_show_no=b.m_show_no)a , member b " + 
				"where a.member_no=b.member_no)a) where num between ? and ?";
		
		ArrayList<Reserve> list = new ArrayList<Reserve>();
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				Reserve rs = new Reserve();
				rs.setBk_no(rset.getString("bk_no"));
				rs.setMember_no(rset.getInt("member_no"));
				rs.setMember_id(rset.getString("member_id"));
				rs.setShow_name(rset.getString("m_show_name"));
				rs.setBk_date(rset.getDate("bk_date"));
				
				list.add(rs);
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
		ResultSet rset=null;
		
		//게시물의 토탈 개수를 구해야함
	    int recordTotalCount=0; //초기값은 정보가 없으므로 0으로 세팅
	    
	    String query = "select count(*) as totalcount from book_inf";
	    
	    try {
			pstmt=conn.prepareStatement(query);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				recordTotalCount = rset.getInt("totalcount");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
	  //구해온 게시물의 토탈 개수를 바탕으로 페이지의 토탈 개수를 구해야함
	    int pageTotalCount = 0; 
	  //페이지의 토탈 개수 구하는 공식
	    if(recordTotalCount % recordCountPerPage!=0) { //나머지가 있으면
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		}else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
	    // 에러 방지 코드
	 	// 다음페이지 이전페이지 이동 에러
	    if(currentPage<1) {
			currentPage = 1;
		}else if(currentPage>pageTotalCount){
			currentPage = pageTotalCount;
		}
	    //시작페이지 구하는 공식
	  	int startNavi = ((currentPage-1)/naviCountPerPage)*naviCountPerPage+1;

	    //끝 페이지 구하는 공식
	  	int endNavi = startNavi+naviCountPerPage-1;
	  	
	  	if(endNavi>pageTotalCount) {
			endNavi=pageTotalCount;
		}
	  	
	  	//'<' '>' 모양
	  	boolean needPrev = true;
		boolean needNext = true;
		
		if(startNavi==1) { needPrev = false;}
		if(endNavi == pageTotalCount){needNext=false;}
		
		StringBuilder sb = new StringBuilder();
		
		if(needPrev==true) { //시작페이지가 1페이지가 아니라면
			sb.append("<a href='/adReserveList.do?currentPage="+(startNavi-1)+"'> < </a> ");
		}
		
		for(int i=startNavi;i<=endNavi;i++) {
			if(i==currentPage) { // 현재 페이지가 내가 있는 위치 페이지와 같다면 진하게 표시
				sb.append("<a href='/adReserveList.do?currentPage="+i+"'><B style='font-size:20px'>"+i+"</B></a> ");
			}else { // 현재 페이지가 내가 있는 위치 페이지와 다르다면 일반 표시
				sb.append("<a href='/adReserveList.do?currentPage="+i+"'>"+i+"</a> ");
			}
		}
		
		if(needNext) {
			sb.append("<a href='/adReserveList.do?currentPage="+(endNavi+1)+"'> > </a> ");
		}
		
		return sb.toString();
	}

	
	

}
