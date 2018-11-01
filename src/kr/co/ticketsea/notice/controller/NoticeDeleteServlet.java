package kr.co.ticketsea.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.ticketsea.notice.model.service.NoticeService;
import kr.co.ticketsea.member.model.vo.*;

/**
 * Servlet implementation class NoticeDeleteServlet
 */
@WebServlet(name = "NoticeDelete", urlPatterns = { "/noticeDelete.do" })
public class NoticeDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDeleteServlet() {
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
		int boardN_no = Integer.parseInt(request.getParameter("boardN_no"));
		
		//3. 세션에서 로그인 사용자 정보를 꺼내어 작성자와 일치하는지 비교
		HttpSession session = request.getSession(false);
		
		try {
			String userId = ((Member)session.getAttribute("member")).getMemberId();
			
			
				int result = new NoticeService().deleteNotice(boardN_no);
				
				if(result>0) 
				{
					response.sendRedirect("/views/notice/deleteSuccess.jsp");
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
