package kr.co.ticketsea.reserve.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
@WebServlet(name = "ReserveSeat", urlPatterns = { "/reserveSeat.do" })
public class ReserveSeatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ReserveSeatServlet() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		int showNo = Integer.parseInt(request.getParameter("showNo"));
		String date_sel = request.getParameter("date_sel");
		String cnt_sel = request.getParameter("cnt_sel");
		String [] dateCnt = {date_sel, cnt_sel};
		
				System.out.println("(ReserveSeatServlet)  showNo >> "+ showNo);
				System.out.println("(ReserveSeatServlet)  " + date_sel);
				System.out.println("(ReserveSeatServlet)  " + cnt_sel);
		
		int performSchNo = new ReserveService().selectOnePerformSchedule(showNo, date_sel, cnt_sel);
		
		if (performSchNo!=0) {
			//response.sendRedirect("/seatCheck.do?showNo="+showNo+"&psNo="+performSchNo);
			//RequestDispatcher view = request.getRequestDispatcher("views/reserve/seat_check.jsp?showNo="+showNo+"&psNo="+performSchNo);
			//RequestDispatcher view = request.getRequestDispatcher("views/reserve/reserv_step_2_seat.jsp?showNo="+showNo+"&psNo="+performSchNo);
			//request.setAttribute("dateCnt", dateCnt);
			//view.forward(request, response);
			
			//예약된 좌석 목록
			ArrayList<Integer> reserved_seats = new ReserveService().selectReservedSeats(performSchNo);
			
						for(int seat : reserved_seats) {
							System.out.println("seat val = "+seat);
						}
			
			//예약진행 중인 좌석 목록
			//String [] progressing_seats = new ReserveService().selectProgressingSeats(showNo,performSchNo);
			
			
			RequestDispatcher view = request.getRequestDispatcher("views/reserve/reserv_step_2_seat.jsp?showNo="+showNo+"&psNo="+performSchNo);
			request.setAttribute("dateCnt", dateCnt);
			view.forward(request, response);
			
		}else {
			response.sendRedirect("/views/reserve/reserveError.jsp");
					System.out.println("error at DateCntSelectServlet");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
