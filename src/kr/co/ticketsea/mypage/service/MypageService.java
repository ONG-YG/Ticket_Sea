package kr.co.ticketsea.mypage.service;

import java.sql.Connection;

import kr.co.ticketsea.common.JDBCTemplate;
import kr.co.ticketsea.mypage.model.dao.MypageDao;

public class MypageService {

	public void selectMusical(int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		new MypageDao().selectMusical(conn, memberNo);
		
		JDBCTemplate.close(conn);
	}

}
