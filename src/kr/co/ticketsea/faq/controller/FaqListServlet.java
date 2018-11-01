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
import kr.co.ticketsea.faq.model.vo.PageData;

/**
 * Servlet implementation class FaqListServlet
 */
@WebServlet(name = "FaqList", urlPatterns = { "/faqList.do" })
public class FaqListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 페이징 처리를 위하여 페이지를 저장하는 변수를 생성
				int currentPage; //현재 페이지를 저장하는 변수
				
				if(request.getParameter("currentPage")==null) {currentPage = 1;}
				else {currentPage = Integer.parseInt(request.getParameter("currentPage"));}
				// 즉, 처음 게시판에 접근하였을땐 무조건 1 페이지로 처리하고
				// 게시판에서 페이지를 이동할때에는 값이 있기 때문에 해당 페이지 값을 가져와서 저장
				
				
				//2. 비즈니스 로직
				// Controller -> Service -> Dao
				// 페이징처리에서는 2개의 값을 DB에서 가져와야 함
				// Controller -> Service -> Dao를 2개 호출함
				PageData pd = new FaqService().faqAllList(currentPage);
				
				
				//3. 결과값을 view페이지로 리턴
				if(pd!=null)
				{
					RequestDispatcher view = request.getRequestDispatcher("views/faq/faqAllList.jsp");
					request.setAttribute("pageData", pd);
					
					view.forward(request, response);
				}
				else
				{
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
