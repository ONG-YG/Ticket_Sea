package kr.co.ticketsea.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.co.ticketsea.common.JDBCTemplate;
import kr.co.ticketsea.member.model.vo.Member;

public class MemberDao {

	public Member selectMember(Connection conn, Member m) {
		 PreparedStatement pstmt = null;
		 ResultSet rset = null;
		 Member member = null;
		 String query = "select * from member where member_id=? "
					+ "and member_pwd=? and member_active='Y'";
		 
		 try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPwd());
			rset=pstmt.executeQuery();
			if(rset.next())
			{
				member = new Member();
				member.setMemberActive(rset.getString("member_active").charAt(0));
				member.setMemberAddr(rset.getString("member_addr"));
				member.setMemberEmail(rset.getString("member_email"));
				member.setMemberGender(rset.getString("member_gender").charAt(0));
				member.setMemberId(rset.getString("member_id"));
				member.setMemberJoinDate(rset.getDate("member_joindate"));
				member.setMemberName(rset.getString("member_name"));
				member.setMemberNo(rset.getInt("member_no"));
				member.setMemberPhone(rset.getString("member_phone"));
				member.setMemberPwd(rset.getString("member_pwd"));
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		 return member;
		
	}

	public int insertMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;	
		
		String query = "insert into member values(SEQ_MEMBER_NO.NEXTVAL,?,?,?,?,?,?,sysdate,'Y',?)";
		
		
		try {
			pstmt = conn.prepareStatement(query);
			
			
			
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberName());
			pstmt.setString(3, m.getMemberPwd());
			pstmt.setString(4, m.getMemberPhone());
			pstmt.setString(5, m.getMemberAddr());
			pstmt.setString(6, m.getMemberEmail());
			
			pstmt.setString(7, m.getMemberGender()+"");
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	public String checkId(String checkId, Connection conn)
	{
		
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select user_id from member where user_id=?";
		
		String userId = null;
		try {
			pstmt = conn.prepareStatement(query);	
			pstmt.setString(1, checkId);		
			rset = pstmt.executeQuery();
			if(rset.next()) {
				userId = rset.getString("user_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
				
		
		return userId;
	}

	public int updateMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;	
		
		String query = "update member set member_pwd=?, member_phone=?,member_email=?,member_addr=?,"
				+ " where user_id = ? ";

		try {
			pstmt = conn.prepareStatement(query);
			
			
			pstmt.setString(1, m.getMemberPwd());
			pstmt.setString(2, m.getMemberPhone());
			pstmt.setString(3, m.getMemberEmail());
			pstmt.setString(4, m.getMemberAddr());
			
			pstmt.setString(5, m.getMemberId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
		
	}

}