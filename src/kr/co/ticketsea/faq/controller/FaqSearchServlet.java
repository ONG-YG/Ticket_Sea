package kr.co.ticketsea.faq.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ticketsea.faq.model.service.FaqService;
import kr.co.ticketsea.faq.model.vo.PageData;

/**
 * Servlet implementation class FaqSearchServlet
 */
@WebServlet(name = "FaqSearch", urlPatterns = { "/faqSearch.do" })
public class FaqSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 한글 인코딩
		request.setCharacterEncoding("utf-8");
		
		
		//2. view에서 보내준 값을 변수에 저장
		String keyword = request.getParameter("search");
			
		
		//3. 페이징 처리를 하기 위한 작업 (현재 페이지값 저장)
		int currentPage;
		if(request.getParameter("currentPage")==null) {currentPage=1;}
		else {currentPage = Integer.parseInt(request.getParameter("currentPage"));}
		
		//4. 비즈니스 로직 처리
		PageData pd = new FaqService().searchList(keyword,currentPage);
		
		
		//5. 결과 리턴
		
		
			RequestDispatcher view = request.getRequestDispatcher("views/faq/faqSearch.jsp");
			request.setAttribute("pageData", pd);
			request.setAttribute("keyword",keyword);
			
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
