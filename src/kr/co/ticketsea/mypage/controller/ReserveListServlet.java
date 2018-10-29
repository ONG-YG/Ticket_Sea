package kr.co.ticketsea.mypage.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.ticketsea.member.model.vo.Member;
import kr.co.ticketsea.mypage.service.MypageService;

/**
 * Servlet implementation class ReserveListServlet
 */
@WebServlet(name = "ReserveList", urlPatterns = { "/reserveList.do" })
public class ReserveListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReserveListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		1. �˻��� ��� �ѹ��� ���Ź�ȣ �˻�
//		select bk_no from book_inf where member_no = '(m.getMember_no)';
//		������ ����..>  �ϳ���  �й� �ʿ�
//
//		2. ������ ���Ź�ȣ�� ����ȸ����ȣ �˻�
//		select ps_no from bk_s_l where bk_no = '(������ ���Ź�ȣ)';
//
//		4. ������ ����ȸ����ȣ�� ������ȣ �˻�
//		select m_show_no from perf_sch where ps_no = '(������ ����ȸ����ȣ)';
//
//		5. ������ȣ�� ������ ����
//		select m_show_name from musical_l where m_show_no = '(������ȣ)';
		
		// 1. ��� ���� �ѹ� ����
		HttpSession session = request.getSession(false);
		int memberNo = ((Member)session.getAttribute("member")).getMemberNo();
		
		// 2. ����Ͻ� ����(������� ���̺����)
		new MypageService().selectMusical(memberNo);
		
		// 3. �������� �����鼭 jsp �������� �̵�.
		RequestDispatcher view = request.getRequestDispatcher("views/mypage/reserveList.jsp");
		request.setAttribute("reserveList", m);
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
