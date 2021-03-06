<%@page import="kr.co.ticketsea.reserve.model.vo.ReserveSession"%>
<%@page import="kr.co.ticketsea.reserve.model.vo.SeatGradeState"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.ticketsea.reserve.model.vo.ReserveProgressing"%>
<%@page import="kr.co.ticketsea.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	request.setCharacterEncoding("utf-8");
	
	ReserveProgressing rp = (ReserveProgressing)request.getAttribute("stepTwo");
	
	int showNo = rp.getShowNo();
	
	int psNo = rp.getPsNo();										//공연회차번호
	String showTitle = rp.getShowTitle();							//공연명
	String showPoster = rp.getShowPoster();							//공연 포스터 파일명
	String theaterName = rp.getTheaterName();						//공연장명
	Date psDate = rp.getPsDate();									//공연일
	int showCnt = rp.getShowCnt();									//공연회차
	String showTime = rp.getShowTime();								//공연시간
	ArrayList<Integer> reservedSeatList = rp.getReservedSeatList();	//예매완료 좌석 목록
	ArrayList<Integer> progSeatList = rp.getProgSeatList();			//예매진행 중 좌석 목록
	ArrayList<SeatGradeState> seatGrdStList = rp.getSeatGrdStList();	//등급별 좌석가격 및 잔여석	
	
	ReserveSession rs = (ReserveSession)session.getAttribute("reserveSession");
	int progNo = rs.getProgNo();
	
	Member m = (Member)session.getAttribute("member");
	//session.setAttribute("member", m);
%>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Ticket Sea 예매</title>
    
    <!-- <link rel="shortcut icon" type="image/x-icon" href="http://ticketlink.dn.toastoven.net/web/favicon.ico"> -->
    
    <!-- 외부 스타일 시트 적용 -->
    <link href="../../css/reserv_common.css" rel="stylesheet" type="text/css">
    <link href="../../css/reserv_step_2.css" rel="stylesheet" type="text/css">
    
    <script
      type="text/javascript"
        src="../../resources/jquery-3.3.1.js">
    </script>
