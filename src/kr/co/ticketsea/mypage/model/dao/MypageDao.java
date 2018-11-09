package kr.co.ticketsea.mypage.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.ticketsea.common.JDBCTemplate;
import kr.co.ticketsea.member.model.vo.Member;
import kr.co.ticketsea.mypage.model.vo.PopupReserveList;
import kr.co.ticketsea.mypage.model.vo.PromoMgr;
import kr.co.ticketsea.mypage.model.vo.QnaMgr;
import kr.co.ticketsea.mypage.model.vo.ReserveList;
import kr.co.ticketsea.mypage.model.vo.ReviewMgr;
import kr.co.ticketsea.reserve.model.vo.ShowInfo;

public class MypageDao {

	public Member memberUpdate(Connection conn, int memberNo) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from member where member_no = ?";
		Member updateList = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, memberNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				updateList = new Member();
				
				updateList.setMemberId(rset.getString("member_id"));
				updateList.setMemberPhone(rset.getString("member_phone"));
				updateList.setMemberAddr(rset.getString("member_addr"));
				updateList.setMemberEmail(rset.getString("member_email"));
				updateList.setMemberPwd(rset.getString("member_pwd"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		
		return updateList;
	}

	public int updateMemberConfirm(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		
		int result = 0;	
		
		String query="update member set member_pwd=?, member_phone=?, member_addr=?, member_email=? where member_id=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			
			pstmt.setString(1, m.getMemberPwd());
			pstmt.setString(2, m.getMemberPhone());
			pstmt.setString(3, m.getMemberAddr());
			pstmt.setString(4, m.getMemberEmail());
			pstmt.setString(5, m.getMemberId());
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<ReviewMgr> getCurrentPage(Connection conn, int currentPage, int recordCountPerPage, String memberId) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		//시작 게시물 계산
		int start = currentPage * recordCountPerPage - (recordCountPerPage-1);
		//ex) currentPage가 3 이고, recordCountPerPage 5라면?
		//만약 요청한 페이지가 3페이지라면? -> 11이 나와야 됨
			// 3 * 5 - (5-1) 	=> 11
		
		
		//끝 게시물 계산
		int end = currentPage * recordCountPerPage;
		// 만약 요청한 페이지가 3페이지라면? -> 15
			// 3 * 5 	=> 15
		
		
		// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 쿼리문 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		String query = "select * from \r\n" + 
				"(select a.*,ROW_NUMBER() OVER (ORDER BY showcomment_date desc) as num from \r\n" + 
				"(select m_show_name,showcomment_contents,showcomment_date from \r\n" + 
				"(select * from showcomment \r\n" + 
				"where SHOWCOMMENT_WRITER=?) a,musical_l b \r\n" + 
				"where a.show_no=b.m_show_no) a) \r\n" + 
				"where num between ? and ?\r\n" + 
				"order by num asc";
		
		

		// 쿼리문 내용 담을 list 배열 초기화		
		ArrayList<ReviewMgr> list = new ArrayList<ReviewMgr>();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) 
			{
				ReviewMgr rm = new ReviewMgr();
				
				rm.setNum(Integer.parseInt(rset.getString("num")));					// 번호
				rm.setmShowName(rset.getString("m_show_name"));						// 공연명
				rm.setShowcommentContents(rset.getString("showcomment_contents"));	// 댓글내용
				rm.setShowcommentDate(rset.getDate("showcomment_date"));			// 작성일
				
				list.add(rm);
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

	public String getPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 게시물의 토탈 개수를 구해야 함
		int recordTotalCount = 0; //초기값은 정보가 없으므로 0으로 셋팅
		
		String query = "select count(*) as totalcount from\r\n" + 
				"(select a.*,ROW_NUMBER() OVER (ORDER BY showcomment_date desc) as num from\r\n" + 
				"(select m_show_name,showcomment_contents,showcomment_date from\r\n" + 
				"(select * from showcomment \r\n" + 
				"where SHOWCOMMENT_WRITER=?) a,musical_l b\r\n" + 
				"where a.show_no=b.m_show_no) a)";
		
		 try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, memberId);
			
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
		 
		
		 // 구해온 게시물의 토탈 개수를 바탕으로 페이지의 토탈 개수를 구해야 함
		 // 즉, 게시물이 124개 라면? 
		 // page는 총 13개가 되어야 함 (페이지당 10개의 게시물이라고 정하였을때 기준)
		 
		 int pageToTalCount = 0; //정보가 없으므로 초기값은 0 셋팅
		 
		 // 페이지의 토탈 개수 구하는 공식
		 // 게시물토탈 개수 / 10  + 1(조건에 따라 적용)
		 
		 if(recordTotalCount%recordCountPerPage !=0)
		 {
			 pageToTalCount = recordTotalCount / recordCountPerPage + 1;
		 }
		 else 
		 {
			 pageToTalCount = recordTotalCount / recordCountPerPage;
		 }
		 
		 // 에러 방지 코드
		 if(currentPage<1)
		 {
			 currentPage = 1;
		 }
		 else if(currentPage>pageToTalCount)
		 {
			 currentPage = pageToTalCount;
		 }
		 
		 
		 // 현재 페이지를 기점으로 시작 navi 와 끝 navi를 만들어야 함
		 
		 // 현재 페이지가 만약 1이라면  ?    1 2 3 4 5
		 // 현재 페이지가 만약 3이라면  ?    1 2 3 4 5
		 // 현재 페이지가 만약 7이라면  ? 	 6 7 8 9 10
		 // 현재 페이지가 만약 12이라면?	 11 12 13 14 15
		 
		 // 시작 페이지를 구하는 공식
		 // ((현재페이지-1)/리스트개수)*리스트개수+1
		 
		 // 만약 1페이지 라면?
		 // ((1-1)/5)*5+1   => 1
		 
		 // 만약 3페이지 라면?
		 // ((3-1)/5)*5+1	=> 1
		 
		 // 만약 7페이지 라면?
		 // ((7-1)/5)*5+1	=> 6
		 
		 // 만약 12페이지 라면?
		 // ((12-1)/5)*5+1	=> 11
		 
		
		 // 시작 페이지 구하는 공식 대입
		int startNavi = ((currentPage-1)/naviCountPerPage)*naviCountPerPage+1; 
		
		
		// 끝 페이지를 구하는 공식
		// 시작navi + 보여줄 navi 개수 -1;
		// ex. 시작 navi가 11 이라면?
		// 11 + 5 -1 => 15 가 나옴
		// 즉, 11 12 13 14 15 가 구해짐  
		
		int endNavi = startNavi + naviCountPerPage -1;
		
		
		// 끝 navi를 구할때 주의해야 항점
		// 토탈 개수가 122개 라고 할때 ( 총 토탈 페이지는 13개)
		// 1 2 3 4 5
		// 6 7 8 9 10
		// 11 12 13 14 15
		// 토탈 페이지를 고려하지 않고 만들게 되면 위에 처럼 끝 navi가 이상하게 구해지게 됨
		
		if(endNavi > pageToTalCount)
		{
			endNavi = pageToTalCount;
		}
	
		// 페이지를 표현하는 navi에서 사용할 '<' 모양과 '>'모양을 쓰기위해
		// 필요한 변수 2개를 생성 (변수에 값에 따라서 시작 부분과 끝부분은 표현하지 않기 위해)
		
		boolean needPrev = true;
		boolean needNext = true;
		
		if(startNavi==1) { needPrev = false;}
		if(endNavi==pageToTalCount) { needNext = false;}
		
		
		
		StringBuilder sb = new StringBuilder();
		
		// needPrev는 시작페이지가 1이면 false, 시작페이지가 1이 아니라면 true
		if(needPrev==true) // 시작 페이지가 1페이지가 아니라면!
		{
			sb.append("<a href='/reviewMgr.do?currentPage="+(startNavi-1)+"'> < </a>");
		}
		// 현재 내 위치(startNavi값)가 2라면? '<' 버튼을 클릭하면 1페이지로 이동해야 함
		// <a href='/noticeList.do?cureentPage=1> < </a>
		
		for(int i=startNavi; i<=endNavi;i++){
			if(i==currentPage)
			{
				// 현재 페이지가 내가 있는 위치페이지와 같다면 진하게 표시
				sb.append("<a href='/reviewMgr.do?currentPage="+i+"'><B style='font-size:30px'>"+i+"</B></a> ");
				//<a href='/noticeList.do?cureentPage=1'><B>1</B></a>
			}
			else
			{
				// 현재 페이지가 내가 있는 위치 페이지와 다르다면 일반 표시
				sb.append("<a href='/reviewMgr.do?currentPage="+i+"'>"+i+"</a> ");
			}
		}
		
		if(needNext) 
		{
			sb.append("<a href='/reviewMgr.do?currentPage="+(endNavi+1)+"'> > </a>");
		}
		
		return sb.toString();	
	}

