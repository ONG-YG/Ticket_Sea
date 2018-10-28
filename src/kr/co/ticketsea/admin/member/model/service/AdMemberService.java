package kr.co.ticketsea.admin.member.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.co.ticketsea.member.model.dao.MemberDao;
import kr.co.ticketsea.member.model.vo.*;
import kr.co.ticketsea.admin.member.model.dao.AdMemberDao;
import kr.co.ticketsea.admin.member.model.vo.*;
import kr.co.ticketsea.admin.show.model.vo.Show;
import kr.co.ticketsea.common.JDBCTemplate;

public class AdMemberService {

	public MemberPageData memberAllList(int currentPage) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		//게시물 개수, navi 개수의 값을 저장하기 위한 변수 생성
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;
		
		//1. 현재 페이지의 게시물 리스트 요청
		ArrayList<Member> list = new AdMemberDao().getCurrentPage(conn,currentPage,recordCountPerPage);
		
		String pageNavi = new AdMemberDao().getPageNavi(conn,currentPage,recordCountPerPage,naviCountPerPage);
	
		MemberPageData pd =null;
		
		if(!list.isEmpty() && !pageNavi.isEmpty()) {
			pd= new MemberPageData();
			pd.setList(list);
			pd.setPageNavi(pageNavi);
		}
		JDBCTemplate.close(conn);
		
		return pd;
	}

	public Member selectOneMember(int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		Member m = new AdMemberDao().selectOneMember(conn,memberNo);
		
		JDBCTemplate.close(conn);
		return m;
		
	}

	public int updateMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		int result=new AdMemberDao().updateMember(conn, m);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
		
	}
	public int deleteMember(int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new AdMemberDao().deleteMember(conn,memberNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}


}
