package kr.co.ticketsea.admin.reserve.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.ticketsea.admin.reserve.model.service.AdReserveService;
import kr.co.ticketsea.member.model.vo.Member;

/**
 * Servlet implementation class AdReserveUpdateServlet
 */
@WebServlet(name = "AdReserveUpdate", urlPatterns = { "/adReserveUpdate.do" })
public class AdReserveUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdReserveUpdateServlet() {
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
					String bk_no=request.getParameter("bk_no");
					String phone=request.getParameter("bk_phone");
					String email=request.getParameter("bk_email");
					
					int result=new AdReserveService().reserveUpdate(bk_no,phone,email);
					
					if(result>0) {
						response.sendRedirect("views/admin/adReserveUpdateSuccess.jsp");
					}else {
						response.sendRedirect("views/admin/error.jsp");
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
