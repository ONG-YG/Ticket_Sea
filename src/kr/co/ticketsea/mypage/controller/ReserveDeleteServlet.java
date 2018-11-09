package kr.co.ticketsea.mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ticketsea.mypage.service.MypageService;

/**
 * Servlet implementation class ReserveDeleteServlet
 */
@WebServlet(name = "ReserveDelete", urlPatterns = { "/reserveDelete.do" })
public class ReserveDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReserveDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 인코딩
		request.setCharacterEncoding("utf-8");

		// 파라미터 불러오기(bk_no불러오기)
		String bkNo = request.getParameter("reserveDelete");
		// 비즈니스 로직
		int result = new MypageService().reserveDelete(bkNo);
		
		
		
		// 성공 페이지 출력
		if(result>0) {
			response.sendRedirect("/views/mypage/reserveDeleteSuccess.jsp");
		}else {
			response.sendRedirect("/views/mypage/reserveDeleteFail.jsp");
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
