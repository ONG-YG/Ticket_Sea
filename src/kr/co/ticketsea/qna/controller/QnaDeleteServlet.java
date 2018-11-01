package kr.co.ticketsea.qna.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.ticketsea.member.model.vo.*;
import kr.co.ticketsea.faq.model.service.FaqService;
import kr.co.ticketsea.qna.model.service.QnaService;

/**
 * Servlet implementation class QnaDeleteServlet
 */
@WebServlet(name = "QnaDelete", urlPatterns = { "/qnaDelete.do" })
public class QnaDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		
		//2. view에서 보낸 정보 변수에 저장
		int boardQ_no = Integer.parseInt(request.getParameter("boardQ_no"));
		String writer = request.getParameter("writer");
		
		//3. 세션에서 로그인 사용자 정보를 꺼내어 작성자와 일치하는지 비교
		HttpSession session = request.getSession(false);
		
		try {
			String userId = ((Member)session.getAttribute("member")).getMemberId();
			
				int result = new QnaService().deleteQna(boardQ_no);
				
				if(result>0) 
				{
					response.sendRedirect("/views/qna/deleteSuccess.jsp");
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
