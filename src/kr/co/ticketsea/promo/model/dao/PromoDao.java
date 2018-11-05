package kr.co.ticketsea.promo.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import kr.co.ticketsea.common.JDBCTemplate;
import kr.co.ticketsea.promo.model.vo.Comment;
import kr.co.ticketsea.promo.model.vo.Promo;

public class PromoDao {

	public ArrayList<Promo> getCurrentPage(Connection conn, int currentPage, int recordCountPerPage) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		//���� �Խù� ���
		int start = currentPage * recordCountPerPage - (recordCountPerPage-1);
		//���� ��û�� �������� 1���������? -> 1�� ���;� ��
			// 1 * 10 - (10-1)  => 1
		//���� ��û�� �������� 4���������? -> 31�� ���;� ��
			// 4 * 10 - (10-1)  => 31
		
		//ex) currentPage�� 3 �̰�, recordCountPerPage 5���?
		//���� ��û�� �������� 3���������? -> 11�� ���;� ��
			// 3 * 5 - (5-1) 	=> 11
		
		
		
		
		//�� �Խù� ���
		int end = currentPage * recordCountPerPage;
		// ���� ��û�� �������� 1���������? -> 10
			// 1 * 10	=> 10
		// ���� ��û�� �������� 3���������? -> 30
			// 3 * 10 	=> 30
		
		
		String query = "select * from (select board_promo.*,row_number() " + 
				"over(order by boardP_no desc) AS num " + 
				"from board_promo) where num between ? and ?";
		
