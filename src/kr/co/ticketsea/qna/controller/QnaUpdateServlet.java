package kr.co.ticketsea.qna.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.ticketsea.qna.model.service.*;
import kr.co.ticketsea.member.model.vo.*;

/**
 * Servlet implementation class QnaUpdateServlet
 */
@WebServlet(name = "QnaUpdate", urlPatterns = { "/qnaUpdate.do" })
public class QnaUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaUpdateServlet() {
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
		int boardQ_no = Integer.parseInt(request.getParameter("boardQ_no"));
		String title = request.getParameter("title_form");
		String contents = request.getParameter("contents_form");
		String userId = null; // 수정을 요청하는 사람의 ID
		//3. 해당 글을 수정하기 위하여 작성자를 확인하여 처리 하도록 세션을 이용
		HttpSession session  = request.getSession(false);
		
		try {
			userId = ((Member)session.getAttribute("member")).getMemberId();
			if(userId!=null) 
			{
			//4. 비즈니스 로직 처리
			int result = new QnaService().qnaUpdate(boardQ_no,title,contents,userId);
			
				if(result>0)
				{
					response.sendRedirect("/views/qna/updateSuccess.jsp?boardQ_no="+boardQ_no);
					
					
					//response.sendRedirect("/views/notice/updateResult.jsp?noticeNo="+noticeNo+"&result=1");
				}else {
					response.sendRedirect("/views/notice/updateFail.jsp?boardQ_no="+boardQ_no);
					
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
