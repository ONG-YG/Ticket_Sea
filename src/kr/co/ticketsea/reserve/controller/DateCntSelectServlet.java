package kr.co.ticketsea.reserve.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ticketsea.reserve.model.service.ReserveService;
import kr.co.ticketsea.reserve.model.vo.PerformSchedule;

/**
 * Servlet implementation class ReserveStServlet
 */
@WebServlet(name = "DateCntSelect", urlPatterns = { "/dateCntSelect.do" })
public class DateCntSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DateCntSelectServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		int showNo = Integer.parseInt(request.getParameter("showNo"));
		
		ArrayList<PerformSchedule> psList = new ReserveService().selectAllPerformSchedule(showNo);
		
		if(!psList.isEmpty()) {
			RequestDispatcher view = request.getRequestDispatcher("views/reserve/reserv_step_1_date_time.jsp?showNo="+showNo);
			request.setAttribute("performSchList",psList);
			view.forward(request, response);
		}else {
			response.sendRedirect("/views/reserve/reserveError.jsp");
			System.out.println("error at reserveStServlet");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
