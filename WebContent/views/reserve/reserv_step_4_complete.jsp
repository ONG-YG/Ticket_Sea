<%@page import="kr.co.ticketsea.member.model.vo.Member"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="kr.co.ticketsea.reserve.model.vo.SelectedSeat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.ticketsea.reserve.model.vo.ReserveSession"%>
<%@page import="kr.co.ticketsea.reserve.model.vo.SeatGradeState"%>
<%@page import="java.sql.Date"%>
<%@page import="kr.co.ticketsea.reserve.model.vo.ReserveProgressing"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	request.setCharacterEncoding("utf-8");
	
	ReserveProgressing rp = (ReserveProgressing)request.getAttribute("stepFour");
	
	int psNo = rp.getPsNo();										//공연회차번호
	int showNo = rp.getShowNo();									//공연번호
	String showTitle = rp.getShowTitle();							//공연명
	String showPoster = rp.getShowPoster();							//공연 포스터 파일명
	String theaterName = rp.getTheaterName();						//공연장명
	Date psDate = rp.getPsDate();									//공연일
	int showCnt = rp.getShowCnt();									//공연회차
	String showTime = rp.getShowTime();								//공연시간
	
	String memberName = rp.getMemberName();							//예매자명
	long bkNo = rp.getBkNo();										//예매번호
	ArrayList<SelectedSeat> selSeatList = rp.getSelSeatList();		//선택좌석 목록
	String phone = rp.getPhone();									//연락처
	String email = rp.getEmail();									//메일
	int commission = rp.getCommission();							//수수료
	int ticketPrice = rp.getTicketPrice();							//티켓가격 총합
	int totalPrice = rp.getTotalPrice();							//총 결제 금액
	String payType = rp.getPayType();								//결제방식
	
	//System.out.println("티켓가격="+ticketPrice);//////////////
	
	ReserveSession rs = (ReserveSession)session.getAttribute("reserveSession");
	int progNo = rs.getProgNo();
	String progTime = rs.getProgTime();
	//System.out.println("progTime" + progTime);
	Member member = (Member)session.getAttribute("member");
	System.out.println(member.getMemberId());////
	session.setAttribute("member", member);
%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Ticket Sea 예매</title>
    
    <!-- 외부 스타일 시트 적용 -->
    <link href="../../css/reserv_common.css" rel="stylesheet" type="text/css">
    <link href="../../css/reserv_step_4.css" rel="stylesheet" type="text/css">
    
    <script
      type="text/javascript"
        src="../../resources/jquery-3.3.1.js">
    </script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
	</script>
    <script>
	    $(document).ready(function(){
	    	
	    	pageInit();
	    	
	    });
	    
	    function pageInit() {
	    	
	    	var showPosterSrc = "/img/poster/'<%=showPoster%>'";
	    	$('#mini_poster img').attr('src',showPosterSrc); //공연 포스터
            
            var showTitle = '<%=showTitle%>';
	    	$('#showName_td').text(showTitle);	//공연명
            
            var bkNo = <%=bkNo%>;
            $('#bkNo_td').text(bkNo); //예매번호
            
            var thName = '<%=theaterName%>';
            $('#thName_td').text(thName); //공연장명
            
	    	var memberName = '<%=memberName%>';
	    	$('#memberName_td').text(memberName); //예매자명(회원이름)
	    	
            $('#phoneNo_td').text('<%=phone%>'); //휴대폰번호
	    	$('#email_td').text('<%=email%>'); //이메일
            
	    	var payInfo = '<%=payType%>'; //결제방식
	    	$('#payInfo_td').text(payInfo); //결제방식
	    	
	    	var psDate = '<%=psDate%>'.split('-');
	    	var year = psDate[0];
	    	var month = psDate[1];
	    	var day = psDate[2];
	    	$('#rInfo_Date').text(year+'년 '+month+'월 '+day+'일');
	    	$('#rInfo_CntTime').text('<%=showCnt%>'+'회 '+'<%=showTime%>');
	    	
	    	var ticketPrice = Number(<%=ticketPrice%>).toLocaleString();
	    	$('#rInfo_TicketP').text(ticketPrice);
	    	
	    	var commission = Number(<%=commission%>).toLocaleString();
	    	$('#rInfo_Comm').text(commission);
	    	
	    	var totalPrice = Number(<%=totalPrice%>).toLocaleString();
	    	$('#rInfo_TotP').text(totalPrice);
	    	
	    	selectedSeatView();
	    }
	    
		function selectedSeatView() {
	    	
	    	var selectedSeatList = [];
        	<%
        	for (SelectedSeat selSeat : selSeatList) {
        	%>
        		var selSeat = ['<%=selSeat.getSeatGrd()%>', '<%=selSeat.getSeatTitle()%>', '<%=selSeat.getSeatGrdColor()%>'];
        		selectedSeatList.push(selSeat);
        	<%} %>
	    	
	    	var list = "";
        	for(var i=0; i<selectedSeatList.length; i++) {
        		var selSeat = selectedSeatList[i];
        		
        		list += "<li id='selected_seat_no_'+selSeat[0]> "
				            +"<div class='seat_color' style='background:"+selSeat[2]+"'></div> "
				            +"<div class='seat_detail_info'> "
				                +"<span class='seat_grade' >"+selSeat[0]+"</span> "
				                +"<span class='seat_no'>"+selSeat[1]+"</span> "
				            +"</div> "
				        +"</li> ";
        	}
        	$('#select_seat_grade').html(list);
        	
        }
		
		
        function reserveView() {
        	location.href="#";
        }
    </script>
    
