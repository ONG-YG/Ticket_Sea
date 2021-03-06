package kr.co.ticketsea.member.service;

import java.sql.Connection;

import kr.co.ticketsea.common.JDBCTemplate;
import kr.co.ticketsea.member.model.dao.MemberDao;
import kr.co.ticketsea.member.model.vo.Member;

public class MemberService {

	public Member selectMember(Member m) {
		// TODO Auto-generated method stub
		Connection conn = null;
		conn=JDBCTemplate.getConnection();
		
		Member member = new MemberDao().selectMember(conn,m);
		
		
		JDBCTemplate.close(conn);
		
		return member;
		
		
		
	
		
	}

	public int memberJoin(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().insertMember(conn,m);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int updateMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().updateMember(conn,m);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}


	public int memberDelete(String userId) {
		int result = 0 ;
		Connection conn = JDBCTemplate.getConnection();
		if(result>0)
		{
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		result = new MemberDao().memberDelete(conn,userId);
		return result;
	}


}
