package kr.co.ticketsea.promo.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.ticketsea.member.model.vo.Member;
import kr.co.ticketsea.promo.model.service.PromoService;

/**
 * Servlet implementation class PromoWriteServlet
 */
@WebServlet(name = "PromoWrite", urlPatterns = { "/promoWrite.do" })
public class PromoWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PromoWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 한글 인코딩
		request.setCharacterEncoding("utf-8");
		
		//2. view에서 보내준 데이터를 변수에 저장
		String title = request.getParameter("title");
		String category = request.getParameter("category");
		String contents = request.getParameter("contents");
		String artist = request.getParameter("artist");
		String location = request.getParameter("location");
		
		//3. session에서 글을 작성한 사람의 ID를 추출
		HttpSession session  = request.getSession(false);
	
		try {
			String userId = ((Member)session.getAttribute("member")).getMemberId();
			//userId를 가져오도록 함 (비로그인 사용자 일시 Exception이 발생함)
			
			
			if(userId != null) {
				
			//4. 비즈니스 로직 처리
			int result = new PromoService().insertPromo(title, category, contents, artist, location, userId);
				
				if(result>0)
				{
					response.sendRedirect("/views/promo/writeSuccess.jsp");
					
				}else {
					throw new Exception();
				}
					}else {
				response.sendRedirect("/views/notice/writeFail.jsp");
				throw new Exception();
			}
			
		} catch (Exception e) {
			response.sendRedirect("/views/notice/error.jsp");
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
