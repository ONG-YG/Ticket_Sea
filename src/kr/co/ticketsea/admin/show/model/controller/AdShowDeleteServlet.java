package kr.co.ticketsea.admin.show.model.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ticketsea.admin.show.model.dao.ShowDao;
import kr.co.ticketsea.admin.show.model.service.ShowService;

/**
 * Servlet implementation class AdShowDeleteServlet
 */
@WebServlet(name = "AdShowDelete", urlPatterns = { "/adShowDelete.do" })
public class AdShowDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdShowDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int showNo=Integer.parseInt(request.getParameter("showNo"));
		
		int result = new ShowService().deleteShow(showNo);
		if(result>0) {
			response.sendRedirect("/adShowList.do");
		}else {
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
