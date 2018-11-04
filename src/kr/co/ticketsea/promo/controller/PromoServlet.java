package kr.co.ticketsea.promo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ticketsea.notice.model.service.NoticeService;
import kr.co.ticketsea.notice.model.vo.Notice;
import kr.co.ticketsea.promo.model.service.PromoService;
import kr.co.ticketsea.promo.model.vo.Promo;

/**
 * Servlet implementation class PromoServlet
 */
@WebServlet(name = "Promo", urlPatterns = { "/promo.do" })
public class PromoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PromoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		
		//2. view에서 전송한 데이터를 변수에 저장
		int boardP_no = Integer.parseInt(request.getParameter("boardP_no"));
		
		//3. 비즈니스 로직 처리 
		Promo promo =  new PromoService().selectOnePromo(boardP_no);
		
		//4. 결과 리턴
		if(promo !=null) {
			RequestDispatcher view = request.getRequestDispatcher("views/promo/promo.jsp");
			request.setAttribute("promo", promo);
			view.forward(request, response);
			
		}else {
			response.sendRedirect("/views/notice/error.jsp");
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
