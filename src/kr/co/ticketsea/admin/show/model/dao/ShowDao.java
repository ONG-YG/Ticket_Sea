package kr.co.ticketsea.admin.show.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import kr.co.ticketsea.admin.show.model.vo.Show;
import kr.co.ticketsea.admin.show.model.vo.ShowCategory;
import kr.co.ticketsea.admin.show.model.vo.ShowPlace;
import kr.co.ticketsea.common.JDBCTemplate;

public class ShowDao {

	public int insertShow(Connection conn, Show s) {
		PreparedStatement pstmt= null;
		int result = 0;
		
		String query = "insert into musical_l values(SHOW_DB.nextval,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			
		
			pstmt.setInt(1, s.getTh_no());
			pstmt.setString(2, s.getSc_code());
			pstmt.setString(3, s.getShow_name());
			pstmt.setString(4, s.getShow_st_date());
			pstmt.setString(5, s.getShow_ed_date());
			pstmt.setString(6, s.getArtists());
			pstmt.setString(7, s.getShow_grd());
			pstmt.setInt(8, s.getShow_run());
			pstmt.setInt(9, s.getBk_comm());
			pstmt.setString(10, s.getShow_poster());
			pstmt.setString(11, s.getShow_dtInfo());
			
			
			result = pstmt.executeUpdate();
			System.out.println(result);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
		
	}

