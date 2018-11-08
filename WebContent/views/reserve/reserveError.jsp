<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Ticket Sea 예매 오류</title>
    
    <!-- 외부 스타일 시트 적용 -->
    <link href="../../css/reserv_common.css" rel="stylesheet" type="text/css">
    <link href="../../css/reserv_error.css?ver=1" rel="stylesheet" type="text/css">
    
    <script
      type="text/javascript"
        src="../../resources/jquery-3.3.1.js">
    </script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
	</script>
    <script>
        function terminate() {
            window.close();
            if(!window.closed) {
                location.href="/";
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
                    <center>
                    <div class="reserve_step3">
                        
                        
                        <strong class="title"><span>오류가 발생했습니다.</span></strong><br>
                        <strong class="title2"><span>문제가 지속될 경우 관리자에게 문의해주세요.</span></strong>
                        
                        
                    <div class="white_box">
                        
                    </div>
                </div>
                	</center>
                <!--------------------------------------->
                <!--------------------------------------->
                <!--------------------------------------->
                </div>
                <div class="reserve_right">
                    <div class="reserve_result"></div>
                    <div id="reserve_btn_only">
                        <a class="btn" onclick="terminate()">닫 기</a>
                    </div>
                    
                </div>
            </div>
        </div>
    </div>

</body>
</html>