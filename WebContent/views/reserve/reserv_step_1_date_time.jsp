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
	
	String showTitle = stOne.getShowTitle();
	String showPoster = stOne.getShowPoster();
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
		//console.log(ps);////////////////////////////////////////////
<%
	}
%>

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
        //var availSeat = null;
        
        $(document).ready(function(){
            
        	pageInit();
        	
        	// 날력의 날짜 클릭했을 때
        	// date_sel에 선택한 날짜 저장
        	// 날짜와 회차 모두 선택한 상태에서 다른 날짜를 다시 선택했을 경우를 위해 먼저, 선택한 회차정보를 초기화 
            $('.calendar-date').click(function(){
            //$('.week .day').click(function(){
            	//alert("hello");/////////////////////////////////////////
                date_sel = null;
                cnt_sel = null;
                psNo = null;
                //availSeat = null;
                
                $('#seat_box ul').html("");
                $('#cnt_box ul').html("");
                $('#seat_box ul').html("");
                $('#cnt_box li').removeClass('selected_cnt_li');
                $('.calendar-date').removeClass('selected_date_td');
                $(this).addClass('selected_date_td');
                
                var yearMonth = $('.year-month').text().split('.');
                var year = yearMonth[0];
                var month = yearMonth[1];
                var day = $(this).text();
                
                /*
                var calSelected = $('#calSelected').val().split('-');
                alert(calSelcected);
                var year = calSelected[0];
                var month = calSelected[1];
                var day = calSelected[2];
                */
                
                date_sel = year+"-"+month+"-"+day;
                $('#date_sel_info span').html(year+"."+month+"."+day);
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
            	
            })
            
            /*
            // 회차 정보 li를 클릭했을 때
            // cnt_sel에 선택한 회차 번호 저장
            // 선택한 날짜와 회차 정보 종합해서 잔여석 표시
            $('#cnt_box li').click(function(){
                alert(date_sel);
            	if(date_sel!=null) {
                    $('#cnt_box li').removeClass('selected_cnt_li');
                    $('#cnt_box li[id=]').addClass('selected_cnt_li');

                    cnt_sel = $(this).children('.cnt').html();
                    $('#cnt_sel_info span').html($(this).text());
                    //alert(cnt_sel);
                }
            })
            */
            
        })
        
        function pageInit() {
        	
        	var showTitle = '<%=showTitle%>';
        	$('#mini_show_title').text(showTitle);	//공연명 표시
        	
        	var showPosterSrc = "/img/poster/<%=showPoster%>";
        	$('#mini_poster img').attr('src',showPosterSrc);  //포스터 이미지 경로 세팅
        	
        	
        	//psList에 담긴 선택가능한 날짜들 달력에 표시해주기
        	
        	
        }
        
     	// 회차 정보 li를 클릭했을 때
        // cnt_sel에 선택한 회차 번호 저장
        // 선택한 날짜와 회차 정보 종합해서 잔여석 표시
        function listClick(cnt){
            
        	if(date_sel!=null) {
        		var selectedLi = '#cnt_box #li_'+cnt;
        		$('#cnt_box li').removeClass('selected_cnt_li');
                $(selectedLi).addClass('selected_cnt_li');

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
                
                //console.log(seatGrdStList);/////////////////
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
        }
        
        function next() {
            var stat = false;
            if(date_sel!=null && cnt_sel!=null) stat = true;
            if(stat==false) {
                alert("날짜/회차를 선택하세요");
            }else {
                //console.log("선택한 날짜 : "+date_sel+"  /  선택한 회차 : "+cnt_sel);/////////
                location.href="/reserveSeat.do?psNo="+psNo;
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
                    <div id="content_left">
                        <div id="ct_left_top">
                            <div id="ct_left_date">
                                <b>날짜선택</b><hr>
                                <div id="date_box">
                                	<!--<jsp:include page="/views/reserve/reserveCalendar.html" />-->
                                	
                                    <div class="calendar">
                                        <div class="calendar-header">
                                            <a class="prev-mon" title="이전달"><span>이전달</span></a>
                                            <a class="next-mon" title="다음달"><span>다음달</span></a>

                                            <div class="current-mon"><span class="year-month">2018.11</span></div>
                                        </div>
                                        <div class="calendar-body">
                                            <table>
                                                <thead>
                                                <tr>
                                                    <th><span title="일요일">일</span></th>
                                                    <th><span title="월요일">월</span></th>
                                                    <th><span title="화요일">화</span></th>
                                                    <th><span title="수요일">수</span></th>
                                                    <th><span title="목요일">목</span></th>
                                                    <th><span title="금요일">금</span></th>
                                                    <th><span title="토요일">토</span></th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                    <tr class="calendar-week"> <!-- 달력의 한 주에 해당하는 엘리먼트 컨테이너 -->
                                                        <td class="calendar-date calendar-sun">30</td>
                                                        <!-- 날짜가 표시될 엘리먼트 -->
                                                        <td class="calendar-date">1</td>
                                                        <td class="calendar-date">2</td>
                                                        <td class="calendar-date">3</td>
                                                        <td class="calendar-date">4</td>
                                                        <td class="calendar-date">5</td>
                                                        <td class="calendar-date calendar-sat">6</td>
                                                    </tr>
                                                    <tr class="calendar-week"> <!-- 달력의 한 주에 해당하는 엘리먼트 컨테이너 -->
                                                        <td class="calendar-date calendar-sun">7</td>
                                                        <!-- 날짜가 표시될 엘리먼트 -->
                                                        <td class="calendar-date">8</td>
                                                        <td class="calendar-date">9</td>
                                                        <td class="calendar-date">10</td>
                                                        <td class="calendar-date">11</td>
                                                        <td class="calendar-date">12</td>
                                                        <td class="calendar-date calendar-sat available">13</td>
                                                    </tr>
                                                    <tr class="calendar-week"> <!-- 달력의 한 주에 해당하는 엘리먼트 컨테이너 -->
                                                        <td class="calendar-date calendar-sun available">14</td>
                                                        <!-- 날짜가 표시될 엘리먼트 -->
                                                        <td class="calendar-date available">15</td>
                                                        <td class="calendar-date calendar-today available">16</td>
                                                        <td class="calendar-date">17</td>
                                                        <td class="calendar-date">18</td>
                                                        <td class="calendar-date">19</td>
                                                        <td class="calendar-date calendar-sat">20</td>
                                                    </tr>
                                                    <tr class="calendar-week"> <!-- 달력의 한 주에 해당하는 엘리먼트 컨테이너 -->
                                                        <td class="calendar-date calendar-sun">21</td>
                                                        <!-- 날짜가 표시될 엘리먼트 -->
                                                        <td class="calendar-date">22</td>
                                                        <td class="calendar-date">23</td>
                                                        <td class="calendar-date">24</td>
                                                        <td class="calendar-date">25</td>
                                                        <td class="calendar-date">26</td>
                                                        <td class="calendar-date calendar-sat">27</td>
                                                    </tr>
                                                    <tr class="calendar-week"> <!-- 달력의 한 주에 해당하는 엘리먼트 컨테이너 -->
                                                        <td class="calendar-date calendar-sun">28</td>
                                                        <!-- 날짜가 표시될 엘리먼트 -->
                                                        <td class="calendar-date">29</td>
                                                        <td class="calendar-date">30</td>
                                                        <td class="calendar-date">31</td>
                                                        <td class="calendar-date calendar-next-mon">1</td>
                                                        <td class="calendar-date calendar-next-mon">2</td>
                                                        <td class="calendar-date calendar-next-mon calendar-sat">3</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                            <span style="color: red; font-size: 10px;">전월 다음달 선택 시 변수에 반영할것</span>
                                        </div>
                                    </div>
                                    
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