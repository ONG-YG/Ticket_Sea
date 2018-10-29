package kr.co.ticketsea.admin.show.model.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ticketsea.admin.show.model.service.MiniShowService;
import kr.co.ticketsea.admin.show.model.vo.MiniPgData;

/**
 * Servlet implementation class MsApproveListServlet
 */
@WebServlet(name = "MsApproveList", urlPatterns = { "/msApproveList.do" })
public class MsApproveListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MsApproveListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage;
		if(request.getParameter("currentPage")==null) {
			currentPage=1;
		}else {
			currentPage=Integer.parseInt(request.getParameter("currentPage"));
		}
		MiniPgData mpd=new MiniShowService().showApproveList(currentPage);
		
		if(mpd!=null) {
			RequestDispatcher view = request.getRequestDispatcher("/views/admin/ad_miniShowAp.jsp");
			request.setAttribute("miniPgData", mpd);
			view.forward(request, response);
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