	public ArrayList<Show> getcurrentPage(Connection conn, int currentPage, int recordCountPerPage) {
		
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		
		//시작 게시물 계산
		int start = currentPage*recordCountPerPage-(recordCountPerPage-1);
		
		//끝 게시물 계산
		int end = currentPage* recordCountPerPage;
		
		String query = "select * from (select row_number() over(order by m_show_no desc) num, musical_l.* from musical_l) where num between ? and ?";
		
		ArrayList<Show> list = new ArrayList<Show>();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rset =  pstmt.executeQuery();
			
			while(rset.next()) {
				Show s = new Show();
				s.setM_show_no(rset.getInt("m_show_no"));
				s.setTh_no(rset.getInt("TH_NO"));
				s.setSc_code(rset.getString("sc_code"));
				s.setShow_name(rset.getString("m_show_name"));
				s.setArtists(rset.getString("m_artists"));
				s.setShow_run(rset.getInt("m_show_run"));
				s.setShow_grd(rset.getString("m_show_grd"));
				s.setShow_st_date(rset.getString("m_show_st_date"));
				s.setShow_ed_date(rset.getString("m_show_ed_date"));
				
				list.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public String getPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		
		//게시물의 토탈 개수를 구해야함
		int recordTotalCount=0; //초기값은 정보가 없으므로 0으로 세팅
		
		String query ="select count(*) as totalcount from musical_l";
		
		try {
			pstmt=conn.prepareStatement(query);
			rset= pstmt.executeQuery();
			if(rset.next()) {
				recordTotalCount = rset.getInt("totalcount");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		int pageTotalCount = 0; //정보가 없으므로 초기값은 0 셋팅
		
		//페이지의 토탈 개수 구하는 공식
		//게시물 토탈 개수 / 10 + 1(조건에 따라 적용)
		
		if(recordTotalCount % recordCountPerPage!=0) { //나머지가 있으면
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		}else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
		
		// 에러 방지 코드
		// 다음페이지 이전페이지 이동 에러
		if(currentPage<1) {
			currentPage = 1;
		}else if(currentPage>pageTotalCount){
			currentPage = pageTotalCount;
		}
		
		//시작페이지 구하는 공식 대입
		int startNavi = ((currentPage-1)/naviCountPerPage)*naviCountPerPage+1;
		
		//끝페이지를 구하는 공식
		
		//시작 navi+ 보여줄 navi 개수 -1;
		//ex. 시작 navi가 11이라면?
		// 11+5-1 => 15가 나옴
		// 즉, 11 12 13 14 15 가 구해짐
		
		int endNavi = startNavi + naviCountPerPage -1;
		
		// 끝 navi를 구할 때 주의해야할점
		// 토탈 개수가 122개라면 총 토탈페이지는 13개 만들어져야함
		// 11 12 13 14 15
		// 토탈 페이지를 고려하지 않고 만들게 되면 끝 navi가 이상하게구해질 수 있음
		if(endNavi>pageTotalCount) {
			endNavi=pageTotalCount;
		}
		
		// 페이지를 표현하는 navi에서 사용할 '<' 모양과 '>'모양을 쓰기위해
		// 필요한 변수 2개를 생성 (변수의 값에 따라서 시작 부분과 끝 부분은 표현하지 않기위해)
		
		boolean needPrev = true;
		boolean needNext = true;
		
		if(startNavi==1) { needPrev = false;}
		if(endNavi == pageTotalCount){needNext=false;}
		
		StringBuilder sb = new StringBuilder();
		//needPrev는 시작페이지가 1이면 false, 시작페이지가 1이 아니라면 true
		if(needPrev==true) { //시작페이지가 1페이지가 아니라면
			sb.append("<a href='/adShowList.do?currentPage="+(startNavi-1)+"'> < </a> ");
		}
		//현재 내 위치 (startNavi값)가 2라면? '<' 버튼을 클릭하면 1페이지로 이동함
		//<a href='/noticeList.do?currentPage=(2-1)> < </a> =>
		
		for(int i=startNavi;i<=endNavi;i++) {
			if(i==currentPage) { // 현재 페이지가 내가 있는 위치 페이지와 같다면 진하게 표시
				sb.append("<a href='/adShowList.do?currentPage="+i+"'><B style='font-size:20px'>"+i+"</B></a> ");
				//<a href='/noticeList.do?currentPage=1'><B>1</B></a>
			}else { // 현재 페이지가 내가 있는 위치 페이지와 다르다면 일반 표시
				sb.append("<a href='/adShowList.do?currentPage="+i+"'>"+i+"</a> ");
			}
		}
		
		if(needNext) {
			sb.append("<a href='/adShowList.do?currentPage="+(endNavi+1)+"'> > </a> ");
		}		
		return sb.toString();
	}

	public Show selectOneShow(Connection conn, int showNo) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		Show show =null;
		
		String query = "select m.m_show_no, s.sc_name, m.M_SHOW_NAME, l.TH_NAME, m.M_ARTISTS, "
				+ "m.M_SHOW_ST_DATE, m.M_SHOW_ED_DATE, m.M_SHOW_GRD, m.M_SHOW_RUN  "
				+"from SHOW_CTG s right join musical_l m on s.SC_CODE = m.SC_CODE "
				+"left outer join THEATER_L l on m.TH_NO = l.TH_NO where m.m_show_no=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, showNo);
			
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				show = new Show();
				show.setM_show_no(rset.getInt("m_show_no"));
				show.setSc_code(rset.getString("sc_name"));
				show.setShow_name(rset.getString("m_show_name"));
				show.setTh_name(rset.getString("th_name"));
				show.setArtists(rset.getString("m_artists"));
				show.setShow_grd(rset.getString("m_show_grd"));
				show.setShow_run(rset.getInt("m_show_run"));
				show.setShow_st_date(rset.getString("m_show_st_date"));
				show.setShow_ed_date(rset.getString("m_show_ed_date"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return show;
	}

	public ArrayList<ShowPlace> showPlaceList(Connection conn) {
		
		ArrayList<ShowPlace> list=new ArrayList<ShowPlace>();
		Statement stmt= null;
		ResultSet rset=null;
		
		String query = "select * from THEATER_L";
		
		try {
			stmt=conn.createStatement();
			rset=stmt.executeQuery(query);
			
			while(rset.next()) {
				ShowPlace sp=new ShowPlace();
				sp.setTh_no(rset.getInt("th_no"));
				sp.setTh_name(rset.getString("th_name"));
				sp.setTh_lct(rset.getString("th_lct"));
				
				list.add(sp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		
		return list;
	}

	public ArrayList<ShowCategory> showCategoryList(Connection conn) {
		ArrayList<ShowCategory> list=new ArrayList<ShowCategory>();
		Statement stmt= null;
		ResultSet rset=null;
		
		String query = "select * from SHOW_CTG";
		
		try {
			stmt=conn.createStatement();
			rset=stmt.executeQuery(query);
			
			while(rset.next()) {
				ShowCategory sc=new ShowCategory();
				sc.setSc_code(rset.getString("sc_code"));
				sc.setSc_name(rset.getString("sc_name"));
				list.add(sc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		
		return list;
	}



}
