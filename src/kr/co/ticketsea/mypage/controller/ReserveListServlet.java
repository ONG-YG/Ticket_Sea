package kr.co.ticketsea.mypage.controller;

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
import kr.co.ticketsea.mypage.model.vo.MyReserveList;
import kr.co.ticketsea.mypage.service.MypageService;
import kr.co.ticketsea.reserve.model.vo.ReserveInfo;


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

		HttpSession session = request.getSession(false);
		int memberNo = ((Member)session.getAttribute("member")).getMemberNo();
		
		// member_no로 bk_no검색
		ArrayList<ReserveInfo> rNumberList = new MypageService().selectReserveNumber(memberNo);
		
		// 추출한 bk_no로 ps_no를 조회하여 공연일,공연번호 추출
		ArrayList<MyReserveList> mrlList = new MypageService().selectPerformSchedule(rNumberList);

		// 매수와 중복된 값을 뽑아낸다.
		MyReserveList finalMrl = new MyReserveList(); // 최종 저장할 mrl값
		ArrayList<MyReserveList> finalMrlList = new ArrayList<MyReserveList>(); // 최종 저장할 배열 mrl값
		int count = 1; // 저장할 매수 초기화
		int tableNum = 1; // view에 표현되는 번호
		
		
		for(int i=0; i<mrlList.size(); i++) {

			if((i+1)!=mrlList.size()) { // int no2변수의 mrlList.get(i+1)이 없는 index이므로 조건 처리
				int no1 = mrlList.get(i).getShowNo();
				int no2 = mrlList.get(i+1).getShowNo();
				
				if(no1==no2) { // 회차번호 같으면 카운트 증가(매수증가)
					count++;
				}else { // 회차번호가 다르면 해당 공연명과 공연일 매수와 함께 배열에 저장
					
					finalMrl.setTableNum(tableNum);						// 번호
					finalMrl.setShowName(mrlList.get(i).getShowName()); // 공연명
					finalMrl.setShowDate(mrlList.get(i).getShowDate());	// 공연일
					finalMrl.setCount(count);							// 매수
					
					count=1; // 배열에 등록 후 카운트 초기화
					tableNum++; // 등록 후 번호 증가
					
					finalMrlList.add(finalMrl);
				}				
			}else { // 마지막 진행 시 넣어주고 끝.
				finalMrl.setTableNum(tableNum);						// 번호
				finalMrl.setShowName(mrlList.get(i).getShowName()); // 공연명
				finalMrl.setShowDate(mrlList.get(i).getShowDate());	// 공연일
				finalMrl.setCount(count);							// 매수
				
				count=1; // 배열에 등록 후 카운트 초기화
				tableNum++; // 등록 후 번호 증가
				
				finalMrlList.add(finalMrl);
			}
		}
		
		
		
		RequestDispatcher view = request.getRequestDispatcher("views/mypage/reserveList.jsp");
		request.setAttribute("finalMrlList", finalMrlList);
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
