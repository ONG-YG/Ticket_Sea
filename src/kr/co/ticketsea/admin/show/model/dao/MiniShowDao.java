package kr.co.ticketsea.admin.show.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.ticketsea.admin.show.model.vo.MiniShow;
import kr.co.ticketsea.common.JDBCTemplate;

public class MiniShowDao {

	public ArrayList<MiniShow> getcurrentPage(Connection conn, int currentPage, int recordCountPerPage) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		
		//시작 게시물 계산
		int start = currentPage*recordCountPerPage-(recordCountPerPage-1);
		
		//끝 게시물 계산
		int end = currentPage* recordCountPerPage;
		
		String query = "select * from ms_stat ms right outer join (select * from (select row_number() over(order by BOARDP_NO desc) num, board_promo.* from board_promo where BOARDP_ACTIVE='N')"+
		"where num between ? and ?) st on ms.STATE_CD =st.BOARDP_ACTIVE";
		
		ArrayList<MiniShow> list = new ArrayList<MiniShow>();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rset =  pstmt.executeQuery();
			
			while(rset.next()) {
				MiniShow ms=new MiniShow();
				ms.setBoardp_no(rset.getInt("boardp_no"));
				ms.setBoardp_title(rset.getString("boardp_title"));
				ms.setBoardp_writer(rset.getString("boardp_writer"));
				ms.setBoardp_artist(rset.getString("boardp_artist"));
				ms.setBoardp_location(rset.getString("boardp_location"));
				ms.setBoardp_date(rset.getDate("boardp_date"));
				ms.setBoardp_price(rset.getInt("boardp_price"));
				ms.setBoardp_category(rset.getString("boardp_category"));
				ms.setBoardp_contents(rset.getString("boardp_contents"));
				ms.setBoardp_filename(rset.getString("boardp_filename"));
				ms.setBoardp_active(rset.getString("boardp_active").charAt(0));
				list.add(ms);
				
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
		
		String query ="select count(*) as totalcount from BOARD_PROMO where BOARDP_ACTIVE='N'";
		
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
		int pageTotalCount = 0; 
		
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
		
		int endNavi = startNavi + naviCountPerPage -1;
		
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
			sb.append("<a href='/miniShowList.do?currentPage="+(startNavi-1)+"'> < </a> ");
		}
		//현재 내 위치 (startNavi값)가 2라면? '<' 버튼을 클릭하면 1페이지로 이동함
		//<a href='/noticeList.do?currentPage=(2-1)> < </a> =>
		
		for(int i=startNavi;i<=endNavi;i++) {
			if(i==currentPage) { // 현재 페이지가 내가 있는 위치 페이지와 같다면 진하게 표시
				sb.append("<a href='/miniShowList.do?currentPage="+i+"'><B style='font-size:20px'>"+i+"</B></a> ");
				//<a href='/noticeList.do?currentPage=1'><B>1</B></a>
			}else { // 현재 페이지가 내가 있는 위치 페이지와 다르다면 일반 표시
				sb.append("<a href='/miniShowList.do?currentPage="+i+"'>"+i+"</a> ");
			}
		}
		
