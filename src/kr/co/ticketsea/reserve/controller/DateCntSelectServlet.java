package kr.co.ticketsea.reserve.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ticketsea.reserve.model.service.ReserveService;

/**
 * Servlet implementation class DateCntSelectServlet
 */
@WebServlet(name = "DateCntSelect", urlPatterns = { "/dateCntSelect.do" })
public class DateCntSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public DateCntSelectServlet() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String date_sel = request.getParameter("date_sel");
		String cnt_sel = request.getParameter("cnt_sel");
		String [] dateCnt = {date_sel, cnt_sel};
		
		System.out.println(date_sel +"(DateCntSelectServlet)");
		System.out.println(cnt_sel +"(DateCntSelectServlet)");
		
		//int result = new ReserveService().dbTest(date_sel);
		
		RequestDispatcher view = request.getRequestDispatcher("views/reserve/TicketSea_reserv_step_2_seat.jsp");
		request.setAttribute("dateCnt", dateCnt);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
