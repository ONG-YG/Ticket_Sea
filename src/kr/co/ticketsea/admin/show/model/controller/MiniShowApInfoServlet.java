package kr.co.ticketsea.admin.show.model.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ticketsea.admin.show.model.service.MiniShowService;
import kr.co.ticketsea.admin.show.model.vo.MiniShow;

/**
 * Servlet implementation class MiniShowApInfoServlet
 */
@WebServlet(name = "MiniShowApInfo", urlPatterns = { "/miniShowApInfo.do" })
public class MiniShowApInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MiniShowApInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getParameter("utf-8");
		
		int msNo=Integer.parseInt(request.getParameter("msNo"));
		
		MiniShow ms = new MiniShowService().selectApShow(msNo);
		RequestDispatcher view = request.getRequestDispatcher("/views/admin/ad_miniApInfo.jsp");
		request.setAttribute("miniShow", ms);
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
