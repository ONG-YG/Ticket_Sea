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

/**
 * Servlet implementation class ReserveListServlet
 */
@WebServlet(name = "ReserveList", urlPatterns = { "/reserveList.do" })
public class ReserveListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReserveListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 아이디로 예매 정보 확인
		
		// 1. 아이디 추출
		HttpSession session = request.getSession(false);
		
		String userId = ((Member)session.getAttribute("member")).getMemberId();
		
		// 2. 비즈니스 로직( 아이디로 예매정보 확인 )
		Member m = new Member();
		
		m.setMemberId("ㅎㅎtest");
		
		// 3. 예매정보 보내면서 jsp 페이지로 이동.
		RequestDispatcher view = request.getRequestDispatcher("views/mypage/reserveList.jsp");
		request.setAttribute("reserveList", m);
		view.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
