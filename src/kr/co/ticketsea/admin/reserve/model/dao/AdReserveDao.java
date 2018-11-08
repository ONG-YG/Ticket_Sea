package kr.co.ticketsea.admin.reserve.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import kr.co.ticketsea.admin.reserve.model.vo.Reserve;
import kr.co.ticketsea.admin.reserve.model.vo.ReserveApInfo;
import kr.co.ticketsea.admin.show.model.vo.Show;
import kr.co.ticketsea.common.JDBCTemplate;
import kr.co.ticketsea.reserve.model.vo.SelectedSeat;

public class AdReserveDao {
	
	public ArrayList<Reserve> getcurrentPage(Connection conn, int currentPage, int recordCountPerPage) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		
		//시작 게시물 계산
		int start = currentPage*recordCountPerPage-(recordCountPerPage-1);
		
		//끝 게시물 계산
		int end = currentPage* recordCountPerPage;
		
		String query = "select * from(select row_number() over(order by bk_no desc)num,a.* \r\n" + 
				"from(select distinct bk_no, a.member_no,member_id,m_show_name,bk_date,bk_stat_cd\r\n" + 
				"from(select member_no,m_show_name,bk_date,bk_no,bk_stat_cd\r\n" + 
				"from (select bk_no,member_no,bk_date,m_show_no,a.ps_no,bk_stat_cd \r\n" + 
				"from(select a.bk_no,member_no,bk_date,ps_no,bk_stat_cd\r\n" + 
				"from book_inf a, bk_s_l b \r\n" + 
				"where a.bk_no=b.bk_no AND bk_stat_cd='RSV_CPL')a,perf_sch b \r\n" + 
				"where a.ps_no=b.ps_no)a,musical_l b \r\n" + 
				"where a.m_show_no=b.m_show_no)a , member b \r\n" + 
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

	public ReserveApInfo selectOneReserve(Connection conn, String reserveNo) {
		
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		ReserveApInfo rs=null;
		String query = "select a.m_show_no,m_show_name,ps_date,ps_cnt,ps_time,th1_seat_grd,seat_title,a.ps_no,a.bk_no,member_no,member_id,member_name,bk_date,bk_tot_price,bk_phone,bk_email,bk_stat_name\r\n" + 
				"from(select ps_date,ps_cnt,ps_time,m_show_no,th1_seat_grd,seat_title,a.ps_no,a.bk_no,member_no,member_id,member_name,bk_date,bk_tot_price,bk_phone,bk_email,bk_stat_name\r\n" + 
				"from(select th1_seat_grd,seat_title,ps_no,a.bk_no,member_no,member_id,member_name,bk_date,bk_tot_price,bk_phone,bk_email,bk_stat_name\r\n" + 
				"from(select ps_no,th1_seat_no,a.bk_no,member_no,member_id,member_name,bk_date,bk_tot_price,bk_phone,bk_email,bk_stat_name\r\n" + 
				"from(select bk_no,a.member_no,member_id,member_name,bk_date,bk_tot_price,bk_phone,bk_email,bk_stat_name\r\n" + 
				"from(select * from book_inf a,bk_state_l b\r\n" + 
				"where a.bk_stat_cd=b.bk_stat_cd)a, member b\r\n" + 
				"where a.member_no=b.member_no)a,BK_S_L b\r\n" + 
				"where a.bk_no=b.bk_no)a, TH1_SEAT_L b\r\n" + 
				"where a.th1_seat_no=b.th1_seat_no)a, perf_sch b\r\n" + 
				"where a.ps_no=b.ps_no)a, musical_l b\r\n" + 
				"where a.m_show_no=b.m_show_no and a.bk_no=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, reserveNo);
			
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				rs=new ReserveApInfo();
				rs.setBk_no(rset.getString("bk_no"));
				rs.setM_show_name(rset.getString("m_show_name"));
				rs.setPs_date(rset.getDate("ps_date"));
				rs.setPs_cnt(rset.getInt("ps_cnt"));
				rs.setPs_time(rset.getString("PS_TIME"));
				rs.setMember_id(rset.getString("member_id"));
				rs.setMember_name(rset.getString("member_name"));
				rs.setBk_date(rset.getDate("bk_date"));
				rs.setBk_tot_price(rset.getInt("bk_tot_price"));
				rs.setBk_phone(rset.getString("bk_phone"));
				rs.setBk_email(rset.getString("bk_email"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return rs;
	}

	public ArrayList<SelectedSeat> seatList(Connection conn, String reserveNo) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		SelectedSeat st=null;
		ArrayList<SelectedSeat> list=new ArrayList<SelectedSeat>();
		
		String query="select th1_seat_grd, seat_title\r\n" + 
				"from(select * from book_inf a, bk_s_l b\r\n" + 
				"where a.bk_no=b.bk_no and a.bk_no=?)a, th1_seat_l b\r\n" + 
				"where a.th1_seat_no=b.TH1_SEAT_NO ";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, reserveNo);
			
			rset=pstmt.executeQuery();
			while(rset.next()) {
				st=new SelectedSeat();
				st.setSeatGrd(rset.getString("th1_seat_grd"));
				st.setSeatTitle(rset.getString("seat_title"));
				
				list.add(st);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return list;
		
	}

	public int reserveUpdate(Connection conn, String bk_no,String phone, String email) {
		PreparedStatement pstmt=null;
		int result =0;
		
		String query="update book_inf set BK_PHONE=?, BK_EMAIL=? where bk_no=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, phone);
			pstmt.setString(2, email);
			pstmt.setString(3, bk_no);
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
		
	}

	public int deleteReserve(Connection conn, String bkNo) {
		PreparedStatement pstmt = null;
		int result = 0;	
		String query ="update book_inf set bk_stat_cd='RSV_CNL' where bk_no=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, bkNo);
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	
	
	

}
