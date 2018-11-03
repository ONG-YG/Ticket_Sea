package kr.co.ticketsea.reserve.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.ticketsea.common.JDBCTemplate;
import kr.co.ticketsea.reserve.model.vo.PerformSchedule;
import kr.co.ticketsea.reserve.model.vo.SeatGradeState;
import kr.co.ticketsea.reserve.model.vo.SelectedSeat;
import kr.co.ticketsea.reserve.model.vo.ShowInfo;

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
				ps.setPerformTime(rset.getString("PS_TIME"));
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

//	public int selectOnePerformSchedule(Connection conn, int showNo, String date_sel, String cnt_sel) {
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		int performSchNo = 0;
//		
//		String query = "SELECT PS_NO FROM PERF_SCH WHERE M_SHOW_NO=? AND PS_DATE=? AND PS_CNT=?";
////		System.out.println(showNo);
////		System.out.println(date_sel);
////		System.out.println(cnt_sel);
//		try {
//			pstmt = conn.prepareStatement(query);
//			pstmt.setInt(1, showNo);
//			pstmt.setString(2, date_sel);
//			pstmt.setString(3, cnt_sel);
//			rset = pstmt.executeQuery();
//			
//			if(rset.next()) {
//				performSchNo = rset.getInt("PS_NO");
//			}			
//				
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			JDBCTemplate.close(rset);
//			JDBCTemplate.close(pstmt);
//		}			
//			
//		return performSchNo;
//	}
	
	public PerformSchedule selectOnePerformSchedule(Connection conn, int psNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PerformSchedule ps = null;
		
		String query = "SELECT * FROM PERF_SCH WHERE PS_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, psNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				ps = new PerformSchedule();
				ps.setPerformSchNo(rset.getInt("PS_NO"));
				ps.setPerformSchDate(rset.getDate("PS_DATE"));
				ps.setPerformSchCnt(rset.getInt("PS_CNT"));
				ps.setShowNo(rset.getInt("M_SHOW_NO"));
				ps.setPerformTime(rset.getString("PS_TIME"));
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}			
			
		return ps;
	}

	public ArrayList<Integer> selectReservedSeats(Connection conn, int performSchNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Integer> reserved_seats = new ArrayList<Integer>();
		
		String query = "SELECT TH1_SEAT_NO FROM BK_S_L WHERE PS_NO=? "
						+ "AND BK_NO IN (SELECT BK_NO FROM BOOK_INF WHERE BK_STAT_CD='RSV_CPL')";
		
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
	
	public ArrayList<Integer> selectProgressingSeats(Connection conn, int psNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Integer> prog_seats = new ArrayList<Integer>();
		
		String query = "SELECT TH1_SEAT_NO FROM PROG_S_L "
				+ "WHERE PS_NO=? AND "
				+ "(SYSDATE-PROG_TIME)*24*60 < 5";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, psNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				int seatNo = rset.getInt("TH1_SEAT_NO");
				prog_seats.add(seatNo);
			}			
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		
		return prog_seats;
	}

	public ShowInfo getShowInfo(Connection conn, int showNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ShowInfo si = null;
		
		String query = "SELECT * FROM MUSICAL_L WHERE M_SHOW_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, showNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				si = new ShowInfo();
				si.setM_show_no(showNo);
				si.setTh_no(rset.getInt("TH_NO"));
				si.setSc_code(rset.getString("SC_CODE"));
				si.setM_show_name(rset.getString("M_SHOW_NAME"));
				si.setM_show_st_date(rset.getDate("M_SHOW_ST_DATE"));
				si.setM_show_ed_date(rset.getDate("M_SHOW_ED_DATE"));
				si.setBk_comm(rset.getInt("BK_COMM"));
				si.setM_show_poster(rset.getString("M_SHOW_POSTER"));
						//System.out.println(si);
			}			
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}		
		
		return si;
	}

	public int reservedSeatCount(Connection conn, int performSchNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int reservedSeat = -1;
		
		String query = "SELECT COUNT(*) AS CNT FROM BK_S_L WHERE PS_NO=? "
						+ "AND BK_NO IN (SELECT BK_NO FROM BOOK_INF WHERE BK_STAT_CD='RSV_CPL')";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, performSchNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				reservedSeat = rset.getInt("CNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return reservedSeat;
	}

	public int totalSeatCount(Connection conn, int performSchNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalSeat = -1;
		
		String query = "SELECT COUNT(*) AS CNT FROM TH1_SEAT_L WHERE TH_NO="
						+ "(SELECT TH_NO FROM MUSICAL_L WHERE M_SHOW_NO="
						+ "(SELECT M_SHOW_NO FROM PERF_SCH WHERE PS_NO=?))";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, performSchNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				totalSeat = rset.getInt("CNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return totalSeat;
	}

	public int getShowNoBypsNo(Connection conn, int psNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int showNo = -1;
		
		String query = "SELECT M_SHOW_NO FROM PERF_SCH WHERE PS_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, psNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				showNo = rset.getInt("M_SHOW_NO");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return showNo;
	}

	public String getTheaterName(Connection conn, int th_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String thName = null;
		
		String query = "SELECT TH_NAME FROM THEATER_L WHERE TH_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, th_no);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				thName = rset.getString("TH_NAME");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return thName;
	}

	public ArrayList<SeatGradeState> getSeatGradeAndPrice(Connection conn, int psNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<SeatGradeState> seatGrdStList = new ArrayList<SeatGradeState>();
		
		String query = "SELECT * FROM TH1_PRICE WHERE TH_NO="
						+ "(SELECT TH_NO FROM MUSICAL_L WHERE M_SHOW_NO="
						+ "(SELECT M_SHOW_NO FROM PERF_SCH WHERE PS_NO=?))";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, psNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				SeatGradeState seatGrdSt = new SeatGradeState();
				seatGrdSt.setTh1_seat_grd(rset.getString("TH1_SEAT_GRD"));
				seatGrdSt.setTh1_seat_prc(rset.getInt("TH1_SEAT_PRC"));
				seatGrdSt.setGrd_color(rset.getString("GRD_COL"));
				
				seatGrdStList.add(seatGrdSt);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return seatGrdStList;
	}

	public int getSeatGradeTotCnt(Connection conn, String grade) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalSeat = -1;
		
		String query = "SELECT COUNT(*) AS CNT FROM TH1_SEAT_L WHERE TH1_SEAT_GRD=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, grade);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				totalSeat = rset.getInt("CNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return totalSeat;
	}

	public int getSeatGradeReservedCnt(Connection conn, int psNo, String grade) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int reservedSeat = -1;
		
		String query = "SELECT COUNT(*) AS CNT FROM BK_S_L " + 
						"WHERE PS_NO=? " + 
						"AND TH1_SEAT_NO IN (SELECT TH1_SEAT_NO FROM TH1_SEAT_L WHERE TH1_SEAT_GRD=?) " + 
						"AND BK_NO IN (SELECT BK_NO FROM BOOK_INF WHERE BK_STAT_CD='RSV_CPL')";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, psNo);
			pstmt.setString(2, grade);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				reservedSeat = rset.getInt("CNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return reservedSeat;
	}

	public int createProgNo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int progNo = -1;
		
		String query = "SELECT SEQ_PROGNO.NEXTVAL AS PROGNO FROM DUAL";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				progNo=rset.getInt("PROGNO");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return progNo;
	}

	public int insertProgData(Connection conn, int progNo, int memberNo, int psNo, int seatNo, String progTime) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "INSERT INTO PROG_S_L VALUES (?,?,?,?,"
							+ "TO_DATE(?,'YYYY-MM-DD HH24:MI:SS'))";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, progNo);
			pstmt.setInt(2, memberNo);
			pstmt.setInt(3, psNo);
			pstmt.setInt(4, seatNo);
			pstmt.setString(5, progTime);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}


	public String getSysdate(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String progTime = null;
		
		String query = "SELECT SYSDATE FROM DUAL";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				String timestamp = rset.getTimestamp("SYSDATE").toString();
				progTime = timestamp.substring(0,timestamp.length()-2);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return progTime;
	}

	public String[] getPhoneMail(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String[] contactInfo = null;
		
		String query = "SELECT * FROM MEMBER WHERE MEMBER_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				String memberName = rset.getString("MEMBER_NAME");
				String memberPhone = rset.getString("MEMBER_PHONE");
				String memberEmail = rset.getString("MEMBER_EMAIL");
				
				contactInfo = new String[] {memberName, memberPhone, memberEmail};
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return contactInfo;
	}

	public SelectedSeat getSeatInfo(Connection conn, String seatNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		SelectedSeat selSeat = null;
		
		String query = "SELECT * FROM TH1_SEAT_L WHERE TH1_SEAT_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, seatNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				selSeat = new SelectedSeat();
				selSeat.setSeatNo(rset.getInt("TH1_SEAT_NO"));
				selSeat.setSeatGrd(rset.getString("TH1_SEAT_GRD"));
				//selSeat.setSeatPrice();
				selSeat.setSeatTitle(rset.getString("SEAT_TITLE"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return selSeat;
	}

	public long createBookNo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		long bkNo = -1;
		
		String query = "SELECT TO_NUMBER(TO_CHAR(SYSDATE, 'YYMMDD'))*100000000+SEQ_BKNO.NEXTVAL AS BKNO FROM DUAL";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				bkNo=rset.getLong("BKNO");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return bkNo;
	}

	
}