	public ArrayList<PopupReserveList> popupReserveList(Connection conn, int currentPage, int recordCountPerPage,
			String bkNo) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		//시작 게시물 계산
		int start = currentPage * recordCountPerPage - (recordCountPerPage-1);
		//ex) currentPage가 3 이고, recordCountPerPage 5라면?
		//만약 요청한 페이지가 3페이지라면? -> 11이 나와야 됨
			// 3 * 5 - (5-1) 	=> 11
				
		//끝 게시물 계산
		int end = currentPage * recordCountPerPage;
		// 만약 요청한 페이지가 3페이지라면? -> 15
			// 3 * 5 	=> 15
		
		
		// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 쿼리문 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		String query = "select * from (select ROW_NUMBER() OVER (ORDER BY ticket_no asc) AS num , a.* from (select ticket_no,bk_no,ps_no,ps_date,ps_cnt,ps_time,th1_seat_no,th1_seat_grd,seat_title,a.m_show_no,m_show_name,m_show_run from (select ticket_no,bk_no,ps_no,ps_date,ps_cnt,ps_time,m_show_no,a.th1_seat_no,th1_seat_grd,seat_title from (select ticket_no,bk_no,a.ps_no,ps_date,ps_cnt,ps_time,m_show_no,th1_seat_no from (select * from bk_s_l where bk_no = ?) a,perf_sch b where a.ps_no = b.ps_no)a, th1_seat_l b where a.th1_seat_no = b.th1_seat_no) a, musical_l b where a.m_show_no = b.m_show_no) a) where num between ? and ? order by num asc";	
		
