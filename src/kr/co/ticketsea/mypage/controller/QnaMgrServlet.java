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
 * Servlet implementation class QnaMgrServlet
 */
@WebServlet(name = "QnaMgr", urlPatterns = { "/qnaMgr.do" })
public class QnaMgrServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaMgrServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 세션 불러오기
		HttpSession session = request.getSession(false);
		// 세션 속성 member의 memberNo 속성 가져와서 변수에 저장(다운캐스팅 해줌)
		String memberId = ((Member) session.getAttribute("member")).getMemberId();

		// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 페이징 처리 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		// 1. 현재 페이지 저장을 위해 변수 선언
		int currentPage;

		if (request.getParameter("currentPage") == null) { // 처음게시판 접근시
			currentPage = 1; // 무조건 1페이지
		} else {
			currentPage = Integer.parseInt(request.getParameter("currentPage")); // 게시판에서 페이지를 이동할때에는 값이 있기 때문에 해당 페이지
																					// 값을 가져와서 저장
		}

		// 2. 비즈니스 로직
		ReservePageData qpd = new MypageService().qnaMgrList(currentPage, memberId);

		
		if(qpd!=null) {
		// 3. jsp 페이지로 넘겨준다
		RequestDispatcher view = request.getRequestDispatcher("views/mypage/qnaMgr.jsp");
		request.setAttribute("qpd", qpd);
		view.forward(request, response);
		}else {
			response.sendRedirect("/views/mypage/qnaMgrNoData.jsp");
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
