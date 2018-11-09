package kr.co.ticketsea.admin.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.ticketsea.admin.member.model.service.AdMemberService;
import kr.co.ticketsea.member.model.vo.Member;

/**
 * Servlet implementation class AdMemberServlet
 */
@WebServlet(name = "AdMember", urlPatterns = { "/adMember.do" })
public class AdMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdMemberServlet() {
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
					int memberNo=Integer.parseInt(request.getParameter("memberNo"));
					
					Member member = new AdMemberService().selectOneMember(memberNo);
					
					if(member!=null) {
						RequestDispatcher view = request.getRequestDispatcher("views/admin/ad_memberInfo.jsp");
						request.setAttribute("Member", member);
						view.forward(request, response);
					}else {
						System.out.println("목록없음");
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