		//select * from (select ROW_NUMBER() OVER (ORDER BY ticket_no asc) AS num , a.* from (select ticket_no,bk_no,ps_no,ps_date,ps_cnt,ps_time,th1_seat_no,th1_seat_grd,seat_title,a.m_show_no,m_show_name,m_show_run from (select ticket_no,bk_no,ps_no,ps_date,ps_cnt,ps_time,m_show_no,a.th1_seat_no,th1_seat_grd,seat_title from (select ticket_no,bk_no,a.ps_no,ps_date,ps_cnt,ps_time,m_show_no,th1_seat_no from (select * from bk_s_l where bk_no = ?) a,perf_sch b where a.ps_no = b.ps_no)a, th1_seat_l b where a.th1_seat_no = b.th1_seat_no) a, musical_l b where a.m_show_no = b.m_show_no) a) where num between ? and ? order by num asc

		// 쿼리문 내용 담을 list 배열 초기화		
		ArrayList<PopupReserveList> pList = new ArrayList<PopupReserveList>();
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, bkNo);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) 
			{
				PopupReserveList prl = new PopupReserveList();
				
				prl.setmShowName(rset.getString("m_show_name"));			// 공연명
				
				prl.setNum(rset.getString("num"));							// 나열하는 번호
				prl.setTicketNo(rset.getString("ticket_no"));				// 티켓 번호
				prl.setPsNo(rset.getInt("ps_no"));							// 공연회차번호
				prl.setPsDate(rset.getDate("ps_date"));						// 공연일
				prl.setPsCnt(rset.getInt("ps_cnt"));						// 공연회차
				prl.setPsTime(rset.getString("ps_time"));					// 공연시간
				prl.setTh1SeatNo(rset.getInt("th1_seat_no"));				// 좌석번호
				prl.setTh1SeatGrd(rset.getString("th1_seat_grd"));			// 좌석등급
				prl.setSeatTitle(rset.getString("seat_title"));				// 좌석 title
				prl.setmShowNo(rset.getInt("m_show_no"));					// 공연번호
				prl.setmShowName(rset.getString("m_show_name"));			// 공연명
				prl.setmShowRun(rset.getInt("m_show_run"));					// 공연관람시간

				pList.add(prl);
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return pList;
	}

	public String getPopupPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage,
			String bkNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 게시물의 토탈 개수를 구해야 함
		int recordTotalCount = 0; //초기값은 정보가 없으므로 0으로 셋팅
		
		String query = "select count(*) as totalcount from\r\n" + 
				"(select a.*,ROW_NUMBER() OVER (ORDER BY ticket_no asc) AS num from\r\n" + 
				"(select ticket_no,bk_no,ps_no,ps_date,ps_cnt,ps_time,a.m_show_no,m_show_name,m_show_run,th1_seat_no from\r\n" + 
				"(select ticket_no,bk_no,a.ps_no,ps_date,ps_cnt,ps_time,m_show_no,th1_seat_no from\r\n" + 
				"(select * from bk_s_l where bk_no = ?) a,perf_sch b\r\n" + 
				"where a.ps_no = b.ps_no)a, musical_l b\r\n" + 
				"where a.m_show_no = b.m_show_no) a)";
		
		 try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, bkNo);
			
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
		 
		
		 // 구해온 게시물의 토탈 개수를 바탕으로 페이지의 토탈 개수를 구해야 함
		 // 즉, 게시물이 124개 라면? 
		 // page는 총 13개가 되어야 함 (페이지당 10개의 게시물이라고 정하였을때 기준)
		 
		 int pageToTalCount = 0; //정보가 없으므로 초기값은 0 셋팅
		 
		 // 페이지의 토탈 개수 구하는 공식
		 // 게시물토탈 개수 / 10  + 1(조건에 따라 적용)
		 
		 if(recordTotalCount%recordCountPerPage !=0)
		 {
			 pageToTalCount = recordTotalCount / recordCountPerPage + 1;
		 }
		 else 
		 {
			 pageToTalCount = recordTotalCount / recordCountPerPage;
		 }
		 
		 // 에러 방지 코드
		 if(currentPage<1)
		 {
			 currentPage = 1;
		 }
		 else if(currentPage>pageToTalCount)
		 {
			 currentPage = pageToTalCount;
		 }
		 
		 
		 // 현재 페이지를 기점으로 시작 navi 와 끝 navi를 만들어야 함
		 
		 // 현재 페이지가 만약 1이라면  ?    1 2 3 4 5
		 // 현재 페이지가 만약 3이라면  ?    1 2 3 4 5
		 // 현재 페이지가 만약 7이라면  ? 	 6 7 8 9 10
		 // 현재 페이지가 만약 12이라면?	 11 12 13 14 15
		 
		 // 시작 페이지를 구하는 공식
		 // ((현재페이지-1)/리스트개수)*리스트개수+1
		 
		 // 만약 1페이지 라면?
		 // ((1-1)/5)*5+1   => 1
		 
		 // 만약 3페이지 라면?
		 // ((3-1)/5)*5+1	=> 1
		 
		 // 만약 7페이지 라면?
		 // ((7-1)/5)*5+1	=> 6
		 
		 // 만약 12페이지 라면?
		 // ((12-1)/5)*5+1	=> 11
		 
		
		 // 시작 페이지 구하는 공식 대입
		int startNavi = ((currentPage-1)/naviCountPerPage)*naviCountPerPage+1; 
		
		
		// 끝 페이지를 구하는 공식
		// 시작navi + 보여줄 navi 개수 -1;
		// ex. 시작 navi가 11 이라면?
		// 11 + 5 -1 => 15 가 나옴
		// 즉, 11 12 13 14 15 가 구해짐  
		
		int endNavi = startNavi + naviCountPerPage -1;
		
		
		// 끝 navi를 구할때 주의해야 항점
		// 토탈 개수가 122개 라고 할때 ( 총 토탈 페이지는 13개)
		// 1 2 3 4 5
		// 6 7 8 9 10
		// 11 12 13 14 15
		// 토탈 페이지를 고려하지 않고 만들게 되면 위에 처럼 끝 navi가 이상하게 구해지게 됨
		
		if(endNavi > pageToTalCount)
		{
			endNavi = pageToTalCount;
		}
	
		// 페이지를 표현하는 navi에서 사용할 '<' 모양과 '>'모양을 쓰기위해
		// 필요한 변수 2개를 생성 (변수에 값에 따라서 시작 부분과 끝부분은 표현하지 않기 위해)
		
		boolean needPrev = true;
		boolean needNext = true;
		
		if(startNavi==1) { needPrev = false;}
		if(endNavi==pageToTalCount) { needNext = false;}
		
		
		
		StringBuilder sb = new StringBuilder();
		
		// needPrev는 시작페이지가 1이면 false, 시작페이지가 1이 아니라면 true
		if(needPrev==true) // 시작 페이지가 1페이지가 아니라면!
		{
			sb.append("<a href='/popupReserveList.do?currentPage="+(startNavi-1)+"'> < </a>");
		}
		// 현재 내 위치(startNavi값)가 2라면? '<' 버튼을 클릭하면 1페이지로 이동해야 함
		// <a href='/noticeList.do?cureentPage=1> < </a>
		
		for(int i=startNavi; i<=endNavi;i++){
			if(i==currentPage)
			{
				// 현재 페이지가 내가 있는 위치페이지와 같다면 진하게 표시
				sb.append("<a href='/popupReserveList.do?currentPage="+i+"'><B style='font-size:30px'>"+i+"</B></a> ");
				//<a href='/noticeList.do?cureentPage=1'><B>1</B></a>
			}
			else
			{
				// 현재 페이지가 내가 있는 위치 페이지와 다르다면 일반 표시
				sb.append("<a href='/popupReserveList.do?currentPage="+i+"'>"+i+"</a> ");
			}
		}
		
		if(needNext) 
		{
			sb.append("<a href='/popupReserveList.do?currentPage="+(endNavi+1)+"'> > </a>");
		}
		
		return sb.toString();	
	}

	public ArrayList<ReserveList> getRCurrentPage(Connection conn, int currentPage, int recordCountPerPage,
			int memberNo) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		//시작 게시물 계산
		int start = currentPage * recordCountPerPage - (recordCountPerPage-1);
		//ex) currentPage가 3 이고, recordCountPerPage 5라면?
		//만약 요청한 페이지가 3페이지라면? -> 11이 나와야 됨
			// 3 * 5 - (5-1) 	=> 11
		
		
		//끝 게시물 계산
		int end = currentPage * recordCountPerPage;
		// 만약 요청한 페이지가 3페이지라면? -> 15
			// 3 * 5 	=> 15
		
		
		// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 쿼리문 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		String query = "select * from\r\n" + 
				"(select a.*, row_number() over (order by bk_no desc) as num from\r\n" + 
				"(select DISTINCT ps_no,bk_no,member_no,a.m_show_no,m_show_name,bk_date,bk_stat_cd from\r\n" + 
				"(select a.ps_no,bk_no,member_no,bk_stat_cd,m_show_no,bk_date from\r\n" + 
				"(select a.bk_no,member_no,bk_stat_cd,bk_date,ps_no\r\n" + 
				"from book_inf a,bk_s_l b\r\n" + 
				"where a.BK_NO = b.bk_no and member_no=?) a,perf_sch b\r\n" + 
				"where a.ps_no = b.ps_no) a,musical_l b\r\n" + 
				"where a.m_show_no = b.m_show_no) a)\r\n" + 
				"where num BETWEEN ? and ?\r\n" + 
				"order by num asc";
		
		/*select * from (select a.*, row_number() over (order by bk_no desc) as num from 
		(select DISTINCT ps_no,bk_no,member_no,a.m_show_no,m_show_name,bk_date,bk_stat_cd from
		(select a.ps_no,bk_no,member_no,bk_stat_cd,m_show_no,bk_date from
		(select a.bk_no,member_no,bk_stat_cd,bk_date,ps_no
		from book_inf a,bk_s_l b
		where a.BK_NO = b.bk_no and member_no=?) a,perf_sch b
		where a.ps_no = b.ps_no) a,musical_l b
		where a.m_show_no = b.m_show_no) a)
		where num BETWEEN ? and ? order by num asc*/
		
		

		// 쿼리문 내용 담을 list 배열 초기화		
		ArrayList<ReserveList> list = new ArrayList<ReserveList>();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) 
			{
				ReserveList rl = new ReserveList();
				
				rl.setmShowName(rset.getString("m_show_name"));
				rl.setBkNo(rset.getString("bk_no"));
				rl.setBkDate(rset.getDate("bk_date"));
				rl.setBkStatCd(rset.getString("bk_stat_cd"));
				rl.setNum(Integer.parseInt(rset.getString("num")));
				
				list.add(rl);
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

	public String getRPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage,
			int memberNo) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		// 게시물의 토탈 개수를 구해야 함
		int recordTotalCount = 0; // 초기값은 정보가 없으므로 0으로 셋팅

		String query = "select count(*) as totalcount from\r\n" + 
				"(select a.*, row_number() over (order by bk_no desc) as num from\r\n" + 
				"(select DISTINCT ps_no,bk_no,member_no,a.m_show_no,m_show_name,bk_date,bk_stat_cd from\r\n" + 
				"(select a.ps_no,bk_no,member_no,bk_stat_cd,m_show_no,bk_date from\r\n" + 
				"(select a.bk_no,member_no,bk_stat_cd,bk_date,ps_no\r\n" + 
				"from book_inf a,bk_s_l b\r\n" + 
				"where a.BK_NO = b.bk_no and member_no=?) a,perf_sch b\r\n" + 
				"where a.ps_no = b.ps_no) a,musical_l b\r\n" + 
				"where a.m_show_no = b.m_show_no) a)";
		
		//	select count(*) as totalcount from (select DISTINCT a.*, row_number() over (order by bk_no desc) as num from (select ps_no,bk_no,member_no,a.m_show_no,m_show_name,bk_date,bk_stat_cd from (select a.ps_no,bk_no,member_no,bk_stat_cd,m_show_no,bk_date from (select a.bk_no,member_no,bk_stat_cd,bk_date,ps_no from book_inf a,bk_s_l b where a.BK_NO = b.bk_no and member_no=?) a,perf_sch b where a.ps_no = b.ps_no) a,musical_l b where a.m_show_no = b.m_show_no) a)

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, memberNo);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				recordTotalCount = rset.getInt("TOTALCOUNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		// 구해온 게시물의 토탈 개수를 바탕으로 페이지의 토탈 개수를 구해야 함
		// 즉, 게시물이 124개 라면?
		// page는 총 13개가 되어야 함 (페이지당 10개의 게시물이라고 정하였을때 기준)

		int pageToTalCount = 0; // 정보가 없으므로 초기값은 0 셋팅

		// 페이지의 토탈 개수 구하는 공식
		// 게시물토탈 개수 / 10 + 1(조건에 따라 적용)

		if (recordTotalCount % recordCountPerPage != 0) {
			pageToTalCount = recordTotalCount / recordCountPerPage + 1;
		} else {
			pageToTalCount = recordTotalCount / recordCountPerPage;
		}

		// 에러 방지 코드
		if (currentPage < 1) {
			currentPage = 1;
		} else if (currentPage > pageToTalCount) {
			currentPage = pageToTalCount;
		}

		// 현재 페이지를 기점으로 시작 navi 와 끝 navi를 만들어야 함

		// 현재 페이지가 만약 1이라면 ? 1 2 3 4 5
		// 현재 페이지가 만약 3이라면 ? 1 2 3 4 5
		// 현재 페이지가 만약 7이라면 ? 6 7 8 9 10
		// 현재 페이지가 만약 12이라면? 11 12 13 14 15

		// 시작 페이지를 구하는 공식
		// ((현재페이지-1)/리스트개수)*리스트개수+1

		// 만약 1페이지 라면?
		// ((1-1)/5)*5+1 => 1

		// 만약 3페이지 라면?
		// ((3-1)/5)*5+1 => 1

		// 만약 7페이지 라면?
		// ((7-1)/5)*5+1 => 6

		// 만약 12페이지 라면?
		// ((12-1)/5)*5+1 => 11

		// 시작 페이지 구하는 공식 대입
		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;

		// 끝 페이지를 구하는 공식
		// 시작navi + 보여줄 navi 개수 -1;
		// ex. 시작 navi가 11 이라면?
		// 11 + 5 -1 => 15 가 나옴
		// 즉, 11 12 13 14 15 가 구해짐

		int endNavi = startNavi + naviCountPerPage - 1;

		// 끝 navi를 구할때 주의해야 항점
		// 토탈 개수가 122개 라고 할때 ( 총 토탈 페이지는 13개)
		// 1 2 3 4 5
		// 6 7 8 9 10
		// 11 12 13 14 15
		// 토탈 페이지를 고려하지 않고 만들게 되면 위에 처럼 끝 navi가 이상하게 구해지게 됨

		if (endNavi > pageToTalCount) {
			endNavi = pageToTalCount;
		}

		// 페이지를 표현하는 navi에서 사용할 '<' 모양과 '>'모양을 쓰기위해
		// 필요한 변수 2개를 생성 (변수에 값에 따라서 시작 부분과 끝부분은 표현하지 않기 위해)

		boolean needPrev = true;
		boolean needNext = true;

		if (startNavi == 1) {
			needPrev = false;
		}
		if (endNavi == pageToTalCount) {
			needNext = false;
		}

		StringBuilder sb = new StringBuilder();

		// needPrev는 시작페이지가 1이면 false, 시작페이지가 1이 아니라면 true
		if (needPrev == true) // 시작 페이지가 1페이지가 아니라면!
		{
			sb.append("<a href='/reserveList.do?currentPage=" + (startNavi - 1) + "'> < </a>");
		}
		// 현재 내 위치(startNavi값)가 2라면? '<' 버튼을 클릭하면 1페이지로 이동해야 함
		// <a href='/noticeList.do?cureentPage=1> < </a>

		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) {
				// 현재 페이지가 내가 있는 위치페이지와 같다면 진하게 표시
				sb.append("<a href='/reserveList.do?currentPage=" + i + "'><B style='font-size:30px'>" + i
						+ "</B></a> ");
				// <a href='/noticeList.do?cureentPage=1'><B>1</B></a>
			} else {
				// 현재 페이지가 내가 있는 위치 페이지와 다르다면 일반 표시
				sb.append("<a href='/reserveList.do?currentPage=" + i + "'>" + i + "</a> ");
			}
		}

		if (needNext) {
			sb.append("<a href='/reserveList.do?currentPage=" + (endNavi + 1) + "'> > </a>");
		}

		return sb.toString();
		
	}

	public ArrayList<QnaMgr> getQnaCurrentPage(Connection conn, int currentPage, int recordCountPerPage,
			String memberId) {
		

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		//시작 게시물 계산
		int start = currentPage * recordCountPerPage - (recordCountPerPage-1);
		//ex) currentPage가 3 이고, recordCountPerPage 5라면?
		//만약 요청한 페이지가 3페이지라면? -> 11이 나와야 됨
			// 3 * 5 - (5-1) 	=> 11
		
		
		//끝 게시물 계산
		int end = currentPage * recordCountPerPage;
		// 만약 요청한 페이지가 3페이지라면? -> 15
			// 3 * 5 	=> 15
		
		
		// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 쿼리문 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		String query = "select * from\r\n" + 
				"(select a.*,row_number() over (order by boardq_date asc) as num from\r\n" + 
				"(select boardq_title, boardq_hit, boardq_date, b.member_Id from \r\n" + 
				"board_qna a, member b\r\n" + 
				"where a.boardq_writer=b.member_Id) a\r\n" + 
				"where member_Id = ?)\r\n" + 
				"where num between ? and ?\r\n" + 
				"order by num asc";

		// 쿼리문 내용 담을 list 배열 초기화		
		ArrayList<QnaMgr> list = new ArrayList<QnaMgr>();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) 
			{
				QnaMgr qm = new QnaMgr();
				
				qm.setNum(rset.getString("num"));
				qm.setBoardqTitle(rset.getString("boardq_title"));
				qm.setBoardqDate(rset.getDate("boardq_date"));
				qm.setBoardqHit(rset.getInt("boardq_hit"));
				
				list.add(qm);
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

	public String getQnaPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage,
			String memberId) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		// 게시물의 토탈 개수를 구해야 함
		int recordTotalCount = 0; // 초기값은 정보가 없으므로 0으로 셋팅

		String query = "select count(*) as totalcount from\r\n" + 
				"(select a.*,row_number() over (order by boardq_date asc) as num from\r\n" + 
				"(select boardq_title, boardq_hit, boardq_date, b.member_Id from \r\n" + 
				"board_qna a, member b\r\n" + 
				"where a.boardq_writer=b.member_id) a\r\n" + 
				"where member_id = ?)";

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, memberId);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				recordTotalCount = rset.getInt("TOTALCOUNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		// 구해온 게시물의 토탈 개수를 바탕으로 페이지의 토탈 개수를 구해야 함
		// 즉, 게시물이 124개 라면?
		// page는 총 13개가 되어야 함 (페이지당 10개의 게시물이라고 정하였을때 기준)

		int pageToTalCount = 0; // 정보가 없으므로 초기값은 0 셋팅

		// 페이지의 토탈 개수 구하는 공식
		// 게시물토탈 개수 / 10 + 1(조건에 따라 적용)

		if (recordTotalCount % recordCountPerPage != 0) {
			pageToTalCount = recordTotalCount / recordCountPerPage + 1;
		} else {
			pageToTalCount = recordTotalCount / recordCountPerPage;
		}

		// 에러 방지 코드
		if (currentPage < 1) {
			currentPage = 1;
		} else if (currentPage > pageToTalCount) {
			currentPage = pageToTalCount;
		}

		// 현재 페이지를 기점으로 시작 navi 와 끝 navi를 만들어야 함

		// 현재 페이지가 만약 1이라면 ? 1 2 3 4 5
		// 현재 페이지가 만약 3이라면 ? 1 2 3 4 5
		// 현재 페이지가 만약 7이라면 ? 6 7 8 9 10
		// 현재 페이지가 만약 12이라면? 11 12 13 14 15

		// 시작 페이지를 구하는 공식
		// ((현재페이지-1)/리스트개수)*리스트개수+1

		// 만약 1페이지 라면?
		// ((1-1)/5)*5+1 => 1

		// 만약 3페이지 라면?
		// ((3-1)/5)*5+1 => 1

		// 만약 7페이지 라면?
		// ((7-1)/5)*5+1 => 6

		// 만약 12페이지 라면?
		// ((12-1)/5)*5+1 => 11

		// 시작 페이지 구하는 공식 대입
		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;

		// 끝 페이지를 구하는 공식
		// 시작navi + 보여줄 navi 개수 -1;
		// ex. 시작 navi가 11 이라면?
		// 11 + 5 -1 => 15 가 나옴
		// 즉, 11 12 13 14 15 가 구해짐

		int endNavi = startNavi + naviCountPerPage - 1;

		// 끝 navi를 구할때 주의해야 항점
		// 토탈 개수가 122개 라고 할때 ( 총 토탈 페이지는 13개)
		// 1 2 3 4 5
		// 6 7 8 9 10
		// 11 12 13 14 15
		// 토탈 페이지를 고려하지 않고 만들게 되면 위에 처럼 끝 navi가 이상하게 구해지게 됨

		if (endNavi > pageToTalCount) {
			endNavi = pageToTalCount;
		}

		// 페이지를 표현하는 navi에서 사용할 '<' 모양과 '>'모양을 쓰기위해
		// 필요한 변수 2개를 생성 (변수에 값에 따라서 시작 부분과 끝부분은 표현하지 않기 위해)

		boolean needPrev = true;
		boolean needNext = true;

		if (startNavi == 1) {
			needPrev = false;
		}
		if (endNavi == pageToTalCount) {
			needNext = false;
		}

		StringBuilder sb = new StringBuilder();

		// needPrev는 시작페이지가 1이면 false, 시작페이지가 1이 아니라면 true
		if (needPrev == true) // 시작 페이지가 1페이지가 아니라면!
		{
			sb.append("<a href='/qnaMgr.do?currentPage=" + (startNavi - 1) + "'> < </a>");
		}
		// 현재 내 위치(startNavi값)가 2라면? '<' 버튼을 클릭하면 1페이지로 이동해야 함
		// <a href='/noticeList.do?cureentPage=1> < </a>

		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) {
				// 현재 페이지가 내가 있는 위치페이지와 같다면 진하게 표시
				sb.append("<a href='/qnaMgr.do?currentPage=" + i + "'><B style='font-size:30px'>" + i
						+ "</B></a> ");
				// <a href='/noticeList.do?cureentPage=1'><B>1</B></a>
			} else {
				// 현재 페이지가 내가 있는 위치 페이지와 다르다면 일반 표시
				sb.append("<a href='/qnaMgr.do?currentPage=" + i + "'>" + i + "</a> ");
			}
		}

		if (needNext) {
			sb.append("<a href='/qnaMgr.do?currentPage=" + (endNavi + 1) + "'> > </a>");
		}

		return sb.toString();
		
		
	}

	public int reserveDelete(Connection conn, String bkNo) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update book_inf set bk_stat_cd=? where bk_no=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, "RSV_CNL");
			pstmt.setString(2, bkNo);
			
			result = pstmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
				
		return result;
	}

	public ArrayList<PromoMgr> getPromoCurrentPage(Connection conn, int currentPage, int recordCountPerPage,
			int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		//시작 게시물 계산
		int start = currentPage * recordCountPerPage - (recordCountPerPage-1);
		//ex) currentPage가 3 이고, recordCountPerPage 5라면?
		//만약 요청한 페이지가 3페이지라면? -> 11이 나와야 됨
			// 3 * 5 - (5-1) 	=> 11
		
		
		//끝 게시물 계산
		int end = currentPage * recordCountPerPage;
		// 만약 요청한 페이지가 3페이지라면? -> 15
			// 3 * 5 	=> 15
		
		
		// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 쿼리문 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		String query = "select * from\r\n" + 
				"(select a.*, row_number() over(order by boardP_no asc) as num from\r\n" + 
				"(select boardP_no,boardP_writer,boardP_title,boardP_category,boardP_active from \r\n" + 
				"BOARD_PROMO a, (select * from member where member_no=?) b\r\n" + 
				"where a.boardP_writer = b.member_id) a)\r\n" + 
				"where num BETWEEN ? and ? order by num asc";
		
		/* select * from (select a.*, row_number() over(order by boardP_no asc) as num from
		(select boardP_no,boardP_writer,boardP_title,boardP_category,boardP_active from
		BOARD_PROMO a, (select * from member where member_no=?) b
		where a.boardP_writer = b.member_id) a) where num BETWEEN ? and ? order by num asc */

		// 쿼리문 내용 담을 list 배열 초기화		
		ArrayList<PromoMgr> list = new ArrayList<PromoMgr>();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) 
			{
				PromoMgr pm = new PromoMgr();
				
				pm.setNum(rset.getString("num"));
				pm.setBoardP_no(rset.getInt("boardP_no"));
				pm.setBoardP_category(rset.getString("boardP_category"));
				pm.setBoardP_title(rset.getString("boardP_title"));
				pm.setBoardP_writer(rset.getString("boardP_writer"));
				pm.setBoardP_active(rset.getString("boardP_active"));
				
				list.add(pm);
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

	public String getPromoPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage,
			int memberNo) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		// 게시물의 토탈 개수를 구해야 함
		int recordTotalCount = 0; // 초기값은 정보가 없으므로 0으로 셋팅

		String query = "select count(*) as totalcount from\r\n" + 
				"(select boardP_no,boardP_writer,boardP_title,boardP_category,boardP_active from \r\n" + 
				"BOARD_PROMO a, (select * from member where member_no=?) b\r\n" + 
				"where a.boardP_writer = b.member_id) a";
		
		/*select count(*) from (select boardP_no,boardP_writer,boardP_title,boardP_category,boardP_active from
		BOARD_PROMO a, (select * from member where member_no=?) b where a.boardP_writer = b.member_id) a*/

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, memberNo);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				recordTotalCount = rset.getInt("TOTALCOUNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		// 구해온 게시물의 토탈 개수를 바탕으로 페이지의 토탈 개수를 구해야 함
		// 즉, 게시물이 124개 라면?
		// page는 총 13개가 되어야 함 (페이지당 10개의 게시물이라고 정하였을때 기준)

		int pageToTalCount = 0; // 정보가 없으므로 초기값은 0 셋팅

		// 페이지의 토탈 개수 구하는 공식
		// 게시물토탈 개수 / 10 + 1(조건에 따라 적용)

		if (recordTotalCount % recordCountPerPage != 0) {
			pageToTalCount = recordTotalCount / recordCountPerPage + 1;
		} else {
			pageToTalCount = recordTotalCount / recordCountPerPage;
		}

		// 에러 방지 코드
		if (currentPage < 1) {
			currentPage = 1;
		} else if (currentPage > pageToTalCount) {
			currentPage = pageToTalCount;
		}

		// 현재 페이지를 기점으로 시작 navi 와 끝 navi를 만들어야 함

		// 현재 페이지가 만약 1이라면 ? 1 2 3 4 5
		// 현재 페이지가 만약 3이라면 ? 1 2 3 4 5
		// 현재 페이지가 만약 7이라면 ? 6 7 8 9 10
		// 현재 페이지가 만약 12이라면? 11 12 13 14 15

		// 시작 페이지를 구하는 공식
		// ((현재페이지-1)/리스트개수)*리스트개수+1

		// 만약 1페이지 라면?
		// ((1-1)/5)*5+1 => 1

		// 만약 3페이지 라면?
		// ((3-1)/5)*5+1 => 1

		// 만약 7페이지 라면?
		// ((7-1)/5)*5+1 => 6

		// 만약 12페이지 라면?
		// ((12-1)/5)*5+1 => 11

		// 시작 페이지 구하는 공식 대입
		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;

		// 끝 페이지를 구하는 공식
		// 시작navi + 보여줄 navi 개수 -1;
		// ex. 시작 navi가 11 이라면?
		// 11 + 5 -1 => 15 가 나옴
		// 즉, 11 12 13 14 15 가 구해짐

		int endNavi = startNavi + naviCountPerPage - 1;

		// 끝 navi를 구할때 주의해야 항점
		// 토탈 개수가 122개 라고 할때 ( 총 토탈 페이지는 13개)
		// 1 2 3 4 5
		// 6 7 8 9 10
		// 11 12 13 14 15
		// 토탈 페이지를 고려하지 않고 만들게 되면 위에 처럼 끝 navi가 이상하게 구해지게 됨

		if (endNavi > pageToTalCount) {
			endNavi = pageToTalCount;
		}

		// 페이지를 표현하는 navi에서 사용할 '<' 모양과 '>'모양을 쓰기위해
		// 필요한 변수 2개를 생성 (변수에 값에 따라서 시작 부분과 끝부분은 표현하지 않기 위해)

		boolean needPrev = true;
		boolean needNext = true;

		if (startNavi == 1) {
			needPrev = false;
		}
		if (endNavi == pageToTalCount) {
			needNext = false;
		}

		StringBuilder sb = new StringBuilder();

		// needPrev는 시작페이지가 1이면 false, 시작페이지가 1이 아니라면 true
		if (needPrev == true) // 시작 페이지가 1페이지가 아니라면!
		{
			sb.append("<a href='/PromoMgr.do?currentPage=" + (startNavi - 1) + "'> < </a>");
		}
		// 현재 내 위치(startNavi값)가 2라면? '<' 버튼을 클릭하면 1페이지로 이동해야 함
		// <a href='/noticeList.do?cureentPage=1> < </a>

		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) {
				// 현재 페이지가 내가 있는 위치페이지와 같다면 진하게 표시
				sb.append("<a href='/PromoMgr.do?currentPage=" + i + "'><B style='font-size:30px'>" + i
						+ "</B></a> ");
				// <a href='/noticeList.do?cureentPage=1'><B>1</B></a>
			} else {
				// 현재 페이지가 내가 있는 위치 페이지와 다르다면 일반 표시
				sb.append("<a href='/PromoMgr.do?currentPage=" + i + "'>" + i + "</a> ");
			}
		}

		if (needNext) {
			sb.append("<a href='/PromoMgr.do?currentPage=" + (endNavi + 1) + "'> > </a>");
		}

		return sb.toString();
		
	}

	public ArrayList<ShowInfo> mainImg(Connection conn) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select m_show_no,m_show_poster,m_show_name from musical_l";
		ArrayList<ShowInfo> list = new ArrayList<ShowInfo>();

		try {
			pstmt = conn.prepareStatement(query);

			rset = pstmt.executeQuery();

			while(rset.next()) {
				ShowInfo si = new ShowInfo();

				si.setM_show_no(rset.getInt("m_show_no"));
				si.setM_show_poster(rset.getString("m_show_poster"));
				si.setM_show_name(rset.getString("m_show_name"));
				
				list.add(si);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return list;
	}

}
