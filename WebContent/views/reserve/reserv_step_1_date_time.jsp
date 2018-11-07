<%@page import="kr.co.ticketsea.reserve.model.vo.SeatGradeState"%>
<%@page import="kr.co.ticketsea.reserve.model.vo.ReserveStepOne"%>
<%@page import="kr.co.ticketsea.reserve.model.vo.PerformSchedule"%>
<%@page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	request.setCharacterEncoding("utf-8");
	int showNo = Integer.parseInt(request.getParameter("showNo"));
	ReserveStepOne stOne = (ReserveStepOne)request.getAttribute("stepOne");
	
	String showTitle = stOne.getShowTitle(); //공연명
	String showPoster = stOne.getShowPoster(); //공연 포스터 파일명
	//String startDate = stOne.getStartDate(); //공연 시작일 ///////////////////////////////////
	//String endDate = stOne.getEndDate(); //공연 종료일 ///////////////////////////////////////
	
	ArrayList<PerformSchedule> psList = stOne.getPsList();
%>

<script>
	var psList = [];
<%
	for(int i=0; i<psList.size(); i++) {
%>
		var psNo=<%=psList.get(i).getPerformSchNo()%>;
		var psDate='<%=psList.get(i).getPerformSchDate()%>';
		var psCnt=<%=psList.get(i).getPerformSchCnt()%>;
		var psTime='<%=psList.get(i).getPerformTime()%>';
		var seatGrdStList = [];
		
		<%
       	for (SeatGradeState seatGrdSt : psList.get(i).getSeatGrdStList()) {
       		String seatGrd = seatGrdSt.getTh1_seat_grd();
       		int avail = seatGrdSt.getAvailableSeatCnt();
       	%>
	       	var seatGrdSt = ['<%=seatGrd%>', <%=avail%>];
	       	seatGrdStList.push(seatGrdSt);
       	<%} %>
		
		var ps = [psNo, psDate, psCnt, psTime, seatGrdStList];
		psList.push(ps);
<%
	}
%>
	
	var startDate = psList[0][1];
	var startDateSp = startDate.split('-');
	var startDateN = Number(startDateSp[0]+startDateSp[1]+startDateSp[2]);
	
	var endDate = psList[psList.length-1][1];
	var endDateSp = endDate.split('-');
	var endDateN = Number(endDateSp[0]+endDateSp[1]+endDateSp[2]);
	//console.log("start : "+startDateN+"\nend : "+endDateN);
	
	//오늘 날짜를 Number 형식으로 today에 저장
	var todayYear = new Date().getFullYear();
	var todayMonth = new Date().getMonth()+1; //오늘이 속한 달
	var todayDate = new Date().getDate();
	var today = todayYear*10000+todayMonth*100+todayDate;
	//console.log("today : " + today);
    