		if(needNext) {
			sb.append("<a href='/miniShowList.do?currentPage="+(endNavi+1)+"'> > </a> ");
		}		
		return sb.toString();
	}

	public ArrayList<MiniShow> getApcurrentPage(Connection conn, int currentPage, int recordCountPerPage) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		
		//시작 게시물 계산
		int start = currentPage*recordCountPerPage-(recordCountPerPage-1);
		
		//끝 게시물 계산
		int end = currentPage* recordCountPerPage;
		
		String query = "select * from ms_stat ms right outer join (select * from (select row_number() over(order by BOARDP_NO desc) num, board_promo.* from board_promo where BOARDP_ACTIVE='Y')\"+\r\n" + 
				"		\"where num between ? and ?) st on ms.STATE_CD =st.BOARDP_ACTIVE";
		
		ArrayList<MiniShow> list = new ArrayList<MiniShow>();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rset =  pstmt.executeQuery();
			
			while(rset.next()) {
				MiniShow ms=new MiniShow();
				ms.setBoardp_no(rset.getInt("boardp_no"));
				ms.setBoardp_title(rset.getString("boardp_title"));
				ms.setBoardp_writer(rset.getString("boardp_writer"));
				ms.setBoardp_artist(rset.getString("boardp_artist"));
				ms.setBoardp_location(rset.getString("boardp_location"));
				ms.setBoardp_date(rset.getDate("boardp_date"));
				ms.setBoardp_price(rset.getInt("boardp_price"));
				ms.setBoardp_category(rset.getString("boardp_category"));
				ms.setBoardp_contents(rset.getString("boardp_contents"));
				ms.setBoardp_filename(rset.getString("boardp_filename"));
				ms.setBoardp_active(rset.getString("boardp_active").charAt(0));
				
				list.add(ms);
				
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

	public String getApPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		
		//게시물의 토탈 개수를 구해야함
		int recordTotalCount=0; //초기값은 정보가 없으므로 0으로 세팅
		
		String query ="select count(*) as totalcount from BOARD_PROMO where BOARDP_ACTIVE='Y'";
		
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
		int pageTotalCount = 0; 
		
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
		
		int endNavi = startNavi + naviCountPerPage -1;
		
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
			sb.append("<a href='/miniShowList.do?currentPage="+(startNavi-1)+"'> < </a> ");
		}
		//현재 내 위치 (startNavi값)가 2라면? '<' 버튼을 클릭하면 1페이지로 이동함
		//<a href='/noticeList.do?currentPage=(2-1)> < </a> =>
		
		for(int i=startNavi;i<=endNavi;i++) {
			if(i==currentPage) { // 현재 페이지가 내가 있는 위치 페이지와 같다면 진하게 표시
				sb.append("<a href='/miniShowList.do?currentPage="+i+"'><B style='font-size:20px'>"+i+"</B></a> ");
				//<a href='/noticeList.do?currentPage=1'><B>1</B></a>
			}else { // 현재 페이지가 내가 있는 위치 페이지와 다르다면 일반 표시
				sb.append("<a href='/miniShowList.do?currentPage="+i+"'>"+i+"</a> ");
			}
		}
		
		if(needNext) {
			sb.append("<a href='/miniShowList.do?currentPage="+(endNavi+1)+"'> > </a> ");
		}		
		return sb.toString();
	}

	public MiniShow selectApShow(Connection conn, int msNo) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		MiniShow ms = null;
		
		String query = "select * from MS_STAT m RIGHT OUTER JOIN (select * from BOARD_PROMO)S ON m.STATE_CD=S.BOARDP_ACTIVE WHERE BOARDP_NO=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, msNo);
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				ms=new MiniShow();
				ms.setBoardp_no(rset.getInt("boardp_no"));
				ms.setBoardp_title(rset.getString("boardp_title"));
				ms.setBoardp_writer(rset.getString("boardp_writer"));
				ms.setBoardp_artist(rset.getString("boardp_artist"));
				ms.setBoardp_location(rset.getString("boardp_location"));
				ms.setBoardp_date(rset.getDate("boardp_date"));
				ms.setBoardp_price(rset.getInt("boardp_price"));
				ms.setBoardp_category(rset.getString("boardp_category"));
				ms.setBoardp_contents(rset.getString("boardp_contents"));
				ms.setBoardp_filename(rset.getString("boardp_filename"));
				ms.setBoardp_active(rset.getString("boardp_active").charAt(0));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return ms;
	}

	public int miniShowApprove(Connection conn, int msNo) {
		PreparedStatement pstmt=null;
		int result = 0;
		String query = "update BOARD_PROMO set BOARDP_ACTIVE='Y' where BOARDP_NO=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, msNo);
			
			result= pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
		
	}

	public int updateApMiniShow(Connection conn, MiniShow ms) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update BOARD_PROMO set BOARDP_ARTIST=?, BOARDP_LOCATION=?, BOARDP_PRICE=?, BOARDP_FILENAME=?, BOARDP_CONTENTS=? where BOARDP_NO=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, ms.getBoardp_artist());
			pstmt.setString(2, ms.getBoardp_location());
			pstmt.setInt(3, ms.getBoardp_price());
			pstmt.setString(4, ms.getBoardp_filename());
			pstmt.setString(5, ms.getBoardp_contents());
			pstmt.setInt(6, ms.getBoardp_no());
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public MiniShow selectWtShow(Connection conn, int msNo) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		MiniShow ms = null;
		
		String query = "select * from MS_STAT m RIGHT OUTER JOIN (select * from BOARD_PROMO)S ON m.STATE_CD=S.BOARDP_ACTIVE WHERE BOARDP_NO=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, msNo);
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				ms=new MiniShow();
				ms.setBoardp_no(rset.getInt("boardp_no"));
				ms.setBoardp_title(rset.getString("boardp_title"));
				ms.setBoardp_writer(rset.getString("boardp_writer"));
				ms.setBoardp_artist(rset.getString("boardp_artist"));
				ms.setBoardp_location(rset.getString("boardp_location"));
				ms.setBoardp_date(rset.getDate("boardp_date"));
				ms.setBoardp_price(rset.getInt("boardp_price"));
				ms.setBoardp_category(rset.getString("boardp_category"));
				ms.setBoardp_contents(rset.getString("boardp_contents"));
				ms.setBoardp_filename(rset.getString("boardp_filename"));
				ms.setBoardp_active(rset.getString("boardp_active").charAt(0));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return ms;
	}

	public int refuseMiniShow(Connection conn, int msNo) {
		PreparedStatement pstmt=null;
		int result =0;
		
		String query="update BOARD_PROMO set BOARDP_ACTIVE='D' where BOARDP_NO=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			
			pstmt.setInt(1, msNo);
			
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
