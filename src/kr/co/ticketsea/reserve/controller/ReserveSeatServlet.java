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

import kr.co.ticketsea.member.model.vo.Member;
import kr.co.ticketsea.reserve.model.service.ReserveService;
import kr.co.ticketsea.reserve.model.vo.PerformSchedule;
import kr.co.ticketsea.reserve.model.vo.ReserveProgressing;
import kr.co.ticketsea.reserve.model.vo.ReserveSession;
import kr.co.ticketsea.reserve.model.vo.SeatGradeState;
import kr.co.ticketsea.reserve.model.vo.ShowInfo;

/**
 * Servlet implementation class DateCntSelectServlet
 */
@WebServlet(name = "ReserveSeat", urlPatterns = { "/reserveSeat.do" })
public class ReserveSeatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ReserveSeatServlet() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			request.setCharacterEncoding("utf-8");
			HttpSession session = request.getSession(false);
			
			//세션발급되지 않은 상태에서 접근 금지
			if(session!=null) {
				Member m = (Member)session.getAttribute("member");
				
				if(m!=null) {
					ReserveSession rs = (ReserveSession)session.getAttribute("reserveSession");
					int currStat = rs.getCurrStat();
					
					//step3_jsp에서 돌아온 경우 예매진행 정보 DELETE
					if(currStat==3) {
						int progNo = rs.getProgNo();
						int result = new ReserveService().deleteProgData(progNo);
						
						if(result>0) {
							currStat = 1;
						}else {
							System.out.println("error at ReserveSeatServlet-10");
							throw new Exception();
						}
					}
					
					//비정상적 루트에서 접근 금지
					if(currStat==1) {
						//세션에 넣을 reserveSession객체 - 진행단계 정보 update
						rs.setCurrStat(2);
						
						int psNo = Integer.parseInt( request.getParameter("psNo") );
						
						//reserveProgressing 객체 생성
						ReserveProgressing rp = null;
						
						//공연회차번호로 공연회차정보 조회
						PerformSchedule ps = new ReserveService().selectOnePerformSchedule(psNo);
						
							if(ps!=null) {
								//공연번호
								int showNo = ps.getShowNo();
								
								//공연정보 받아오기
								ShowInfo si = new ReserveService().getShowInfo(showNo);
								
								if(si!=null) {
									rp = new ReserveProgressing();
									rp.setPsNo(psNo);
									rp.setShowNo(showNo);
									rp.setShowTitle(si.getM_show_name());
									rp.setShowPoster(si.getM_show_poster());
									String thName = new ReserveService().getTheaterName(si.getTh_no());
									
									if(thName!=null) {
										rp.setTheaterName(thName);			
									}
									else {
										System.out.println("error at ReserveSeatServlet-9");
										throw new Exception();
									}
									rp.setPsDate(ps.getPerformSchDate());
									rp.setShowCnt(ps.getPerformSchCnt());
									rp.setShowTime(ps.getPerformTime());
									ArrayList<Integer> reserved_seats = new ReserveService().selectReservedSeats(psNo);
									rp.setReservedSeatList(reserved_seats);
									ArrayList<Integer> prog_seats = new ReserveService().selectProgressingSeats(psNo);  //예매 진행 정보 INSERT 시점부터 20분경과하지 않은 좌석
									rp.setProgSeatList(prog_seats);
									
									ArrayList<SeatGradeState> seatGrdStList = new ReserveService().getSeatGradeStatus(psNo);
									
									if(!seatGrdStList.isEmpty()) {
										rp.setSeatGrdSt(seatGrdStList);
										System.out.println("\n\nReserveSeatServlet\n"+rp);/////////////////////////
										
										//세션에 넣을 reserveSession객체 - 예매 진행 번호 생성
										int progNo = new ReserveService().createProgNo();
										
										if(progNo!=-1) {
											rs.setProgNo(progNo);
										}else {
											System.out.println("error at ReserveSeatServlet-8");
											throw new Exception();
										}//if(progNo!=-1) END
										
										//세션 정보 저장
										session.setAttribute("member", m);
										session.setAttribute("reserveSession", rs);
										
										RequestDispatcher view = request.getRequestDispatcher("views/reserve/reserv_step_2_seat.jsp");
										request.setAttribute("stepTwo", rp);
										view.forward(request, response);
									}
									else {
										System.out.println("error at ReserveSeatServlet-7");
										throw new Exception();
									}//if(!seatGrdStList.isEmpty())
									
								}
								else {
									System.out.println("error at ReserveSeatServlet-6");
									throw new Exception();
								}//if(si!=null) END
							}
							else {
								System.out.println("error at ReserveSeatServlet-5");
								throw new Exception();
							}//if(ps!=null) END
							
					}else {
						System.out.println("error at ReserveSeatServlet-4");
						throw new Exception();
					}//if(currStat==1) END
				}else {
					System.out.println("error at ReserveSeatServlet-3");
					throw new Exception();
				}//if(m!=null) END
			}else {
				System.out.println("error at ReserveSeatServlet-2");
				throw new Exception();
			}//if(session!=null) END
		} catch (Exception e) {
			response.sendRedirect("/views/reserve/reserveError.jsp");
			System.out.println("error at ReserveSeatServlet-1");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