</script>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Ticket Sea 예매</title>
    
    <!-- <link rel="shortcut icon" type="image/x-icon" href="http://ticketlink.dn.toastoven.net/web/favicon.ico"> -->
    
    <!-- 외부 스타일 시트 적용 -->
    <link href="../../css/reserv_common.css" rel="stylesheet" type="text/css">
    <link href="../../css/reserv_step_1.css" rel="stylesheet" type="text/css">
    
    <script
      type="text/javascript"
        src="../../resources/jquery-3.3.1.js">
    </script>
    <script>
        var date_sel = null;
        var cnt_sel = null;
        var psNo = null;
        
        $(document).ready(function(){
			
			pageInit();
			
			/* 
			$(document).one('mousemove', function(){
				viewSelectableDays();
			});
			*/
			$(document).mousemove(function(){
				viewSelectableDays();
			});
			

        	// 날력의 날짜 클릭했을 때
        	// date_sel에 선택한 날짜 저장
        	// 날짜와 회차 모두 선택한 상태에서 다른 날짜를 다시 선택했을 경우를 위해 먼저, 선택한 회차정보를 초기화 
			$('calendar').click(function(){
				
				date_sel = null;
	            cnt_sel = null;
	            psNo = null;
				
				var chkSel = $('#calSelected').val();
				
				if(date_sel!=chkSel) {
					date_sel = chkSel;
					//console.log( "date_sel : " + date_sel );
				}
				
				viewSelectableDays();
				
                $('#seat_box ul').html("");
                $('#cnt_box ul').html("");
                $('#seat_box ul').html("");
                $('#cnt_box li').removeClass('selected_cnt_li');
                //$('.calendar-date').removeClass('selected_date_td');
                //$(this).addClass('selected_date_td');
                
                var calSelected = date_sel.split('-');
                //console.log("date_sel : "+date_sel);
                //console.log("calSelcected : "+calSelected);
                
                var year = calSelected[0];
                var month = calSelected[1];
                var day = calSelected[2];
                
                //$('#date_sel_info span').html(year+"."+month+"."+day);
                $('#date_sel_info span').html("");
                $('#cnt_sel_info span').html(cnt_sel);
                //alert(date_sel);
                
                var cntList = [];
                for(var i=0; i<psList.length; i++) {
                	if(psList[i][1]==date_sel) {
               			var ps = psList[i];
               			var cntTime = [ ps[2], ps[3] ];
               			cntList.push(cntTime);
                	}
                }
                
                var list = "";
            	for(var i=0; i<cntList.length; i++) {
            		list += " <li id='li_"+ cntList[i][0] +"' onclick='listClick("+ cntList[i][0] +");'> "
		                        +"<span class='cnt'>"+ cntList[i][0] +"</span> "
		                        +"<span>회</span> "
		                        +"<span class='cnt_time'>"+ cntList[i][1] +"</span> "
		                    +" </li> ";
            	}
            	$('#cnt_box ul').html(list);
				
			});//$('calendar').click END
        	
        	
        });//$(document).ready END
        
        
        
        function pageInit() {
        	
        	var showTitle = '<%=showTitle%>';
        	$('#mini_show_title').text(showTitle);	//공연명 표시
        	
        	var showPosterSrc = "/img/poster/<%=showPoster%>";
        	$('#mini_poster img').attr('src',showPosterSrc);  //포스터 이미지 경로 세팅
        	
        }//function pageInit() END
        
     	// 회차 정보 li를 클릭했을 때
        // cnt_sel에 선택한 회차 번호 저장
        // 선택한 날짜와 회차 정보 종합해서 잔여석 표시
        function listClick(cnt){
            
        	if(date_sel!=null) {
        		var selectedLi = '#cnt_box #li_'+cnt;
        		$('#cnt_box li').removeClass('selected_cnt_li');
                $(selectedLi).addClass('selected_cnt_li');
                
                ////////////////////////
                var calSelected = date_sel.split('-');
                var year = calSelected[0];
                var month = calSelected[1];
                var day = calSelected[2];
                $('#date_sel_info span').html(year+"."+month+"."+day);
                /////////////////////
                
                cnt_sel = $(selectedLi).children('.cnt').html();
                var cntText = $(selectedLi).text();
                $('#cnt_sel_info span').html(cntText);
                //alert(cnt_sel);
                
                var seatGrdStList = [];
                for(var i=0; i<psList.length; i++) {
                	if(psList[i][1]==date_sel && psList[i][2]==cnt_sel) {
                		psNo = psList[i][0];
                		seatGrdStList = psList[i][4];
                		break;
                	}
				}
                //console.log(seatGrdStList);
                
                var list = "";
                for(var i=0; i<seatGrdStList.length; i++) {
                	var seatGrdSt = seatGrdStList[i];
	           		list += " <li> "
		                        +"<span class='seat_grade'>"+ seatGrdSt[0] +" 석 </span> "
		                        +"<span class='available_seat'> "+ seatGrdSt[1] +" </span> "
		                        +"<span>석</span> "
		                   +" </li> ";
	            	
                }
                $('#seat_box ul').html(list);
                
            }
        }//function listClick(cnt) END
     	
		function viewSelectableDays(){
	    	
			//스케줄 시작일이 오늘 이후인지 아닌지
			var diff = today - startDateN;
			console.log("diff : "+diff);
			
			var viewStart = null; //표시 시작일
			var viewEnd = endDateN; //표시 종료일
			
			if(diff>=0) {
				//양수나 0이면 오늘부터
				viewStart = today;
			}
			else {
				//음수면 스케줄 시작일부터
				viewStart = startDateN;
			}
			
			//console.log("viewStart : " + viewStart);
			//console.log("viewEnd : " + viewEnd);
			
			// 현재표시시작일 & 현재표시종료일 세팅
			var viewStartMonth = Math.floor(viewStart/100); //표시 시작일 달(연도포함)
			var viewEndMonth = Math.floor(viewEnd/100); //표시 종료일 달(연도포함)
			//console.log("viewStartMonth : " + viewStartMonth);
			//console.log("viewEndMonth : " + viewEndMonth);
			
			//달력이 표시 중인 월(연도포함)
			var currMonth = Number($('calendar div.header span.ng-binding').text().split('월,')[1])*100
							+ Number($('calendar div.header span.ng-binding').text().split('월,')[0]);
			//console.log("currMonth : " + currMonth);
			
			var currStart = null; //현재 표시 시작일
			var currEnd = null; //현재 표시 종료일
			
			if(currMonth == viewStartMonth) {
				currStart = viewStart;
				if(currMonth == viewEndMonth) {
					currEnd = viewEnd;
					//console.log("1-1");
				}
				else if(currMonth > viewEndMonth) {
					currStart = Math.floor(viewStart/100)*100 + 00;
					currEnd = Math.floor(viewEnd/100)*100 + 00;
					//console.log("1-2");
				}
				else {
					currEnd = Math.floor(viewEnd/100)*100 + 50;
					//console.log("1-3");
				}
			}
			else if(currMonth > viewStartMonth) {
				
				if(currMonth == viewEndMonth) {
					currStart = Math.floor(viewStart/100)*100 + 1;
					currEnd = viewEnd;
					//console.log("2-1");
				}
				else if(currMonth > viewEndMonth) {
					currStart = Math.floor(viewStart/100)*100 + 00;
					currEnd = Math.floor(viewEnd/100)*100 + 00;
					//console.log("2-2");
				}
				else {
					currStart = Math.floor(viewStart/100)*100 + 1;
					currEnd = Math.floor(viewEnd/100)*100 + 50;
					//console.log("2-3");
				}
			}
			else {
				currStart = Math.floor(viewStart/100)*100 + 00;
				currEnd = Math.floor(viewEnd/100)*100 + 00;
				//console.log("3");
			}
			
			//console.log("currStart : " + currStart);
			//console.log("currEnd : " + currEnd);
			
			
			var days = $('calendar div.week span.day.ng-binding').not('.different-month');
			
			
			$.each( days, function(index, item){
				
				var s = currStart%100;
				var e = currEnd%100;
				//console.log("s : " + s);
				//console.log("e : " + e);
				
				//console.log("1-"+$('calendar div.week span.day.ng-binding').eq(index).text());
				//console.log("2-"+item.innerHTML);
				
				if(item.innerHTML>=s && item.innerHTML<=e) {
					//console.log("day-"+item.innerHTML);
					days.eq(index).addClass('selectable');
				}
			});
	    
	    }//function viewSelectableDays() END
        
        function next() {
            var stat = false;
            if(date_sel!=null && cnt_sel!=null) stat = true;
            if(stat==false) {
                alert("날짜/회차를 선택하세요");
            }else {
                //console.log("선택한 날짜 : "+date_sel+"  /  선택한 회차 : "+cnt_sel);
                location.href="/reserveSeat.do?psNo="+psNo;
            }
        }//function next() END
        
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
                    <div id="content_left">
                        <div id="ct_left_top">
                            <div id="ct_left_date">
                                <b>날짜선택</b><hr>
                                <div id="date_box">
                                	
                                	<jsp:include page="/views/reserve/reserveCalendar.html" />
                                	
                                </div>
                            </div>
                            <div id="ct_left_cnt">
                                <b>회차선택</b><hr>
                                <div id="cnt_box">
                                    <ul>
                                        
                                        <!-- 날짜 선택시 javaScript function 작동으로 채워짐 -->
                                        <!-- 
                                        <li>
                                            <span class="cnt">1</span>
                                            <span>회</span>
                                            <span class="cnt_time">10:00 ~ 11:30</span>
                                        </li>
                                        <li>
                                            <span class="cnt">2</span>
                                            <span>회</span>
                                            <span class="cnt_time">12:30 ~ 14:00</span>
                                        </li>
                                        <li>
                                            <span class="cnt">3</span>
                                            <span>회</span>
                                            <span class="cnt_time">15:00 ~ 16:30</span>
                                        </li>
                                         -->
                                    </ul>
                                </div>
                            </div>
                            <div id="ct_left_seat">
                                <b>잔여석</b><hr>
                                <div id="seat_box">
                                    <ul>
                                    	<!-- 날짜와 회차 선택하면 표시됨 -->
                                    	<!--
                                        <li>
                                            <span class="seat_grade">전체</span>
                                            <span class="available_seat">27</span>
                                            <span>석</span>
                                        </li>
                                        -->
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div id="ct_left_btm">
                            <strong class="notice_title"><em></em>티켓예매시 참고사항</strong>
                            <ul class="notice">
                                <li>- 장애인, 국가유공자 할인등급의 경우 현장 수령만 가능합니다. <br>장애인 등록증이나 복지카드 증빙 후 티켓을 수령하실 수 있습니다. 미지참시 할인 혜택을 받으실 수 없으며, 차액만큼 지불하셔야 합니다.</li>
                                <li>- 관람일 하루 전 오후5시까지 취소 가능합니다.<br>평일/주말/공유 일일 경우는 17시까지, 토요일일 경우는 11시까지 가능합니다.
                                </li>
                                <li>- 예매시 결제방법을 무통장입금으로 선택하시면, 입금 가능한 은행명, 계좌번호, 입금 마감일을 알려드립니다.<br>입금 마감 시간은 은행별로 상이할 수 있습니다. 또한, ATM 이용 시 입금이 불가능할 수 있습니다.<br>예매
                                    금액과 입금 금액이 일치하지 않을 경우 입금 오류가 발생하여 입금 처리가 되지 않습니다. 예매시 예매 금액을 꼭 확인하세요.
                                </li>
                                <li>- 신용카드 단일 결제 시 부분 취소가 가능합니다.<br>단, 문화누리카드 및 일부 직불카드는 부분 취소가 불가능 합니다.</li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!--------------------------------------->
                <!--------------------------------------->
                <!--------------------------------------->
                <div class="reserve_right">
                    <div class="top_info_area" style="text-align: left; padding-left: 18px;">
                        <div id="mini_poster">
                            <img src="#">
                        </div>
                        <strong id="mini_show_title">공연이름 출력란</strong>
                    </div>
                    
                    <div class="reserve_result">
                        <div id="reserve_res_tit">예매 정보 출력</div>
                        <hr>
                        <div id="reserve_info">
                            <div id="date_sel_info">
                                <h5>공연일</h5><span></span>
                            </div>
                            <div id="cnt_sel_info">
                                <h5>공연회차</h5><span></span>
                            </div>
                        </div>
                    </div>
                    <div id="reserve_btn_only">
                        <a class="btn" onclick="next()">다음단계</a>
                    </div>

                </div>
            </div>
        </div>
    </div>
	
</body>
</html>
