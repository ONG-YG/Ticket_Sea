<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ticket Sea || 로그인</title>
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
		#idInputArea{
			width: 330px;
			height: 97px;
			padding-bottom: 10px;
		}
		#idInput{
			display: block;
			position: relative;
			
			width: 330px;
			height: 44px;
			padding-bottom: 10px;
		}
		#pwdInput{
			display: block;
			width: 330px;
			height: 44px;
			padding-bottom: 10px;
		}
		#id{
			width: 307px;
			height: 18px;
			padding: 12px 10px;
			padding-top: 12px;
    		padding-right: 10px;
    		padding-bottom: 12px;
			padding-left: 10px;
		}
		#pwd{
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
	</style>
</head>
<body>

	<div id="wrap" class="wrap">
		<!-- header -->
		<div id="header">
			<div id="header_logo">
			<h1 class="logo_area">
					<span class="logo_ticketsea">Ticket Sea</span>
		        	<span class="txt"> 로그인하여 <em>Ticket Sea</em> 서비스를 이용합니다.</span>
				</h1>
			</div>
		</div>
		<!-- container -->
		<div id="container">
            
			<div id="content" class="content">
				<form id="frameLogin" action="/login.do" method="post">
					 <fieldset>
						<legend>로그인</legend>
						 <!--id및 pw 입력란-->
						 <div id="idInputArea">
						 	<span class="inp_txt" id="idInput">
							<input id="id" name="userId" type="text" maxlength="45" placeholder="아이디"  value="" >
							</span>
							 <span class="inp_txt" id="pwdInput">
							<input id="pwd" name="userPwd" type="password" maxlength="15" placeholder="비밀번호"  value="" >
							</span>
							 
						 </div>
						
						 	
						 
						
							
						<!--로그인 버튼-->
						<div class="btn_area">
							<button type="submit" id="loginBtn" onclick="return logError();">
                                로그인</button>
						</div>

						 </div>
					 </fieldset>
					
				
					
				</form>
			</div>
		</div>
		<!-- footer -->
		<div class="footer_menu">
	       	<div class="footer_inner">
	        		<ul class="menu_lst">
						<li class="menu_item"><a href=""  onclick="window.open('/views/member/idSearch.jsp','아이디찾기','width=430,height=430'); return false;"
							>아이디 찾기</a></li>
						<li class="menu_item"><span class="bar">|</span> <a href="#" class="menu_link">비밀번호 찾기</a></li>
						<li class="menu_item"><span class="bar">|</span><a href="#" onclick="window.open('/views/member/memberJoin.jsp','회원가입','width=430,height=750'); return false;" 
												class="menu_link" >회원 가입</a></li>
					</ul>
              </div>
       	</div>
	</div>
	
	<script>
		function logError(){
			var userId = document.getElementById("id").value;
			var userPwd = document.getElementById("pwd").value;
			
			if(userId=="")
				{
					alert("아이디를 입력해주세요凸");
					return false;
				}else if(userPwd=="")
				{
					alert("비밀번호를 입력해주세요凸");
					return false;
				}else
					{return true;}
		}
	</script>
</body>
</html>