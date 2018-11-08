package kr.co.ticketsea.admin.reserve.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ticketsea.admin.reserve.model.service.AdReserveService;
import kr.co.ticketsea.admin.reserve.model.vo.ReserveApInfo;
import kr.co.ticketsea.reserve.model.service.ReserveService;
import kr.co.ticketsea.reserve.model.vo.SelectedSeat;

/**
 * Servlet implementation class AdOneReserveServlet
 */
@WebServlet(name = "AdOneReserve", urlPatterns = { "/adOneReserve.do" })
public class AdOneReserveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdOneReserveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String reserveNo = request.getParameter("rs_bk_no");
		
		ReserveApInfo rs= new AdReserveService().selectOneReserve(reserveNo);
		ArrayList<SelectedSeat> seatList = new AdReserveService().reserveSeat(reserveNo);
		
		rs.setSeatInfo(seatList);
		
		if(rs!=null) {
			RequestDispatcher view = request.getRequestDispatcher("views/admin/ad_reserveInfo.jsp");
			request.setAttribute("ReserveInfo", rs);
			view.forward(request, response);
		}else {
			System.out.println("목록없음");
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
