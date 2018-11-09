package kr.co.ticketsea.admin.show.model.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.ticketsea.admin.show.model.service.PlaceService;
import kr.co.ticketsea.member.model.vo.Member;

/**
 * Servlet implementation class PlaceInsertServlet
 */
@WebServlet(name = "PlaceInsert", urlPatterns = { "/placeInsert.do" })
public class PlaceInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaceInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			request.setCharacterEncoding("UTF-8");
			
			HttpSession session = request.getSession(false);
			
			if(session!=null) {
				Member m = (Member)session.getAttribute("member");
				
				if(m!=null && m.getMemberGrade()=='A') {
					request.setCharacterEncoding("UTF-8");
					String th_name = request.getParameter("placeName");
					String th_lct = request.getParameter("placeAddress");
					
					System.out.println("장소이름:"+th_name);
					System.out.println("주소:"+th_lct);
					
					int result=new PlaceService().insertPlace(th_name,th_lct);
					
					if(result>0) {
						response.sendRedirect("/adShowPlace.do");
					}else {
						response.sendRedirect("/views/admin/error.jsp");
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
