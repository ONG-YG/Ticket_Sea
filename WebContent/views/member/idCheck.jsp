<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="kr.co.ticketsea.member.model.service.*" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>아이디 중복확인창</title>
</head>
<body>

<% String checkId = request.getParameter("checkId");
	%>
<%
	boolean result = new MemberService().checkId(checkId);
	// 해당 ID가 이미 존재한다면 true 리턴
	// 해당 ID가 존재하지 않는다면 false 리턴
	
%>

	<script>
		var userId = '<%=checkId%>';
		var checkFlag = 0;
	
	<%
		if(result){
	%>
	
		// 참일때 동작 코드 (참일때 : 해당 ID가 중복일때)
			window.onload = function(){
				var msg = document.getElementById('msg');
				msg.innerHTML = '해당 ID는 중복ID입니다.';
				msg.style.color='red';
				userId="";
			}	
	
		<%}else{ %>
		// 거짓일때 동작 코드 (참일때 : 해당 ID가 중복이 아닐때)
			window.onload = function(){
				if((/^[a-z][a-z0-9]{3,11}$/.test(userId)))
					{
				var msg = document.getElementById('msg');
				msg.innerHTML = '해당 ID는 사용가능 ID입니다.';
				msg.style.color='blue';
				checkFlag = 1;
					}else{
						var msg = document.getElementById('msg');
						msg.innerHTML = '해당 ID는 사용불가 ID입니다.';
						msg.style.color='red';
					}
			}
	
	
	<%}%>

		function backBtn(){
			opener.document.getElementById('userId').value = userId;
			opener.document.getElementById('checkFlag').value = checkFlag;
			window.close(); //브라우저 종료 (내 자신 : 팝업창)
		}
		

</script>

<center>
<span id="msg"></span> <button onclick="backBtn();">확인</button>
</center>

</body>
</html>