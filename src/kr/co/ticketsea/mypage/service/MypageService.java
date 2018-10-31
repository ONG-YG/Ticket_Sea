package kr.co.ticketsea.mypage.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.co.ticketsea.common.JDBCTemplate;
import kr.co.ticketsea.mypage.model.dao.MypageDao;
import kr.co.ticketsea.reserve.model.vo.PerformSchedule;
import kr.co.ticketsea.reserve.model.vo.ReserveInfo;

public class MypageService {

	public ArrayList<ReserveInfo> selectReserveNumber(int memberNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<ReserveInfo> list = new MypageDao().selectReserveNumber(conn,memberNo);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	public void selectPerformSchedule(ArrayList<ReserveInfo> rNumberList) {
		Connection conn = JDBCTemplate.getConnection();
		
		for(ReserveInfo ri:rNumberList) {
			PerformSchedule ps = new MypageDao().selectPerformSchedule(conn, ri.getBk_no());
			
			String mName = new MypageDao().selectMusicalName(conn, ps);
			
			System.out.println(ps.getShowNo());
			System.out.println(ps.getPerformSchDate());
			System.out.println(mName);
		}
		
		
		
		JDBCTemplate.close(conn);
		
	}

}
