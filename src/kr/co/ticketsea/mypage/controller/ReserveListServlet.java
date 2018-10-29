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
		
//		1. 검색한 멤버 넘버로 예매번호 검색
//		select bk_no from book_inf where member_no = '(m.getMember_no)';
//		여러개 나옴..>  하나씩  분배 필요
//
//		2. 가져온 예매번호로 공연회차번호 검색
//		select ps_no from bk_s_l where bk_no = '(가져온 예매번호)';
//
//		4. 가져온 공연회차번호로 공연번호 검색
//		select m_show_no from perf_sch where ps_no = '(가져온 공연회차번호)';
//
//		5. 공연번호로 공연명 추출
//		select m_show_name from musical_l where m_show_no = '(공연번호)';
		
		// 1. 멤버 고유 넘버 추출
		HttpSession session = request.getSession(false);
		int memberNo = ((Member)session.getAttribute("member")).getMemberNo();
		
		// 2. 비즈니스 로직(공연목록 테이블까지)
		new MypageService().selectMusical(memberNo);
		
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
