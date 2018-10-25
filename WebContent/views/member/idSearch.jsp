<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ticket Sea || 아이디 찾기</title>
</head>
<body>


	<style>
		div {
			
		}
		#wrap{
			width: 420px;
			height: 100%;
		}
		#header {
			width: 100%;
			min-height: 100%;
			position: relative;
			line-height: 45px;
			text-align: center;
		}
		#header_logo{
			margin-left: 45px;
			margin-right: 45px;
			padding-top: 30px;
			padding-bottom: 30px;
		}
		.logo_area{
			width: 330px;
			height: 60px;
		}
		.logo_ticketsea{
			text-align: center;
			display: block;
			font-size: 30px;
			color:#41b40a;
		}
		.txt{
			text-align: center;
			font-size: 14px;
		}
		em{
			color: #41b40a;
		}
		legend{
			display: none;
		}
		fieldset{
			border: 0;
			margin-left: 30px;
			margin-right: 30px;
		}
		#container {
			width: 420px;
			height: 200px;
            position: relative;
		}
		#infoInputArea{
			width: 330px;
			height: 97px;
			padding-bottom: 10px;
		}
		#nameInput{
			display: block;
			float: left;
			width: 220px;
			height: 44px;
			padding-bottom: 10px;
		}
		#emailInput{
			display: block;
			width: 330px;
			height: 44px;
			padding-bottom: 10px;
		}
		#name{
			width: 180px;
			height: 18px;
			padding: 12px 10px;
			padding-top: 12px;
    		padding-right: 10px;
    		padding-bottom: 12px;
			padding-left: 10px;
		}
		#email{
			width: 307px;
			height: 18px;
			padding: 12px 10px;
			padding-top: 12px;
    		padding-right: 10px;
    		padding-bottom: 12px;
			padding-left: 10px;
		}
		.btn_area{
			width: 330px;
			height: 50px;
		}
		#loginBtn {
			background-color: #41b40a;
			color: white;
			width: 100%;
			height: 100%;
            
			
		}
		.inp_label{
			font-size: 12px;
			color: gray;
		}
		.footer_inner{
			width: 100%;
			bottom: 0;
			left: 0;
			right: 0;
			background: #f5f5f5;
			text-align: center;
			border: 1px solid #f5f5f5;
            
            
			
		}
		.menu_lst{
			list-style: none;
			width: 320px;
			height: 15px;
			padding-left: 75px;
			padding-right: 0px;
			
			
		}
		.menu_lst li{
			float: left;
			
		}
		.menu_lst li a{
			text-decoration: none;
			font-size: 12px;
			margin-right: 10px;
			margin-left: 10px;
			color: #222;
			
		}
		.bar{
			color: #ebebeb;
		}
		.ico_chk {
			width: 20px;
			height: 20px;
			top: 0;
			bottom: 0;
			left: 0;
			right: 0;
		}
        
        #container>p {
            text-align: center;
            color: red;
            font-size: 14px;
        }
		#container>span{
			font-size: 12px;
			color: darkgray;
			padding-left: 45px;
			
		}
		.btn_radio{
			background: white;
			border: 1px solid darkgray;
			box-sizing: padding-box;
			display: block;
			float: right;
			height: 33px;
			width: 53px;
			text-align: center;
			padding-top: 12px;
			
		}
		.radio_area{
			float:left;}
		
		.inp_label{
			font-size: 17px;
			text-align: center;
		}
	</style>

	
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
				<form id="frameIdSearch" action="" method="post">
					 <fieldset>
						<legend>아이디찾기</legend>
						 <!--id및 pw 입력란-->
						 <div id="infoInputArea">
						 	<span class="inp_txt" id="nameInput">
							<input id="name" name="name" type="text" maxlength="45" placeholder="이름"  value="" >
							</span>
							 
							 <!--남녀선택칸-->
							 
							 <div class="radio_area gender_area">
								
						<div class="btn_radio btn_radio_female" onclick="femaleCheck();">
							<label for="female" class="inp_label female" tabindex="0">여</label>
							<input type="radio" value="F" id="female" name="gender" hidden="" class="inp_radio" tabindex="-1" >
						</div>
						<div class="btn_radio is_checked btn_radio_male" onclick="maleCheck();">
							<label for="male" class="inp_label male" tabindex="0">남 </label>
							<input type="radio" value="M" id="male" name="gender"  hidden="" class="inp_radio" tabindex="-1" >
						</div>
								 
						
							</div>

							 <span class="inp_txt" id="emailInput">
							<input id="email" name="email" type="text" maxlength="100" placeholder="이메일입력"  value="" >
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
			male[0].style.color="red";
			maleborder[0].style.border="1px solid red";
			female[0].style.color="darkgray";
			femaleborder[0].style.border="1px solid darkgray";
			male[0].value="checked";
			
		}
		function femaleCheck(){
			var male = document.getElementsByClassName("male");
			var maleborder = document.getElementsByClassName("btn_radio_male");
			var female = document.getElementsByClassName("female");
			var femaleborder = document.getElementsByClassName("btn_radio_female");
			female[0].style.color="red";
			femaleborder[0].style.border="1px solid red";
			male[0].style.color="darkgray";
			maleborder[0].style.border="1px solid darkgray";
			male[0].value="checked";
		}
	</script>
</body>
</html>