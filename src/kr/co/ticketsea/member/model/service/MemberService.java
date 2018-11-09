package kr.co.ticketsea.member.model.service;

import java.sql.Connection;
import java.util.Random;

import kr.co.ticketsea.admin.member.model.vo.MemberPageData;
import kr.co.ticketsea.common.JDBCTemplate;
import kr.co.ticketsea.member.model.dao.MemberDao;
import kr.co.ticketsea.member.model.vo.Member;
import kr.co.ticketsea.member.model.vo.PwdMember;

public class MemberService {

	Random r = new Random();
	
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

	public PwdMember pwdSearchMember(Member m) {
		
		Connection conn = JDBCTemplate.getConnection();
		char userActive = 0;
		String userId = null;
		String ranPwd="";
		// 사용자의 정보를 보낸 후 사용자의 active 값을 넘겨받음
		// Active값이 'Y'면 비밀번호 재설정 후 알려줌
		// 'N'이면 에러페이지
		userActive = new MemberDao().pwdSearchMember(conn, m);
		
		userId= m.getMemberId();
		//랜덤 패스워드를 생성함
		ranPwd=randomPwd();
		
		
		//랜덤 패스워드로 바꿈
		int result=new MemberDao().pwdChange(conn,ranPwd,userId);
		
		if(result>0 && userActive=='Y')
		{
			JDBCTemplate.commit(conn);
			
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		PwdMember pm = new PwdMember();
		
		pm.setRanPwd(ranPwd);
		pm.setResult(result);
		pm.setUserId(userId);
		
		
		return pm;
		
	}

	//랜덤 패스워드 생성기 (8자리)
	public String randomPwd() {
		String ranPwd="";
		int count =0;
		String[] random = {"a","b","c","d","e","f","g",
				"h","i","j","k","l","m","n","o","p","q",
				"r","s","t","u","v","w","x","y","z","1",
				"2","3","4","5","6","7","8","9","0"};
		
		
		
		
		while(count<8)
		{
			
			ranPwd+=random[r.nextInt(36)];
			count++;
			
		}
		
 		
		
		return ranPwd;
	}

}
