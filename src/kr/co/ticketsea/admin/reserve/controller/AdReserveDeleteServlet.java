package kr.co.ticketsea.admin.reserve.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ticketsea.admin.reserve.model.service.AdReserveService;

/**
 * Servlet implementation class AdReserveDeleteServlet
 */
@WebServlet(name = "AdReserveDelete", urlPatterns = { "/adReserveDelete.do" })
public class AdReserveDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdReserveDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String bkNo=request.getParameter("bkNo");
		int result=new AdReserveService().deleteReserve(bkNo);
		
		if(result>0) {
			response.sendRedirect("/views/admin/reserveDeleteSuccess.jsp");
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
