package kr.co.ticketsea.admin.show.model.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ticketsea.admin.show.model.service.ShowService;
import kr.co.ticketsea.admin.show.model.vo.Show;

/**
 * Servlet implementation class ShowInsertServlet
 */
@WebServlet(name = "ShowInsert", urlPatterns = { "/showInsert.do" })
public class ShowInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		Show s = new Show();
		
		s.setShow_name(request.getParameter("show_name"));
		s.setShow_st_date(request.getParameter("show_st_date"));
		s.setShow_ed_date(request.getParameter("show_ed_date"));
		s.setArtists(request.getParameter("artists"));
		s.setShow_grd(request.getParameter("show_grd"));
		s.setShow_run(Integer.parseInt(request.getParameter("show_run")));
		
		
		int result = new ShowService().insertShow(s);
		
		if(result>0) {
			response.sendRedirect("/views/admin/showInsertSuccess.jsp");
		}else {
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
