package kr.co.ticketsea.reserve.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SeatSelectServlet
 */
@WebServlet(name = "ReserveConfirm", urlPatterns = { "/reserveConfirm.do" })
public class ReserveConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReserveConfirmServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			request.setCharacterEncoding("utf-8");
			HttpSession session = request.getSession(false);
			
			if(session!=null) {
				int progNo = Integer.parseInt( request.getParameter("progNo"));
				int psNo = Integer.parseInt( request.getParameter("psNo") );
				String seatList = request.getParameter("seatList");
				
				
				
				
			}else {
				System.out.println("error at ReserveConfirmServlet-2");
				throw new Exception();
			}//if(session!=null) END
		} catch (Exception e) {
			System.out.println("error at ReserveConfirmServlet-1");
			response.sendRedirect("/views/reserve/reserveError.jsp");
		}
		
		
		
		
		request.setCharacterEncoding("utf-8");
//		String date_sel = request.getParameter("date_sel");
//		String cnt_sel = request.getParameter("cnt_sel");
//		String seat_sel = request.getParameter("seat_sel");
//		String [] dateCntSeat = {date_sel, cnt_sel, seat_sel};
//		
//		System.out.println("(SeatSelectServlet)  " + date_sel);
//		System.out.println("(SeatSelectServlet)  " + cnt_sel);
//		System.out.println("(SeatSelectServlet)  " + seat_sel);
//		System.out.println("(SeatSelectServlet)  " + dateCntSeat[0]+"/"+dateCntSeat[1]+"/"+dateCntSeat[2]);
//		
//		RequestDispatcher view = request.getRequestDispatcher("views/reserve/reserv_step_3_confirm.jsp");
//		request.setAttribute("dateCntSeat", dateCntSeat);
//		view.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
