package kr.co.ticketsea.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ticketsea.member.model.service.MemberService;
import kr.co.ticketsea.member.model.vo.Member;

/**
 * Servlet implementation class PwdSearchServlet
 */
@WebServlet(name = "PwdSearch", urlPatterns = { "/pwdSearch.do" })
public class PwdSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PwdSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글 인코딩	
		request.setCharacterEncoding("utf-8");
		
		//비밀번호 찾기를 위한 변수 저장 id, name, email, phone
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String userEmail = request.getParameter("userEmail");
		String userPhone = request.getParameter("userPhone");
		
		Member m = new Member();
		m.setMemberId(userId);
		m.setMemberName(userName);
		m.setMemberEmail(userEmail);
		m.setMemberPhone(userPhone);
		
		new MemberService().pwdSearchMember(m);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
