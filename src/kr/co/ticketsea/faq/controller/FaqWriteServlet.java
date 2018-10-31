package kr.co.ticketsea.faq.controller;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class FaqWriteServlet
 */
@WebServlet(name = "FaqWrite", urlPatterns = { "/faqWrite.do" })
public class FaqWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Faq> list = new FaqService().faqAllList();
		
		if(!list.isEmpty()) {
			RequestDispatcher view = request.getRequestDispatcher("views/faq/FaqAllList.jsp");
			
			request.setAttribute("faqList", list);

			view.forward(request, response);
		} 
		else {
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
