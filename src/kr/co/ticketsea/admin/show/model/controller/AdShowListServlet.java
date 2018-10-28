package kr.co.ticketsea.admin.show.model.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.co.ticketsea.admin.show.model.vo.*;

import kr.co.ticketsea.admin.show.model.service.ShowService;

/**
 * Servlet implementation class AdShowListServlet
 */
@WebServlet(name = "AdShowList", urlPatterns = { "/adShowList.do" })
public class AdShowListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdShowListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int currentPage;
		
		if(request.getParameter("currentPage")==null) {
			currentPage=1;
		}else {
			currentPage=Integer.parseInt(request.getParameter("currentPage"));
		}
		
		PageData pd=new ShowService().showAllList(currentPage);
		
		// 3. 결과값을 view 페이지로 리턴
		if(pd!=null) {
			
			RequestDispatcher view = request.getRequestDispatcher("views/admin/ad_showList.jsp"); 
			request.setAttribute("pageData", pd);
			view.forward(request, response);
			
		}else {
			System.out.println("불러오기실패");
			response.sendRedirect("/views/admin/error.jsp");
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