		ArrayList<Promo> list = new ArrayList<Promo>();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) 
			{
				Promo p = new Promo();
				p.setBoardP_no(rset.getInt("boardP_no"));
				p.setBoardP_writer(rset.getString("boardP_writer"));
				p.setBoardP_category(rset.getString("boardP_category"));
				p.setBoardP_title(rset.getString("boardP_title"));
				p.setBoardP_artist(rset.getString("boardP_artist"));
				p.setBoardP_contents(rset.getString("boardP_contents"));
				p.setBoardP_location(rset.getString("boardP_location"));
				p.setBoardP_date(rset.getDate("boardP_date"));
				p.setBoardP_no(rset.getInt("boardP_no"));
				
				list.add(p);
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
		
		// �Խù��� ��Ż ������ ���ؾ� ��
		int recordTotalCount = 0; //�ʱⰪ�� ������ �����Ƿ� 0���� ����
		
		String query = "select count(*) AS TOTALCOUNT FROM board_promo";
		
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
		 
		
		 // ���ؿ� �Խù��� ��Ż ������ �������� �������� ��Ż ������ ���ؾ� ��
		 // ��, �Խù��� 124�� ���? 
		 // page�� �� 13���� �Ǿ�� �� (�������� 10���� �Խù��̶�� ���Ͽ����� ����)
		 
		 int pageToTalCount = 0; //������ �����Ƿ� �ʱⰪ�� 0 ����
		 
		 
		 // �������� ��Ż ���� ���ϴ� ����
		 // �Խù���Ż ���� / 10  + 1(���ǿ� ���� ����)
		 
		 if(recordTotalCount%recordCountPerPage !=0)
		 {
			 pageToTalCount = recordTotalCount / recordCountPerPage + 1;
		 }
		 else 
		 {
			 pageToTalCount = recordTotalCount / recordCountPerPage;
		 }
		 
		 
		 // ���� ���� �ڵ�
		 if(currentPage<1)
		 {
			 currentPage = 1;
		 }
		 else if(currentPage>pageToTalCount)
		 {
			 currentPage = pageToTalCount;
		 }
		 
		int startNavi = ((currentPage-1)/naviCountPerPage)*naviCountPerPage+1; 
		
		
		int endNavi = startNavi + naviCountPerPage -1;
		
		
		if(endNavi > pageToTalCount)
		{
			endNavi = pageToTalCount;
		}
	
		boolean needPrev = true;
		boolean needNext = true;
		
		if(startNavi==1) { needPrev = false;}
		if(endNavi==pageToTalCount) { needNext = false;}
		
		
		
		StringBuilder sb = new StringBuilder();
		
		// needPrev�� ������������ 1�̸� false, ������������ 1�� �ƴ϶�� true
		if(needPrev==true) // ���� �������� 1�������� �ƴ϶��!
		{
			sb.append("<a href='/promoList.do?currentPage="+(startNavi-1)+"'> < </a>");
		}
		// ���� �� ��ġ(startNavi��)�� 2���? '<' ��ư�� Ŭ���ϸ� 1�������� �̵��ؾ� ��
		// <a href='/promoList.do?cureentPage=1> < </a>
		
		for(int i=startNavi; i<=endNavi;i++){
			if(i==currentPage)
			{
				// ���� �������� ���� �ִ� ��ġ�������� ���ٸ� ���ϰ� ǥ��
				sb.append("<a href='/promoList.do?currentPage="+i+"'><B style='font-size:30px'>"+i+"</B></a> ");
				//<a href='/promoList.do?cureentPage=1'><B>1</B></a>
			}
			else
			{
				// ���� �������� ���� �ִ� ��ġ �������� �ٸ��ٸ� �Ϲ� ǥ��
				sb.append("<a href='/promoList.do?currentPage="+i+"'>"+i+"</a> ");
			}
		}
		
		if(needNext) 
		{
			sb.append("<a href='/promoList.do?currentPage="+(endNavi+1)+"'> > </a>");
		}
		
		return sb.toString();		
		
	}

	public Promo selectOnePromo(Connection conn, int boardP_no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Promo promo = null;
		
		String query = "select * from board_promo where boardP_no = ?";
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardP_no);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				promo = new Promo();
				promo.setBoardP_no(rset.getInt("boardP_no"));
				promo.setBoardP_writer(rset.getString("boardP_writer"));
				promo.setBoardP_category(rset.getString("boardP_category"));
				promo.setBoardP_title(rset.getString("boardP_title"));
				promo.setBoardP_artist(rset.getString("boardP_artist"));
				promo.setBoardP_contents(rset.getString("boardP_contents"));
				promo.setBoardP_location(rset.getString("boardP_location"));
				promo.setBoardP_date(rset.getDate("boardP_date"));	
				promo.setBoardP_hit(rset.getInt("boardP_hit"));
				promo.setBoardP_fileName(rset.getString("boardP_fileName"));
				promo.setBoardP_filePath(rset.getString("boardP_filePath"));
				promo.setBoardP_fileSize(rset.getLong("boardP_fileSize"));
				promo.setBoardP_uploadTime(rset.getTimestamp("boardP_uploadTime"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return promo;	
	}

	public int insertPromo(Connection conn, String title, String category,String contents, String artist, String location,
			String userId, String fileName, String fullFilePath, long fileSize, Timestamp uploadTime) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "insert into board_promo values(seq_boardp_no.NEXTVAL,?,?,?,?,?,?,SYSDATE,default,default,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, category);
			pstmt.setString(3, title);
			pstmt.setString(4, artist);
			pstmt.setString(5, contents);
			pstmt.setString(6, location);
			pstmt.setString(7, fileName);
			pstmt.setString(8, fullFilePath);
			pstmt.setLong(9,  fileSize);
			pstmt.setTimestamp(10, uploadTime);
			
			result = pstmt.executeUpdate();
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int insertComment(Connection conn, int promoNo, String contents, String userId) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "insert into promocomment values(SEQ_promocomment.NEXTVAL,?,?,?,SYSDATE)";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, promoNo);
			pstmt.setString(2, contents);
			pstmt.setString(3, userId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		
		return result;
	}

	public ArrayList<Comment> selectComments(Connection conn, int boardP_no) {
	
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from PROMOCOMMENT "
				+ "where boardP_no = ?";
		
		ArrayList<Comment> list = new ArrayList<Comment>();
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, boardP_no);
			
			rset = pstmt.executeQuery();
			
			while(rset.next())
			{
				Comment co = new Comment();
				co.setCommentNo(rset.getInt("promoComment"));
				co.setPromoNo(rset.getInt("boardP_no"));
				co.setContents(rset.getString("promoComment_contents"));
				co.setUserId(rset.getString("promoComment_writer"));
				co.setRegDate(rset.getDate("promoComment_date"));
				
				list.add(co);
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int deletePromo(Connection conn, int boardP_no, String userId) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "delete from board_promo where boardP_no=? and boardP_writer=?";
		
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, boardP_no);
			pstmt.setString(2, userId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
}
