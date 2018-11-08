package kr.co.ticketsea.show.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.ticketsea.member.model.vo.Member;
import kr.co.ticketsea.show.model.service.ShowService;

/**
 * Servlet implementation class ShowDeleteCommentServlet
 */
@WebServlet(name = "ShowDeleteComment", urlPatterns = { "/showDeleteComment.do" })
public class ShowDeleteCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowDeleteCommentServlet() {
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
		int m_show_no = Integer.parseInt(request.getParameter("m_show_no"));
		int showcomment = Integer.parseInt(request.getParameter("showcomment"));
		
		System.out.println(m_show_no + showcomment);
		
		//3. 작성자
		HttpSession session = request.getSession(false);
		
		
		
		try { //로그인한 사용자만 댓글을 작성하고 그 이외에는 Exception 발생
			
			String userId = ((Member)session.getAttribute("member")).getMemberId();
			
		int result = new ShowService().deleteComment(m_show_no,showcomment);
		
			if(result>0)
			{
				response.sendRedirect("/show.do?m_show_no="+m_show_no);
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
