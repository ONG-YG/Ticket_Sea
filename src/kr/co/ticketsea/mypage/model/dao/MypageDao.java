package kr.co.ticketsea.mypage.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.ticketsea.common.JDBCTemplate;
import kr.co.ticketsea.member.model.vo.Member;
import kr.co.ticketsea.reserve.model.vo.PerformSchedule;
import kr.co.ticketsea.reserve.model.vo.ReserveInfo;

public class MypageDao {

	public ArrayList<ReserveInfo> selectReserveNumber(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select bk_no from book_inf where member_no = ?";
		
		ArrayList<ReserveInfo> list = new ArrayList<ReserveInfo>();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				ReserveInfo ri = new ReserveInfo();
				
				ri.setBk_no(rset.getString("bk_no"));
				list.add(ri);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public PerformSchedule selectPerformSchedule(Connection conn, String bk_no) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PerformSchedule ps = null;
		
		String query = "select * from perf_sch where ps_no=(select ps_no from bk_s_l where bk_no=?)";
		
		try {
			pstmt = conn.prepareStatement(query);			
			pstmt.setString(1, bk_no);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				ps = new PerformSchedule();
				
				ps.setPerformSchNo(rset.getInt("ps_no"));		// 공연회차번호
				ps.setPerformSchDate(rset.getDate("ps_date"));	// 공연일
				ps.setShowNo(rset.getInt("m_show_no"));			// 공연번호

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return ps;
	}

	public String selectMusicalName(Connection conn, PerformSchedule ps) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String mName = null;
		String query = "select m_show_name from musical_l where m_show_no=?";
		
		try {
			pstmt = conn.prepareStatement(query);			
			pstmt.setInt(1, ps.getShowNo());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				mName = rset.getString("m_show_name");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return mName;
		
	}

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

}
