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
					<span class="logo_ticketsea">Ticket Sea</span> <span class="txt">
						회원 정보에 등록된 정보를 입력해 주세요.</span>
				</h1>
			</div>
		</div>
		<!-- container -->
		<div id="container">

			<div id="content" class="content">
				<form id="framePwdSearch" action="/pwdSearch.do" method="post">
					<fieldset>


						<legend>비밀번호찾기</legend>
					

						<div id="infoInputArea">
							<span class="inp_txt" id="idInput"> <input id="id"
								name="userId" type="text" maxlength="45" placeholder="아이디"
								value="">
							</span> <span class="inp_txt" id="nameInput"> <input id="name"
								name="userName" type="text" maxlength="45" placeholder="이름"
								value="">
							</span>

							<!--남녀선택칸-->


							<span class="inp_txt" id="emailInput"> <input id="email"
								name="userEmail" type="text" maxlength="100"
								placeholder="이메일 입력" value="">
							</span> <span class="inp_txt" id="phoneInput"> <input id="phone"
								name="userPhone" type="text" maxlength="100"
								placeholder="폰 번호 입력 (-제외)" value="">
							</span>



						</div>



						<!--로그인 버튼-->
						<div class="btn_area">
							<button type="submit" id="loginBtn" onclick="return logError();">
								비밀번호 찾기</button>
						</div>
			</div>
			</fieldset>
			<br> <span>본인 인증 후 비밀번호를 찾을 수 있습니다.</span>


			</form>
		</div>
	</div>

	</div>


	<script>
		function logError() {
			userName = document.getElementById("name").value;
			userEmail = document.getElementById("email").value;
			userId = document.getElementById("id").value;
			userPhone = document.getElementById("phone").value;

			if (userId == "") {
				alert("아이디를 입력해주세요");
				return false;
			} else if (!(/^[a-z][a-z0-9]{3,11}$/.test(userId)))
			//아이디검사 아이디는 소문자, 숫자만 가능 4~12글자
			{
				alert("아이디는 소문자,숫자 4-12글자만 가능합니다.");
				return false;
				
			}else if ( userName == "")
			//이름 검사 한글만 통과 자음 안됨 ㅇㅋ?
			{
				alert("이름을 입력해주세요.");
				return false;
				
			} else if (!(/^[가-힣]+$/.test(userName)) || userName == "")
			//이름 검사 한글만 통과 자음 안됨 ㅇㅋ?
			{
				alert("이름은 한글만 가능합니다.");
				return false;
			} else if (userPhone == "") //폰번호 공백검사
			{
				alert("가입시 등록했던 핸드폰번호를 입력해주세요");
				return false;
			} 
			else if (!(/^[a-z0-9]{4,50}@/.test(userEmail)))
			// 이메일 검사 4~50자 이후 @ 확인

			{
				alert("이메일 주소를 입력해주세요");
				return false;
			}

			// 아이디 중복검사 체크
			else if (checkFlag == 0) {
				alert("ID 중복확인을 진행해주세요");
				return false;
			}

			else//모든 검사 만족시 true 반환
			{
				return true;
			}
			if (userName == "") {
				alert("이름를 입력해주세요.");
				return false;
			} else if (userEmail == "") {
				alert("이메일을 입력해주세요.");
				return false;
			} else//모든 검사 만족시 true 반환
			{
				return true;
			}

		}
	</script>

</body>
</html>