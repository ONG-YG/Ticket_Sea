package kr.co.ticketsea.reserve.model.service;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

import kr.co.ticketsea.common.JDBCTemplate;
import kr.co.ticketsea.reserve.model.dao.ReserveDao;
import kr.co.ticketsea.reserve.model.vo.PerformSchedule;
import kr.co.ticketsea.reserve.model.vo.SeatGradeState;
import kr.co.ticketsea.reserve.model.vo.ShowInfo;

public class ReserveService {

	public ArrayList<PerformSchedule> selectAllPerformSchedule(int showNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<PerformSchedule> psList = new ReserveDao().selectAllPerformSchedule(conn, showNo);		
		
		JDBCTemplate.close(conn);		
		
		return psList;
	}

//	public int selectOnePerformSchedule(int showNo, String date_sel, String cnt_sel) {
//		Connection conn = JDBCTemplate.getConnection();
//		
//		int performSchNo = new ReserveDao().selectOnePerformSchedule(conn, showNo, date_sel, cnt_sel);
//		
//		JDBCTemplate.close(conn);
//		
//		return performSchNo;
//	}
	
	public PerformSchedule selectOnePerformSchedule(int psNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		PerformSchedule ps = new ReserveDao().selectOnePerformSchedule(conn, psNo);
		
		JDBCTemplate.close(conn);
		
		return ps;
	}

	public ArrayList<Integer> selectReservedSeats(int psNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Integer> reserved_seats = new ReserveDao().selectReservedSeats(conn, psNo);
		
		JDBCTemplate.close(conn);
		
		return reserved_seats;
	}
	
	public ArrayList<Integer> selectProgressingSeats(int psNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Integer> prog_seats = new ReserveDao().selectProgressingSeats(conn, psNo);
		
		JDBCTemplate.close(conn);
		
		return prog_seats;
	}

	public int availableSeatCount(int performSchNo) {
		Connection conn = JDBCTemplate.getConnection();
		int availableSeat = -1;
		
		int reservedSeat = new ReserveDao().reservedSeatCount(conn, performSchNo);
		int totalSeat = new ReserveDao().totalSeatCount(conn, performSchNo);
		
			//System.out.println("resv "+reservedSeat);
			//System.out.println("tot "+totalSeat);
		
		if(reservedSeat==-1 || totalSeat==-1) {
			return availableSeat;
		}
		else {
			availableSeat = totalSeat - reservedSeat;
		}
		
		JDBCTemplate.close(conn);		
		
		return availableSeat;
	}

	public ShowInfo getShowInfo(int showNo) {
		Connection conn = JDBCTemplate.getConnection();
		ShowInfo si = new ReserveDao().getShowInfo(conn, showNo);
		
		JDBCTemplate.close(conn);
		
		return si;
	}
	
	public int getShowNoBypsNo(int psNo) {
		Connection conn = JDBCTemplate.getConnection();
		int showNo = new ReserveDao().getShowNoBypsNo(conn, psNo);
		
		JDBCTemplate.close(conn);
		
		return showNo;
	}

	public String getTheaterName(int th_no) {
		Connection conn = JDBCTemplate.getConnection();
		String thName = new ReserveDao().getTheaterName(conn, th_no);
		
		JDBCTemplate.close(conn);
		
		return thName;
	}

	public ArrayList<SeatGradeState> getSeatGradeStatus(int psNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<SeatGradeState> seatGrdStList = new ReserveDao().getSeatGradeAndPrice(conn);
		
		for (int i=0; i<seatGrdStList.size(); i++) {
			String grade = seatGrdStList.get(i).getTh1_seat_grd();
			int tot = new ReserveDao().getSeatGradeTotCnt(conn, grade);
			int reserved = new ReserveDao().getSeatGradeReservedCnt(conn, psNo, grade);
			int available = tot - reserved;
			seatGrdStList.get(i).setAvailableSeatCnt(available);
		}
		
		return seatGrdStList;
	}

	public int getProgNo() {
		Connection conn =JDBCTemplate.getConnection();
		int progNo = new ReserveDao().getProgNo(conn);
		
		JDBCTemplate.close(conn);
		
		return progNo;
	}

	public Date insertProgData(int progNo, int memberNo, int psNo, String[] seatList) {
		Connection conn = JDBCTemplate.getConnection();
		Date progTime = null;
		
		try {
			for(String seat : seatList) {
				int seatNo = Integer.parseInt(seat);
				int result = new ReserveDao().insertProgData(conn, progNo, memberNo, psNo, seatNo);
				
				if(result>0) {
					JDBCTemplate.commit(conn);
				}else {
					JDBCTemplate.rollback(conn);
					throw new Exception();
				}
			}
		} catch (Exception e) {
			return null;
		}
		
		progTime = new ReserveDao().getSysdate(conn);
		
		
		JDBCTemplate.close(conn);
		
		return progTime;
	}

	
	
}
