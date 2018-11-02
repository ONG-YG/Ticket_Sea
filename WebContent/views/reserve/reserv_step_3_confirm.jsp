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
	
	ReserveProgressing rp = (ReserveProgressing)request.getAttribute("stepThree");
	
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
	
	
	ReserveSession rs = (ReserveSession)session.getAttribute("reserveSession");
	int progNo = rs.getProgNo();
%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Ticket Sea 예매</title>
    
    <!-- 외부 스타일 시트 적용 -->
    <link href="../../css/reserv_common.css" rel="stylesheet" type="text/css">
    <link href="../../css/reserv_step_3.css" rel="stylesheet" type="text/css">
    
    <script
      type="text/javascript"
        src="../../resources/jquery-3.3.1.js">
    </script>
    <script>
	    $(document).ready(function(){
	    	
	    	pageInit();
			
	    });
	    
	    function pageInit() {
	    	
	    	var memberName = '<%=memberName%>';
	    	$('#bookMemName').text(memberName);
	    	
	    	var showTitle = '<%=showTitle%>';
	    	$('#mini_show_title').text(showTitle);	//공연명
	    	
	    	var showPosterSrc = "/img/poster/<%=showPoster%>";
	    	$('#mini_poster img').attr('src',showPosterSrc);
	    	
	    	$('#inputPhoneNo').val(<%=phone%>);
	    	$('#inputEmail').val('<%=email%>');
	    	
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
        		var selSeat = ['<%=selSeat.getSeatGrd()%>', '<%=selSeat.getSeatTitle()%>'];
        		selectedSeatList.push(selSeat);
        	<%} %>
        	//console.log(selectedSeatList);/////////////////////////////
	    	
	    	var list = "";
        	for(var i=0; i<selectedSeatList.length; i++) {
        		var selSeat = selectedSeatList[i];
        		var grdColor = 'black';
        		if(selSeat[0]=='R') {grdColor='#ffc000';}//////////////////
        		else if(selSeat[0]=='S') {grdColor='blue';}////////////////
        		list += "<li id='selected_seat_no_'+selSeat[0]> "
				            +"<div class='seat_color' style='background:"+grdColor+"'></div> "
				            +"<div class='seat_detail_info'> "
				                +"<span class='seat_grade' >"+selSeat[0]+"</span> "
				                +"<span class='seat_no'>"+selSeat[1]+"</span> "
				            +"</div> "
				        +"</li> ";
        	}
        	$('#select_seat_grade').html(list);
        	
        }
    	
        function prev() {
            var chk = confirm("이전 단계로 돌아가면 현재의 예매 정보를 잃게 됩니다.");
            if(chk) {
            	<%
	        	//세션에 담긴 reserveSession객체 - 진행단계 정보 update
				rs.setCurrStat(1);
        		session.setAttribute("reserveSession", rs);
        		//예매중정보지우는 코드 추가할것////////////////////////////////////////////////////////////////////////////////  ※※※※※※※※※※※※※※※
        		%>
                location.href="/reserveSeat.do?psNo="+<%=psNo%>;
                //document.getElementById("goBackStep2").submit();
            }
        }
        function next() {
            var phone = document.getElementById("inputPhoneNo").value;
            var userCheck = document.getElementById("agree_phone").checked;
            //alert(userCheck);
            var userInfoAgree = document.getElementById("reserve_agree2").checked;
            if(phone=="") {
                alert("휴대폰번호를 입력해주세요");
            }
            else if(!userCheck) {
                alert("주문자 확인 및 휴대폰 번호 수집을 확인하셔야 결제가 가능합니다.");
            }
            else if(!userInfoAgree) {
                alert("개인정보 제 3자 제공에 동의하셔야 결제가 가능합니다.");
            }
            else {
               	var patt1 = new RegExp("^[0-9]+$");
               	var patt2 = new RegExp("^[0-9]{9,20}$");
               	var res1 = patt1.test( $("#inputPhoneNo").val());
               	var res2 = patt2.test( $("#inputPhoneNo").val());
               	
               	if( !res1 ){ alert("휴대폰 번호는 숫자만 입력할 수 있습니다."); }
               	else if( !res2 ){ alert("휴대폰 번호를 9자 이상 입력해주세요."); }
               	else { location.href="#"; }
            }
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
                    <div class="reserve_step3">
                    <strong class="title"><span>주문자 확인</span></strong>
                    <div class="white_box">
                        <table>
                            <caption>주문자 확인</caption>
                            <colgroup>
                                <col style="width:86px;">
                                <col>
                                <col style="width:100px;">
                                <col>
                            </colgroup>
                            <tbody>
                            <tr>
                                <th>이름 <span class="color_red">*<span class="blind">필수입력란</span></span></th>
                                <td id="bookMemName">주문자 이름</td>
                                <th>휴대폰 번호 <span class="color_red">*<span class="blind">필수입력란</span></span></th>
                                <td>
                                    <div class="input_block">
                                        <input type="text" class="input" id="inputPhoneNo" style="width:235px" maxlength="20" title="휴대폰 번호" value="">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th>이메일</th>
                                <td colspan="3">
                                    <div class="input_block">
                                        <input type="text" class="input" id="inputEmail" style="width:450px" title="이메일" value="">
                                    </div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
        <div class="reserve_step3">
                    <strong class="title"><span class="sp">예매자 확인</span></strong>
                    <ul class="input_block_lst">
                        <li>
                            <span class="checkbox">
                                <input type="checkbox" id="agree_phone" name="agree_phone">
                            </span>
                            <label for="agree_phone">주문자 확인 및 예매처리를 위해 휴대폰번호, 이메일, (배송수령 시)주소, (입력필요 시) 생년월일을 수집하며, 이용목적 달성 이후 파기합니다.</label>
                        </li>
                        <li>
                            <span class="checkbox">
					            <input type="checkbox" id="reserve_agree2" name="reserve_agree2" value="false">
                            </span>
                            <label for="reserve_agree2">개인정보 제 3자 제공에 동의합니다. (고객응대 및 관람정보안내 등을 위함)</label>
                            <a href="#" class="link_green" >[상세보기]</a>
                        </li>
                    </ul>
                </div>
                <!--------------------------------------->
                <!--------------------------------------->
                <!--------------------------------------->
                </div>
                <div class="reserve_right">
                    <div class="top_info_area" style="text-align: left; padding-left: 18px;">
                        <div id="mini_poster">
                            <img src="#">
                        </div>
                        <strong id="mini_show_title">공연이름 출력란</strong>
                    </div>
                    
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
                            <li id="seat_grade_33625">
                                <div class="seat_color" style="background:#ffc000"></div>
                                <div class="seat_detail_info">
                                    <span class="seat_grade" >VIP석</span>
                                    <span class="seat_no">K열 22번</span>
                                </div>
                            </li>
                            <li id="seat_grade_33625">
                                <div class="seat_color" style="background:#ffc000"></div>
                                <div class="seat_detail_info">
                                    <span class="seat_grade" >VIP석</span>
                                    <span class="seat_no">K열 23번</span>
                                </div>
                            </li>
                            <li id="seat_grade_33625">
                                <div class="seat_color" style="background:#ffc000"></div>
                                <div class="seat_detail_info">
                                    <span class="seat_grade" >VIP석</span>
                                    <span class="seat_no">K열 24번</span>
                                </div>
                            </li>
                            <li id="seat_grade_33625">
                                <div class="seat_color" style="background:#ffc000"></div>
                                <div class="seat_detail_info">
                                    <span class="seat_grade" >VIP석</span>
                                    <span class="seat_no">K열 25번</span>
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
                        <!--
                        <form action="/reserveSeat.do?psNo=" method="post" id="goBackStep2">
                        </form>
                         -->
                    </div>
                    <div class="reserve_btn">
                        <a class="btn" onclick="prev()">이전단계</a>
                        <a class="btn" onclick="next()">다음단계</a>
                    </div>

                </div>
            </div>
        </div>
    </div>

</body>
</html>