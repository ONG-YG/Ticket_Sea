<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String [] dateCntSeat = (String [])request.getAttribute("dateCntSeat");
	String date_sel = dateCntSeat[0];
	String cnt_sel = dateCntSeat[1];
	String seat_sel = dateCntSeat[2];
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
        function prev() {
            var chk = confirm("이전 단계로 돌아가면 현재의 예매 정보를 잃게 됩니다.");
            if(chk) {
                location.href="./reserv_step_2_seat.html"
            }
        }
        function next() {
            var phone = document.getElementById("phone").value;
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
                location.href="#";
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
                                <td>주문자 이름</td>
                                <th>휴대폰 번호 <span class="color_red">*<span class="blind">필수입력란</span></span></th>
                                <td>
                                    <div class="input_block">
                                        <input type="text" class="input" id="phone" style="width:235px" maxlength="11" title="휴대폰 번호" number-text-box="">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th>이메일</th>
                                <td colspan="3">
                                    <div class="input_block">
                                        <input type="text" class="input" style="width:450px" title="이메일">
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
                            <img src="../../img/poster/show-0619.png">
                        </div>
                        <strong id="mini_show_title">공연이름 출력란</strong>
                    </div>
                    
                    <div class="reserve_result">
                        <div id="reserve_res_tit">예매 정보</div>
                        <hr>
                        <ul id="select_seat_grade" class="seat_lst">
                            <!--좌석등급 별 list-->
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

                        </ul>
                        <div id="reserve_info">
                            <div id="date_sel_info">
                                <strong>공연일</strong><span>2018.10.29 (월)</span>
                            </div>
                            <hr>
                            <div id="cnt_sel_info">
                                <strong>공연회차</strong><span>2회 13:00 ~14:30</span>
                            </div>
                            <hr>
                            <div id="ticket_price_info">
                                <strong>티켓금액</strong><span>140,000</span>
                            </div>
                            <hr>
                            <div id="commision_info">
                                <strong>예매수수료</strong><span>1,000</span>
                            </div>
                            <hr id="final_hr">
                            <div id="total_price_info">
                                <strong>총 결제</strong><span>141,000</span>
                            </div>
                        </div>
                    </div>
                    <div class="reserve_btn">
                        <a href="#" class="btn" onclick="prev()">이전단계</a>
                        <a href="#" class="btn" onclick="next()">다음단계</a>
                    </div>

                </div>
            </div>
        </div>
    </div>

</body>
</html>