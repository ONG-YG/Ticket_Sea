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
import kr.co.ticketsea.mypage.model.vo.ReservePageData;
import kr.co.ticketsea.mypage.service.MypageService;

/**
 * Servlet implementation class PopupReserveListServlet
 */
@WebServlet(name = "PopupReserveList", urlPatterns = { "/popupReserveList.do" })
public class PopupReserveListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PopupReserveListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 파라미터 불러오기(bk_no불러오기)
		String bkNo = request.getParameter("show_btn");
		
		
		// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 페이징 처리 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		// 1. 현재 페이지 저장을 위해 변수 선언
		int currentPage;
		
		if(request.getParameter("currentPage")==null) { // 처음게시판 접근시
			currentPage = 1; // 무조건 1페이지
		}else {
			currentPage = Integer.parseInt(request.getParameter("currentPage")); // 게시판에서 페이지를 이동할때에는 값이 있기 때문에 해당 페이지 값을 가져와서 저장
		}
		
		//2. 비즈니스 로직
		ReservePageData pd = new MypageService().popupReserveList(currentPage,bkNo);		

		//3. jsp 페이지로 넘겨준다
		RequestDispatcher view = request.getRequestDispatcher("views/mypage/popupReserveList.jsp");
		request.setAttribute("pd", pd);
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
