package kr.co.ticketsea.admin.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.ticketsea.member.model.vo.Member;
import kr.co.ticketsea.common.JDBCTemplate;

public class AdMemberDao {

	public ArrayList<Member> getCurrentPage(Connection conn, int currentPage, int recordCountPerPage) {
		PreparedStatement pstmt=null;
		ResultSet rset= null;
		
		//시작게시물 계산
		int start = currentPage*recordCountPerPage-(recordCountPerPage-1);
		
		//끝 게시물 계산
		int end = currentPage * recordCountPerPage;
		
		String query="select * from (select row_number() over(order by member_no desc) num, member.* from member) where num between ? and ?";
		
		ArrayList<Member> list = new ArrayList<Member>();

		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Member m = new Member();
				m.setMemberNo(rset.getInt("member_no"));
				m.setMemberId(rset.getString("member_id"));
				m.setMemberName(rset.getString("member_name"));
				m.setMemberActive(rset.getString("member_active").charAt(0));
				m.setMemberGender(rset.getString("member_gender").charAt(0));
				m.setMemberAddr(rset.getString("member_addr"));
				m.setMemberEmail(rset.getString("member_email"));
				m.setMemberJoinDate(rset.getDate("member_joindate"));
				m.setMemberPhone(rset.getString("member_phone"));
				
				list.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public String getPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage) {
		PreparedStatement pstmt=null;
		ResultSet rset = null;
		
		//게시물의 토탈 개수
		int recordTotalCount = 0;
		String query = "select count(*) as totalcount from member";
		
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
		int pageTotalCount = 0; //정보가 없으므로 초기값은 0 셋팅
		
		//페이지의 토탈 개수 구하는 공식
		//게시물 토탈 개수 / 10 + 1(조건에 따라 적용)
		
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
		//시작페이지 구하는 공식 대입
		int startNavi = ((currentPage-1)/naviCountPerPage)*naviCountPerPage+1;
		
		//끝페이지 구하는 공식 대입
		int endNavi = startNavi + naviCountPerPage -1;
		
		if(endNavi>pageTotalCount) {
			endNavi=pageTotalCount;
		}
		
		//'<' '>'
		boolean needPrev = true;
		boolean needNext = true;
		
		if(startNavi==1) { needPrev = false;}
		if(endNavi == pageTotalCount){needNext=false;}
		
		StringBuilder sb = new StringBuilder();
		//needPrev는 시작페이지가 1이면 false, 시작페이지가 1이 아니라면 true
		if(needPrev==true) { //시작페이지가 1페이지가 아니라면
			sb.append("<a href='/adMemberList.do?currentPage="+(startNavi-1)+"'> < </a> ");
		}
		//현재 내 위치 (startNavi값)가 2라면? '<' 버튼을 클릭하면 1페이지로 이동함
		//<a href='/noticeList.do?currentPage=(2-1)> < </a> =>
		
		for(int i=startNavi;i<=endNavi;i++) {
			if(i==currentPage) { // 현재 페이지가 내가 있는 위치 페이지와 같다면 진하게 표시
				sb.append("<a href='/adMemberList.do?currentPage="+i+"'><B style='font-size:20px'>"+i+"</B></a> ");
				//<a href='/noticeList.do?currentPage=1'><B>1</B></a>
			}else { // 현재 페이지가 내가 있는 위치 페이지와 다르다면 일반 표시
				sb.append("<a href='/adMemberList.do?currentPage="+i+"'>"+i+"</a> ");
			}
		}
		
		if(needNext) {
			sb.append("<a href='/adMemberList.do?currentPage="+(endNavi+1)+"'> > </a> ");
		}
		
		
		return sb.toString();
		
	}

