package kr.co.ticketsea.reserve.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.ticketsea.common.JDBCTemplate;
import kr.co.ticketsea.reserve.model.vo.PerformSchedule;

public class ReserveDao {
	
	public int dbTest(Connection conn) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "SELECT * FROM MEMBER";
		
		try {
			pstmt = conn.prepareStatement(query);
			//pstmt.setString(1, temp);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public ArrayList<PerformSchedule> selectAllPerformSchedule(Connection conn, int showNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<PerformSchedule> psList = new ArrayList<PerformSchedule>();
		
		String query = "SELECT * FROM PERF_SCH WHERE M_SHOW_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, showNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				PerformSchedule ps = new PerformSchedule();
				ps.setPerformSchNo(rset.getInt("PS_NO"));
				ps.setPerformSchDate(rset.getDate("PS_DATE"));
				ps.setPerformSchCnt(rset.getInt("PS_CNT"));
				ps.setShowNo(rset.getInt("M_SHOW_NO"));
				psList.add(ps);
			}			
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return psList;
	}

	public int selectOnePerformSchedule(Connection conn, int showNo, String date_sel, String cnt_sel) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int performSchNo = 0;
		
		String query = "SELECT PS_NO FROM PERF_SCH WHERE M_SHOW_NO=? AND PS_DATE=? AND PS_CNT=?";
//		System.out.println(showNo);
//		System.out.println(date_sel);
//		System.out.println(cnt_sel);
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, showNo);
			pstmt.setString(2, date_sel);
			pstmt.setString(3, cnt_sel);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				performSchNo = rset.getInt("PS_NO");
			}			
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}			
			
		return performSchNo;
	}

	public ArrayList<Integer> selectReservedSeats(Connection conn, int performSchNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Integer> reserved_seats = new ArrayList<Integer>();
		
		String query = "SELECT TH1_SEAT_NO FROM BK_S_L WHERE PS_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, performSchNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				int seatNo = rset.getInt("TH1_SEAT_NO");
				reserved_seats.add(seatNo);
			}			
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		
		return reserved_seats;
	}
}
