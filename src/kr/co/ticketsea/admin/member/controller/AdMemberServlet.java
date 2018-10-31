package kr.co.ticketsea.admin.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		request.getParameter("utf-8");
		
		int memberNo=Integer.parseInt(request.getParameter("memberNo"));
		
		Member m = new AdMemberService().selectOneMember(memberNo);
		
		if(m!=null) {
			RequestDispatcher view = request.getRequestDispatcher("views/admin/ad_memberInfo.jsp");
			request.setAttribute("Member", m);
			view.forward(request, response);
		}else {
			System.out.println("불러오기실패");
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
