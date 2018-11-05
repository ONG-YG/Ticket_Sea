package kr.co.ticketsea.promo.model.service;

import java.sql.Connection;

import kr.co.ticketsea.common.JDBCTemplate;
import kr.co.ticketsea.promo.model.dao.ShowDao;
import kr.co.ticketsea.promo.model.vo.*;
public class ShowService {
	public Show selectOneShow(int show_no) {
		//하나의 공지사항을 읽으면 공지사항의 내용과 댓글을 가져와야 함
		
		
		Connection conn = JDBCTemplate.getConnection();
		
		//공지사항 가져오는 메소드
		Show show = new ShowDao().selectOneShow(conn,show_no);
		
		
		//리턴을 하기 위하여 공지사항과 댓글을 저장하는 VO를 생성하여 저장
		
		if(show != null) {
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return show;
		
		
	}
}
