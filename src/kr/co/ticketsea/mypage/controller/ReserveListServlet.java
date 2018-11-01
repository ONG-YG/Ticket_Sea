package kr.co.ticketsea.mypage.controller;

import java.io.IOException;
import java.util.ArrayList;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.ticketsea.member.model.vo.Member;
import kr.co.ticketsea.mypage.model.vo.MyReserveList;
import kr.co.ticketsea.mypage.service.MypageService;
import kr.co.ticketsea.reserve.model.vo.ReserveInfo;


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

		HttpSession session = request.getSession(false);
		int memberNo = ((Member)session.getAttribute("member")).getMemberNo();
		
		// member_no로 bk_no검색
		ArrayList<ReserveInfo> rNumberList = new MypageService().selectReserveNumber(memberNo);
		
		// 추출한 bk_no로 ps_no를 조회하여 공연일,공연번호 추출
		ArrayList<MyReserveList> mrlList = new MypageService().selectPerformSchedule(rNumberList);

		RequestDispatcher view = request.getRequestDispatcher("views/mypage/reserveList.jsp");
		request.setAttribute("rNumberList", rNumberList);
		request.setAttribute("mrlList", mrlList);

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