	public Member selectOneMember(Connection conn, int memberNo) {
		PreparedStatement pstmt= null;
		ResultSet rset=null;
		Member m = null;
		String query = "select * from member where member_no=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				m=new Member();
				m.setMemberNo(rset.getInt("member_no"));
				m.setMemberId(rset.getString("member_id"));
				m.setMemberName(rset.getString("member_name"));
				m.setMemberActive(rset.getString("member_active").charAt(0));
				m.setMemberGender(rset.getString("member_gender").charAt(0));
				m.setMemberAddr(rset.getString("member_addr"));
				m.setMemberPhone(rset.getString("member_phone"));
				m.setMemberEmail(rset.getString("member_email"));
				m.setMemberJoinDate(rset.getDate("member_joindate"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return m;
	}

	public int updateMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;	
		
		String query="update member set member_phone=?, member_addr=?, member_email=? where member_no=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			
			pstmt.setString(1, m.getMemberPhone());
			pstmt.setString(2, m.getMemberAddr());
			pstmt.setString(3, m.getMemberEmail());
			pstmt.setInt(4, m.getMemberNo());
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int deleteMember(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		int result = 0;	
		
		String query = "delete from member where member_no=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;

	}

	public ArrayList<Member> getSearchCurrentPage(Connection conn, int currentPage, int recordCountPerPage,
			String keyword) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		
		//시작 게시물 계산
		int start = currentPage*recordCountPerPage-(recordCountPerPage-1);
		//만약 요청한 페이지가 1페이지라면 ? => 1이 나와야함
			//1*10-(10-1)=>1
		//만약 요청한 페이지가 4페이지라면 ? -> 31이 나와야함
		// ex) currentPage가 3이고 recordCountPerPage가 5라면?
		
		//끝 게시물 계산
		int end = currentPage* recordCountPerPage;
		
		//만약 요청한 페이지가 1페이지라면 ? -> 10
			//1*10 =>10
		
		String query = "select * from (select member.*, row_number() over(order by member_no desc) as num from member where member_name like ?) where num between ? and ?";
		
		ArrayList<Member> list = new ArrayList<Member>();
		
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setString(1, '%'+keyword+'%');
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Member member =new Member();
				member.setMemberNo(rset.getInt("member_no"));
				member.setMemberId(rset.getString("member_id"));
				member.setMemberName(rset.getString("member_name"));
				member.setMemberActive(rset.getString("member_active").charAt(0));
				member.setMemberGender(rset.getString("member_gender").charAt(0));
				member.setMemberAddr(rset.getString("member_addr"));
				member.setMemberEmail(rset.getString("member_email"));
				member.setMemberJoinDate(rset.getDate("member_joindate"));
				member.setMemberPhone(rset.getString("member_phone"));
				
				list.add(member);
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

	public String getSearchPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage,
			String keyword) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		
		//게시물의 토탈 개수를 구해야함
		int recordTotalCount=0; //초기값은 정보가 없으므로 0으로 세팅
		
		String query ="select count(*) as totalcount from member where member_name like ?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, '%'+keyword+'%');
			rset= pstmt.executeQuery();
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
		//즉, 게시물이 124개라면?
		//page는 총 13개가 되어야함(페이지당 10개의 게시물이라고 정하였을때 기준)
		
		int pageTotalCount = 0; //정보가 없으므로 초기값은 0 셋팅
		
		//페이지의 토탈 개수 구하는 공식
		//게시물 토탈 개수 / 10 + 1(조건에 따라 적용)
		
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

		//현재 페이지를 기점으로 시작 navi와 끝 navi를 만들어야함
		
		//현재 페이지가 만약 1이라면 > 1 2 3 4 5 가 보여야함
		//만약 3이라면? 1 2 3 4 5
		
		// 시작페이지를 구하는 공식
		// ((현재페이지-1)/리스트개수)*리스트개수+1
		
		//만약 1페이지라면?
		// ((1-1)/5)*5+1 =>1
		
		//만약 7페이지라면?
		// ((7-1)/5)*5+1 => 6
		
		//시작페이지 구하는 공식 대입
		int startNavi = ((currentPage-1)/naviCountPerPage)*naviCountPerPage+1;
		
		//끝페이지를 구하는 공식
		
		//시작 navi+ 보여줄 navi 개수 -1;
		//ex. 시작 navi가 11이라면?
		// 11+5-1 => 15가 나옴
		// 즉, 11 12 13 14 15 가 구해짐
		
		int endNavi = startNavi + naviCountPerPage -1;
	
		// 끝 navi를 구할 때 주의해야할점
		// 토탈 개수가 122개라면 총 토탈페이지는 13개 만들어져야함
		// 11 12 13 14 15
		// 토탈 페이지를 고려하지 않고 만들게 되면 끝 navi가 이상하게구해질 수 있음
		if(endNavi>pageTotalCount) {
			endNavi=pageTotalCount;
		}
		
		// 페이지를 표현하는 navi에서 사용할 '<' 모양과 '>'모양을 쓰기위해
		// 필요한 변수 2개를 생성 (변수의 값에 따라서 시작 부분과 끝 부분은 표현하지 않기위해)
		
		boolean needPrev = true;
		boolean needNext = true;
		
		if(startNavi==1) { needPrev = false;}
		if(endNavi == pageTotalCount){needNext=false;}
		
		StringBuilder sb = new StringBuilder();
		//needPrev는 시작페이지가 1이면 false, 시작페이지가 1이 아니라면 true
		if(needPrev==true) { //시작페이지가 1페이지가 아니라면
			sb.append("<a href='/memberSearch.do?search="+keyword+"&currentPage="+(startNavi-1)+"'> < </a> ");
		}
		//현재 내 위치 (startNavi값)가 2라면? '<' 버튼을 클릭하면 1페이지로 이동함
		//<a href='/noticeList.do?currentPage=(2-1)> < </a> =>
		
		for(int i=startNavi;i<=endNavi;i++) {
			if(i==currentPage) { // 현재 페이지가 내가 있는 위치 페이지와 같다면 진하게 표시
				sb.append("<a href='/memberSearch.do?search="+keyword+"&currentPage="+i+"'><B style='font-size:20px'>"+i+"</B></a> ");
				//<a href='/noticeList.do?currentPage=1'><B>1</B></a>
			}else { // 현재 페이지가 내가 있는 위치 페이지와 다르다면 일반 표시
				sb.append("<a href='/memberSearch.do?search="+keyword+"&currentPage="+i+"'>"+i+"</a> ");
			}
		}
		
		if(needNext) {
			sb.append("<a href='/memberSearch.do?search="+keyword+"&currentPage="+(endNavi+1)+"'> > </a> ");
		}
		
		return sb.toString();
	}


}