<!--
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
-->
    <script>		
        var selected_seat = [];
        var selected_seat_val = [];
        
        var seatGrdStList = [];
    	<%
    	for (SeatGradeState seatGrdSt : seatGrdStList) {
    		String seatGrd = seatGrdSt.getTh1_seat_grd();
    		int seatPrice = seatGrdSt.getTh1_seat_prc();
    		int avail = seatGrdSt.getAvailableSeatCnt();
    		String grd_color = seatGrdSt.getGrd_color();
    	%>
    	var seatGrdSt = ['<%=seatGrd%>', <%=seatPrice%>, <%=avail%>, '<%=grd_color%>'];
    	seatGrdStList.push(seatGrdSt);
    	<%} %>
        
        $(document).ready(function(){
        	
        	pageInit();
    		
            $('.seat_a').click(function(){
            	var selected = $(this).attr('id');
                var index = selected_seat.indexOf(selected);
                
                if(index>=0) {
                    //이미 선택된 좌석 다시 클릭
                    selected_seat.splice(index,1);
                    $(this).toggleClass("selected");
                }
                else if (index==-1 & selected_seat.length<5) {
                    //선택 안된 상태의 좌석 클릭
                    selected_seat.push( selected );
                    $(this).toggleClass("selected");
                }
                else {
                    alert("최대 5개까지 선택이 가능합니다.")
                }
                
                if(selected_seat.length>0) {
                    $('.reserve_btn>a').eq(1).css('background-color','skyblue');
                    $('.reserve_btn>a').eq(1).css('color','white');
                }
                else {
                    $('.reserve_btn>a').eq(1).css('background-color','white');
                    $('.reserve_btn>a').eq(1).css('color','dodgerblue');
                }
                selected_seat.sort();
                selectedSeatView();
                //alert(selected_seat);
            });
            
            $('.seat_p').click(function(){
            	alert("결제 진행 중인 좌석입니다.");
            });
            
            //$('.seat_a').tooltip();
            
        });
        $('.seat_a').tooltip();
        
        function pageInit() {
        	var psDate = '<%=psDate%>'.split('-');
        	var year = psDate[0];
        	var month = psDate[1];
        	var day = psDate[2];
        	var dateTime = year+"년 "+month+"월 "+day+"일  "+'[ <%=showTime%> ]';
        	
        	$('.show_etc_info .date').text(dateTime);					//공연일 + 공연시간
        	$('.show_etc_info .place').text('<%=theaterName%>');		//공연장명
        	$('.show_title h4').text('<%=showTitle%>');					//공연명
        	
        	var reservedSeatList = [];
        	<%
        	for (int seatNo : reservedSeatList) {
        	%>
        	reservedSeatList.push(<%=seatNo%>);
        	<%} %>
        	
        	var progSeatList = [];
        	<%
        	for (int seatNo : progSeatList) {
        	%>
        	progSeatList.push(<%=seatNo%>);
        	<%} %>
        	
        	for (var i=0; i<reservedSeatList.length; i++) {
        		$('#select_seat div[value='+reservedSeatList[i]+']').addClass('seat_r');
        		$('#select_seat div[value='+reservedSeatList[i]+']').removeClass('seat_a');
        	}
        	
        	for (var i=0; i<progSeatList.length; i++) {
        		$('#select_seat div[value='+progSeatList[i]+']').addClass('seat_p');
        		$('#select_seat div[value='+progSeatList[i]+']').removeClass('seat_a');
        	}
        	
        	var list = "";
        	for(var i=0; i<seatGrdStList.length; i++) {
        		list += "<li id='seat_grade_"+seatGrdStList[i][0]+"'>"
       				+"<div class='seat_color' style='background:#ffc000'></div>"
       				+"<div class='seat_detail_info'>"
	        				+"<span class='seat_grade'>"+seatGrdStList[i][0]+"석</span>"
	        				+"<span class='seat_price'>"+seatGrdStList[i][1]+"원</span>"
	        				+"<span class='seat_avail'>"
		        				+"<span class='seat_avail_n'>"+seatGrdStList[i][2]+"</span>석"
	        				+"</span>"
       				+"</div>"
       			+"</li>";
       			
        	}
        	
        	$('#select_seat_grade').html(list);
        	
        }
        
        function seatIni() {
            selected_seat = [];
            $('#select_seat div').removeClass('selected');
            
            $('li[id^=selected_seat_no_] .selected_seat_grade').text(); //좌석등급
            $('li[id^=selected_seat_no_] .selected_seat_grade').text() //좌석위치
            $('li[id^=selected_seat_no_]').removeClass('selected_li');
            
            $('.reserve_btn>a').eq(1).css('background-color','white');
            $('.reserve_btn>a').eq(1).css('color','dodgerblue');
        }
        
        function selectedSeatView() {
            $('li[id^=selected_seat_no_]').removeClass('selected_li');
            for(var i=0; i<selected_seat.length; i++) {
                var seat_grd = $('#divSeatArray').children('#'+selected_seat[i]).attr('grade');
                var seat_loc = $('#divSeatArray').children('#'+selected_seat[i]).attr('title');
                
                var sel_list = $('li[id^=selected_seat_no_]').eq(i);
                $(sel_list).addClass('selected_li');
                
                var grdColor = "";
                var temp = seat_grd.split('석')[0];
                
                for(var k=0; k<seatGrdStList.length; k++){
                	var seatGrdSt = seatGrdStList[k];
                 	if(seatGrdSt[0]==seat_grd.split('석')[0]) {
                		grdColor = seatGrdSt[3];
                		break;
                	}
                }
                
                $(sel_list).children('.selected_seat_color').attr('style', 'background:'+grdColor);
                $(sel_list).children().children('.selected_seat_grade').text(seat_grd); //좌석등급
                $(sel_list).children().children('.selected_seat_no').text(seat_loc) //좌석위치
            }
        }
        
        function prev() {
        	<%
        	Member member = new Member();
        	member.setMemberNo(rs.getMemberNo());
        	session.setAttribute("member", member);//////////
        	%>
        	var showNo = <%= showNo %>;
            location.href="/dateCntSelect.do?showNo="+showNo;
        }

        function next() {
            var stat = false;
            if(selected_seat.length>0){
                stat = true;
            }
            
            if(stat==false) {
                alert("좌석을 선택하지 않았습니다. 좌석을 선택해주세요.");
            }else {
            	<%
            	//step3_jsp로 넘어갈때 세션 끊기는 문제 있어서 다시 setAttribute
            	session.setAttribute("member", m);
            	%>
            	var selected_seat_val = [];
            	for(var i=0; i<selected_seat.length; i++){
            		var v = $('#'+selected_seat[i]).attr('value');
            		selected_seat_val.push( v );
            	}
            	$('#seat_form').val(selected_seat_val);
                document.getElementById("seatSubmitForm").submit();
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
            <div class="reserve_content">
                <div class="reserve_left">
                    <div class="reserve_show_info">
                        <p class="show_etc_info">
                            <span class="date">2018.10.16(화) 20:00</span>
                            <span class="place">!!! 공연장명 !!!</span>
                        </p>
                        <div class="show_title">
                            <h4>!!! 공연명 !!!</h4>
                        </div>
                    </div>
                    <div id="main_view_top">
                        <div id="select_seat" style="width: 678px; height: 405px; background-image: url(&quot;../../img/Theater_seat.jpg&quot;); background-repeat: no-repeat; background-position: 0px 0px;">
                            <!--좌석-->
                            <div id="divSeatArray">
                                <!--<div class="label">A</div>-->
                                <div class="seat_a" id="t300008" style="LEFT: 203px; TOP: 229px" name="tk" value="300008" title="1층 A열 001번" grade="R석"></div>
									<div class="seat_a" id="t300009" style="LEFT: 214px; TOP: 229px" name="tk" value="300009" title="1층 A열 002번" grade="S석"></div>
									<div class="seat_a" id="t300010" style="LEFT: 225px; TOP: 229px" name="tk" value="300010" title="1층 A열 003번" grade="S석"></div>
									<div class="seat_a" id="t300011" style="LEFT: 236px; TOP: 229px" name="tk" value="300011" title="1층 A열 004번" grade="S석"></div>
									<div class="seat_a" id="t300015" style="LEFT: 259px; TOP: 229px" name="tk" value="300015" title="1층 A열 005번" grade="S석"></div>
									<div class="seat_a" id="t300016" style="LEFT: 270px; TOP: 229px" name="tk" value="300016" title="1층 A열 006번" grade="S석"></div>
									<div class="seat_a" id="t300017" style="LEFT: 281px; TOP: 229px" name="tk" value="300017" title="1층 A열 007번" grade="S석"></div>
									<div class="seat_a" id="t300018" style="LEFT: 292px; TOP: 229px" name="tk" value="300018" title="1층 A열 008번" grade="S석"></div>
									<div class="seat_a" id="t300019" style="LEFT: 303px; TOP: 229px" name="tk" value="300019" title="1층 A열 009번" grade="S석"></div>
									<div class="seat_a" id="t300020" style="LEFT: 314px; TOP: 229px" name="tk" value="300020" title="1층 A열 010번" grade="S석"></div>
									<div class="seat_a" id="t300021" style="LEFT: 325px; TOP: 229px" name="tk" value="300021" title="1층 A열 011번" grade="S석"></div>
									<div class="seat_a" id="t300025" style="LEFT: 351px; TOP: 229px" name="tk" value="300025" title="1층 A열 012번" grade="S석"></div>
									<div class="seat_a" id="t300026" style="LEFT: 362px; TOP: 229px" name="tk" value="300026" title="1층 A열 013번" grade="R석"></div>
									<div class="seat_a" id="t300027" style="LEFT: 373px; TOP: 229px" name="tk" value="300027" title="1층 A열 014번" grade="R석"></div>
									<div class="seat_a" id="t300028" style="LEFT: 384px; TOP: 229px" name="tk" value="300028" title="1층 A열 015번" grade="R석"></div>
									<div class="seat_a" id="t300029" style="LEFT: 395px; TOP: 229px" name="tk" value="300029" title="1층 A열 016번" grade="R석"></div>
									<div class="seat_a" id="t300030" style="LEFT: 406px; TOP: 229px" name="tk" value="300030" title="1층 A열 017번" grade="R석"></div>
									<div class="seat_a" id="t300031" style="LEFT: 417px; TOP: 229px" name="tk" value="300031" title="1층 A열 018번" grade="R석"></div>
									<div class="seat_a" id="t300035" style="LEFT: 440px; TOP: 229px" name="tk" value="300035" title="1층 A열 019번" grade="R석"></div>
									<div class="seat_a" id="t300036" style="LEFT: 451px; TOP: 229px" name="tk" value="300036" title="1층 A열 020번" grade="S석"></div>
									<div class="seat_a" id="t300037" style="LEFT: 462px; TOP: 229px" name="tk" value="300037" title="1층 A열 021번" grade="S석"></div>
									<div class="seat_a" id="t300038" style="LEFT: 473px; TOP: 229px" name="tk" value="300038" title="1층 A열 022번" grade="S석"></div>
									<!--<div class="label">B</div>-->
									<div class="seat_a" id="t400007" style="LEFT: 192px; TOP: 241px" name="tk" value="400007" title="1층 B열 001번" grade="S석"></div>
									<div class="seat_a" id="t400008" style="LEFT: 203px; TOP: 241px" name="tk" value="400008" title="1층 B열 002번" grade="S석"></div>
									<div class="seat_a" id="t400009" style="LEFT: 214px; TOP: 241px" name="tk" value="400009" title="1층 B열 003번" grade="S석"></div>
									<div class="seat_a" id="t400010" style="LEFT: 225px; TOP: 241px" name="tk" value="400010" title="1층 B열 004번" grade="S석"></div>
									<div class="seat_a" id="t400011" style="LEFT: 236px; TOP: 241px" name="tk" value="400011" title="1층 B열 005번" grade="S석"></div>
									<div class="seat_a" id="t400015" style="LEFT: 259px; TOP: 241px" name="tk" value="400015" title="1층 B열 006번" grade="S석"></div>
									<div class="seat_a" id="t400016" style="LEFT: 270px; TOP: 241px" name="tk" value="400016" title="1층 B열 007번" grade="S석"></div>
									<div class="seat_a" id="t400017" style="LEFT: 281px; TOP: 241px" name="tk" value="400017" title="1층 B열 008번" grade="S석"></div>
									<div class="seat_a" id="t400018" style="LEFT: 292px; TOP: 241px" name="tk" value="400018" title="1층 B열 009번" grade="S석"></div>
									<div class="seat_a" id="t400019" style="LEFT: 303px; TOP: 241px" name="tk" value="400019" title="1층 B열 010번" grade="S석"></div>
									<div class="seat_a" id="t400020" style="LEFT: 314px; TOP: 241px" name="tk" value="400020" title="1층 B열 011번" grade="S석"></div>
									<div class="seat_a" id="t400021" style="LEFT: 325px; TOP: 241px" name="tk" value="400021" title="1층 B열 012번" grade="S석"></div>
									<div class="seat_a" id="t400025" style="LEFT: 351px; TOP: 241px" name="tk" value="400025" title="1층 B열 013번" grade="R석"></div>
									<div class="seat_a" id="t400026" style="LEFT: 362px; TOP: 241px" name="tk" value="400026" title="1층 B열 014번" grade="R석"></div>
									<div class="seat_a" id="t400027" style="LEFT: 373px; TOP: 241px" name="tk" value="400027" title="1층 B열 015번" grade="R석"></div>
									<div class="seat_a" id="t400028" style="LEFT: 384px; TOP: 241px" name="tk" value="400028" title="1층 B열 016번" grade="R석"></div>
									<div class="seat_a" id="t400029" style="LEFT: 395px; TOP: 241px" name="tk" value="400029" title="1층 B열 017번" grade="R석"></div>
									<div class="seat_a" id="t400030" style="LEFT: 406px; TOP: 241px" name="tk" value="400030" title="1층 B열 018번" grade="R석"></div>
									<div class="seat_a" id="t400031" style="LEFT: 417px; TOP: 241px" name="tk" value="400031" title="1층 B열 019번" grade="R석"></div>
									<div class="seat_a" id="t400035" style="LEFT: 440px; TOP: 241px" name="tk" value="400035" title="1층 B열 020번" grade="S석"></div>
									<div class="seat_a" id="t400036" style="LEFT: 451px; TOP: 241px" name="tk" value="400036" title="1층 B열 021번" grade="S석"></div>
									<div class="seat_a" id="t400037" style="LEFT: 462px; TOP: 241px" name="tk" value="400037" title="1층 B열 022번" grade="S석"></div>
									<div class="seat_a" id="t400038" style="LEFT: 473px; TOP: 241px" name="tk" value="400038" title="1층 B열 023번" grade="S석"></div>
									<div class="seat_a" id="t400039" style="LEFT: 484px; TOP: 241px" name="tk" value="400039" title="1층 B열 024번" grade="S석"></div>
									<!--<div class="label">C</div>-->
									<div class="seat_a" id="t500007" style="LEFT: 192px; TOP: 253px" name="tk" value="500007" title="1층 C열 001번" grade="S석"></div>
									<div class="seat_a" id="t500008" style="LEFT: 203px; TOP: 253px" name="tk" value="500008" title="1층 C열 002번" grade="S석"></div>
									<div class="seat_a" id="t500009" style="LEFT: 214px; TOP: 253px" name="tk" value="500009" title="1층 C열 003번" grade="S석"></div>
									<div class="seat_a" id="t500010" style="LEFT: 225px; TOP: 253px" name="tk" value="500010" title="1층 C열 004번" grade="S석"></div>
									<div class="seat_a" id="t500011" style="LEFT: 236px; TOP: 253px" name="tk" value="500011" title="1층 C열 005번" grade="S석"></div>
									<div class="seat_a" id="t500015" style="LEFT: 259px; TOP: 253px" name="tk" value="500015" title="1층 C열 006번" grade="S석"></div>
									<div class="seat_a" id="t500016" style="LEFT: 270px; TOP: 253px" name="tk" value="500016" title="1층 C열 007번" grade="S석"></div>
									<div class="seat_a" id="t500017" style="LEFT: 281px; TOP: 253px" name="tk" value="500017" title="1층 C열 008번" grade="S석"></div>
									<div class="seat_a" id="t500018" style="LEFT: 292px; TOP: 253px" name="tk" value="500018" title="1층 C열 009번" grade="S석"></div>
									<div class="seat_a" id="t500019" style="LEFT: 303px; TOP: 253px" name="tk" value="500019" title="1층 C열 010번" grade="S석"></div>
									<div class="seat_a" id="t500020" style="LEFT: 314px; TOP: 253px" name="tk" value="500020" title="1층 C열 011번" grade="S석"></div>
									<div class="seat_a" id="t500021" style="LEFT: 325px; TOP: 253px" name="tk" value="500021" title="1층 C열 012번" grade="S석"></div>
									<div class="seat_a" id="t500025" style="LEFT: 351px; TOP: 253px" name="tk" value="500025" title="1층 C열 013번" grade="R석"></div>
									<div class="seat_a" id="t500026" style="LEFT: 362px; TOP: 253px" name="tk" value="500026" title="1층 C열 014번" grade="R석"></div>
									<div class="seat_a" id="t500027" style="LEFT: 373px; TOP: 253px" name="tk" value="500027" title="1층 C열 015번" grade="R석"></div>
									<div class="seat_a" id="t500028" style="LEFT: 384px; TOP: 253px" name="tk" value="500028" title="1층 C열 016번" grade="R석"></div>
									<div class="seat_a" id="t500029" style="LEFT: 395px; TOP: 253px" name="tk" value="500029" title="1층 C열 017번" grade="R석"></div>
									<div class="seat_a" id="t500030" style="LEFT: 406px; TOP: 253px" name="tk" value="500030" title="1층 C열 018번" grade="R석"></div>
									<div class="seat_a" id="t500031" style="LEFT: 417px; TOP: 253px" name="tk" value="500031" title="1층 C열 019번" grade="R석"></div>
									<div class="seat_a" id="t500035" style="LEFT: 440px; TOP: 253px" name="tk" value="500035" title="1층 C열 020번" grade="S석"></div>
									<div class="seat_a" id="t500036" style="LEFT: 451px; TOP: 253px" name="tk" value="500036" title="1층 C열 021번" grade="S석"></div>
									<div class="seat_a" id="t500037" style="LEFT: 462px; TOP: 253px" name="tk" value="500037" title="1층 C열 022번" grade="S석"></div>
									<div class="seat_a" id="t500038" style="LEFT: 473px; TOP: 253px" name="tk" value="500038" title="1층 C열 023번" grade="S석"></div>
									<div class="seat_a" id="t500039" style="LEFT: 484px; TOP: 253px" name="tk" value="500039" title="1층 C열 024번" grade="S석"></div>
									<!--<div class="label">D</div>-->
									<div class="seat_a" id="t600007" style="LEFT: 192px; TOP: 265px" name="tk" value="600007" title="1층 D열 001번" grade="S석"></div>
									<div class="seat_a" id="t600008" style="LEFT: 203px; TOP: 265px" name="tk" value="600008" title="1층 D열 002번" grade="S석"></div>
									<div class="seat_a" id="t600009" style="LEFT: 214px; TOP: 265px" name="tk" value="600009" title="1층 D열 003번" grade="S석"></div>
									<div class="seat_a" id="t600010" style="LEFT: 225px; TOP: 265px" name="tk" value="600010" title="1층 D열 004번" grade="S석"></div>
									<div class="seat_a" id="t600011" style="LEFT: 236px; TOP: 265px" name="tk" value="600011" title="1층 D열 005번" grade="S석"></div>
									<div class="seat_a" id="t600015" style="LEFT: 259px; TOP: 265px" name="tk" value="600015" title="1층 D열 006번" grade="S석"></div>
									<div class="seat_a" id="t600016" style="LEFT: 270px; TOP: 265px" name="tk" value="600016" title="1층 D열 007번" grade="S석"></div>
									<div class="seat_a" id="t600017" style="LEFT: 281px; TOP: 265px" name="tk" value="600017" title="1층 D열 008번" grade="S석"></div>
									<div class="seat_a" id="t600018" style="LEFT: 292px; TOP: 265px" name="tk" value="600018" title="1층 D열 009번" grade="S석"></div>
									<div class="seat_a" id="t600019" style="LEFT: 303px; TOP: 265px" name="tk" value="600019" title="1층 D열 010번" grade="S석"></div>
									<div class="seat_a" id="t600020" style="LEFT: 314px; TOP: 265px" name="tk" value="600020" title="1층 D열 011번" grade="S석"></div>
									<div class="seat_a" id="t600021" style="LEFT: 325px; TOP: 265px" name="tk" value="600021" title="1층 D열 012번" grade="S석"></div>
									<div class="seat_a" id="t600025" style="LEFT: 351px; TOP: 265px" name="tk" value="600025" title="1층 D열 013번" grade="R석"></div>
									<div class="seat_a" id="t600026" style="LEFT: 362px; TOP: 265px" name="tk" value="600026" title="1층 D열 014번" grade="R석"></div>
									<div class="seat_a" id="t600027" style="LEFT: 373px; TOP: 265px" name="tk" value="600027" title="1층 D열 015번" grade="R석"></div>
									<div class="seat_a" id="t600028" style="LEFT: 384px; TOP: 265px" name="tk" value="600028" title="1층 D열 016번" grade="R석"></div>
									<div class="seat_a" id="t600029" style="LEFT: 395px; TOP: 265px" name="tk" value="600029" title="1층 D열 017번" grade="R석"></div>
									<div class="seat_a" id="t600030" style="LEFT: 406px; TOP: 265px" name="tk" value="600030" title="1층 D열 018번" grade="R석"></div>
									<div class="seat_a" id="t600031" style="LEFT: 417px; TOP: 265px" name="tk" value="600031" title="1층 D열 019번" grade="R석"></div>
									<div class="seat_a" id="t600035" style="LEFT: 440px; TOP: 265px" name="tk" value="600035" title="1층 D열 020번" grade="S석"></div>
									<div class="seat_a" id="t600036" style="LEFT: 451px; TOP: 265px" name="tk" value="600036" title="1층 D열 021번" grade="S석"></div>
									<div class="seat_a" id="t600037" style="LEFT: 462px; TOP: 265px" name="tk" value="600037" title="1층 D열 022번" grade="S석"></div>
									<div class="seat_a" id="t600038" style="LEFT: 473px; TOP: 265px" name="tk" value="600038" title="1층 D열 023번" grade="S석"></div>
									<div class="seat_a" id="t600039" style="LEFT: 484px; TOP: 265px" name="tk" value="600039" title="1층 D열 024번" grade="S석"></div>
									<!--<div class="label">E</div>-->
									<div class="seat_a" id="t700007" style="LEFT: 192px; TOP: 277px" name="tk" value="700007" title="1층 E열 001번" grade="S석"></div>
									<div class="seat_a" id="t700008" style="LEFT: 203px; TOP: 277px" name="tk" value="700008" title="1층 E열 002번" grade="S석"></div>
									<div class="seat_a" id="t700009" style="LEFT: 214px; TOP: 277px" name="tk" value="700009" title="1층 E열 003번" grade="S석"></div>
									<div class="seat_a" id="t700010" style="LEFT: 225px; TOP: 277px" name="tk" value="700010" title="1층 E열 004번" grade="S석"></div>
									<div class="seat_a" id="t700011" style="LEFT: 236px; TOP: 277px" name="tk" value="700011" title="1층 E열 005번" grade="S석"></div>
									<div class="seat_a" id="t700015" style="LEFT: 259px; TOP: 277px" name="tk" value="700015" title="1층 E열 006번" grade="S석"></div>
									<div class="seat_a" id="t700016" style="LEFT: 270px; TOP: 277px" name="tk" value="700016" title="1층 E열 007번" grade="S석"></div>
									<div class="seat_a" id="t700017" style="LEFT: 281px; TOP: 277px" name="tk" value="700017" title="1층 E열 008번" grade="S석"></div>
									<div class="seat_a" id="t700018" style="LEFT: 292px; TOP: 277px" name="tk" value="700018" title="1층 E열 009번" grade="S석"></div>
									<div class="seat_a" id="t700019" style="LEFT: 303px; TOP: 277px" name="tk" value="700019" title="1층 E열 010번" grade="S석"></div>
									<div class="seat_a" id="t700020" style="LEFT: 314px; TOP: 277px" name="tk" value="700020" title="1층 E열 011번" grade="S석"></div>
									<div class="seat_a" id="t700021" style="LEFT: 325px; TOP: 277px" name="tk" value="700021" title="1층 E열 012번" grade="S석"></div>
									<div class="seat_a" id="t700025" style="LEFT: 351px; TOP: 277px" name="tk" value="700025" title="1층 E열 013번" grade="R석"></div>
									<div class="seat_a" id="t700026" style="LEFT: 362px; TOP: 277px" name="tk" value="700026" title="1층 E열 014번" grade="R석"></div>
									<div class="seat_a" id="t700027" style="LEFT: 373px; TOP: 277px" name="tk" value="700027" title="1층 E열 015번" grade="R석"></div>
									<div class="seat_a" id="t700028" style="LEFT: 384px; TOP: 277px" name="tk" value="700028" title="1층 E열 016번" grade="R석"></div>
									<div class="seat_a" id="t700029" style="LEFT: 395px; TOP: 277px" name="tk" value="700029" title="1층 E열 017번" grade="R석"></div>
									<div class="seat_a" id="t700030" style="LEFT: 406px; TOP: 277px" name="tk" value="700030" title="1층 E열 018번" grade="R석"></div>
									<div class="seat_a" id="t700031" style="LEFT: 417px; TOP: 277px" name="tk" value="700031" title="1층 E열 019번" grade="R석"></div>
									<div class="seat_a" id="t700035" style="LEFT: 440px; TOP: 277px" name="tk" value="700035" title="1층 E열 020번" grade="S석"></div>
									<div class="seat_a" id="t700036" style="LEFT: 451px; TOP: 277px" name="tk" value="700036" title="1층 E열 021번" grade="S석"></div>
									<div class="seat_a" id="t700037" style="LEFT: 462px; TOP: 277px" name="tk" value="700037" title="1층 E열 022번" grade="S석"></div>
									<div class="seat_a" id="t700038" style="LEFT: 473px; TOP: 277px" name="tk" value="700038" title="1층 E열 023번" grade="S석"></div>
									<div class="seat_a" id="t700039" style="LEFT: 484px; TOP: 277px" name="tk" value="700039" title="1층 E열 024번" grade="S석"></div>
									<!--<div class="label">F</div>-->
									<div class="seat_a" id="t800007" style="LEFT: 192px; TOP: 289px" name="tk" value="800007" title="1층 F열 001번" grade="S석"></div>
									<div class="seat_a" id="t800008" style="LEFT: 203px; TOP: 289px" name="tk" value="800008" title="1층 F열 002번" grade="S석"></div>
									<div class="seat_a" id="t800009" style="LEFT: 214px; TOP: 289px" name="tk" value="800009" title="1층 F열 003번" grade="S석"></div>
									<div class="seat_a" id="t800010" style="LEFT: 225px; TOP: 289px" name="tk" value="800010" title="1층 F열 004번" grade="S석"></div>
									<div class="seat_a" id="t800011" style="LEFT: 236px; TOP: 289px" name="tk" value="800011" title="1층 F열 005번" grade="S석"></div>
									<div class="seat_a" id="t800015" style="LEFT: 259px; TOP: 289px" name="tk" value="800015" title="1층 F열 006번" grade="S석"></div>
									<div class="seat_a" id="t800016" style="LEFT: 270px; TOP: 289px" name="tk" value="800016" title="1층 F열 007번" grade="S석"></div>
									<div class="seat_a" id="t800017" style="LEFT: 281px; TOP: 289px" name="tk" value="800017" title="1층 F열 008번" grade="S석"></div>
									<div class="seat_a" id="t800018" style="LEFT: 292px; TOP: 289px" name="tk" value="800018" title="1층 F열 009번" grade="S석"></div>
									<div class="seat_a" id="t800019" style="LEFT: 303px; TOP: 289px" name="tk" value="800019" title="1층 F열 010번" grade="S석"></div>
									<div class="seat_a" id="t800020" style="LEFT: 314px; TOP: 289px" name="tk" value="800020" title="1층 F열 011번" grade="S석"></div>
									<div class="seat_a" id="t800021" style="LEFT: 325px; TOP: 289px" name="tk" value="800021" title="1층 F열 012번" grade="S석"></div>
									<div class="seat_a" id="t800025" style="LEFT: 351px; TOP: 289px" name="tk" value="800025" title="1층 F열 013번" grade="R석"></div>
									<div class="seat_a" id="t800026" style="LEFT: 362px; TOP: 289px" name="tk" value="800026" title="1층 F열 014번" grade="R석"></div>
									<div class="seat_a" id="t800027" style="LEFT: 373px; TOP: 289px" name="tk" value="800027" title="1층 F열 015번" grade="R석"></div>
									<div class="seat_a" id="t800028" style="LEFT: 384px; TOP: 289px" name="tk" value="800028" title="1층 F열 016번" grade="R석"></div>
									<div class="seat_a" id="t800029" style="LEFT: 395px; TOP: 289px" name="tk" value="800029" title="1층 F열 017번" grade="R석"></div>
									<div class="seat_a" id="t800030" style="LEFT: 406px; TOP: 289px" name="tk" value="800030" title="1층 F열 018번" grade="R석"></div>
									<div class="seat_a" id="t800031" style="LEFT: 417px; TOP: 289px" name="tk" value="800031" title="1층 F열 019번" grade="R석"></div>
									<div class="seat_a" id="t800035" style="LEFT: 440px; TOP: 289px" name="tk" value="800035" title="1층 F열 020번" grade="S석"></div>
									<div class="seat_a" id="t800036" style="LEFT: 451px; TOP: 289px" name="tk" value="800036" title="1층 F열 021번" grade="S석"></div>
									<div class="seat_a" id="t800037" style="LEFT: 462px; TOP: 289px" name="tk" value="800037" title="1층 F열 022번" grade="S석"></div>
									<div class="seat_a" id="t800038" style="LEFT: 473px; TOP: 289px" name="tk" value="800038" title="1층 F열 023번" grade="S석"></div>
									<div class="seat_a" id="t800039" style="LEFT: 484px; TOP: 289px" name="tk" value="800039" title="1층 F열 024번" grade="S석"></div>
									<!--<div class="label">G</div>-->
									<div class="seat_a" id="t900007" style="LEFT: 192px; TOP: 301px" name="tk" value="900007" title="1층 G열 001번" grade="S석"></div>
									<div class="seat_a" id="t900008" style="LEFT: 203px; TOP: 301px" name="tk" value="900008" title="1층 G열 002번" grade="S석"></div>
									<div class="seat_a" id="t900009" style="LEFT: 214px; TOP: 301px" name="tk" value="900009" title="1층 G열 003번" grade="S석"></div>
									<div class="seat_a" id="t900010" style="LEFT: 225px; TOP: 301px" name="tk" value="900010" title="1층 G열 004번" grade="S석"></div>
									<div class="seat_a" id="t900011" style="LEFT: 236px; TOP: 301px" name="tk" value="900011" title="1층 G열 005번" grade="S석"></div>
									<div class="seat_a" id="t900015" style="LEFT: 259px; TOP: 301px" name="tk" value="900015" title="1층 G열 006번" grade="S석"></div>
									<div class="seat_a" id="t900016" style="LEFT: 270px; TOP: 301px" name="tk" value="900016" title="1층 G열 007번" grade="S석"></div>
									<div class="seat_a" id="t900017" style="LEFT: 281px; TOP: 301px" name="tk" value="900017" title="1층 G열 008번" grade="S석"></div>
									<div class="seat_a" id="t900018" style="LEFT: 292px; TOP: 301px" name="tk" value="900018" title="1층 G열 009번" grade="S석"></div>
									<div class="seat_a" id="t900019" style="LEFT: 303px; TOP: 301px" name="tk" value="900019" title="1층 G열 010번" grade="S석"></div>
									<div class="seat_a" id="t900020" style="LEFT: 314px; TOP: 301px" name="tk" value="900020" title="1층 G열 011번" grade="S석"></div>
									<div class="seat_a" id="t900021" style="LEFT: 325px; TOP: 301px" name="tk" value="900021" title="1층 G열 012번" grade="S석"></div>
									<div class="seat_a" id="t900025" style="LEFT: 351px; TOP: 301px" name="tk" value="900025" title="1층 G열 013번" grade="R석"></div>
									<div class="seat_a" id="t900026" style="LEFT: 362px; TOP: 301px" name="tk" value="900026" title="1층 G열 014번" grade="R석"></div>
									<div class="seat_a" id="t900027" style="LEFT: 373px; TOP: 301px" name="tk" value="900027" title="1층 G열 015번" grade="R석"></div>
									<div class="seat_a" id="t900028" style="LEFT: 384px; TOP: 301px" name="tk" value="900028" title="1층 G열 016번" grade="R석"></div>
									<div class="seat_a" id="t900029" style="LEFT: 395px; TOP: 301px" name="tk" value="900029" title="1층 G열 017번" grade="R석"></div>
									<div class="seat_a" id="t900030" style="LEFT: 406px; TOP: 301px" name="tk" value="900030" title="1층 G열 018번" grade="R석"></div>
									<div class="seat_a" id="t900031" style="LEFT: 417px; TOP: 301px" name="tk" value="900031" title="1층 G열 019번" grade="R석"></div>
									<div class="seat_a" id="t900035" style="LEFT: 440px; TOP: 301px" name="tk" value="900035" title="1층 G열 020번" grade="S석"></div>
									<div class="seat_a" id="t900036" style="LEFT: 451px; TOP: 301px" name="tk" value="900036" title="1층 G열 021번" grade="S석"></div>
									<div class="seat_a" id="t900037" style="LEFT: 462px; TOP: 301px" name="tk" value="900037" title="1층 G열 022번" grade="S석"></div>
									<div class="seat_a" id="t900038" style="LEFT: 473px; TOP: 301px" name="tk" value="900038" title="1층 G열 023번" grade="S석"></div>
									<div class="seat_a" id="t900039" style="LEFT: 484px; TOP: 301px" name="tk" value="900039" title="1층 G열 024번" grade="S석"></div>
									<!--<div class="label">H</div>-->
									<div class="seat_a" id="t1000007" style="LEFT: 192px; TOP: 313px" name="tk" value="1000007" title="1층 H열 001번" grade="S석"></div>
									<div class="seat_a" id="t1000008" style="LEFT: 203px; TOP: 313px" name="tk" value="1000008" title="1층 H열 002번" grade="S석"></div>
									<div class="seat_a" id="t1000009" style="LEFT: 214px; TOP: 313px" name="tk" value="1000009" title="1층 H열 003번" grade="S석"></div>
									<div class="seat_a" id="t1000010" style="LEFT: 225px; TOP: 313px" name="tk" value="1000010" title="1층 H열 004번" grade="S석"></div>
									<div class="seat_a" id="t1000011" style="LEFT: 236px; TOP: 313px" name="tk" value="1000011" title="1층 H열 005번" grade="S석"></div>
									<div class="seat_a" id="t1000015" style="LEFT: 259px; TOP: 313px" name="tk" value="1000015" title="1층 H열 006번" grade="S석"></div>
									<div class="seat_a" id="t1000016" style="LEFT: 270px; TOP: 313px" name="tk" value="1000016" title="1층 H열 007번" grade="S석"></div>
									<div class="seat_a" id="t1000017" style="LEFT: 281px; TOP: 313px" name="tk" value="1000017" title="1층 H열 008번" grade="S석"></div>
									<div class="seat_a" id="t1000018" style="LEFT: 292px; TOP: 313px" name="tk" value="1000018" title="1층 H열 009번" grade="S석"></div>
									<div class="seat_a" id="t1000019" style="LEFT: 303px; TOP: 313px" name="tk" value="1000019" title="1층 H열 010번" grade="S석"></div>
									<div class="seat_a" id="t1000020" style="LEFT: 314px; TOP: 313px" name="tk" value="1000020" title="1층 H열 011번" grade="S석"></div>
									<div class="seat_a" id="t1000021" style="LEFT: 325px; TOP: 313px" name="tk" value="1000021" title="1층 H열 012번" grade="S석"></div>
									<div class="seat_a" id="t1000025" style="LEFT: 351px; TOP: 313px" name="tk" value="1000025" title="1층 H열 013번" grade="R석"></div>
									<div class="seat_a" id="t1000026" style="LEFT: 362px; TOP: 313px" name="tk" value="1000026" title="1층 H열 014번" grade="R석"></div>
									<div class="seat_a" id="t1000027" style="LEFT: 373px; TOP: 313px" name="tk" value="1000027" title="1층 H열 015번" grade="R석"></div>
									<div class="seat_a" id="t1000028" style="LEFT: 384px; TOP: 313px" name="tk" value="1000028" title="1층 H열 016번" grade="R석"></div>
									<div class="seat_a" id="t1000029" style="LEFT: 395px; TOP: 313px" name="tk" value="1000029" title="1층 H열 017번" grade="R석"></div>
									<div class="seat_a" id="t1000030" style="LEFT: 406px; TOP: 313px" name="tk" value="1000030" title="1층 H열 018번" grade="R석"></div>
									<div class="seat_a" id="t1000031" style="LEFT: 417px; TOP: 313px" name="tk" value="1000031" title="1층 H열 019번" grade="R석"></div>
									<div class="seat_a" id="t1000035" style="LEFT: 440px; TOP: 313px" name="tk" value="1000035" title="1층 H열 020번" grade="S석"></div>
									<div class="seat_a" id="t1000036" style="LEFT: 451px; TOP: 313px" name="tk" value="1000036" title="1층 H열 021번" grade="S석"></div>
									<div class="seat_a" id="t1000037" style="LEFT: 462px; TOP: 313px" name="tk" value="1000037" title="1층 H열 022번" grade="S석"></div>
									<div class="seat_a" id="t1000038" style="LEFT: 473px; TOP: 313px" name="tk" value="1000038" title="1층 H열 023번" grade="S석"></div>
									<div class="seat_a" id="t1000039" style="LEFT: 484px; TOP: 313px" name="tk" value="1000039" title="1층 H열 024번" grade="S석"></div>
									<div class="seat_a" id="t1300007" style="LEFT: 192px; TOP: 349px" name="tk" value="1300007" title="1층 I열 001번" grade="S석"></div>
									<div class="seat_a" id="t1300008" style="LEFT: 203px; TOP: 349px" name="tk" value="1300008" title="1층 I열 002번" grade="S석"></div>
									<div class="seat_a" id="t1300009" style="LEFT: 214px; TOP: 349px" name="tk" value="1300009" title="1층 I열 003번" grade="S석"></div>
									<div class="seat_a" id="t1300010" style="LEFT: 225px; TOP: 349px" name="tk" value="1300010" title="1층 I열 004번" grade="S석"></div>
									<div class="seat_a" id="t1300011" style="LEFT: 236px; TOP: 349px" name="tk" value="1300011" title="1층 I열 005번" grade="S석"></div>
									<div class="seat_a" id="t1300012" style="LEFT: 247px; TOP: 349px" name="tk" value="1300012" title="1층 I열 006번" grade="S석"></div>
									<div class="seat_a" id="t1300018" style="LEFT: 278px; TOP: 349px" name="tk" value="1300018" title="1층 I열 007번" grade="S석"></div>
									<div class="seat_a" id="t1300019" style="LEFT: 289px; TOP: 349px" name="tk" value="1300019" title="1층 I열 008번" grade="S석"></div>
									<div class="seat_a" id="t1300020" style="LEFT: 300px; TOP: 349px" name="tk" value="1300020" title="1층 I열 009번" grade="S석"></div>
									<div class="seat_a" id="t1300021" style="LEFT: 311px; TOP: 349px" name="tk" value="1300021" title="1층 I열 010번" grade="S석"></div>
									<div class="seat_a" id="t1300022" style="LEFT: 322px; TOP: 349px" name="tk" value="1300022" title="1층 I열 011번" grade="S석"></div>
									<div class="seat_a" id="t1300023" style="LEFT: 333px; TOP: 349px" name="tk" value="1300023" title="1층 I열 012번" grade="S석"></div>
									<div class="seat_a" id="t1300024" style="LEFT: 344px; TOP: 349px" name="tk" value="1300024" title="1층 I열 013번" grade="R석"></div>
									<div class="seat_a" id="t1300025" style="LEFT: 355px; TOP: 349px" name="tk" value="1300025" title="1층 I열 014번" grade="R석"></div>
									<div class="seat_a" id="t1300026" style="LEFT: 366px; TOP: 349px" name="tk" value="1300026" title="1층 I열 015번" grade="R석"></div>
									<div class="seat_a" id="t1300027" style="LEFT: 377px; TOP: 349px" name="tk" value="1300027" title="1층 I열 016번" grade="R석"></div>
									<div class="seat_a" id="t1300028" style="LEFT: 388px; TOP: 349px" name="tk" value="1300028" title="1층 I열 017번" grade="R석"></div>
									<div class="seat_a" id="t1300029" style="LEFT: 399px; TOP: 349px" name="tk" value="1300029" title="1층 I열 018번" grade="R석"></div>
									<div class="seat_a" id="t1300034" style="LEFT: 429px; TOP: 349px" name="tk" value="1300034" title="1층 I열 019번" grade="S석"></div>
									<div class="seat_a" id="t1300035" style="LEFT: 440px; TOP: 349px" name="tk" value="1300035" title="1층 I열 020번" grade="S석"></div>
									<div class="seat_a" id="t1300036" style="LEFT: 451px; TOP: 349px" name="tk" value="1300036" title="1층 I열 021번" grade="S석"></div>
									<div class="seat_a" id="t1300037" style="LEFT: 462px; TOP: 349px" name="tk" value="1300037" title="1층 I열 022번" grade="S석"></div>
									<div class="seat_a" id="t1300038" style="LEFT: 473px; TOP: 349px" name="tk" value="1300038" title="1층 I열 023번" grade="S석"></div>
									<div class="seat_a" id="t1300039" style="LEFT: 484px; TOP: 349px" name="tk" value="1300039" title="1층 I열 024번" grade="S석"></div>
									<!--<div class="label">J</div>-->
									<div class="seat_a" id="t1400007" style="LEFT: 192px; TOP: 361px" name="tk" value="1400007" title="1층 J열 001번" grade="S석"></div>
									<div class="seat_a" id="t1400008" style="LEFT: 203px; TOP: 361px" name="tk" value="1400008" title="1층 J열 002번" grade="S석"></div>
									<div class="seat_a" id="t1400009" style="LEFT: 214px; TOP: 361px" name="tk" value="1400009" title="1층 J열 003번" grade="S석"></div>
									<div class="seat_a" id="t1400010" style="LEFT: 225px; TOP: 361px" name="tk" value="1400010" title="1층 J열 004번" grade="S석"></div>
									<div class="seat_a" id="t1400011" style="LEFT: 236px; TOP: 361px" name="tk" value="1400011" title="1층 J열 005번" grade="S석"></div>
									<div class="seat_a" id="t1400012" style="LEFT: 247px; TOP: 361px" name="tk" value="1400012" title="1층 J열 006번" grade="S석"></div>
									<div class="seat_a" id="t1400018" style="LEFT: 278px; TOP: 361px" name="tk" value="1400018" title="1층 J열 007번" grade="S석"></div>
									<div class="seat_a" id="t1400019" style="LEFT: 289px; TOP: 361px" name="tk" value="1400019" title="1층 J열 008번" grade="S석"></div>
									<div class="seat_a" id="t1400020" style="LEFT: 300px; TOP: 361px" name="tk" value="1400020" title="1층 J열 009번" grade="S석"></div>
									<div class="seat_a" id="t1400021" style="LEFT: 311px; TOP: 361px" name="tk" value="1400021" title="1층 J열 010번" grade="S석"></div>
									<div class="seat_a" id="t1400022" style="LEFT: 322px; TOP: 361px" name="tk" value="1400022" title="1층 J열 011번" grade="S석"></div>
									<div class="seat_a" id="t1400023" style="LEFT: 333px; TOP: 361px" name="tk" value="1400023" title="1층 J열 012번" grade="S석"></div>
									<div class="seat_a" id="t1400024" style="LEFT: 344px; TOP: 361px" name="tk" value="1400024" title="1층 J열 013번" grade="R석"></div>
									<div class="seat_a" id="t1400025" style="LEFT: 355px; TOP: 361px" name="tk" value="1400025" title="1층 J열 014번" grade="R석"></div>
									<div class="seat_a" id="t1400026" style="LEFT: 366px; TOP: 361px" name="tk" value="1400026" title="1층 J열 015번" grade="R석"></div>
									<div class="seat_a" id="t1400027" style="LEFT: 377px; TOP: 361px" name="tk" value="1400027" title="1층 J열 016번" grade="R석"></div>
									<div class="seat_a" id="t1400028" style="LEFT: 388px; TOP: 361px" name="tk" value="1400028" title="1층 J열 017번" grade="R석"></div>
									<div class="seat_a" id="t1400029" style="LEFT: 399px; TOP: 361px" name="tk" value="1400029" title="1층 J열 018번" grade="R석"></div>
									<div class="seat_a" id="t1400034" style="LEFT: 429px; TOP: 361px" name="tk" value="1400034" title="1층 J열 019번" grade="S석"></div>
									<div class="seat_a" id="t1400035" style="LEFT: 440px; TOP: 361px" name="tk" value="1400035" title="1층 J열 020번" grade="S석"></div>
									<div class="seat_a" id="t1400036" style="LEFT: 451px; TOP: 361px" name="tk" value="1400036" title="1층 J열 021번" grade="S석"></div>
									<div class="seat_a" id="t1400037" style="LEFT: 462px; TOP: 361px" name="tk" value="1400037" title="1층 J열 022번" grade="S석"></div>
									<div class="seat_a" id="t1400038" style="LEFT: 473px; TOP: 361px" name="tk" value="1400038" title="1층 J열 023번" grade="S석"></div>
									<div class="seat_a" id="t1400039" style="LEFT: 484px; TOP: 361px" name="tk" value="1400039" title="1층 J열 024번" grade="S석"></div>
									<!--<div class="label">K</div>-->
									<div class="seat_a" id="t1500007" style="LEFT: 192px; TOP: 373px" name="tk" value="1500007" title="1층 K열 001번" grade="S석"></div>
									<div class="seat_a" id="t1500008" style="LEFT: 203px; TOP: 373px" name="tk" value="1500008" title="1층 K열 002번" grade="S석"></div>
									<div class="seat_a" id="t1500009" style="LEFT: 214px; TOP: 373px" name="tk" value="1500009" title="1층 K열 003번" grade="S석"></div>
									<div class="seat_a" id="t1500010" style="LEFT: 225px; TOP: 373px" name="tk" value="1500010" title="1층 K열 004번" grade="S석"></div>
									<div class="seat_a" id="t1500011" style="LEFT: 236px; TOP: 373px" name="tk" value="1500011" title="1층 K열 005번" grade="S석"></div>
									<div class="seat_a" id="t1500012" style="LEFT: 247px; TOP: 373px" name="tk" value="1500012" title="1층 K열 006번" grade="S석"></div>
									<div class="seat_a" id="t1500018" style="LEFT: 278px; TOP: 373px" name="tk" value="1500018" title="1층 K열 007번" grade="S석"></div>
									<div class="seat_a" id="t1500019" style="LEFT: 289px; TOP: 373px" name="tk" value="1500019" title="1층 K열 008번" grade="S석"></div>
									<div class="seat_a" id="t1500020" style="LEFT: 300px; TOP: 373px" name="tk" value="1500020" title="1층 K열 009번" grade="S석"></div>
									<div class="seat_a" id="t1500021" style="LEFT: 311px; TOP: 373px" name="tk" value="1500021" title="1층 K열 010번" grade="S석"></div>
									<div class="seat_a" id="t1500022" style="LEFT: 322px; TOP: 373px" name="tk" value="1500022" title="1층 K열 011번" grade="S석"></div>
									<div class="seat_a" id="t1500023" style="LEFT: 333px; TOP: 373px" name="tk" value="1500023" title="1층 K열 012번" grade="S석"></div>
									<div class="seat_a" id="t1500024" style="LEFT: 344px; TOP: 373px" name="tk" value="1500024" title="1층 K열 013번" grade="R석"></div>
									<div class="seat_a" id="t1500025" style="LEFT: 355px; TOP: 373px" name="tk" value="1500025" title="1층 K열 014번" grade="R석"></div>
									<div class="seat_a" id="t1500026" style="LEFT: 366px; TOP: 373px" name="tk" value="1500026" title="1층 K열 015번" grade="R석"></div>
									<div class="seat_a" id="t1500027" style="LEFT: 377px; TOP: 373px" name="tk" value="1500027" title="1층 K열 016번" grade="R석"></div>
									<div class="seat_a" id="t1500028" style="LEFT: 388px; TOP: 373px" name="tk" value="1500028" title="1층 K열 017번" grade="R석"></div>
									<div class="seat_a" id="t1500029" style="LEFT: 399px; TOP: 373px" name="tk" value="1500029" title="1층 K열 018번" grade="R석"></div>
									<div class="seat_a" id="t1500034" style="LEFT: 429px; TOP: 373px" name="tk" value="1500034" title="1층 K열 019번" grade="S석"></div>
									<div class="seat_a" id="t1500035" style="LEFT: 440px; TOP: 373px" name="tk" value="1500035" title="1층 K열 020번" grade="S석"></div>
									<div class="seat_a" id="t1500036" style="LEFT: 451px; TOP: 373px" name="tk" value="1500036" title="1층 K열 021번" grade="S석"></div>
									<div class="seat_a" id="t1500037" style="LEFT: 462px; TOP: 373px" name="tk" value="1500037" title="1층 K열 022번" grade="S석"></div>
									<div class="seat_a" id="t1500038" style="LEFT: 473px; TOP: 373px" name="tk" value="1500038" title="1층 K열 023번" grade="S석"></div>
									<div class="seat_a" id="t1500039" style="LEFT: 484px; TOP: 373px" name="tk" value="1500039" title="1층 K열 024번" grade="S석"></div>
									<!--<div class="label">L</div>-->
									<div class="seat_a" id="t1600007" style="LEFT: 192px; TOP: 385px" name="tk" value="1600007" title="1층 L열 001번" grade="S석"></div>
									<div class="seat_a" id="t1600008" style="LEFT: 203px; TOP: 385px" name="tk" value="1600008" title="1층 L열 002번" grade="S석"></div>
									<div class="seat_a" id="t1600009" style="LEFT: 214px; TOP: 385px" name="tk" value="1600009" title="1층 L열 003번" grade="S석"></div>
									<div class="seat_a" id="t1600010" style="LEFT: 225px; TOP: 385px" name="tk" value="1600010" title="1층 L열 004번" grade="S석"></div>
									<div class="seat_a" id="t1600011" style="LEFT: 236px; TOP: 385px" name="tk" value="1600011" title="1층 L열 005번" grade="S석"></div>
									<div class="seat_a" id="t1600012" style="LEFT: 247px; TOP: 385px" name="tk" value="1600012" title="1층 L열 006번" grade="S석"></div>
									<div class="seat_a" id="t1600018" style="LEFT: 278px; TOP: 385px" name="tk" value="1600018" title="1층 L열 007번" grade="S석"></div>
									<div class="seat_a" id="t1600019" style="LEFT: 289px; TOP: 385px" name="tk" value="1600019" title="1층 L열 008번" grade="S석"></div>
									<div class="seat_a" id="t1600020" style="LEFT: 300px; TOP: 385px" name="tk" value="1600020" title="1층 L열 009번" grade="S석"></div>
									<div class="seat_a" id="t1600021" style="LEFT: 311px; TOP: 385px" name="tk" value="1600021" title="1층 L열 010번" grade="S석"></div>
									<div class="seat_a" id="t1600022" style="LEFT: 322px; TOP: 385px" name="tk" value="1600022" title="1층 L열 011번" grade="S석"></div>
									<div class="seat_a" id="t1600023" style="LEFT: 333px; TOP: 385px" name="tk" value="1600023" title="1층 L열 012번" grade="S석"></div>
									<div class="seat_a" id="t1600024" style="LEFT: 344px; TOP: 385px" name="tk" value="1600024" title="1층 L열 013번" grade="R석"></div>
									<div class="seat_a" id="t1600025" style="LEFT: 355px; TOP: 385px" name="tk" value="1600025" title="1층 L열 014번" grade="R석"></div>
									<div class="seat_a" id="t1600026" style="LEFT: 366px; TOP: 385px" name="tk" value="1600026" title="1층 L열 015번" grade="R석"></div>
									<div class="seat_a" id="t1600027" style="LEFT: 377px; TOP: 385px" name="tk" value="1600027" title="1층 L열 016번" grade="R석"></div>
									<div class="seat_a" id="t1600028" style="LEFT: 388px; TOP: 385px" name="tk" value="1600028" title="1층 L열 017번" grade="R석"></div>
									<div class="seat_a" id="t1600029" style="LEFT: 399px; TOP: 385px" name="tk" value="1600029" title="1층 L열 018번" grade="R석"></div>
									<div class="seat_a" id="t1600034" style="LEFT: 429px; TOP: 385px" name="tk" value="1600034" title="1층 L열 019번" grade="S석"></div>
									<div class="seat_a" id="t1600035" style="LEFT: 440px; TOP: 385px" name="tk" value="1600035" title="1층 L열 020번" grade="S석"></div>
									<div class="seat_a" id="t1600036" style="LEFT: 451px; TOP: 385px" name="tk" value="1600036" title="1층 L열 021번" grade="S석"></div>
									<div class="seat_a" id="t1600037" style="LEFT: 462px; TOP: 385px" name="tk" value="1600037" title="1층 L열 022번" grade="S석"></div>
									<div class="seat_a" id="t1600038" style="LEFT: 473px; TOP: 385px" name="tk" value="1600038" title="1층 L열 023번" grade="S석"></div>
									<div class="seat_a" id="t1600039" style="LEFT: 484px; TOP: 385px" name="tk" value="1600039" title="1층 L열 024번" grade="S석"></div>
									<!--<div class="label">M</div>-->
									<div class="seat_a" id="t1700007" style="LEFT: 192px; TOP: 397px" name="tk" value="1700007" title="1층 M열 001번" grade="S석"></div>
									<div class="seat_a" id="t1700008" style="LEFT: 203px; TOP: 397px" name="tk" value="1700008" title="1층 M열 002번" grade="S석"></div>
									<div class="seat_a" id="t1700009" style="LEFT: 214px; TOP: 397px" name="tk" value="1700009" title="1층 M열 003번" grade="S석"></div>
									<div class="seat_a" id="t1700010" style="LEFT: 225px; TOP: 397px" name="tk" value="1700010" title="1층 M열 004번" grade="S석"></div>
									<div class="seat_a" id="t1700011" style="LEFT: 236px; TOP: 397px" name="tk" value="1700011" title="1층 M열 005번" grade="S석"></div>
									<div class="seat_a" id="t1700012" style="LEFT: 247px; TOP: 397px" name="tk" value="1700012" title="1층 M열 006번" grade="S석"></div>
									<div class="seat_a" id="t1700018" style="LEFT: 278px; TOP: 397px" name="tk" value="1700018" title="1층 M열 007번" grade="S석"></div>
									<div class="seat_a" id="t1700019" style="LEFT: 289px; TOP: 397px" name="tk" value="1700019" title="1층 M열 008번" grade="S석"></div>
									<div class="seat_a" id="t1700020" style="LEFT: 300px; TOP: 397px" name="tk" value="1700020" title="1층 M열 009번" grade="S석"></div>
									<div class="seat_a" id="t1700021" style="LEFT: 311px; TOP: 397px" name="tk" value="1700021" title="1층 M열 010번" grade="S석"></div>
									<div class="seat_a" id="t1700022" style="LEFT: 322px; TOP: 397px" name="tk" value="1700022" title="1층 M열 011번" grade="S석"></div>
									<div class="seat_a" id="t1700023" style="LEFT: 333px; TOP: 397px" name="tk" value="1700023" title="1층 M열 012번" grade="S석"></div>
									<div class="seat_a" id="t1700024" style="LEFT: 344px; TOP: 397px" name="tk" value="1700024" title="1층 M열 013번" grade="R석"></div>
									<div class="seat_a" id="t1700025" style="LEFT: 355px; TOP: 397px" name="tk" value="1700025" title="1층 M열 014번" grade="R석"></div>
									<div class="seat_a" id="t1700026" style="LEFT: 366px; TOP: 397px" name="tk" value="1700026" title="1층 M열 015번" grade="R석"></div>
									<div class="seat_a" id="t1700027" style="LEFT: 377px; TOP: 397px" name="tk" value="1700027" title="1층 M열 016번" grade="R석"></div>
									<div class="seat_a" id="t1700028" style="LEFT: 388px; TOP: 397px" name="tk" value="1700028" title="1층 M열 017번" grade="R석"></div>
									<div class="seat_a" id="t1700029" style="LEFT: 399px; TOP: 397px" name="tk" value="1700029" title="1층 M열 018번" grade="R석"></div>
									<div class="seat_a" id="t1700034" style="LEFT: 429px; TOP: 397px" name="tk" value="1700034" title="1층 M열 019번" grade="S석"></div>
									<div class="seat_a" id="t1700035" style="LEFT: 440px; TOP: 397px" name="tk" value="1700035" title="1층 M열 020번" grade="S석"></div>
									<div class="seat_a" id="t1700036" style="LEFT: 451px; TOP: 397px" name="tk" value="1700036" title="1층 M열 021번" grade="S석"></div>
									<div class="seat_a" id="t1700037" style="LEFT: 462px; TOP: 397px" name="tk" value="1700037" title="1층 M열 022번" grade="S석"></div>
									<div class="seat_a" id="t1700038" style="LEFT: 473px; TOP: 397px" name="tk" value="1700038" title="1층 M열 023번" grade="S석"></div>
									<div class="seat_a" id="t1700039" style="LEFT: 484px; TOP: 397px" name="tk" value="1700039" title="1층 M열 024번" grade="S석"></div>
									<!--<div class="label">N</div>-->
									<div class="seat_a" id="t1800007" style="LEFT: 192px; TOP: 409px" name="tk" value="1800007" title="1층 N열 001번" grade="S석"></div>
									<div class="seat_a" id="t1800008" style="LEFT: 203px; TOP: 409px" name="tk" value="1800008" title="1층 N열 002번" grade="S석"></div>
									<div class="seat_a" id="t1800009" style="LEFT: 214px; TOP: 409px" name="tk" value="1800009" title="1층 N열 003번" grade="S석"></div>
									<div class="seat_a" id="t1800010" style="LEFT: 225px; TOP: 409px" name="tk" value="1800010" title="1층 N열 004번" grade="S석"></div>
									<div class="seat_a" id="t1800011" style="LEFT: 236px; TOP: 409px" name="tk" value="1800011" title="1층 N열 005번" grade="S석"></div>
									<div class="seat_a" id="t1800012" style="LEFT: 247px; TOP: 409px" name="tk" value="1800012" title="1층 N열 006번" grade="S석"></div>
									<div class="seat_a" id="t1800018" style="LEFT: 278px; TOP: 409px" name="tk" value="1800018" title="1층 N열 007번" grade="S석"></div>
									<div class="seat_a" id="t1800019" style="LEFT: 289px; TOP: 409px" name="tk" value="1800019" title="1층 N열 008번" grade="S석"></div>
									<div class="seat_a" id="t1800020" style="LEFT: 300px; TOP: 409px" name="tk" value="1800020" title="1층 N열 009번" grade="S석"></div>
									<div class="seat_a" id="t1800021" style="LEFT: 311px; TOP: 409px" name="tk" value="1800021" title="1층 N열 010번" grade="S석"></div>
									<div class="seat_a" id="t1800022" style="LEFT: 322px; TOP: 409px" name="tk" value="1800022" title="1층 N열 011번" grade="S석"></div>
									<div class="seat_a" id="t1800023" style="LEFT: 333px; TOP: 409px" name="tk" value="1800023" title="1층 N열 012번" grade="S석"></div>
									<div class="seat_a" id="t1800024" style="LEFT: 344px; TOP: 409px" name="tk" value="1800024" title="1층 N열 013번" grade="R석"></div>
									<div class="seat_a" id="t1800025" style="LEFT: 355px; TOP: 409px" name="tk" value="1800025" title="1층 N열 014번" grade="R석"></div>
									<div class="seat_a" id="t1800026" style="LEFT: 366px; TOP: 409px" name="tk" value="1800026" title="1층 N열 015번" grade="R석"></div>
									<div class="seat_a" id="t1800027" style="LEFT: 377px; TOP: 409px" name="tk" value="1800027" title="1층 N열 016번" grade="R석"></div>
									<div class="seat_a" id="t1800028" style="LEFT: 388px; TOP: 409px" name="tk" value="1800028" title="1층 N열 017번" grade="R석"></div>
									<div class="seat_a" id="t1800029" style="LEFT: 399px; TOP: 409px" name="tk" value="1800029" title="1층 N열 018번" grade="R석"></div>
									<div class="seat_a" id="t1800034" style="LEFT: 429px; TOP: 409px" name="tk" value="1800034" title="1층 N열 019번" grade="S석"></div>
									<div class="seat_a" id="t1800035" style="LEFT: 440px; TOP: 409px" name="tk" value="1800035" title="1층 N열 020번" grade="S석"></div>
									<div class="seat_a" id="t1800036" style="LEFT: 451px; TOP: 409px" name="tk" value="1800036" title="1층 N열 021번" grade="S석"></div>
									<div class="seat_a" id="t1800037" style="LEFT: 462px; TOP: 409px" name="tk" value="1800037" title="1층 N열 022번" grade="S석"></div>
									<div class="seat_a" id="t1800038" style="LEFT: 473px; TOP: 409px" name="tk" value="1800038" title="1층 N열 023번" grade="S석"></div>
									<div class="seat_a" id="t1800039" style="LEFT: 484px; TOP: 409px" name="tk" value="1800039" title="1층 N열 024번" grade="S석"></div>
									<!--<div class="label">O</div>-->
									<div class="seat_a" id="t1900007" style="LEFT: 192px; TOP: 421px" name="tk" value="1900007" title="1층 O열 001번" grade="S석"></div>
									<div class="seat_a" id="t1900008" style="LEFT: 203px; TOP: 421px" name="tk" value="1900008" title="1층 O열 002번" grade="S석"></div>
									<div class="seat_a" id="t1900009" style="LEFT: 214px; TOP: 421px" name="tk" value="1900009" title="1층 O열 003번" grade="S석"></div>
									<div class="seat_a" id="t1900010" style="LEFT: 225px; TOP: 421px" name="tk" value="1900010" title="1층 O열 004번" grade="S석"></div>
									<div class="seat_a" id="t1900011" style="LEFT: 236px; TOP: 421px" name="tk" value="1900011" title="1층 O열 005번" grade="S석"></div>
									<div class="seat_a" id="t1900012" style="LEFT: 247px; TOP: 421px" name="tk" value="1900012" title="1층 O열 006번" grade="S석"></div>
									<div class="seat_a" id="t1900018" style="LEFT: 278px; TOP: 421px" name="tk" value="1900018" title="1층 O열 007번" grade="S석"></div>
									<div class="seat_a" id="t1900019" style="LEFT: 289px; TOP: 421px" name="tk" value="1900019" title="1층 O열 008번" grade="S석"></div>
									<div class="seat_a" id="t1900020" style="LEFT: 300px; TOP: 421px" name="tk" value="1900020" title="1층 O열 009번" grade="S석"></div>
									<div class="seat_a" id="t1900021" style="LEFT: 311px; TOP: 421px" name="tk" value="1900021" title="1층 O열 010번" grade="S석"></div>
									<div class="seat_a" id="t1900022" style="LEFT: 322px; TOP: 421px" name="tk" value="1900022" title="1층 O열 011번" grade="S석"></div>
									<div class="seat_a" id="t1900023" style="LEFT: 333px; TOP: 421px" name="tk" value="1900023" title="1층 O열 012번" grade="S석"></div>
									<div class="seat_a" id="t1900024" style="LEFT: 344px; TOP: 421px" name="tk" value="1900024" title="1층 O열 013번" grade="R석"></div>
									<div class="seat_a" id="t1900025" style="LEFT: 355px; TOP: 421px" name="tk" value="1900025" title="1층 O열 014번" grade="R석"></div>
									<div class="seat_a" id="t1900026" style="LEFT: 366px; TOP: 421px" name="tk" value="1900026" title="1층 O열 015번" grade="R석"></div>
									<div class="seat_a" id="t1900027" style="LEFT: 377px; TOP: 421px" name="tk" value="1900027" title="1층 O열 016번" grade="R석"></div>
									<div class="seat_a" id="t1900028" style="LEFT: 388px; TOP: 421px" name="tk" value="1900028" title="1층 O열 017번" grade="R석"></div>
									<div class="seat_a" id="t1900029" style="LEFT: 399px; TOP: 421px" name="tk" value="1900029" title="1층 O열 018번" grade="R석"></div>
									<div class="seat_a" id="t1900034" style="LEFT: 429px; TOP: 421px" name="tk" value="1900034" title="1층 O열 019번" grade="S석"></div>
									<div class="seat_a" id="t1900035" style="LEFT: 440px; TOP: 421px" name="tk" value="1900035" title="1층 O열 020번" grade="S석"></div>
									<div class="seat_a" id="t1900036" style="LEFT: 451px; TOP: 421px" name="tk" value="1900036" title="1층 O열 021번" grade="S석"></div>
									<div class="seat_a" id="t1900037" style="LEFT: 462px; TOP: 421px" name="tk" value="1900037" title="1층 O열 022번" grade="S석"></div>
									<div class="seat_a" id="t1900038" style="LEFT: 473px; TOP: 421px" name="tk" value="1900038" title="1층 O열 023번" grade="S석"></div>
									<div class="seat_a" id="t1900039" style="LEFT: 484px; TOP: 421px" name="tk" value="1900039" title="1층 O열 024번" grade="S석"></div>
									<!--<div class="label">P</div>-->
									<div class="seat_a" id="t2000007" style="LEFT: 192px; TOP: 433px" name="tk" value="2000007" title="1층 P열 001번" grade="S석"></div>
									<div class="seat_a" id="t2000008" style="LEFT: 203px; TOP: 433px" name="tk" value="2000008" title="1층 P열 002번" grade="S석"></div>
									<div class="seat_a" id="t2000009" style="LEFT: 214px; TOP: 433px" name="tk" value="2000009" title="1층 P열 003번" grade="S석"></div>
									<div class="seat_a" id="t2000010" style="LEFT: 225px; TOP: 433px" name="tk" value="2000010" title="1층 P열 004번" grade="S석"></div>
									<div class="seat_a" id="t2000011" style="LEFT: 236px; TOP: 433px" name="tk" value="2000011" title="1층 P열 005번" grade="S석"></div>
									<div class="seat_a" id="t2000012" style="LEFT: 247px; TOP: 433px" name="tk" value="2000012" title="1층 P열 006번" grade="S석"></div>
									<div class="seat_a" id="t2000018" style="LEFT: 278px; TOP: 433px" name="tk" value="2000018" title="1층 P열 007번" grade="S석"></div>
									<div class="seat_a" id="t2000019" style="LEFT: 289px; TOP: 433px" name="tk" value="2000019" title="1층 P열 008번" grade="S석"></div>
									<div class="seat_a" id="t2000020" style="LEFT: 300px; TOP: 433px" name="tk" value="2000020" title="1층 P열 009번" grade="S석"></div>
									<div class="seat_a" id="t2000021" style="LEFT: 311px; TOP: 433px" name="tk" value="2000021" title="1층 P열 010번" grade="S석"></div>
									<div class="seat_a" id="t2000022" style="LEFT: 322px; TOP: 433px" name="tk" value="2000022" title="1층 P열 011번" grade="S석"></div>
									<div class="seat_a" id="t2000023" style="LEFT: 333px; TOP: 433px" name="tk" value="2000023" title="1층 P열 012번" grade="S석"></div>
									<div class="seat_a" id="t2000024" style="LEFT: 344px; TOP: 433px" name="tk" value="2000024" title="1층 P열 013번" grade="R석"></div>
									<div class="seat_a" id="t2000025" style="LEFT: 355px; TOP: 433px" name="tk" value="2000025" title="1층 P열 014번" grade="R석"></div>
									<div class="seat_a" id="t2000026" style="LEFT: 366px; TOP: 433px" name="tk" value="2000026" title="1층 P열 015번" grade="R석"></div>
									<div class="seat_a" id="t2000027" style="LEFT: 377px; TOP: 433px" name="tk" value="2000027" title="1층 P열 016번" grade="R석"></div>
									<div class="seat_a" id="t2000028" style="LEFT: 388px; TOP: 433px" name="tk" value="2000028" title="1층 P열 017번" grade="R석"></div>
									<div class="seat_a" id="t2000029" style="LEFT: 399px; TOP: 433px" name="tk" value="2000029" title="1층 P열 018번" grade="R석"></div>
									<div class="seat_a" id="t2000034" style="LEFT: 429px; TOP: 433px" name="tk" value="2000034" title="1층 P열 019번" grade="S석"></div>
									<div class="seat_a" id="t2000035" style="LEFT: 440px; TOP: 433px" name="tk" value="2000035" title="1층 P열 020번" grade="S석"></div>
									<div class="seat_a" id="t2000036" style="LEFT: 451px; TOP: 433px" name="tk" value="2000036" title="1층 P열 021번" grade="S석"></div>
									<div class="seat_a" id="t2000037" style="LEFT: 462px; TOP: 433px" name="tk" value="2000037" title="1층 P열 022번" grade="S석"></div>
									<div class="seat_a" id="t2000038" style="LEFT: 473px; TOP: 433px" name="tk" value="2000038" title="1층 P열 023번" grade="S석"></div>
									<div class="seat_a" id="t2000039" style="LEFT: 484px; TOP: 433px" name="tk" value="2000039" title="1층 P열 024번" grade="S석"></div>
									<!--<div class="label">Q</div>-->
									<div class="seat_a" id="t2100008" style="LEFT: 203px; TOP: 445px" name="tk" value="2100008" title="1층 Q열 001번" grade="S석"></div>
									<div class="seat_a" id="t2100009" style="LEFT: 214px; TOP: 445px" name="tk" value="2100009" title="1층 Q열 002번" grade="S석"></div>
									<div class="seat_a" id="t2100010" style="LEFT: 225px; TOP: 445px" name="tk" value="2100010" title="1층 Q열 003번" grade="S석"></div>
									<div class="seat_a" id="t2100011" style="LEFT: 236px; TOP: 445px" name="tk" value="2100011" title="1층 Q열 004번" grade="S석"></div>
									<div class="seat_a" id="t2100012" style="LEFT: 247px; TOP: 445px" name="tk" value="2100012" title="1층 Q열 005번" grade="S석"></div>
									<div class="seat_a" id="t2100018" style="LEFT: 278px; TOP: 445px" name="tk" value="2100018" title="1층 Q열 006번" grade="S석"></div>
									<div class="seat_a" id="t2100019" style="LEFT: 289px; TOP: 445px" name="tk" value="2100019" title="1층 Q열 007번" grade="S석"></div>
									<div class="seat_a" id="t2100020" style="LEFT: 300px; TOP: 445px" name="tk" value="2100020" title="1층 Q열 008번" grade="S석"></div>
									<div class="seat_a" id="t2100021" style="LEFT: 311px; TOP: 445px" name="tk" value="2100021" title="1층 Q열 009번" grade="S석"></div>
									<div class="seat_a" id="t2100022" style="LEFT: 322px; TOP: 445px" name="tk" value="2100022" title="1층 Q열 010번" grade="S석"></div>
									<div class="seat_a" id="t2100023" style="LEFT: 333px; TOP: 445px" name="tk" value="2100023" title="1층 Q열 011번" grade="S석"></div>
									<div class="seat_a" id="t2100024" style="LEFT: 344px; TOP: 445px" name="tk" value="2100024" title="1층 Q열 012번" grade="S석"></div>
									<div class="seat_a" id="t2100025" style="LEFT: 355px; TOP: 445px" name="tk" value="2100025" title="1층 Q열 013번" grade="R석"></div>
									<div class="seat_a" id="t2100026" style="LEFT: 366px; TOP: 445px" name="tk" value="2100026" title="1층 Q열 014번" grade="R석"></div>
									<div class="seat_a" id="t2100027" style="LEFT: 377px; TOP: 445px" name="tk" value="2100027" title="1층 Q열 015번" grade="R석"></div>
									<div class="seat_a" id="t2100028" style="LEFT: 388px; TOP: 445px" name="tk" value="2100028" title="1층 Q열 016번" grade="R석"></div>
									<div class="seat_a" id="t2100029" style="LEFT: 399px; TOP: 445px" name="tk" value="2100029" title="1층 Q열 017번" grade="R석"></div>
									<div class="seat_a" id="t2100034" style="LEFT: 429px; TOP: 445px" name="tk" value="2100034" title="1층 Q열 018번" grade="R석"></div>
									<div class="seat_a" id="t2100035" style="LEFT: 440px; TOP: 445px" name="tk" value="2100035" title="1층 Q열 019번" grade="S석"></div>
									<div class="seat_a" id="t2100036" style="LEFT: 451px; TOP: 445px" name="tk" value="2100036" title="1층 Q열 020번" grade="S석"></div>
									<div class="seat_a" id="t2100037" style="LEFT: 462px; TOP: 445px" name="tk" value="2100037" title="1층 Q열 021번" grade="S석"></div>
									<div class="seat_a" id="t2100038" style="LEFT: 473px; TOP: 445px" name="tk" value="2100038" title="1층 Q열 022번" grade="S석"></div>
									<!--<div class="label">R</div>-->
									<div class="seat_a" id="t2200008" style="LEFT: 203px; TOP: 457px" name="tk" value="2200008" title="1층 R열 001번" grade="S석"></div>
									<div class="seat_a" id="t2200009" style="LEFT: 214px; TOP: 457px" name="tk" value="2200009" title="1층 R열 002번" grade="S석"></div>
									<div class="seat_a" id="t2200010" style="LEFT: 225px; TOP: 457px" name="tk" value="2200010" title="1층 R열 003번" grade="S석"></div>
									<div class="seat_a" id="t2200011" style="LEFT: 236px; TOP: 457px" name="tk" value="2200011" title="1층 R열 004번" grade="S석"></div>
									<div class="seat_a" id="t2200012" style="LEFT: 247px; TOP: 457px" name="tk" value="2200012" title="1층 R열 005번" grade="S석"></div>
									<div class="seat_a" id="t2200018" style="LEFT: 278px; TOP: 457px" name="tk" value="2200018" title="1층 R열 006번" grade="S석"></div>
									<div class="seat_a" id="t2200019" style="LEFT: 289px; TOP: 457px" name="tk" value="2200019" title="1층 R열 007번" grade="S석"></div>
									<div class="seat_a" id="t2200020" style="LEFT: 300px; TOP: 457px" name="tk" value="2200020" title="1층 R열 008번" grade="S석"></div>
									<div class="seat_a" id="t2200021" style="LEFT: 311px; TOP: 457px" name="tk" value="2200021" title="1층 R열 009번" grade="S석"></div>
									<div class="seat_a" id="t2200022" style="LEFT: 322px; TOP: 457px" name="tk" value="2200022" title="1층 R열 010번" grade="S석"></div>
									<div class="seat_a" id="t2200023" style="LEFT: 333px; TOP: 457px" name="tk" value="2200023" title="1층 R열 011번" grade="S석"></div>
									<div class="seat_a" id="t2200024" style="LEFT: 344px; TOP: 457px" name="tk" value="2200024" title="1층 R열 012번" grade="S석"></div>
									<div class="seat_a" id="t2200025" style="LEFT: 355px; TOP: 457px" name="tk" value="2200025" title="1층 R열 013번" grade="R석"></div>
									<div class="seat_a" id="t2200026" style="LEFT: 366px; TOP: 457px" name="tk" value="2200026" title="1층 R열 014번" grade="R석"></div>
									<div class="seat_a" id="t2200027" style="LEFT: 377px; TOP: 457px" name="tk" value="2200027" title="1층 R열 015번" grade="R석"></div>
									<div class="seat_a" id="t2200028" style="LEFT: 388px; TOP: 457px" name="tk" value="2200028" title="1층 R열 016번" grade="R석"></div>
									<div class="seat_a" id="t2200029" style="LEFT: 399px; TOP: 457px" name="tk" value="2200029" title="1층 R열 017번" grade="R석"></div>
									<div class="seat_a" id="t2200034" style="LEFT: 429px; TOP: 457px" name="tk" value="2200034" title="1층 R열 018번" grade="R석"></div>
									<div class="seat_a" id="t2200035" style="LEFT: 440px; TOP: 457px" name="tk" value="2200035" title="1층 R열 019번" grade="S석"></div>
									<div class="seat_a" id="t2200036" style="LEFT: 451px; TOP: 457px" name="tk" value="2200036" title="1층 R열 020번" grade="S석"></div>
									<div class="seat_a" id="t2200037" style="LEFT: 462px; TOP: 457px" name="tk" value="2200037" title="1층 R열 021번" grade="S석"></div>
									<div class="seat_a" id="t2200038" style="LEFT: 473px; TOP: 457px" name="tk" value="2200038" title="1층 R열 022번" grade="S석"></div>
									<!--<div class="label">S</div>-->
									<div class="seat_a" id="t2300008" style="LEFT: 203px; TOP: 469px" name="tk" value="2300008" title="1층 S열 001번" grade="S석"></div>
									<div class="seat_a" id="t2300009" style="LEFT: 214px; TOP: 469px" name="tk" value="2300009" title="1층 S열 002번" grade="S석"></div>
									<div class="seat_a" id="t2300010" style="LEFT: 225px; TOP: 469px" name="tk" value="2300010" title="1층 S열 003번" grade="S석"></div>
									<div class="seat_a" id="t2300011" style="LEFT: 236px; TOP: 469px" name="tk" value="2300011" title="1층 S열 004번" grade="S석"></div>
									<div class="seat_a" id="t2300012" style="LEFT: 247px; TOP: 469px" name="tk" value="2300012" title="1층 S열 005번" grade="S석"></div>
									<div class="seat_a" id="t2300018" style="LEFT: 278px; TOP: 469px" name="tk" value="2300018" title="1층 S열 006번" grade="S석"></div>
									<div class="seat_a" id="t2300019" style="LEFT: 289px; TOP: 469px" name="tk" value="2300019" title="1층 S열 007번" grade="S석"></div>
									<div class="seat_a" id="t2300020" style="LEFT: 300px; TOP: 469px" name="tk" value="2300020" title="1층 S열 008번" grade="S석"></div>
									<div class="seat_a" id="t2300021" style="LEFT: 311px; TOP: 469px" name="tk" value="2300021" title="1층 S열 009번" grade="S석"></div>
									<div class="seat_a" id="t2300022" style="LEFT: 322px; TOP: 469px" name="tk" value="2300022" title="1층 S열 010번" grade="S석"></div>
									<div class="seat_a" id="t2300023" style="LEFT: 333px; TOP: 469px" name="tk" value="2300023" title="1층 S열 011번" grade="S석"></div>
									<div class="seat_a" id="t2300024" style="LEFT: 344px; TOP: 469px" name="tk" value="2300024" title="1층 S열 012번" grade="S석"></div>
									<div class="seat_a" id="t2300025" style="LEFT: 355px; TOP: 469px" name="tk" value="2300025" title="1층 S열 013번" grade="R석"></div>
									<div class="seat_a" id="t2300026" style="LEFT: 366px; TOP: 469px" name="tk" value="2300026" title="1층 S열 014번" grade="R석"></div>
									<div class="seat_a" id="t2300027" style="LEFT: 377px; TOP: 469px" name="tk" value="2300027" title="1층 S열 015번" grade="R석"></div>
									<div class="seat_a" id="t2300028" style="LEFT: 388px; TOP: 469px" name="tk" value="2300028" title="1층 S열 016번" grade="R석"></div>
									<div class="seat_a" id="t2300029" style="LEFT: 399px; TOP: 469px" name="tk" value="2300029" title="1층 S열 017번" grade="R석"></div>
									<div class="seat_a" id="t2300034" style="LEFT: 429px; TOP: 469px" name="tk" value="2300034" title="1층 S열 018번" grade="R석"></div>
									<div class="seat_a" id="t2300035" style="LEFT: 440px; TOP: 469px" name="tk" value="2300035" title="1층 S열 019번" grade="S석"></div>
									<div class="seat_a" id="t2300036" style="LEFT: 451px; TOP: 469px" name="tk" value="2300036" title="1층 S열 020번" grade="S석"></div>
									<div class="seat_a" id="t2300037" style="LEFT: 462px; TOP: 469px" name="tk" value="2300037" title="1층 S열 021번" grade="S석"></div>
									<div class="seat_a" id="t2300038" style="LEFT: 473px; TOP: 469px" name="tk" value="2300038" title="1층 S열 022번" grade="S석"></div>
									<!--<div class="label">T</div>-->
									<div class="seat_a" id="t2400008" style="LEFT: 203px; TOP: 481px" name="tk" value="2400008" title="1층 T열 001번" grade="S석"></div>
									<div class="seat_a" id="t2400009" style="LEFT: 214px; TOP: 481px" name="tk" value="2400009" title="1층 T열 002번" grade="S석"></div>
									<div class="seat_a" id="t2400010" style="LEFT: 225px; TOP: 481px" name="tk" value="2400010" title="1층 T열 003번" grade="S석"></div>
									<div class="seat_a" id="t2400011" style="LEFT: 236px; TOP: 481px" name="tk" value="2400011" title="1층 T열 004번" grade="S석"></div>
									<div class="seat_a" id="t2400012" style="LEFT: 247px; TOP: 481px" name="tk" value="2400012" title="1층 T열 005번" grade="S석"></div>
									<div class="seat_a" id="t2400018" style="LEFT: 278px; TOP: 481px" name="tk" value="2400018" title="1층 T열 006번" grade="S석"></div>
									<div class="seat_a" id="t2400019" style="LEFT: 289px; TOP: 481px" name="tk" value="2400019" title="1층 T열 007번" grade="S석"></div>
									<div class="seat_a" id="t2400020" style="LEFT: 300px; TOP: 481px" name="tk" value="2400020" title="1층 T열 008번" grade="S석"></div>
									<div class="seat_a" id="t2400021" style="LEFT: 311px; TOP: 481px" name="tk" value="2400021" title="1층 T열 009번" grade="S석"></div>
									<div class="seat_a" id="t2400022" style="LEFT: 322px; TOP: 481px" name="tk" value="2400022" title="1층 T열 010번" grade="S석"></div>
									<div class="seat_a" id="t2400023" style="LEFT: 333px; TOP: 481px" name="tk" value="2400023" title="1층 T열 011번" grade="S석"></div>
									<div class="seat_a" id="t2400024" style="LEFT: 344px; TOP: 481px" name="tk" value="2400024" title="1층 T열 012번" grade="S석"></div>
									<div class="seat_a" id="t2400025" style="LEFT: 355px; TOP: 481px" name="tk" value="2400025" title="1층 T열 013번" grade="R석"></div>
									<div class="seat_a" id="t2400026" style="LEFT: 366px; TOP: 481px" name="tk" value="2400026" title="1층 T열 014번" grade="R석"></div>
									<div class="seat_a" id="t2400027" style="LEFT: 377px; TOP: 481px" name="tk" value="2400027" title="1층 T열 015번" grade="R석"></div>
									<div class="seat_a" id="t2400028" style="LEFT: 388px; TOP: 481px" name="tk" value="2400028" title="1층 T열 016번" grade="R석"></div>
									<div class="seat_a" id="t2400029" style="LEFT: 399px; TOP: 481px" name="tk" value="2400029" title="1층 T열 017번" grade="R석"></div>
									<div class="seat_a" id="t2400034" style="LEFT: 429px; TOP: 481px" name="tk" value="2400034" title="1층 T열 018번" grade="R석"></div>
									<div class="seat_a" id="t2400035" style="LEFT: 440px; TOP: 481px" name="tk" value="2400035" title="1층 T열 019번" grade="S석"></div>
									<div class="seat_a" id="t2400036" style="LEFT: 451px; TOP: 481px" name="tk" value="2400036" title="1층 T열 020번" grade="S석"></div>
									<div class="seat_a" id="t2400037" style="LEFT: 462px; TOP: 481px" name="tk" value="2400037" title="1층 T열 021번" grade="S석"></div>
									<div class="seat_a" id="t2400038" style="LEFT: 473px; TOP: 481px" name="tk" value="2400038" title="1층 T열 022번" grade="S석"></div>
									<!--<div class="label">U</div>-->
									<div class="seat_a" id="t2500009" style="LEFT: 214px; TOP: 493px" name="tk" value="2500009" title="1층 U열 001번" grade="S석"></div>
									<div class="seat_a" id="t2500010" style="LEFT: 225px; TOP: 493px" name="tk" value="2500010" title="1층 U열 002번" grade="S석"></div>
									<div class="seat_a" id="t2500011" style="LEFT: 236px; TOP: 493px" name="tk" value="2500011" title="1층 U열 003번" grade="S석"></div>
									<div class="seat_a" id="t2500012" style="LEFT: 247px; TOP: 493px" name="tk" value="2500012" title="1층 U열 004번" grade="S석"></div>
									<div class="seat_a" id="t2500018" style="LEFT: 278px; TOP: 493px" name="tk" value="2500018" title="1층 U열 005번" grade="S석"></div>
									<div class="seat_a" id="t2500019" style="LEFT: 289px; TOP: 493px" name="tk" value="2500019" title="1층 U열 006번" grade="S석"></div>
									<div class="seat_a" id="t2500020" style="LEFT: 300px; TOP: 493px" name="tk" value="2500020" title="1층 U열 007번" grade="S석"></div>
									<div class="seat_a" id="t2500021" style="LEFT: 311px; TOP: 493px" name="tk" value="2500021" title="1층 U열 008번" grade="S석"></div>
									<div class="seat_a" id="t2500022" style="LEFT: 322px; TOP: 493px" name="tk" value="2500022" title="1층 U열 009번" grade="S석"></div>
									<div class="seat_a" id="t2500023" style="LEFT: 333px; TOP: 493px" name="tk" value="2500023" title="1층 U열 010번" grade="S석"></div>
									<div class="seat_a" id="t2500024" style="LEFT: 344px; TOP: 493px" name="tk" value="2500024" title="1층 U열 011번" grade="S석"></div>
									<div class="seat_a" id="t2500025" style="LEFT: 355px; TOP: 493px" name="tk" value="2500025" title="1층 U열 012번" grade="S석"></div>
									<div class="seat_a" id="t2500026" style="LEFT: 366px; TOP: 493px" name="tk" value="2500026" title="1층 U열 013번" grade="R석"></div>
									<div class="seat_a" id="t2500027" style="LEFT: 377px; TOP: 493px" name="tk" value="2500027" title="1층 U열 014번" grade="R석"></div>
									<div class="seat_a" id="t2500028" style="LEFT: 388px; TOP: 493px" name="tk" value="2500028" title="1층 U열 015번" grade="R석"></div>
									<div class="seat_a" id="t2500029" style="LEFT: 399px; TOP: 493px" name="tk" value="2500029" title="1층 U열 016번" grade="R석"></div>
									<div class="seat_a" id="t2500034" style="LEFT: 429px; TOP: 493px" name="tk" value="2500034" title="1층 U열 017번" grade="R석"></div>
									<div class="seat_a" id="t2500035" style="LEFT: 440px; TOP: 493px" name="tk" value="2500035" title="1층 U열 018번" grade="R석"></div>
									<div class="seat_a" id="t2500036" style="LEFT: 451px; TOP: 493px" name="tk" value="2500036" title="1층 U열 019번" grade="S석"></div>
									<div class="seat_a" id="t2500037" style="LEFT: 462px; TOP: 493px" name="tk" value="2500037" title="1층 U열 020번" grade="S석"></div>
									<!--<div class="label">V</div>-->
									<div class="seat_a" id="t2600009" style="LEFT: 214px; TOP: 505px" name="tk" value="2600009" title="1층 V열 001번" grade="S석"></div>
									<div class="seat_a" id="t2600010" style="LEFT: 225px; TOP: 505px" name="tk" value="2600010" title="1층 V열 002번" grade="S석"></div>
									<div class="seat_a" id="t2600011" style="LEFT: 236px; TOP: 505px" name="tk" value="2600011" title="1층 V열 003번" grade="S석"></div>
									<div class="seat_a" id="t2600012" style="LEFT: 247px; TOP: 505px" name="tk" value="2600012" title="1층 V열 004번" grade="S석"></div>
									<div class="seat_a" id="t2600018" style="LEFT: 278px; TOP: 505px" name="tk" value="2600018" title="1층 V열 005번" grade="S석"></div>
									<div class="seat_a" id="t2600019" style="LEFT: 289px; TOP: 505px" name="tk" value="2600019" title="1층 V열 006번" grade="S석"></div>
									<div class="seat_a" id="t2600020" style="LEFT: 300px; TOP: 505px" name="tk" value="2600020" title="1층 V열 007번" grade="S석"></div>
									<div class="seat_a" id="t2600021" style="LEFT: 311px; TOP: 505px" name="tk" value="2600021" title="1층 V열 008번" grade="S석"></div>
									<div class="seat_a" id="t2600022" style="LEFT: 322px; TOP: 505px" name="tk" value="2600022" title="1층 V열 009번" grade="S석"></div>
									<div class="seat_a" id="t2600023" style="LEFT: 333px; TOP: 505px" name="tk" value="2600023" title="1층 V열 010번" grade="S석"></div>
									<div class="seat_a" id="t2600024" style="LEFT: 344px; TOP: 505px" name="tk" value="2600024" title="1층 V열 011번" grade="S석"></div>
									<div class="seat_a" id="t2600025" style="LEFT: 355px; TOP: 505px" name="tk" value="2600025" title="1층 V열 012번" grade="S석"></div>
									<div class="seat_a" id="t2600026" style="LEFT: 366px; TOP: 505px" name="tk" value="2600026" title="1층 V열 013번" grade="R석"></div>
									<div class="seat_a" id="t2600027" style="LEFT: 377px; TOP: 505px" name="tk" value="2600027" title="1층 V열 014번" grade="R석"></div>
									<div class="seat_a" id="t2600028" style="LEFT: 388px; TOP: 505px" name="tk" value="2600028" title="1층 V열 015번" grade="R석"></div>
									<div class="seat_a" id="t2600029" style="LEFT: 399px; TOP: 505px" name="tk" value="2600029" title="1층 V열 016번" grade="R석"></div>
									<div class="seat_a" id="t2600034" style="LEFT: 429px; TOP: 505px" name="tk" value="2600034" title="1층 V열 017번" grade="R석"></div>
									<div class="seat_a" id="t2600035" style="LEFT: 440px; TOP: 505px" name="tk" value="2600035" title="1층 V열 018번" grade="R석"></div>
									<div class="seat_a" id="t2600036" style="LEFT: 451px; TOP: 505px" name="tk" value="2600036" title="1층 V열 019번" grade="S석"></div>
									<div class="seat_a" id="t2600037" style="LEFT: 462px; TOP: 505px" name="tk" value="2600037" title="1층 V열 020번" grade="S석"></div>
                            </div>
                        </div>

                        <div class="select_seat_info" style="z-index: 20;;z-index: 20;">
                                <strong>좌석을 선택해주세요. 최대 5개까지 선택 가능합니다.</strong>
                                <img src="../../img/selected_seat_refresh_icon.png" onclick="seatIni();">
                        </div>
                    </div>
                </div>
                <!--------------------------------------->
                <!--------------------------------------->
                <!--------------------------------------->
                <div class="reserve_right">
                    <div class="top_info_area">
                        <div id="selected_seat_tit_div">
                            <div id="selected_seat_tit">선택한 좌석</div>
                        </div>
                        <hr>
                        <ul id="selected_seat">
                        	
                            <li id="selected_seat_no_1">
                                <div class="selected_seat_color" style="background:#ffc000"></div>
                                <div class="selected_seat_detail_info">
                                    <span class="selected_seat_grade" >--석</span>
                                    <span class="selected_seat_no">-열 --번</span>
                                </div>
                            </li>
                            <li id="selected_seat_no_2">
                                <div class="selected_seat_color" style="background:#ffc000"></div>
                                <div class="selected_seat_detail_info">
                                    <span class="selected_seat_grade" >--석</span>
                                    <span class="selected_seat_no">-열 --번</span>
                                </div>
                            </li>
                            <li id="selected_seat_no_3">
                                <div class="selected_seat_color" style="background:#ffc000"></div>
                                <div class="selected_seat_detail_info">
                                    <span class="selected_seat_grade" >--석</span>
                                    <span class="selected_seat_no">-열 --번</span>
                                </div>
                            </li>
                            <li id="selected_seat_no_4">
                                <div class="selected_seat_color" style="background:#ffc000"></div>
                                <div class="selected_seat_detail_info">
                                    <span class="selected_seat_grade" >--석</span>
                                    <span class="selected_seat_no">-열 --번</span>
                                </div>
                            </li>
                            <li id="selected_seat_no_5">
                                <div class="selected_seat_color" style="background:#ffc000"></div>
                                    <div class="selected_seat_detail_info">
                                        <span class="selected_seat_grade" >--석</span>
                                        <span class="selected_seat_no">-열 --번</span>
                                    </div>
                            </li>
						</ul>
                    </div>
                    <div class="reserve_grade">
                        <div class="reserve_tit_div">
                            잔여석
                        </div>
                        <hr>
                        <ul id="select_seat_grade" class="seat_lst">
                        	
                            <!--좌석등급 별 list-->
                            <!--
                            <li id="seat_grade_33625">
                                <div class="seat_color" style="background:#ffc000"></div>
                                <div class="seat_detail_info">
                                    <span class="seat_grade" >VIP석</span>
                                    <span class="seat_price">
                                        140,0000원</span>
                                    <span class="seat_avail">
                                        <span class="seat_avail_n">232</span>
                                        석
                                    </span>
                                </div>
                            </li>
							-->
                        </ul>
                        <form action="/reserveConfirm.do?progNo=<%=progNo%>" method="post" id="seatSubmitForm">
                            <input type="hidden" id="psNo_form" name="psNo" value="<%= psNo %>"/>
                            <input type="hidden" id="seat_form" name="seatList" />
                        </form>
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