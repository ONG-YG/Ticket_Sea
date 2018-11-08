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

//import kr.co.ticketsea.member.model.service.MemberService;////////////////////////
import kr.co.ticketsea.member.model.vo.Member;
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
		
		/*
		<script>
	    function reserve(){
	        var showNo = 20000; //////////////////////////////////////////////////// showNo 받아오는 코드 추가할 것
	        window.open("/dateCntSelect.do?showNo="+showNo, "reservePopUp", "width=1010, height=625");
	        
	        return false;
	    }
	    </script>
	    /////////////////휘명이 공연정보페이지에 넣을것
		*/
		try {
			
			request.setCharacterEncoding("utf-8");
			HttpSession session = request.getSession(false);
			
			////////////////////////////////////////////
			/*
			HttpSession session = request.getSession(true);
			
			Member m = new Member();//=
			m.setMemberId("mslove");//=
			m.setMemberPwd("1234");//=
			m = new MemberService().selectMember(m);
			*/
			//////////////////////////////////////////// 세션확인부분 수정할 것
			
			if(session!=null) {
				Member m = (Member)session.getAttribute("member");
				if(m!=null) {
					int memberNo = m.getMemberNo();
					ReserveSession rs = new ReserveSession();
					rs.setMemberNo(memberNo);
					rs.setCurrStat(1);
					rs.setProgNo(-1);
					rs.setProgTime(null);
					
					session.setAttribute("member", m);
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
						//공연 시작일
						String startDate = si.getM_show_st_date().toString();
						//공연 종료일
						String endDate = si.getM_show_ed_date().toString();
						
						
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
									System.out.println("error at DateCntSelectServlet-6");
									throw new Exception();
								}
							}
							
							stOne = new ReserveStepOne();
							stOne.setShowNo(showNo);
							stOne.setShowTitle(showTitle);
							stOne.setShowPoster(showPoster);
							stOne.setPsList(psList);
							stOne.setStartDate(startDate);
							stOne.setEndDate(endDate);
							
						}
						
						if(stOne!=null) {
							RequestDispatcher view = request.getRequestDispatcher("views/reserve/reserv_step_1_date_time.jsp?showNo="+showNo);
							request.setAttribute("stepOne",stOne);
							view.forward(request, response);
						}else {
							System.out.println("error at DateCntSelectServlet-5");
							throw new Exception();
						}
						
					}else {
						System.out.println("error at DateCntSelectServlet-4");
						throw new Exception();
					}
				}else {
					System.out.println("error at DateCntSelectServlet-3");
					throw new Exception();
				}
			}else {
				System.out.println("error at DateCntSelectServlet-2");
				System.out.println("로그인 안내");
				throw new Exception();
			}
		} catch (Exception e) {
			response.sendRedirect("/views/reserve/reserveError.jsp");
			System.out.println("error at DateCntSelectServlet-1");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
