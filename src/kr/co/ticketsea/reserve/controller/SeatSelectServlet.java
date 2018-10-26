package kr.co.ticketsea.reserve.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SeatSelectServlet
 */
@WebServlet(name = "SeatSelect", urlPatterns = { "/seatSelect.do" })
public class SeatSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeatSelectServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String date_sel = request.getParameter("date_sel");
		String cnt_sel = request.getParameter("cnt_sel");
		String seat_sel = request.getParameter("seat_sel");
		String [] dateCntSeat = {date_sel, cnt_sel, seat_sel};
		
		System.out.println(date_sel +"(SeatSelectServlet)");
		System.out.println(cnt_sel +"(SeatSelectServlet)");
		System.out.println(seat_sel +"(SeatSelectServlet)");
		
		//int result = new ReserveService().dbTest(date_sel);
		
		//RequestDispatcher view = request.getRequestDispatcher("#");
		//request.setAttribute("dateCntSeat", dateCntSeat);
		//view.forward(request, response);	
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
