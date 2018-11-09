package kr.co.ticketsea.admin.show.model.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.ticketsea.admin.show.model.service.MiniShowService;
import kr.co.ticketsea.admin.show.model.vo.MiniPgData;
import kr.co.ticketsea.member.model.vo.Member;

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
					MiniPgData mpd=new MiniShowService().showWaitList(currentPage);
					
					
					//결과값 view로 리턴
					if(mpd!=null) {
						System.out.println("mpd있음");
						RequestDispatcher view = request.getRequestDispatcher("/views/admin/ad_miniShowList.jsp");
						request.setAttribute("miniPgData", mpd);
						view.forward(request, response);
					}else {
						System.out.println("mpd없음");
						response.sendRedirect("/views/admin/miniShowListFail.jsp");
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
