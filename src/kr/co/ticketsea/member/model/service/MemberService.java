package kr.co.ticketsea.member.model.service;

import java.sql.Connection;

import kr.co.ticketsea.common.JDBCTemplate;
import kr.co.ticketsea.member.model.dao.MemberDao;
import kr.co.ticketsea.member.model.vo.Member;

public class MemberService {

	public boolean checkId(String checkId) {
		Connection conn = JDBCTemplate.getConnection();
		String userId = new MemberDao().checkId(checkId, conn);
		JDBCTemplate.close(conn);

		if (userId == null) {
			// null인 경우는 해당 ID를 사용하는 사용자가 없다라는 의미kk
			return false; // 리턴 값이 false는 해당 아이디 사용자가 없음값
		} else {
			return true;
		}

	}

	public Member selectMember(Member m) {
		// TODO Auto-generated method stub
		Connection conn = null;
		conn = JDBCTemplate.getConnection();

		Member member = new MemberDao().selectMember(conn, m);

		JDBCTemplate.close(conn);

		return member;

	}

	public int memberJoin(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().insertMember(conn, m);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);

		return result;
	}

	public int updateMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().updateMember(conn, m);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);

		return result;
	}

	public int memberDelete(String userId) {
		int result = 0;
		Connection conn = JDBCTemplate.getConnection();
		result = new MemberDao().memberDelete(conn,userId);
		if(result>0)
		{
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public String idSearchMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();

		String userId = null;
		userId = new MemberDao().idSearchMember(conn, m);

		JDBCTemplate.close(conn);

		return userId;

	}

	public char pwdSearchMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		char userActive = 0;
		
		userActive = new MemberDao().pwdSearchMember(conn, m);
		
		JDBCTemplate.close(conn);
		
		return userActive;
		
	}

}
