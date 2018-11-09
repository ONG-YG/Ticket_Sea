package kr.co.ticketsea.admin.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.ticketsea.admin.member.model.service.AdMemberService;
import kr.co.ticketsea.member.model.vo.Member;

/**
 * Servlet implementation class AdMemberUpdateServlet
 */
@WebServlet(name = "AdMemberUpdate", urlPatterns = { "/adMemberUpdate.do" })
public class AdMemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdMemberUpdateServlet() {
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
					int memberNo=Integer.parseInt(request.getParameter("member_no"));
					String phone = request.getParameter("phone");
					String email = request.getParameter("email");
					String address = request.getParameter("address");
					System.out.println(memberNo);
					System.out.println(phone);
					Member member = new Member();
					member.setMemberNo(memberNo);
					member.setMemberPhone(phone);
					member.setMemberEmail(email);
					member.setMemberAddr(address);
					
					//비즈니스 로직
					int result = new AdMemberService().updateMember(member);
						
					if(result>0) {
						response.sendRedirect("/views/admin/memberUpdateSuccess.jsp");
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
