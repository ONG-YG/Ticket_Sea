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
 * Servlet implementation class NoticeWriteServlet
 */
@WebServlet(name = "NoticeWrite", urlPatterns = { "/noticeWrite.do" })
public class NoticeWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//1. 한글 인코딩
				request.setCharacterEncoding("utf-8");
				
				//2. view에서 보내준 데이터를 변수에 저장
				String title = request.getParameter("title");
				String category = request.getParameter("category");
				String contents = request.getParameter("contents");
				
				//3. session에서 글을 작성한 사람의 ID를 추출
				HttpSession session  = request.getSession(false);
			
				try {
					String userId = ((Member)session.getAttribute("member")).getMemberId();
					//userId를 가져오도록 함 (비로그인 사용자 일시 Exception이 발생함)
					
					
					if(userId.equals("admin")) {
						
					//4. 비즈니스 로직 처리
					int result = new NoticeService().insertNotice(category,title,contents);
						
						if(result>0)
						{
							response.sendRedirect("/views/notice/writeSuccess.jsp");
							
						}else {
							throw new Exception();
						}
							}else {
						response.sendRedirect("/views/notice/writeFail.jsp");
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
