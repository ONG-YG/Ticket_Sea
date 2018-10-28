<%@page import="kr.co.ticketsea.reserve.model.vo.ReserveStepOne"%>
<%@page import="kr.co.ticketsea.reserve.model.vo.PerformSchedule"%>
<%@page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Ticket Sea 예매</title>
    
    <link rel="shortcut icon" type="image/x-icon" href="http://ticketlink.dn.toastoven.net/web/favicon.ico">
    
    <!-- 외부 스타일 시트 적용 -->
    <link href="../../css/reserv_common.css" rel="stylesheet" type="text/css">
    <link href="../../css/reserv_step_1.css" rel="stylesheet" type="text/css">
    
    <script
      type="text/javascript"
        src="../../resources/jquery-3.3.1.js">
    </script>
    
    
    
    
    
    
    <script src="./calendar/angular.min.js"></script>
    <link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
    <link rel="stylesheet" type="text/css"  href="./calendar/calendar.css" />

    <script  src="./calendar/moment.min.js"></script>
    <script type="text/javascript">
    moment.locale('kr', {
        months : "1월_2월_3월_4월_5월_6월_7월_8월_9월_10월_11월_12월".split("_"),
        monthsShort :  "1월_2월_3월_4월_5월_6월_7월_8월_9월_10월_11월_12월".split("_"),
        weekdays : "일요일_월요일_화요일_수요일_목요일_금요일_토요일".split("_"),
        weekdaysShort : "일._월._화._수._목._금_토.".split("_"),
        weekdaysMin :"일._월._화._수._목._금_토.".split("_"),
        ordinal : function (number) {
            return number + '일'
        }
    });
    </script>
    <script  src="./calendar/calendar.js"></script>
    
    
    
    
    
    
    <script>
        var date_sel = null;
        var cnt_sel = null;
        $(document).ready(function(){
            
            $('.calendar-date').click(function(){
                date_sel = null;
                cnt_sel = null;
                $('#cnt_box li').removeClass('selected_cnt_li');
                $('.calendar-date').removeClass('selected_date_td');
                $(this).addClass('selected_date_td');

                date_sel = $('.year-month').text()+"."+$(this).text();
                $('#date_sel_info span').html(date_sel);
                //$('#date_form').val(date_sel);
                $('#cnt_sel_info span').html(cnt_sel);
                //$('#cnt_form').val(cnt_sel);
                //alert(date_sel);
            })

            $('#cnt_box li').click(function(){
                if(date_sel!=null) {
                    $('#cnt_box li').removeClass('selected_cnt_li');
                    $(this).addClass('selected_cnt_li');

                    cnt_sel = $(this).children('.cnt').html();
                    $('#cnt_sel_info span').html($(this).text());
                    //$('#cnt_form').val(cnt_sel);
                    //alert(cnt_sel);
                }
            })
            
        })
        
        function next() {
            var stat = false;
            if(date_sel!=null && cnt_sel!=null) stat = true;
            if(stat==false) {
                alert("날짜/회차를 선택하세요");
            }else {
                //alert("선택한 날짜 : "+date_sel+"  /  선택한 회차 : "+cnt_sel);
                $('#date_form').val(date_sel);
                $('#cnt_form').val(cnt_sel);
                document.getElementById("dateCntForm").submit();
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
                                	 <div ng-controller="calendarWidget" onclick="test();">
									    <calendar selected="day" ></calendar>
									    <input type="hidden" value="{{day.format('YYYY-MM-DD')}}"/>
									</div>
                                </div>
                            </div>
                            <div id="ct_left_cnt">
                                <b>회차선택</b><hr>
                                <div id="cnt_box">
                                    <ul>
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
                                    </ul>
                                </div>
                            </div>
                            <div id="ct_left_seat">
                                <b>잔여석</b><hr>
                                <div id="seat_box">
                                    <ul>
                                        <li>
                                            <span class="seat_grade">일반석</span>
                                            <span class="available_seat">27</span>
                                            <span>석</span>
                                        </li>
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
                            <img src="../../img/poster/show-0619.png">
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
                            <form action="/reserveSeat.do" method="post" id="dateCntForm">
                            <input type="hidden" name="showNo" value=""/>
                            <input type="hidden" id="date_form" name="date_sel" />
                            <input type="hidden" id="cnt_form" name="cnt_sel" />
                            </form>
                        </div>
                    </div>
                    <div id="reserve_btn_only">
                        <a href="#" class="btn" onclick="next()">다음단계</a>
                    </div>

                </div>
            </div>
        </div>
    </div>

</body>
</html>