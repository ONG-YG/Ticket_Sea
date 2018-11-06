<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ticket Sea || 로그인</title>
</head>
<body>


  <link rel=StyleSheet type="text/css" href="/css/member_login.css"> 

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

						<li class="menu_item"><span class="bar">|</span> <a href="#" class="menu_link" onclick="window.open('/views/member/pwdSearch.jsp','아이디찾기','width=430,height=510'); return false;">비밀번호 찾기</a></li>

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