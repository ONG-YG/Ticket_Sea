package kr.co.ticketsea.admin.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import kr.co.ticketsea.admin.member.model.service.AdMemberService;
import kr.co.ticketsea.admin.member.model.vo.MemberPageData;
import kr.co.ticketsea.member.model.service.MemberService;

/**
 * Servlet implementation class MemberSearchServlet
 */
@WebServlet(name = "MemberSearch", urlPatterns = { "/memberSearch.do" })
public class MemberSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 한글 인코딩
		request.setCharacterEncoding("utf-8");
				
		//2. view에서 보내준 값을 변수에 저장
		String keyword =request.getParameter("search");
		
		//3. 페이징 처리를 하기 위한 작업
		
		int currentPage;
		if(request.getParameter("currentPage")==null){
			currentPage=1;
		}
		else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		//4. 비즈니스 로직 처리
		MemberPageData mpd = new AdMemberService().searchList(keyword,currentPage);
		
		RequestDispatcher view= request.getRequestDispatcher("views/admin/memberSearch.jsp");
		request.setAttribute("pageData", mpd);
		request.setAttribute("keyword", keyword);
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
