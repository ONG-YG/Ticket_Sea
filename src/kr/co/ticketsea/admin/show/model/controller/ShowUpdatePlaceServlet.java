package kr.co.ticketsea.admin.show.model.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.ticketsea.admin.show.model.service.ShowService;
import kr.co.ticketsea.admin.show.model.vo.ShowCategory;
import kr.co.ticketsea.admin.show.model.vo.ShowPlace;
import kr.co.ticketsea.member.model.vo.Member;

/**
 * Servlet implementation class ShowUpdatePlaceServlet
 */
@WebServlet(name = "ShowUpdatePlace", urlPatterns = { "/showUpdatePlace.do" })
public class ShowUpdatePlaceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowUpdatePlaceServlet() {
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
					ArrayList<ShowPlace> splist = new ShowService().showPlaceList();
					
					ArrayList<ShowCategory> sclist = new ShowService().showCategoryList();
					
					RequestDispatcher view= request.getRequestDispatcher("/views/admin/ad_showInsert.jsp");
					request.setAttribute("showPlaceList", splist);
					request.setAttribute("showCTGList", sclist);
					view.forward(request, response);
				}else{
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
