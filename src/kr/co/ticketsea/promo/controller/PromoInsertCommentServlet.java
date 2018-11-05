package kr.co.ticketsea.promo.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.ticketsea.member.model.vo.Member;
import kr.co.ticketsea.promo.model.service.*;

/**
 * Servlet implementation class PromoInsertCommentServlet
 */
@WebServlet(name = "PromoInsertComment", urlPatterns = { "/promoInsertComment.do" })
public class PromoInsertCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PromoInsertCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		
		//2. view에서 보내준 데이터 변수에 저장 (내용,글번호)
		String contents = request.getParameter("reviewContent");
		int promoNo = Integer.parseInt(request.getParameter("promoNo"));
		
		//3. 작성자
		HttpSession session = request.getSession(false);
		
		
		
		try { //로그인한 사용자만 댓글을 작성하고 그 이외에는 Exception 발생
			
			String userId = ((Member)session.getAttribute("member")).getMemberId();
			
			
		int result = new PromoService().insertComment(promoNo,contents,userId);
		
			if(result>0)
			{
				response.sendRedirect("/promo.do?boardP_no="+promoNo);
			}else {
				throw new Exception();
			}
		} catch (Exception e) {
			response.sendRedirect("/views/notice/error.jsp");
		}
		
		
		
		
		
		
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
