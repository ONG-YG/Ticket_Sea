<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ticket Sea || 비밀번호 찾기</title>
</head>
<body>
 <link rel=StyleSheet type="text/css" href="/css/member_pwdSearch.css"> 
	
	
	
	<div id="wrap" class="wrap">
		<!-- header -->
		<div id="header">
			<div id="header_logo">
			<h1 class="logo_area">
					<span class="logo_ticketsea">Ticket Sea</span>
		        	<span class="txt"> 회원 정보에 등록된 정보를 입력해 주세요.</span>
				</h1>
			</div>
		</div>
		<!-- container -->
		<div id="container">
            
			<div id="content" class="content">
				<form id="frameIdSearch" action="/idSearch.do" method="post">
					 <fieldset>
						
						 	
						<legend>아이디찾기</legend>
						 <!--id및 pw 입력란-->
						 
						 <div id="infoInputArea">
							 <span class="inp_txt" id="idInput">
							<input id="id" name="userId" type="text" maxlength="45" placeholder="아이디"  value="" >
							</span>
							 
						 	<span class="inp_txt" id="nameInput">
							<input id="name" name="userName" type="text" maxlength="45" placeholder="이름"  value="" >
							</span>
							 
							 <!--남녀선택칸-->
							 
							 
							 <span class="inp_txt" id="emailInput">
							<input id="email" name="userEmail" type="text" maxlength="100" placeholder="이메일 입력"  value="" >
							</span>
							 
							 <span class="inp_txt" id="phoneInput">
							<input id="phone" name="userPhone" type="text" maxlength="100" placeholder="폰 번호 입력 (-제외)"  value="" >
							</span>
							 
							 
							 
						 </div>
						
						 	
						
						<!--로그인 버튼-->
						<div class="btn_area">
							<button type="submit" id="loginBtn" onclick="return logError();">
                               		 비밀번호 찾기</button>
						</div>

						 </div>
					 </fieldset>
					<br>
					<span>본인 인증 후 비밀번호를 찾을 수 있습니다.</span>
				
					
				</form>
			</div>
		</div>
		
	</div>

</body>
</html>