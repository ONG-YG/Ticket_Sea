package kr.co.ticketsea.admin.reserve.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ticketsea.admin.reserve.model.vo.ReservePageData;
import kr.co.ticketsea.admin.show.model.service.ShowService;
import kr.co.ticketsea.reserve.model.service.ReserveService;

/**
 * Servlet implementation class AdReserveListServlet
 */
@WebServlet(name = "AdReserveList", urlPatterns = { "/adReserveList.do" })
public class AdReserveListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdReserveListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage;
		
		if(request.getParameter("currentPage")==null) {
			currentPage=1;
		}else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		ReservePageData pd = new ReserveService().reserveAllList(currentPage);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
