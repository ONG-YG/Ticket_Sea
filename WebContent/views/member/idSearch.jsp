<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ticket Sea || 아이디 찾기</title>

<link rel=StyleSheet type="text/css" href="/css/member_idSearch.css">

</head>
<body>
	
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
						 	<span class="inp_txt" id="nameInput">
							<input id="name" name="userName" type="text" maxlength="45" placeholder="이름"  value="" >

							</span>
							 
							 <!--남녀선택칸-->
							 
							 <div class="radio_area gender_area">
								
						<div class="btn_radio btn_radio_female" onclick="femaleCheck();">
							<label for="female" class="inp_label female" tabindex="0" onclick="femaleCheck();">여
							<input type="radio" value="F" id="female" name="gender"  class="inp_radio" tabindex="-1" ></label>
							
						</div>
						<div class="btn_radio is_checked btn_radio_male" onclick="maleCheck();">
							<label for="male" class="inp_label male" tabindex="0" onclick="maleCheck();">남 
							<input type="radio" value="M" id="male" name="gender"   class="inp_radio" tabindex="-1" ></label>
							
						</div>
								 
						
							</div>

							 <span class="inp_txt" id="emailInput">
							<input id="email" name="userEmail" type="text" maxlength="100" placeholder="이메일입력"  value="" >

							</span>
							 
						 </div>
						
						 	
						
						<!--로그인 버튼-->
						<div class="btn_area">
							<button type="submit" id="loginBtn" onclick="return logError();">

                               		 아이디 찾기</button>

						</div>

						 </div>
					 </fieldset>
					<br>
					<span>본인 인증을 한 아이디만 찾을 수 있습니다.</span>
				
					
				</form>
			</div>
		</div>
		
	</div>
	
	<script>
		function maleCheck(){
			var male = document.getElementsByClassName("male");
			var maleborder = document.getElementsByClassName("btn_radio_male");
			var female = document.getElementsByClassName("female");
			var femaleborder = document.getElementsByClassName("btn_radio_female");
			var maleInput = document.getElementById("male");
			var femaleInput = document.getElementById("female");
			male[0].style.color="#87ceeb";
			maleborder[0].style.border="1px solid #87ceeb";
			female[0].style.color="darkgray";
			femaleborder[0].style.border="1px solid darkgray";
			male[0].value="checked";

		}
		function femaleCheck(){
			var male = document.getElementsByClassName("male");
			var maleborder = document.getElementsByClassName("btn_radio_male");
			var female = document.getElementsByClassName("female");
			var femaleborder = document.getElementsByClassName("btn_radio_female");
			var maleInput = document.getElementById("male");
			var femaleInput = document.getElementById("female");
			female[0].style.color="#87ceeb";
			femaleborder[0].style.border="1px solid #87ceeb";
			male[0].style.color="darkgray";
			maleborder[0].style.border="1px solid darkgray";
			male[0].value="checked";
			

		}
	</script>
</body>
</html>