package kr.co.ticketsea.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.ticketsea.member.model.service.MemberService;
import kr.co.ticketsea.member.model.vo.Member;

/**
 * Servlet implementation class IdSearchServlet
 */
@WebServlet(name = "IdSearch", urlPatterns = { "/idSearch.do" })
public class IdSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		System.out.println(session.getId());
		request.setCharacterEncoding("utf-8");
		
		try {
			
		
		Member member=(Member)session.getAttribute("member");
		
		String userName = request.getParameter("userName");
		char gender = request.getParameter("gender").charAt(0);
		String userEmail = request.getParameter("userEmail");
		
		Member m = new Member();
		
		
		try {
			
				if(member!=null) {// 로그인 성공시
					throw new Exception();
					
				}else { //로그인 상태가 아닐시
					m.setMemberName(userName);
					m.setMemberGender(gender);
					m.setMemberEmail(userEmail);
					
					String userId = new MemberService().idSearchMember(m);
					
					if(userId!= null)
					{
						RequestDispatcher view = request.getRequestDispatcher("/views/member/idSearchResult.jsp");
						request.setAttribute("id", userId);
						view.forward(request, response);
						
					}else {
						response.sendRedirect("/views/member/idSearchFail.jsp");
					}
					
				}
				
				
			
		} catch (Exception e) {
			
				response.sendRedirect("/views/member/error.jsp");
		
			}
		
		} catch (Exception e) {
			response.sendRedirect("/views/member/error.jsp");
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
