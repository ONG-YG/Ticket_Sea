package kr.co.ticketsea.admin.reserve.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.ticketsea.admin.reserve.model.service.AdReserveService;
import kr.co.ticketsea.admin.reserve.model.vo.Reserve;
import kr.co.ticketsea.admin.reserve.model.vo.ReservePageData;
import kr.co.ticketsea.member.model.vo.Member;

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
		try {
			request.setCharacterEncoding("utf-8");
			
			HttpSession session = request.getSession(false);
			
			if(session!=null) {
				Member m = (Member)session.getAttribute("member");
					
				if(m!=null && m.getMemberGrade()=='A') {
					int currentPage;
					
					if(request.getParameter("currentPage")==null) {
						currentPage=1;
					}else {
						currentPage=Integer.parseInt(request.getParameter("currentPage"));
					}
					
					//비즈니스 로직 
					ReservePageData rpg=new AdReserveService().reserveAllList(currentPage);
					
					if(rpg!=null) {
						RequestDispatcher view = request.getRequestDispatcher("views/admin/ad_reserveList.jsp");
						request.setAttribute("reservePgData", rpg);
						view.forward(request, response);
					}else {
						response.sendRedirect("/views/admin/reserveListFail.jsp");
					}
				}else {
					throw new Exception();
				}
			}else {
				throw new Exception();
			}
		}catch (Exception e) {
			response.sendRedirect("/views/admin/adminError.jsp");
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
