package kr.co.ticketsea.qna.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ticketsea.faq.model.service.FaqService;
import kr.co.ticketsea.faq.model.vo.Faq;
import kr.co.ticketsea.qna.model.service.QnaService;
import kr.co.ticketsea.qna.model.vo.Qna;

/**
 * Servlet implementation class QnaServlet
 */
@WebServlet(name = "Qna", urlPatterns = { "/qna.do" })
public class QnaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		
		//2. view에서 전송한 데이터를 변수에 저장
		int boardQ_no = Integer.parseInt(request.getParameter("boardQ_no"));
		
		//3. 비즈니스 로직 처리 
		Qna qna =  new QnaService().selectOneQna(boardQ_no);
		
		//4. 결과 리턴
		if(qna !=null) {
			RequestDispatcher view = request.getRequestDispatcher("views/qna/qna.jsp");
			request.setAttribute("qna", qna);
			view.forward(request, response);
			
		}else {
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
