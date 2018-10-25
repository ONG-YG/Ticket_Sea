package kr.or.tks.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.tks.member.model.vo.Member;
import kr.or.tks.member.service.MemberService;

/**
 * Servlet implementation class MemberJoinServlet
 */
@WebServlet(name = "MemberJoin", urlPatterns = { "/memberJoin.do" })
public class MemberJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberJoinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		Member m = new Member();
		
		m.setMemberAddr(request.getParameter("userAddress"));
		m.setMemberEmail(request.getParameter("userEmail"));
		m.setMemberGender(request.getParameter("gender").charAt(0));
		m.setMemberId(request.getParameter("userId"));
		m.setMemberName(request.getParameter("userName"));
		m.setMemberPhone(request.getParameter("userPhone"));
		m.setMemberPwd(request.getParameter("userPwd"));
		
		System.out.println(m.getMemberGender());
		
		int result = new MemberService().memberJoin(m);
		
		if(result>0) {
			
			response.sendRedirect("/views/member/success.jsp");
			
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
