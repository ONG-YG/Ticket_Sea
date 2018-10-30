package kr.co.ticketsea.reserve.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.ticketsea.reserve.model.service.ReserveService;
import kr.co.ticketsea.reserve.model.vo.ReserveSession;

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
				String[] seatList = request.getParameter("seatList").split(",");
				
				ReserveSession rs = (ReserveSession)session.getAttribute("reserveSession");
				int memberNo = rs.getMemberNo();
				int currStat = rs.getCurrStat();
				if(currStat==2) {
					//세션에 넣을 객체에 현재 진행단계를 3으로 바꿔줌
					rs.setCurrStat(3);
					
					//예매진행정보 INSERT
					String progTime = new ReserveService().insertProgData(progNo, memberNo, psNo, seatList);
					
					if(progTime!=null) {
						//세션에 넣을 객체 생성 및 예매시작시간 세팅
						rs.setProgTime(progTime);
						
						
						
						
						
					}else {
						System.out.println("error at ReserveConfirmServlet-3");
						throw new Exception();
					}//if(progTime!=null) END
				}
				
			}else {
				System.out.println("error at ReserveConfirmServlet-2");
				throw new Exception();
			}//if(session!=null) END
		} catch (Exception e) {
			System.out.println("error at ReserveConfirmServlet-1");
			response.sendRedirect("/views/reserve/reserveError.jsp");
		}
		
		
		
		
		
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
