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
	String progTime = rs.getProgTime();
	//System.out.println("progTime" + progTime);
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
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
	</script>
    <script>
	    var payType = null;/////////////////////////
		var customerPhone = null;///////////////////
		var customerEmail = null;///////////////////
	    
	    $(document).ready(function(){
	    	
	    	pageInit();
	    	
	    	$(document).change(function(){
	    		//console.log("mouseup");
	    		check();
	    	});
	    	
	    	$(document).keyup(function(){
	    		//console.log("keyup");
	    		check();
	    	});
	    	
	    	IMP.init('imp06112513');
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
		
		function check() {
			var phone = document.getElementById("inputPhoneNo").value;
			var userCheck = document.getElementById("agree_phone").checked;
			var userInfoAgree = document.getElementById("reserve_agree2").checked;
			
			var patt1 = new RegExp("^[0-9]+$");
		   	var patt2 = new RegExp("^[0-9]{9,20}$");
		   	var res1 = patt1.test( $("#inputPhoneNo").val());
		   	var res2 = patt2.test( $("#inputPhoneNo").val());
			
			
			if(phone!="" && userCheck && userInfoAgree && res1 && res2) {
				$('.reserve_btn>a').eq(1).css('background-color','skyblue');
                $('.reserve_btn>a').eq(1).css('color','white');
			}else {
				$('.reserve_btn>a').eq(1).css('background-color','white');
                $('.reserve_btn>a').eq(1).css('color','dodgerblue');
			}
			
		}
    	
        function prev() {
            var chk = confirm("이전 단계로 돌아가면 현재의 예매 정보를 잃게 됩니다.");
            if(chk) {
            	<%
	        	//세션에 담긴 reserveSession객체 - 진행단계 정보 update
				rs.setCurrStat(3); // currStat==3 이면 예매진행 중 정보 DELETE
            	//rs.setProgNo(-1);
            	rs.setProgTime(null);
        		session.setAttribute("reserveSession", rs);
        		%>
                location.href="/reserveSeat.do?psNo="+<%=psNo%>;
            }
        }
        function next() {
        	
        	// 예매 진행시간 10분 초과했으면 결제 불가 안내!
        	// 예매 진행 정보 지우고 창 close
        	var diff = null;
        	<%
	    		try {
	    			long progTimeL = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss").parse(progTime).getTime();
	    	%>

        	var progTimeL = <%=progTimeL%>;
        	
        	var timestamp = new Date();
        	//console.log("현재 시간 : "+timestamp);
        	var currentTimeL = timestamp.getTime();
        	
        	diff = (currentTimeL - progTimeL)/1000/60;
        	//console.log("경과시간(분) : "+diff);
        	
        	<%
	    		} catch (ParseException e) {
	    			e.printStackTrace();
	    		}
        	%>
        	
        	if(diff>10) {/////////////////////////////////////////////10분으로 바꿀것
        		deleteProgData();
        	}
        	else {
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
                   	else {
                   		//var payResult = payStart();/////////////////////////// 임시로 결제 단계 off
                   		var payResult = true;/////////////////////////////////// 임시로 결제 단계 off
                   		payType = "card";///////// 일단 임의로 카드결제로 설정(결제 function 다시 활성화할 경우 지울것 )
                   		customerPhone = $('#inputPhoneNo').val();////// 일단 임의로 카드결제로 설정(결제 function 다시 활성화할 경우 지울것 )
            			customerEmail = $('#inputEmail').val();//////// 일단 임의로 카드결제로 설정(결제 function 다시 활성화할 경우 지울것 )
                   		if(payResult) {
                   			//결제 성공할 경우 예매 정보 DB에 등록
                   			insertBookInfo();
                   		}
                   		else {
                   			//결제 실패할 경우 예매진행중 데이터를 삭제
        			        deleteProgData();
                   		}
                   	}
                }
        	}//if(diff>1) END
            
        }//function next() END
        
		function payStart(){
        	
			payType = "card";///////////////////////////////////////////일단 임의로 카드결제로 설정
			customerPhone = $('#inputPhoneNo').val();///////////////////
			customerEmail = $('#inputEmail').val();/////////////////////
    		
			<%-- 
        	var test = "merchant_uid : "+<%=bkNo%>+",\n"+
			    "name : "+'<%=showTitle%>'+" 예매,\n"+
			    "amount : "+<%=totalPrice%>+",\n"+
			    "buyer_email : "+customerEmail+",\n"+
			    "buyer_name : "+'<%=memberName%>'+",\n"+
			    "buyer_tel : "+customerPhone;
			alert(test);///////////////////////////////////////////////
			 --%>
			
			IMP.request_pay({
			    //pg : 'html5_inicis', //ActiveX 결제창은 inicis를 사용
			    pay_method : payType, //card(신용카드), trans(실시간계좌이체), vbank(가상계좌), phone(휴대폰소액결제)
			    merchant_uid : <%=bkNo%>, //상점에서 관리하시는 고유 주문번호를 전달
			    name : '<%=showTitle%>'+' 예매',
			    amount : <%=totalPrice%>,
			    buyer_email : customerEmail,
			    buyer_name : '<%=memberName%>',
			    buyer_tel : customerPhone //누락되면 이니시스 결제창에서 오류
			    
			}, function(rsp) {
				var payResult = null;
			    if ( rsp.success ) {
			    	
			    	alert("성공적으로 결제가 완료되었습니다.");///////////////////////////////////////////
			    	//insertBookInfo();
			    	payResult = true;
			    	
			    	<%-- 
			    	//[1] 서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid 전달하기
			    	jQuery.ajax({
			    		url: "/payments/complete", //cross-domain error가 발생하지 않도록 주의해주세요
			    		type: 'POST',
			    		dataType: 'json',
			    		data: {
				    		imp_uid : rsp.imp_uid
				    		//기타 필요한 데이터가 있으면 추가 전달
			    		}
			    	}).done(function(data) {
			    		//[2] 서버에서 REST API로 결제정보확인 및 서비스루틴이 정상적인 경우
			    		if ( everythings_fine ) {
			    			var msg = '결제가 완료되었습니다.';
			    			msg += '\n고유ID : ' + rsp.imp_uid;
			    			msg += '\n상점 거래ID : ' + rsp.merchant_uid;
			    			msg += '\n결제 금액 : ' + rsp.paid_amount;
			    			msg += '카드 승인번호 : ' + rsp.apply_num;
			    			
			    			alert(msg);
			    			
			    			// REST API로 아임포트 서버에서 결제 정상적으로 완료되었는지 확인 후 step4로 넘어가도록 아래 코드 주석 풀기
		               		//insertBookInfo();
		               		
		               		
			    		} else {
			    			//[3] 아직 제대로 결제가 되지 않았습니다.
			    			//[4] 결제된 금액이 요청한 금액과 달라 결제를 자동취소처리하였습니다.
			    		}
			    	});
			    	 --%>
			    	 
			    } else {
			    	
			        var msg = '결제에 실패하였습니다.';/////////////////
			        msg += '에러내용 : ' + rsp.error_msg;///////////
			        
			        alert(msg);//////////////////////////////////
			        
			        //결제 실패할 경우 예매진행중 데이터를 삭제
			        //deleteProgData();
			        payResult = false;
			    }
			    return payResult;
			});
			
		}//function payStart() END
		
		function insertBookInfo() {
			
			<%
			//세션에 reserveSession객체 저장
			rs.setBkNo(bkNo);
			session.setAttribute("reserveSession", rs);
			%>
			
			var bkStateCd = "RSV_CPL";///////////////////일단 임의로 완료상태로 설정
			
			//form태그에 예매자가 입력한 휴대폰번호, 이메일주소, 결제상태, 결제방식을 입력해줌
			$('#phone_form').val( customerPhone );
			$('#email_form').val( customerEmail );
			$('#bkStateCd_form').val( bkStateCd );
			$('#payType_form').val( payType );
			
			//////////////////////////////////////////////////////////////////////세션 메인페이지 용으로 바꿔줄 것
			
			//위에서 hidden type의 input태그에 값을 채워준 form태그를 reserveComplete서블릿에 submit
			//ReserveComplete서블릿 안에 예매진행중 데이터 지우는 과정 포함되어 있음 (deleteProgData()함수 호출 안해도됨)
			document.getElementById("completeSubmitForm").submit();
    		
		}//function insertBookInfo() END
		
		function deleteProgData() {
			var progNo_del = <%=progNo%>;
    		//console.log("progNo_del : "+progNo_del);
    		
    		$.ajax({
				url : "/reserveExpire.do",
				data : {progNo: progNo_del},
				type : "post",
				success : function(){
					//console.log("정상 처리 완료");
				},
				error : function(){
					//console.log("ajax 통신 에러");
				},
				complete : function(){
					alert("예매 진행 가능 시간이 초과되어 예매가 종료됩니다.");
					
					//////////////////////////////////////////////////////////세션 메인페이지용으로 바꿀것
					
					window.close();///////////////////////////////안되면 예매 시작단계(step1)로 이동하도록 수정해둠 >> 메인페이지로 넘어가도록 바꿀것
					if( !window.closed ) {
						location.href="/dateCntSelect.do?showNo=<%=showNo%>";
					}
				}
			});
		}//function deleteProgData() END
		
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
                                <strong>총 결제 금액</strong><span id="rInfo_TotP">141,000</span>
                            </div>
                        </div>
                        <form action="/reserveComplete.do?progNo=<%=progNo%>" method="post" id="completeSubmitForm">
                            <input type="hidden" id="psNo_form" name="psNo" value="<%= psNo %>"/>
                            <input type="hidden" id="phone_form" name="phone" />
                            <input type="hidden" id="email_form" name="email" />
                            <input type="hidden" id="bkStateCd_form" name="bkStateCd" />
                            <input type="hidden" id="payType_form" name="payType" />
                        </form>
                    </div>
                    <div class="reserve_btn">
                        <a class="btn" onclick="prev()">이전단계</a>
                        <a class="btn" onclick="next()">결제하기</a>
                    </div>

                </div>
            </div>
        </div>
    </div>

</body>
</html>