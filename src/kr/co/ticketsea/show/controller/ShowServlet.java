package kr.co.ticketsea.show.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ticketsea.show.model.service.ShowService;
import kr.co.ticketsea.show.model.vo.ShowData;
import kr.co.ticketsea.show.model.service.ShowService;

/**
 * Servlet implementation class ShowServlet
 */
@WebServlet(name = "Show", urlPatterns = { "/show.do" })
public class ShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowServlet() {
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
		int show_no = Integer.parseInt(request.getParameter("show_no"));
		
		//3. 비즈니스 로직 처리 
		ShowData pd =  new ShowService().selectOneShow(show_no);
		
		//4. 결과 리턴
		if(pd !=null) {
			RequestDispatcher view = request.getRequestDispatcher("views/show/show.jsp");
			request.setAttribute("showData", pd);
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
