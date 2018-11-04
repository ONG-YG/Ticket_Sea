package kr.co.ticketsea.mypage.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.co.ticketsea.admin.member.model.dao.AdMemberDao;
import kr.co.ticketsea.common.JDBCTemplate;
import kr.co.ticketsea.member.model.vo.Member;
import kr.co.ticketsea.mypage.model.dao.MypageDao;
import kr.co.ticketsea.mypage.model.vo.MyReserveList;
import kr.co.ticketsea.notice.model.dao.NoticeDao;
import kr.co.ticketsea.notice.model.vo.Notice;
import kr.co.ticketsea.notice.model.vo.PageData;
import kr.co.ticketsea.reserve.model.vo.PerformSchedule;
import kr.co.ticketsea.reserve.model.vo.ReserveInfo;

public class MypageService {

	public ArrayList<ReserveInfo> selectReserveNumber(int memberNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<ReserveInfo> list = new MypageDao().selectReserveNumber(conn,memberNo);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	public ArrayList<MyReserveList> selectPerformSchedule(ArrayList<ReserveInfo> rNumberList) {
		Connection conn = JDBCTemplate.getConnection();
		
		MyReserveList mrl = new MyReserveList();
		ArrayList<MyReserveList> mrlList = new ArrayList<MyReserveList>();
		
		
		for(ReserveInfo ri:rNumberList) {
			PerformSchedule ps = new MypageDao().selectPerformSchedule(conn, ri.getBk_no());
			
			String mName = new MypageDao().selectMusicalName(conn, ps);
			
			mrl.setShowNo(ps.getShowNo());//int
			mrl.setShowDate(ps.getPerformSchDate());//date
			mrl.setShowName(mName);//string
					
			mrlList.add(mrl);
		}
		
		JDBCTemplate.close(conn);
		
		return mrlList;

	}

	public void reserveAllList(int currentPage) {
		
		Connection conn = JDBCTemplate.getConnection();
			
		int recordCountPerPage = 5; //게시물의 개수
		int naviCountPerPage = 5; //navi의 개수
		
		
		// Service에서 DAO를 호출 (2번의 DAO를 호출)
		// 1. 현재 페이지의 게시물 리스트 요청
		// 2. 현재 페이지를 중심으로 만들어지는 navi 리스트 요청
				
		ArrayList<Notice> list = new NoticeDao().getCurrentPage(conn,currentPage,recordCountPerPage);
		String pageNavi = new NoticeDao().getPageNavi(conn,currentPage,recordCountPerPage,naviCountPerPage);
		PageData pd = null;
				
		if(!list.isEmpty() && !pageNavi.isEmpty())
		{
			pd = new PageData();
			pd.setList(list);
			pd.setPageNavi(pageNavi);
		}
		
		JDBCTemplate.close(conn);
		
		//return pd;		
				
		
	}

	public Member memberUpdate(int memberNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		// Dao(select문 : member_no 로 id,phone,addr,email을 담은 객체를 가져옴)
		Member updateList = new MypageDao().memberUpdate(conn, memberNo);
		
		JDBCTemplate.close(conn);
		
		return updateList;
		
	}

	public int updateMemberConfirm(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		int result=new MypageDao().updateMemberConfirm(conn, m);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
		
	}

}
