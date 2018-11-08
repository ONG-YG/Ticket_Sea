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
 * Servlet implementation class MiniShowListServlet
 */
@WebServlet(name = "MiniShowList", urlPatterns = { "/miniShowList.do" })
public class MiniShowListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MiniShowListServlet() {
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
		MiniPgData mpd=new MiniShowService().showWaitList(currentPage);
		
		//결과값 view로 리턴
		
			RequestDispatcher view = request.getRequestDispatcher("/views/admin/ad_miniShowList.jsp");
			request.setAttribute("miniPgData", mpd);
			view.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
