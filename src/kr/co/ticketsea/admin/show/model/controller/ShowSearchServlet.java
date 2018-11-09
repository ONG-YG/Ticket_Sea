package kr.co.ticketsea.admin.show.model.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ticketsea.admin.member.model.service.AdMemberService;
import kr.co.ticketsea.admin.member.model.vo.MemberPageData;
import kr.co.ticketsea.admin.show.model.service.ShowService;
import kr.co.ticketsea.admin.show.model.vo.PageData;

/**
 * Servlet implementation class ShowSearchServlet
 */
@WebServlet(name = "ShowSearch", urlPatterns = { "/showSearch.do" })
public class ShowSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowSearchServlet() {
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
				PageData spd = new ShowService().searchList(keyword,currentPage);
				
				RequestDispatcher view= request.getRequestDispatcher("views/admin/showSearch.jsp");
				request.setAttribute("pageData", spd);
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
