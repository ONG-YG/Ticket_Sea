package kr.co.ticketsea.faq.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ticketsea.faq.model.service.FaqService;
import kr.co.ticketsea.faq.model.vo.Faq;
import kr.co.ticketsea.notice.model.service.NoticeService;
import kr.co.ticketsea.notice.model.vo.Notice;

/**
 * Servlet implementation class FaqServlet
 */
@WebServlet(name = "Faq", urlPatterns = { "/faq.do" })
public class FaqServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqServlet() {
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
		int boardF_no = Integer.parseInt(request.getParameter("boardF_no"));
		
		//3. 비즈니스 로직 처리 
		Faq faq =  new FaqService().selectOneFaq(boardF_no);
		
		//4. 결과 리턴
		if(faq !=null) {
			RequestDispatcher view = request.getRequestDispatcher("views/faq/faq.jsp");
			request.setAttribute("faq", faq);
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
