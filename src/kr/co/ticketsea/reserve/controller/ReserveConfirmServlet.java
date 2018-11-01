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
import kr.co.ticketsea.reserve.model.vo.ReserveProgressing;
import kr.co.ticketsea.reserve.model.vo.ReserveSession;
import kr.co.ticketsea.reserve.model.vo.SeatGradeState;
import kr.co.ticketsea.reserve.model.vo.SelectedSeat;
import kr.co.ticketsea.reserve.model.vo.ShowInfo;

/**
 * Servlet implementation class SeatSelectServlet
 */
@WebServlet(name = "ReserveConfirm", urlPatterns = { "/reserveConfirm.do" })
public class ReserveConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private boolean postDone = false;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReserveConfirmServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!postDone) {
			try {
				
				request.setCharacterEncoding("utf-8");
				HttpSession session = request.getSession(false);
				System.out.println("check");////////////////////////////////

				if(session!=null) {
					int progNo = Integer.parseInt( request.getParameter("progNo"));
					int psNo = Integer.parseInt( request.getParameter("psNo") );
					
					String[] seatList = request.getParameter("seatList").split(",");
					
					ReserveSession rs = (ReserveSession)session.getAttribute("reserveSession");
					
					int memberNo = rs.getMemberNo();
					int currStat = rs.getCurrStat();
					
					if(currStat==2) {
						//세션에 넣을 객체에 현재 진행단계를 3으로 바꿔줌
						rs.setCurrStat(3);
						
						//ReserveProgressing 객체 생성
						ReserveProgressing rp = null;
						
						//공연회차번호로 공연회차정보 조회
						PerformSchedule ps = new ReserveService().selectOnePerformSchedule(psNo);
						
							//step2에서 받았던 정보들 다시 받아와서 rp에 저장
							//---------------------------------------------------------------------------------------------//
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
										System.out.println("error at ReserveConfirmServlet-6");
										throw new Exception();
									}
									rp.setPsDate(ps.getPerformSchDate());
									rp.setShowCnt(ps.getPerformSchCnt());
									rp.setShowTime(ps.getPerformTime());
									rp.setCommission(si.getBk_comm());
									
									ArrayList<SeatGradeState> seatGrdStList = new ReserveService().getSeatGradeStatus(psNo);
									if(!seatGrdStList.isEmpty()) {
										rp.setSeatGrdSt(seatGrdStList);
										
									}
									else {
										System.out.println("error at ReserveConfirmServlet-7");
										throw new Exception();
									}//if(!seatGrdStList.isEmpty()) END
									
								}
								else {
									System.out.println("error at ReserveConfirmServlet-5");
									throw new Exception();
								}//if(si!=null) END
								
							}//if(ps!=null) END
							
							//---------------------------------------------------------------------------------------------//
							
							//-----3단계로 넘어가기 전 필요한 작업 수행-----//
							
							//예매진행정보 INSERT
							String progTime = new ReserveService().insertProgData(progNo, memberNo, psNo, seatList);
							
							if(progTime!=null) {
								//세션에 넣을 객체 생성 및 예매시작시간 세팅
								rs.setProgTime(progTime);
								
								//회원번호로 회원이름, 연락처, 메일 조회 (String 배열로 받아옴)
								String[] contactInfo = new ReserveService().getPhoneMail(memberNo);
								
								if(contactInfo!=null) {
									rp.setMemberName(contactInfo[0]);
									rp.setPhone(contactInfo[1]);
									rp.setEmail(contactInfo[2]);
									
									//선택좌석의 등급, title 조회
									ArrayList<SelectedSeat> selSeatList = new ReserveService().getSeatInfo(seatList);
									
									if(!selSeatList.isEmpty()) {
										rp.setSelecSeatList(selSeatList);
										
										//각 선택좌석의 가격정보 저장
										boolean chk = true;
										gp: for(int i=0; i<selSeatList.size(); i++) {
											String seatGrd = selSeatList.get(i).getSeatGrd();
											
											for(SeatGradeState sg: rp.getSeatGrdSt()) {
												if (sg.getTh1_seat_grd().equals(seatGrd)) {
													selSeatList.get(i).setSeatPrice(sg.getTh1_seat_prc());
													continue gp;
												}
											}
											chk = false;
											break;
										}
										
										//티켓가격 총합 & 총 결제 금액 계산
										if(chk) {
											rp.setTicketPrice();
											rp.setTotalPrice();
											
										}else {
											System.out.println("error at ReserveConfirmServlet-8");
											throw new Exception();
										}//if(chk) END
										
										//예매번호 생성 후 정상적으로 예매번호 생성완료 시 step3 jsp페이지로 객체 전달 및 이동
										long bkNo = new ReserveService().createBookNo();
										if(bkNo!=-1) {
											rp.setBkNo(bkNo);
											
											System.out.println("ReserveConfirmServlet\n"+rp);/////////////////////////
											
											//세션 정보 저장
											session.setAttribute("reserveSession", rs);
											
											RequestDispatcher view = request.getRequestDispatcher("views/reserve/reserv_step_3_confirm.jsp");
											request.setAttribute("stepThree", rp);
											view.forward(request, response);
										}
										
									}else {
										System.out.println("error at ReserveConfirmServlet-5");
										throw new Exception();
									}//if(!selSeatList.isEmpty()) END
									
								}else {
									System.out.println("error at ReserveConfirmServlet-4");
									throw new Exception();
								}//if(contactInfo!=null) END
								
							}else {
								System.out.println("error at ReserveConfirmServlet-3");
								throw new Exception();
							}//if(progTime!=null) END
							
					}//if(currStat==2) END
					
				}else {
					System.out.println("error at ReserveConfirmServlet-2");
					throw new Exception();
				}//if(session!=null) END
				
			} catch (Exception e) {
				System.out.println("error at ReserveConfirmServlet-1");
				response.sendRedirect("/views/reserve/reserveError.jsp");
			}
		}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		postDone = true;
	}

}
