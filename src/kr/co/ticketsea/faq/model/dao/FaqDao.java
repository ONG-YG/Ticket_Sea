package kr.co.ticketsea.faq.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import kr.co.ticketsea.common.JDBCTemplate;
import kr.co.ticketsea.faq.model.vo.Faq;
import kr.co.ticketsea.notice.model.vo.Notice;

public class FaqDao {
		public ArrayList<Faq> faqAllList(Connection conn) {
				
				Statement stmt = null;
				ResultSet rset = null;
				
				ArrayList<Faq> list = new ArrayList<Faq>();
				
				String query = "select * from board_faq";
				
				try {
					stmt = conn.createStatement();
					rset = stmt.executeQuery(query);
					
					while(rset.next()) {
						Faq f = new Faq();
						f.setBoardF_no(rset.getInt("boardF_no"));
						
						f.setBoardF_category(rset.getString("boardF_category"));
						f.setBoardF_title(rset.getString("boardF_title"));
						f.setBoardF_contents(rset.getString("boardF_contents"));
						
						list.add(f);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					JDBCTemplate.close(rset);
					JDBCTemplate.close(stmt);
				}
				
				return list;				
			}

		public Faq selectOneFaq(Connection conn, int boardF_no) {
			
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			Faq faq = null;
			
			String query = "select * from board_faq where boardF_no = ?";
			
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, boardF_no);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					faq = new Faq();
					faq.setBoardF_no(rset.getInt("boardF_no"));
					faq.setBoardF_title(rset.getString("boardF_title"));
					faq.setBoardF_category(rset.getString("boardF_category"));
					faq.setBoardF_contents(rset.getString("boardF_contents"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			return faq;	
		}

		public int deleteFaq(Connection conn, int boardF_no) {
			
			PreparedStatement pstmt = null;
			int result = 0;
			
			String query = "delete from board_faq where boardF_no=?";
			
			
			try {
				pstmt = conn.prepareStatement(query);
				
				pstmt.setInt(1, boardF_no);
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
			return result;
		}

		public int faqUpdate(Connection conn, int boardF_no, String boardF_title, String boardF_contents, String userId) {
			
			PreparedStatement pstmt = null;
			int result = 0;
			
			String query = "update board_faq set boardF_title=?, boardF_contents=?"
					+ " where boardF_no=? and user_id = ?";
			
			try {
				pstmt = conn.prepareStatement(query);
				
				pstmt.setString(1, boardF_title);
				pstmt.setString(2, boardF_contents);
				pstmt.setInt(3, boardF_no);
				pstmt.setString(4, userId);
				
				result = pstmt.executeUpdate();
				

				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
			
			return result;	
			
	}
		
		public ArrayList<Faq> getCurrentPage(Connection conn, int currentPage, int recordCountPerPage) {
			
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			//시작 게시물 계산
			int start = currentPage * recordCountPerPage - (recordCountPerPage-1);
			//만약 요청한 페이지가 1페이지라면? -> 1이 나와야 됨
				// 1 * 10 - (10-1)  => 1
			//만약 요청한 페이지가 4페이지라면? -> 31이 나와야 됨
				// 4 * 10 - (10-1)  => 31
			
			//ex) currentPage가 3 이고, recordCountPerPage 5라면?
			//만약 요청한 페이지가 3페이지라면? -> 11이 나와야 됨
				// 3 * 5 - (5-1) 	=> 11
			
			
			
			
			//끝 게시물 계산
			int end = currentPage * recordCountPerPage;
			// 만약 요청한 페이지가 1페이지라면? -> 10
				// 1 * 10	=> 10
			// 만약 요청한 페이지가 3페이지라면? -> 30
				// 3 * 10 	=> 30
			
			
			String query = "select * from (select board_faq.*,row_number() " + 
					"over(order by boardF_no desc) AS num " + 
					"from board_faq) where num between ? and ?";
			
			ArrayList<Faq> list = new ArrayList<Faq>();
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) 
				{
					Faq f = new Faq();
					f.setBoardF_no(rset.getInt("boardF_no"));
					f.setBoardF_category(rset.getString("boardF_category"));
					f.setBoardF_title(rset.getString("boardF_title"));
					f.setBoardF_contents(rset.getString("boardF_contents"));
					
					list.add(f);
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
			
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			// 게시물의 토탈 개수를 구해야 함
			int recordTotalCount = 0; //초기값은 정보가 없으므로 0으로 셋팅
			
			String query = "select count(*) AS TOTALCOUNT FROM board_faq";
			
			 try {
				pstmt = conn.prepareStatement(query);
				rset = pstmt.executeQuery();
				if(rset.next()) {
					recordTotalCount = rset.getInt("TOTALCOUNT");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			 
			
			 // 구해온 게시물의 토탈 개수를 바탕으로 페이지의 토탈 개수를 구해야 함
			 // 즉, 게시물이 124개 라면? 
			 // page는 총 13개가 되어야 함 (페이지당 10개의 게시물이라고 정하였을때 기준)
			 
			 int pageToTalCount = 0; //정보가 없으므로 초기값은 0 셋팅
			 
			 
			 // 페이지의 토탈 개수 구하는 공식
			 // 게시물토탈 개수 / 10  + 1(조건에 따라 적용)
			 
			 if(recordTotalCount%recordCountPerPage !=0)
			 {
				 pageToTalCount = recordTotalCount / recordCountPerPage + 1;
			 }
			 else 
			 {
				 pageToTalCount = recordTotalCount / recordCountPerPage;
			 }
			 
			 
			 // 에러 방지 코드
			 if(currentPage<1)
			 {
				 currentPage = 1;
			 }
			 else if(currentPage>pageToTalCount)
			 {
				 currentPage = pageToTalCount;
			 }
			 
			 
			 // 현재 페이지를 기점으로 시작 navi 와 끝 navi를 만들어야 함
			 
			 // 현재 페이지가 만약 1이라면  ?    1 2 3 4 5
			 // 현재 페이지가 만약 3이라면  ?    1 2 3 4 5
			 // 현재 페이지가 만약 7이라면  ? 	 6 7 8 9 10
			 // 현재 페이지가 만약 12이라면?	 11 12 13 14 15
			 
			 // 시작 페이지를 구하는 공식
			 // ((현재페이지-1)/리스트개수)*리스트개수+1
			 
			 // 만약 1페이지 라면?
			 // ((1-1)/5)*5+1   => 1
			 
			 // 만약 3페이지 라면?
			 // ((3-1)/5)*5+1	=> 1
			 
			 // 만약 7페이지 라면?
			 // ((7-1)/5)*5+1	=> 6
			 
			 // 만약 12페이지 라면?
			 // ((12-1)/5)*5+1	=> 11
			 
			
			 // 시작 페이지 구하는 공식 대입
			int startNavi = ((currentPage-1)/naviCountPerPage)*naviCountPerPage+1; 
			
			
			// 끝 페이지를 구하는 공식
			// 시작navi + 보여줄 navi 개수 -1;
			// ex. 시작 navi가 11 이라면?
			// 11 + 5 -1 => 15 가 나옴
			// 즉, 11 12 13 14 15 가 구해짐  
			
			int endNavi = startNavi + naviCountPerPage -1;
			
			
			// 끝 navi를 구할때 주의해야 항점
			// 토탈 개수가 122개 라고 할때 ( 총 토탈 페이지는 13개)
			// 1 2 3 4 5
			// 6 7 8 9 10
			// 11 12 13 14 15
			// 토탈 페이지를 고려하지 않고 만들게 되면 위에 처럼 끝 navi가 이상하게 구해지게 됨
			
			if(endNavi > pageToTalCount)
			{
				endNavi = pageToTalCount;
			}
		
			
			// 페이지를 표현하는 navi에서 사용할 '<' 모양과 '>'모양을 쓰기위해
			// 필요한 변수 2개를 생성 (변수에 값에 따라서 시작 부분과 끝부분은 표현하지 않기 위해)
			
			boolean needPrev = true;
			boolean needNext = true;
			
			if(startNavi==1) { needPrev = false;}
			if(endNavi==pageToTalCount) { needNext = false;}
			
			
			
			StringBuilder sb = new StringBuilder();
			
			// needPrev는 시작페이지가 1이면 false, 시작페이지가 1이 아니라면 true
			if(needPrev==true) // 시작 페이지가 1페이지가 아니라면!
			{
				sb.append("<a href='/faqList.do?currentPage="+(startNavi-1)+"'> < </a>");
			}
			// 현재 내 위치(startNavi값)가 2라면? '<' 버튼을 클릭하면 1페이지로 이동해야 함
			// <a href='/noticeList.do?cureentPage=1> < </a>
			
			for(int i=startNavi; i<=endNavi;i++){
				if(i==currentPage)
				{
					// 현재 페이지가 내가 있는 위치페이지와 같다면 진하게 표시
					sb.append("<a href='/faqList.do?currentPage="+i+"'><B style='font-size:30px'>"+i+"</B></a> ");
					//<a href='/noticeList.do?cureentPage=1'><B>1</B></a>
				}
				else
				{
					// 현재 페이지가 내가 있는 위치 페이지와 다르다면 일반 표시
					sb.append("<a href='/faqList.do?currentPage="+i+"'>"+i+"</a> ");
				}
			}
			
			if(needNext) 
			{
				sb.append("<a href='/faqList.do?currentPage="+(endNavi+1)+"'> > </a>");
			}
			
			return sb.toString();		
			
		}

		public int insertFaq(Connection conn, String category, String title, String contents) {
			
			PreparedStatement pstmt = null;
			int result = 0;
			
			String query = "insert into board_faq values(SEQ_boardF_NO.nextval,?,?,?)";
			
			try {
				pstmt = conn.prepareStatement(query);
				
				pstmt.setString(1, category);
				pstmt.setString(2, title);
				pstmt.setString(3, contents);
				
				result = pstmt.executeUpdate();
			
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(pstmt);
			}
			
			return result;
		}

		public ArrayList<Faq> getSearchCurrentPage(Connection conn, int currentPage, int recordCountPerPage,
				String keyword) {
			

			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			//시작 게시물 계산
			int start = currentPage * recordCountPerPage - (recordCountPerPage-1);
			//만약 요청한 페이지가 1페이지라면? -> 1이 나와야 됨
				// 1 * 10 - (10-1)  => 1
			//만약 요청한 페이지가 4페이지라면? -> 31이 나와야 됨
				// 4 * 10 - (10-1)  => 31
			
			//ex) currentPage가 3 이고, recordCountPerPage 5라면?
			//만약 요청한 페이지가 3페이지라면? -> 11이 나와야 됨
				// 3 * 5 - (5-1) 	=> 11
			
			
			
			
			//끝 게시물 계산
			int end = currentPage * recordCountPerPage;
			// 만약 요청한 페이지가 1페이지라면? -> 10
				// 1 * 10	=> 10
			// 만약 요청한 페이지가 3페이지라면? -> 30
				// 3 * 10 	=> 30
			
			
			String query = "select * from(select board_faq.*, "
					+ "row_number() over(order by boardF_no desc) " + 
					"as num " + 
					"from board_faq " + 
					"where boardF_title like ?) " + 
					"where num between ? and ?";
			
			ArrayList<Faq> list = new ArrayList<Faq>();
			
			try {
				pstmt = conn.prepareStatement(query);
				
				pstmt.setString(1, '%'+keyword+'%');
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) 
				{
					Faq f = new Faq();
					f.setBoardF_no(rset.getInt("boardF_no"));
					f.setBoardF_title(rset.getString("boardF_title"));
					f.setBoardF_category(rset.getString("boardF_category"));
					f.setBoardF_contents(rset.getString("boardF_contents"));
					
					list.add(f);
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

		public String getSearchPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage,
				String keyword) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			// 게시물의 토탈 개수를 구해야 함
			int recordTotalCount = 0; //초기값은 정보가 없으므로 0으로 셋팅
			
			String query = "select count(*) AS TOTALCOUNT " + 
					"FROM board_faq " + 
					"WHERE boardf_title like ?";
			
			 try {
				pstmt = conn.prepareStatement(query);
				
				pstmt.setString(1, '%'+keyword+'%');
				
				
				rset = pstmt.executeQuery();
				if(rset.next()) {
					recordTotalCount = rset.getInt("TOTALCOUNT");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			 
			
			 // 구해온 게시물의 토탈 개수를 바탕으로 페이지의 토탈 개수를 구해야 함
			 // 즉, 게시물이 124개 라면? 
			 // page는 총 13개가 되어야 함 (페이지당 10개의 게시물이라고 정하였을때 기준)
			 
			 int pageToTalCount = 0; //정보가 없으므로 초기값은 0 셋팅
			 
			 
			 // 페이지의 토탈 개수 구하는 공식
			 // 게시물토탈 개수 / 10  + 1(조건에 따라 적용)
			 
			 if(recordTotalCount%recordCountPerPage !=0)
			 {
				 pageToTalCount = recordTotalCount / recordCountPerPage + 1;
			 }
			 else 
			 {
				 pageToTalCount = recordTotalCount / recordCountPerPage;
			 }
			 
			 
			 // 에러 방지 코드
			 if(currentPage<1)
			 {
				 currentPage = 1;
			 }
			 else if(currentPage>pageToTalCount)
			 {
				 currentPage = pageToTalCount;
			 }
			 
			 
			 // 현재 페이지를 기점으로 시작 navi 와 끝 navi를 만들어야 함
			 
			 // 현재 페이지가 만약 1이라면  ?    1 2 3 4 5
			 // 현재 페이지가 만약 3이라면  ?    1 2 3 4 5
			 // 현재 페이지가 만약 7이라면  ? 	 6 7 8 9 10
			 // 현재 페이지가 만약 12이라면?	 11 12 13 14 15
			 
			 // 시작 페이지를 구하는 공식
			 // ((현재페이지-1)/리스트개수)*리스트개수+1
			 
			 // 만약 1페이지 라면?
			 // ((1-1)/5)*5+1   => 1
			 
			 // 만약 3페이지 라면?
			 // ((3-1)/5)*5+1	=> 1
			 
			 // 만약 7페이지 라면?
			 // ((7-1)/5)*5+1	=> 6
			 
			 // 만약 12페이지 라면?
			 // ((12-1)/5)*5+1	=> 11
			 
			
			 // 시작 페이지 구하는 공식 대입
			int startNavi = ((currentPage-1)/naviCountPerPage)*naviCountPerPage+1; 
			
			
			// 끝 페이지를 구하는 공식
			// 시작navi + 보여줄 navi 개수 -1;
			// ex. 시작 navi가 11 이라면?
			// 11 + 5 -1 => 15 가 나옴
			// 즉, 11 12 13 14 15 가 구해짐  
			
			int endNavi = startNavi + naviCountPerPage -1;
			
			
			// 끝 navi를 구할때 주의해야 항점
			// 토탈 개수가 122개 라고 할때 ( 총 토탈 페이지는 13개)
			// 1 2 3 4 5
			// 6 7 8 9 10
			// 11 12 13 14 15
			// 토탈 페이지를 고려하지 않고 만들게 되면 위에 처럼 끝 navi가 이상하게 구해지게 됨
			
			if(endNavi > pageToTalCount)
			{
				endNavi = pageToTalCount;
			}
		
			
			// 페이지를 표현하는 navi에서 사용할 '<' 모양과 '>'모양을 쓰기위해
			// 필요한 변수 2개를 생성 (변수에 값에 따라서 시작 부분과 끝부분은 표현하지 않기 위해)
			
			boolean needPrev = true;
			boolean needNext = true;
			
			if(startNavi==1) { needPrev = false;}
			if(endNavi==pageToTalCount) { needNext = false;}
			
			
			
			StringBuilder sb = new StringBuilder();
			
			// needPrev는 시작페이지가 1이면 false, 시작페이지가 1이 아니라면 true
			if(needPrev==true) // 시작 페이지가 1페이지가 아니라면!
			{
				sb.append("<a href='/noticeSearch.do?search="+keyword+"&currentPage="+(startNavi-1)+"'> < </a>");
			}
			// 현재 내 위치(startNavi값)가 2라면? '<' 버튼을 클릭하면 1페이지로 이동해야 함
			// <a href='/noticeList.do?cureentPage=1> < </a>
			
			for(int i=startNavi; i<=endNavi;i++){
				if(i==currentPage)
				{
					// 현재 페이지가 내가 있는 위치페이지와 같다면 진하게 표시
					sb.append("<a href='/noticeSearch.do?search="+keyword+"&currentPage="+i+"'><B style='font-size:30px'>"+i+"</B></a> ");
					//<a href='/noticeList.do?cureentPage=1'><B>1</B></a>
				}
				else
				{
					// 현재 페이지가 내가 있는 위치 페이지와 다르다면 일반 표시
					sb.append("<a href='/noticeSearch.do?search="+keyword+"&currentPage="+i+"'>"+i+"</a> ");
				}
			}
			
			if(needNext) 
			{
				sb.append("<a href='/noticeSearch.do?search="+keyword+"&currentPage="+(endNavi+1)+"'> > </a>");
			}
			
			return sb.toString();	
		}

}
