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


}
