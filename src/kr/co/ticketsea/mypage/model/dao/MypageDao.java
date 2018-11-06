package kr.co.ticketsea.mypage.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.ticketsea.common.JDBCTemplate;
import kr.co.ticketsea.member.model.vo.Member;
import kr.co.ticketsea.mypage.model.vo.ReserveList;

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
		
		String query="update member set member_phone=?, member_addr=?, member_email=? where member_id=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			
			pstmt.setString(1, m.getMemberPhone());
			pstmt.setString(2, m.getMemberAddr());
			pstmt.setString(3, m.getMemberEmail());
			pstmt.setString(4, m.getMemberId());
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<ReserveList> getCurrentPage(Connection conn, int currentPage, int recordCountPerPage, int memberNo) {
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
				"(select DISTINCT a.*, ROW_NUMBER() OVER (ORDER BY bk_no desc) AS num from\r\n" + 
				"(select ps_no,bk_no,member_no,a.m_show_no,m_show_name,bk_date,bk_stat_cd from \r\n" + 
				"(select a.ps_no,bk_no,member_no,bk_stat_cd,m_show_no,bk_date from \r\n" + 
				"(select a.bk_no,member_no,bk_stat_cd,bk_date,ps_no \r\n" + 
				"from book_inf a,bk_s_l b \r\n" + 
				"where a.BK_NO = b.bk_no and member_no=?) a,perf_sch b\r\n" + 
				"where a.ps_no = b.ps_no) a,musical_l b\r\n" + 
				"where a.m_show_no = b.m_show_no) a)\r\n" + 
				"where num BETWEEN ? and ?\r\n" + 
				"order by num asc";		

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
				
				rl.setmShowName(rset.getString("m_show_name"));			// 공연명
				rl.setBkDate(rset.getDate("bk_date"));					// 예매일
				rl.setBkStatCd(rset.getString("bk_stat_cd"));			// 예매 상태
				rl.setNum(Integer.parseInt(rset.getString("num")));		// 번호
				
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

	public String getPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 게시물의 토탈 개수를 구해야 함
		int recordTotalCount = 0; //초기값은 정보가 없으므로 0으로 셋팅
		
		String query = "select count(*) as totalcount from \r\n" + 
				"(select DISTINCT a.*, ROW_NUMBER() OVER (ORDER BY bk_no desc) AS num from\r\n" + 
				"(select ps_no,bk_no,member_no,a.m_show_no,m_show_name,bk_date,bk_stat_cd from \r\n" + 
				"(select a.ps_no,bk_no,member_no,bk_stat_cd,m_show_no,bk_date from \r\n" + 
				"(select a.bk_no,member_no,bk_stat_cd,bk_date,ps_no \r\n" + 
				"from book_inf a,bk_s_l b \r\n" + 
				"where a.BK_NO = b.bk_no and member_no=?) a,perf_sch b\r\n" + 
				"where a.ps_no = b.ps_no) a,musical_l b\r\n" + 
				"where a.m_show_no = b.m_show_no) a)";
		
		 try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, memberNo);
			
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
			sb.append("<a href='/reserveList.do?currentPage="+(startNavi-1)+"'> < </a>");
		}
		// 현재 내 위치(startNavi값)가 2라면? '<' 버튼을 클릭하면 1페이지로 이동해야 함
		// <a href='/noticeList.do?cureentPage=1> < </a>
		
		for(int i=startNavi; i<=endNavi;i++){
			if(i==currentPage)
			{
				// 현재 페이지가 내가 있는 위치페이지와 같다면 진하게 표시
				sb.append("<a href='/reserveList.do?currentPage="+i+"'><B style='font-size:30px'>"+i+"</B></a> ");
				//<a href='/noticeList.do?cureentPage=1'><B>1</B></a>
			}
			else
			{
				// 현재 페이지가 내가 있는 위치 페이지와 다르다면 일반 표시
				sb.append("<a href='/reserveList.do?currentPage="+i+"'>"+i+"</a> ");
			}
		}
		
		if(needNext) 
		{
			sb.append("<a href='/reserveList.do?currentPage="+(endNavi+1)+"'> > </a>");
		}
		
		return sb.toString();	
	}

}
