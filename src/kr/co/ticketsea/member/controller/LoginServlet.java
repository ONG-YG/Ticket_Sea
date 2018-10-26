package kr.co.ticketsea.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.tickectsea.member.model.vo.Member;
import kr.co.ticketsea.member.service.MemberService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "Login", urlPatterns = { "/login.do" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String memberId = request.getParameter("userId");
		String memberPwd = request.getParameter("userPwd");
		Member m = new Member();
		m.setMemberId(memberId);
		m.setMemberPwd(memberPwd);
		Member member = new MemberService().selectMember(m);
		if(member!=null) //로그인을 성공 했을때 
		{
			HttpSession session  = request.getSession(true);
			// true : 세션값이 없으면 새롭게 생성
			// false : 세션값이 없으면 null 리턴
			
			
			session.setAttribute("member", member);
			
			response.sendRedirect("/views/member/success.jsp");
			//sendRedirect 메소드는 해당되는 페이지로 이동시키는 메소드
		}else {
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
