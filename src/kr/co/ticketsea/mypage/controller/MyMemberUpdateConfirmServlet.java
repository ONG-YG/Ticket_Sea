package kr.co.ticketsea.mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ticketsea.member.model.vo.Member;
import kr.co.ticketsea.mypage.service.MypageService;

/**
 * Servlet implementation class MyMemberUpdateConfirmServlet
 */
@WebServlet(name = "MyMemberUpdateConfirm", urlPatterns = { "/myMemberUpdateConfirm.do" })
public class MyMemberUpdateConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyMemberUpdateConfirmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String memberId=request.getParameter("member_id");
		
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		
		
		Member m = new Member();
		
		m.setMemberId(memberId);
		m.setMemberPhone(phone);
		m.setMemberEmail(email);
		m.setMemberAddr(address);
		
		//비즈니스 로직
		int result = new MypageService().updateMemberConfirm(m);
			
		if(result>0) {
			response.sendRedirect("/views/mypage/memberUpdateSuccess.jsp");
		}else {
			response.sendRedirect("/views/mypage/error.jsp");
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
