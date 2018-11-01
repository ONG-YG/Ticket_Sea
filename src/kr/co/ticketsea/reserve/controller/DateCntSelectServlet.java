package kr.co.ticketsea.reserve.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.ticketsea.reserve.model.service.ReserveService;
import kr.co.ticketsea.reserve.model.vo.PerformSchedule;
import kr.co.ticketsea.reserve.model.vo.ReserveSession;
import kr.co.ticketsea.reserve.model.vo.ReserveStepOne;
import kr.co.ticketsea.reserve.model.vo.SeatGradeState;

import kr.co.ticketsea.reserve.model.vo.ShowInfo;

/**
 * Servlet implementation class ReserveStServlet
 */
@WebServlet(name = "DateCntSelect", urlPatterns = { "/dateCntSelect.do" })
public class DateCntSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DateCntSelectServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);//////////////////////////////
		ReserveSession rs = new ReserveSession();
		rs.setMemberNo(2);/////////////////////////////////////////////////////////// 세션확인부분 수정할 것

//		HttpSession session = request.getSession(false);
		if(session!=null) {
//			Member m = (Member)session.getAttribute("member");
//			int memberNo = m.getMemberNo();
//			ReserveSession rs = new ReserveSession();
//			rs.setMemberNo(memberNo);
			rs.setCurrStat(1);
			rs.setProgNo(-1);
			rs.setProgTime(null);
			session.setAttribute("reserveSession", rs);
			
			int showNo = Integer.parseInt(request.getParameter("showNo"));
			
			//step1객체 생성
			ReserveStepOne stOne = null;
			
			//공연정보 받아오기
			ShowInfo si = new ReserveService().getShowInfo(showNo);
			
			if(si!=null) {
				//공연명
				String showTitle = si.getM_show_name();
				//공연 포스터 파일명
				String showPoster = si.getM_show_poster();
				
				//공연 일정 목록 (잔여좌석정보x)
				ArrayList<PerformSchedule> psList = new ReserveService().selectAllPerformSchedule(showNo);
				//for (PerformSchedule ps : psList) { System.out.println(ps); }
				
				if(!psList.isEmpty()) {
					//잔여좌석정보 (PS_NO확인 & BK_S_L테이블에서 예약완료인지 확인)
					ArrayList<SeatGradeState> seatGrdStList = null;
					for(int i=0; i<psList.size(); i++) {
						PerformSchedule ps = psList.get(i);
						int psNo = ps.getPerformSchNo();
						//System.out.println("회차번호 = "+ps.getPerformSchNo());
						
						
						seatGrdStList = new ReserveService().getSeatGradeStatus(psNo);
						if(!seatGrdStList.isEmpty()) {
							ps.setSeatGrdStList(seatGrdStList);
						}else {
							response.sendRedirect("/views/reserve/reserveError.jsp");
							System.out.println("error at DateCntSelectServlet-3");
						}
						
						
						//int availableSeat = new ReserveService().availableSeatCount(ps.getPerformSchNo());
						//ps.setAvailableSeat(availableSeat);
						//System.out.println("잔여좌석수 = "+psList.get(i).getAvailableSeat());
					}
					
					

					stOne = new ReserveStepOne();
					stOne.setShowNo(showNo);
					stOne.setShowTitle(showTitle);
					stOne.setShowPoster(showPoster);
					stOne.setPsList(psList);
					
					//stOne.setSeatGrdStList(seatGrdStList);
					
					
					
					
					
					
				}
				
				if(stOne!=null) {
					RequestDispatcher view = request.getRequestDispatcher("views/reserve/reserv_step_1_date_time.jsp?showNo="+showNo);
					request.setAttribute("stepOne",stOne);
					view.forward(request, response);
				}else {
					response.sendRedirect("/views/reserve/reserveError.jsp");
					System.out.println("error at DateCntSelectServlet-1");
				}
				
			}else {
				response.sendRedirect("/views/reserve/reserveError.jsp");
				System.out.println("error at DateCntSelectServlet-2");
			}
		}else {
			response.sendRedirect("/views/reserve/reserveError.jsp");
			System.out.println("로그인 안내");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
