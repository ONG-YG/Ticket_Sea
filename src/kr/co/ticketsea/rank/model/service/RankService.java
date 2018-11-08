package kr.co.ticketsea.rank.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.co.ticketsea.common.JDBCTemplate;
import kr.co.ticketsea.rank.model.dao.RankDao;
import kr.co.ticketsea.rank.model.vo.Rank;

public class RankService {

	public ArrayList<Rank> newestShow() {
		Connection conn= JDBCTemplate.getConnection();
		
		ArrayList<Rank> list = new RankDao().newestShow(conn);
		
		JDBCTemplate.close(conn);
		return list;
		
		
	}

	

}
