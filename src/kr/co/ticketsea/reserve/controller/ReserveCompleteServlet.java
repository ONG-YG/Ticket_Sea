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
@WebServlet(name = "ReserveComplete", urlPatterns = { "/reserveComplete.do" })
public class ReserveCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private boolean postDone = false;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReserveCompleteServlet() {
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
				
				if(session!=null) {
					int progNo = Integer.parseInt( request.getParameter("progNo"));
					int psNo = Integer.parseInt( request.getParameter("psNo") );
					String phone = request.getParameter("phone");
					String email = request.getParameter("email");
					String bkStateCd = request.getParameter("bkStateCd");
					String payType = request.getParameter("payType");
					String sc_code = null;
					
					String[] seatList = new ReserveService().getSeatListByProgNo(progNo);
					
					if(seatList!=null) {
						ReserveSession rs = (ReserveSession)session.getAttribute("reserveSession");
						
						int memberNo = rs.getMemberNo();
						int currStat = rs.getCurrStat();
						long bkNo = rs.getBkNo();
						
						if(currStat==3) {
							//세션에 넣을 객체에 현재 진행단계를 4으로 바꿔줌
							rs.setCurrStat(4);
							
							//ReserveProgressing 객체 생성
							ReserveProgressing rp = null;
							
							//공연회차번호로 공연회차정보 조회
							PerformSchedule ps = new ReserveService().selectOnePerformSchedule(psNo);
							
								//step3에서 받았던 정보들 다시 받아와서 rp에 저장
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
											System.out.println("error at ReserveCompleteServlet-11");
											throw new Exception();
										}
										rp.setPsDate(ps.getPerformSchDate());
										rp.setShowCnt(ps.getPerformSchCnt());
										rp.setShowTime(ps.getPerformTime());
										rp.setCommission(si.getBk_comm());
										
										sc_code = si.getSc_code();
										
										ArrayList<SeatGradeState> seatGrdStList = new ReserveService().getSeatGradeStatus(psNo);
										if(!seatGrdStList.isEmpty()) {
											rp.setSeatGrdSt(seatGrdStList);
											
										}
										else {
											System.out.println("error at ReserveCompleteServlet-10");
											throw new Exception();
										}//if(!seatGrdStList.isEmpty()) END
										
									}
									else {
										System.out.println("error at ReserveCompleteServlet-9");
										throw new Exception();
									}//if(si!=null) END
									
								}//if(ps!=null) END
								
								//---------------------------------------------------------------------------------------------//
								
								//-----4단계로 넘어가기 전 필요한 작업 수행-----//
								
								
								//회원번호로 회원이름 조회 (String 배열로 받아옴)////////////////////////////////////STEP3에서 받아온 정보 써야함
								String[] contactInfo = new ReserveService().getPhoneMail(memberNo);
								
								if(contactInfo!=null) {
									rp.setMemberName(contactInfo[0]);
									
									//step3에서 받아온 연락처, 메일 rp에 저장
									rp.setPhone(phone);
									rp.setEmail(email);
									
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
													selSeatList.get(i).setSeatGrdColor(sg.getGrd_color());
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
											System.out.println("error at ReserveCompleteServlet-8");
											throw new Exception();
										}//if(chk) END
									
										System.out.println("ReserveCompleteServlet\n"+rp);/////////////////////////
										
										
										//예매번호, 결제방식 reserveProgressing객체에 저장
										rp.setBkNo(bkNo);
										rp.setPayType(payType);
										
										//예매정보 데이터 INSERT - BOOK_INF 테이블
										int result1 = new ReserveService().insertBookInfo(bkNo, memberNo, bkStateCd, rp.getTicketPrice(), rp.getTotalPrice(), payType, phone, email);
										
										if(result1>0) {
											
											//예매좌석목록 데이터 INSERT - BK_S_L 테이블
											int result2 = new ReserveService().insertBookSeatList(bkNo, psNo, seatList, sc_code);
											if(result2>0) {
												
												//예매진행중 데이터 DELETE - PROG_S_L 테이블
												int result3 = new ReserveService().deleteProgData(progNo);
												
												if(result3>0) {
													
													
													
													
													//세션 정보 저장
													session.setAttribute("reserveSession", rs);
													
													RequestDispatcher view = request.getRequestDispatcher("views/reserve/reserv_step_4_complete.jsp");
													request.setAttribute("stepFour", rp);
													view.forward(request, response);
													
													
													
													
													
												}else {
													System.out.println("error at ReserveCompleteServlet-9");
													throw new Exception();
												}//if(result3>0) END
												
											}else {
												System.out.println("error at ReserveCompleteServlet-7");
												throw new Exception();
											}//if(result2>0) END
											
										}else {
											System.out.println("error at ReserveCompleteServlet-6");
											throw new Exception();
										}//if(result1>0) END
										
									}else {
										System.out.println("error at ReserveCompleteServlet-5");
										throw new Exception();
									}//if(!selSeatList.isEmpty()) END
									
								}else {
									System.out.println("error at ReserveCompleteServlet-4");
									throw new Exception();
								}//if(contactInfo!=null) END
								
						}//if(currStat==2) END
					}else {
						System.out.println("error at ReserveCompleteServlet-3");
						throw new Exception();
					}//if(seatList!=null) END
				}else {
					System.out.println("error at ReserveCompleteServlet-2");
					throw new Exception();
				}//if(session!=null) END
				
			} catch (Exception e) {
				System.out.println("error at ReserveCompleteServlet-1");
				response.sendRedirect("/views/reserve/reserveError.jsp");
			}
		
		}//if(!postDone) END
		else {
			postDone = false;
		}
		
			
	}//void doGet함수 END

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		postDone = true;
	}

}
