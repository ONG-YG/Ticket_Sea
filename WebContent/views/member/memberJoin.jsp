<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel=StyleSheet type="text/css" href="/css/member_join.css">

</head>

<body>


	<!-- wrap-->
	<div class="wrap">
		<!--header-->
		<div class="header">
			<h4>
				<span>Ticket Sea</span>
				</h1>
		</div>
		<div class="contents">

			<h4>
				<span>Ticket Sea 가입을 환영합니다.</span>
				</h3>
				<p>Ticket Sea 서비스는 회원가입후 이용할 수 있습니다.</p>
		</div>

		<form id="frameJoin" action="/memberJoin.do" method="post">

			<input type="text" name="userId" maxlength="45" title="아이디"
				placeholder="아이디" id="userId">

			<button onclick="return idCheck();" id="idCheckBtn">ID중복확인</button>
			<br> <input type="hidden" id="checkFlag" value=0 />

			<p id="id_msg" class="error_txt" style="display: none;">아이디를
				입력해주세요(영문[소문자]+숫자 4~12자)</p>

			<input type="password" name="userPwd" maxlength="15" id="userPwd"
				title="비밀번호" placeholder="비밀번호(영문,숫자8-15자)">
			<p id="pwd_msg" class="error_txt" style="display: none;">비밀번호를
				입력해주세요(영문,숫자 8-15자)</p>
			<input type="password" value="" name="userPwd_re" maxlength="15"
				id="userPwd_re" title="비밀번호" placeholder="비밀번호 재입력">
			<p id="pwd_re_msg" class="error_txt" style="display: none;">비밀번호를
				확인해주세요</p>
			<input type="text" value="" name="userName" maxlength="10"
				id="userName" title="이름" placeholder="이름">
			<!--남녀선택칸-->

			<div class="radio_area gender_area">

				<div class="btn_radio btn_radio_female" onclick="femaleCheck();">
					<label for="female" class="inp_label female">여</label> <input
						type="radio" value="F" id="female" name="gender" hidden=""
						class="inp_radio">
				</div>
				<div class="btn_radio is_checked btn_radio_male"
					onclick="maleCheck();">
					<label for="male" class="inp_label male">남 </label> <input
						type="radio" value="M" id="male" name="gender" hidden=""
						class="inp_radio" selected>

				</div>


			</div>
			<p id="name_msg" class="error_txt" style="display: none;">이름을
				입력해주세요(한글)</p>
			<input type="text" value="" name="userPhone" maxlength="11"
				title="전화번호" placeholder="전화번호('-'하이픈 제외)" id="userPhone">
			<p id="phone_msg" class="error_txt" style="display: none;"
				maxlenth="11">전화번호를 입력해주세요('-'하이픈 제외)</p>
			<input type="text" value="" name="userAddress" maxlength="45"
				id="userAddr" title="주소" placeholder="주소">
			<p id="addr_msg" class="error_txt" style="display: none;">주소를
				입력해주세요</p>
			<input type="text" value="" name="userEmail" maxlength="45"
				id="userEmail" title="이메일" placeholder="이메일 주소">
			<p id="email_msg" class="error_txt" style="display: none;">이메일을
				입력해주세요</p>

			<input id="submitBtn" type="submit" value="회원가입" align="center"
				onclick="return check();">
		</form>
		<p>Ticket Sea 회원이 되시면 페이지의 다양한 서비스를 이용하실수 있습니다.</p>


	</div>

	<script>

	// 아이디 중복검사
	function idCheck() {
		var userId = document.getElementById("userId").value;
		window.open("/views/member/idCheck.jsp?checkId=" + userId,
				"_blank", "width=500px,height=100px");

		return false;
	}
		window.onload = function() {

		}//유효성 검사

		function check(){
            userId= document.getElementById("userId").value;
			userPwd= document.getElementById("userPwd").value;
			userPwd_re=document.getElementById("userPwd_re").value;
			userName= document.getElementById("userName").value;
			userPhone= document.getElementById("userPhone").value;
			userAddr= document.getElementById("userAddr").value;
			userEmail= document.getElementById("userEmail").value;

			 checkFlag = document.getElementById('checkFlag').value;

			if(userId=="")
            {
			     document.getElementById("id_msg").style.display="block";
				return false;
			}else if(!(/^[a-z][a-z0-9]{3,11}$/.test(userId)))
				//아이디검사 아이디는 소문자, 숫자만 가능 4~12글자
				{
					document.getElementById("id_msg").style.display="block";
					return false;
     
		} else if (userPwd == "") {
			document.getElementById("pwd_msg").style.display = "block";
			return false;
		}

            else if(!(/^[a-z0-9][a-z0-9]{7,14}$/i.test(userPwd)))
                //패스워드 검사 비밀번호는 영,숫 포함 8~15자
                {
                    document.getElementById("pwd_msg").style.display="block";
					return false;
                }
            else if(userPwd!=userPwd_re)
                //패스워드가 같지 않다면 false
            {
                document.getElementById("pwd_re_msg").style.display="block";
				return false;
<<<<<<< HEAD
            } 
=======
            } else if (userName == "") {
				document.getElementById("name_msg").style.display = "block";
				return false;
			}
>>>>>>> ts/hwi
            else if(!(/^[가-힣]+$/.test(userName))||userName=="")
                //이름 검사 한글만 통과 자음 안됨 ㅇㅋ?
            {
                document.getElementById("name_msg").style.display="block";
				return false;
            }
            else if(userPhone=="") //폰번호 공백검사
                {   
                     document.getElementById("phone_msg").style.display="block";
                    return false;
                }
            else if(userAddr=="") // 주소 공백검사
                {
                    document.getElementById("addr_msg").style.display="block";
                    return false;
                }

            else if(!(/^[a-z0-9]{4,50}@/.test(userEmail))||
                   userEmail=="")
                // 이메일 검사 4~50자 이후 @ 확인

			{
                     document.getElementById("email_msg").style.display="block";
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
			
		}


		// 남녀 체크
		function maleCheck() {
			var maleRadio = document.getElementById("male");
			var femaleRadio = document.getElementById("female");
			var male = document.getElementsByClassName("male");
			var maleborder = document.getElementsByClassName("btn_radio_male");
			var female = document.getElementsByClassName("female");
			var femaleborder = document
					.getElementsByClassName("btn_radio_female");

			male[0].style.color = "#87ceeb";
			maleborder[0].style.border = "1px solid #87ceeb";
			female[0].style.color = "darkgray";
			femaleborder[0].style.border = "1px solid darkgray";

		}
		// 성별 여자로 클릭시

		function femaleCheck() {
			var maleRadio = document.getElementById("male");
			var femaleRadio = document.getElementById("female");
			var male = document.getElementsByClassName("male");
			var maleborder = document.getElementsByClassName("btn_radio_male");
			var female = document.getElementsByClassName("female");
			var femaleborder = document
					.getElementsByClassName("btn_radio_female");
			female[0].style.color = "#87ceeb";
			femaleborder[0].style.border = "1px solid #87ceeb";
			male[0].style.color = "darkgray";
			maleborder[0].style.border = "1px solid darkgray";

		}

	</script>
</body>
</html>