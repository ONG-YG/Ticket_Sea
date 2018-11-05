package kr.co.ticketsea.mypage.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.ticketsea.member.model.vo.Member;
import kr.co.ticketsea.mypage.service.MypageService;

/**
 * Servlet implementation class MyMemberUpdateServlet
 */
@WebServlet(name = "MyMemberUpdate", urlPatterns = { "/myMemberUpdate.do" })
public class MyMemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyMemberUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 세션 없을경우 계속 없는상태 유지
		HttpSession session = request.getSession(false);
		
		// member_no 추출
		int memberNo = ((Member)session.getAttribute("member")).getMemberNo();
		
		// 비즈니스 로직 (member정보가 담긴 객체 리턴)
		Member updateList = new MypageService().memberUpdate(memberNo);
		
		// view 페이지로 객체 리턴
		if(updateList==null) { // updateList가 없을경우 에러페이지로 이동
			response.sendRedirect("/views/mypage/error.jsp");
		}else {
			RequestDispatcher view = request.getRequestDispatcher("views/mypage/memberUpdate.jsp");
			request.setAttribute("updateMember", updateList);
			view.forward(request, response);
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
