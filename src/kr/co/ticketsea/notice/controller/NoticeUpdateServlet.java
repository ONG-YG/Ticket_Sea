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
 * Servlet implementation class NoticeUpdateServlet
 */
@WebServlet(name = "NoticeUpdate", urlPatterns = { "/noticeUpdate.do" })
public class NoticeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		
		
		//2. view에서 넘겨준 데이터를 변수에 저장
		int noticeNo = Integer.parseInt(request.getParameter("boardN_No"));
		String title = request.getParameter("boardN_title");
		String contents = request.getParameter("boardN_contents");
		String userId = null; // 수정을 요청하는 사람의 ID
		//3. 해당 글을 수정하기 위하여 작성자를 확인하여 처리 하도록 세션을 이용
		HttpSession session  = request.getSession(false);
		
		try {
			userId = ((Member)session.getAttribute("member")).getMemberId();
			if(userId!=null) 
			{
			//4. 비즈니스 로직 처리
			int result = new NoticeService().noticeUpdate(noticeNo,title,contents);
			
				if(result>0)
				{
					response.sendRedirect("/views/notice/updateSuccess.jsp?boardN_no="+noticeNo);
					
					
					//response.sendRedirect("/views/notice/updateResult.jsp?noticeNo="+noticeNo+"&result=1");
				}else {
					response.sendRedirect("/views/notice/updateFail.jsp?boardN_no="+noticeNo);
					
					//response.sendRedirect("/views/notice/updateResult.jsp?noticeNo="+noticeNo+"&result=0");
				}
				
				

				
				
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
