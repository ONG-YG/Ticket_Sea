package kr.co.ticketsea.admin.show.model.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.ticketsea.admin.show.model.service.MiniShowService;
import kr.co.ticketsea.member.model.vo.Member;

/**
 * Servlet implementation class MiniShowRefuseServlet
 */
@WebServlet(name = "MiniShowRefuse", urlPatterns = { "/miniShowRefuse.do" })
public class MiniShowRefuseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MiniShowRefuseServlet() {
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
					int msNo = Integer.parseInt(request.getParameter("msNo"));
					
					int result = new MiniShowService().refuseMiniShow(msNo);
					
					if(result>0) {
						response.sendRedirect("/views/admin/msRefuseSuccess.jsp");
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
