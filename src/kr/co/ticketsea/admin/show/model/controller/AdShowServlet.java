package kr.co.ticketsea.admin.show.model.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ticketsea.admin.show.model.service.ShowService;
import kr.co.ticketsea.admin.show.model.vo.Show;

/**
 * Servlet implementation class AdShowServlet
 */
@WebServlet(name = "AdShow", urlPatterns = { "/adShow.do" })
public class AdShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 인코딩
		request.setCharacterEncoding("utf-8");
		
		// 2.view에서 전송한 데이터를 변수에 저장
		int showNo = Integer.parseInt(request.getParameter("m_show_no"));
		
		//3. 비즈니스 로직 처리 
		Show show=new ShowService().selectOneShow(showNo);
		
		//4. 결과 리턴
		if(show!=null) {
			RequestDispatcher view = request.getRequestDispatcher("views/admin/ad_showUpdate.jsp");
			request.setAttribute("showData", show);
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
