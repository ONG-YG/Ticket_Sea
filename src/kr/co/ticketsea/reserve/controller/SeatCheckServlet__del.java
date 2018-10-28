package kr.co.ticketsea.reserve.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ticketsea.reserve.model.service.ReserveService;

/**
 * Servlet implementation class SeatCheckServlet
 */
@WebServlet(name = "SeatCheck", urlPatterns = { "/seatCheck.do" })
public class SeatCheckServlet__del extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeatCheckServlet__del() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		//int showNo = Integer.parseInt(request.getParameter("showNo"));
		int performSchNo = Integer.parseInt(request.getParameter("psNo"));
		
		//예약된 좌석 목록
		String [] reserved_seats = new ReserveService().selectReservedSeats(performSchNo);
		
		//예약진행 중인 좌석 목록
		//String [] progressing_seats = new ReserveService().selectProgressingSeats(showNo,performSchNo);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