</head>
    
<body>
    <div id="wrap_reserve">
        <div id="header">
            <div class="reserve_title">
                <h1>TicketSea</h1>
                <h2>예매</h2>
            </div>
            <ul class="reserve_step">
                <li class="step1">
                    <span>날짜/회차선택</span>  
                </li>
                
                <li class="step2">
                    <span>등급/좌석선택</span>  
                </li>
       
                <li class="step3">    
                    <span>배송선택/예매확인</span>  
                </li>
                
                <li class="step4">    
                    <span>결제완료</span>  
                </li>
                
            </ul>
        </div>
<!----------------------------------------------------------->
        <div id="container">
            <div class="reserve_content3">
                <div class="reserve_left">
                    <center>
                    <div class="reserve_step3">
                    <strong class="title"><span>티켓예매가 완료되었습니다</span></strong>
                    <div class="white_box">
                        
                        <table>
                            <colgroup>
                                <col style="width:150px;">
                                <col>
                            </colgroup>
                            <tbody>
                                <tr height="150px">
                                    <th id="showPoster_td"><img height="150px" src="/img/poster/poster_20000.gif"></th>
                                    <td id="showName_td">공연명</td>
                                </tr>
                                <tr>
                                    <th>예매번호</th>
                                    <td id="bkNo_td">예매번호</td>
                                </tr>
                                <tr>
                                    <th>공연장명</th>
                                    <td id="thName_td">공연장명</td>
                                </tr>
                                <tr>
                                    <th>예매자명</th>
                                    <td id="memberName_td">주문자 이름</td>
                                </tr>
                                <tr>
                                    <th>휴대폰 번호</th>
                                    <td id="phoneNo_td">연락처</td>
                                </tr>
                                <tr>
                                    <th>이메일</th>
                                    <td id="email_td">이메일</td>
                                </tr>
                                <tr>
                                    <th>결제방식</th>
                                    <td id="payInfo_td">결제방식</td>
                                </tr>
                            </tbody>
                        </table>
                        
                    </div>
                </div>
                </center>
                <!--------------------------------------->
                <!--------------------------------------->
                <!--------------------------------------->
                </div>
                <div class="reserve_right">
                    <!--
                    <div class="top_info_area" style="text-align: left; padding-left: 18px;">
                        <div id="mini_poster">
                            <img src="#">
                        </div>
                        <strong id="mini_show_title">공연이름 출력란</strong>
                    </div>
                    -->
                    <div class="reserve_result">
                        <div id="reserve_res_tit">예매 정보</div>
                        <hr>
                        <ul id="select_seat_grade" class="seat_lst">
                            <!--좌석등급 별 list-->
                            <!-- 
                            <li id="seat_grade_33625">
                                <div class="seat_color" style="background:#ffc000"></div>
                                <div class="seat_detail_info">
                                    <span class="seat_grade" >VIP석</span>
                                    <span class="seat_no">K열 21번</span>
                                </div>
                            </li>
                             -->
                        </ul>
                        <div id="reserve_info">
                            <div id="date_sel_info">
                                <strong>공연일</strong><span id="rInfo_Date">2018.10.29 (월)</span>
                            </div>
                            <hr>
                            <div id="cnt_sel_info">
                                <strong>공연회차</strong><span id="rInfo_CntTime">2회 13:00 ~14:30</span>
                            </div>
                            <hr>
                            <div id="ticket_price_info">
                                <strong>티켓금액</strong><span id="rInfo_TicketP">140,000</span>
                            </div>
                            <hr>
                            <div id="commision_info">
                                <strong>예매수수료</strong><span id="rInfo_Comm">1,000</span>
                            </div>
                            <hr id="final_hr">
                            <div id="total_price_info">
                                <strong>총 결제</strong><span id="rInfo_TotP">141,000</span>
                            </div>
                        </div>
                    </div>
                    
                    <div id="reserve_btn_only">
                        <a class="btn" onclick="reserveView()">예매확인 페이지</a>
                    </div>
                    
                </div>
            </div>
        </div>
    </div>

</body>
</html>