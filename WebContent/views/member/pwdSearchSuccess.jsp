<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="kr.co.ticketsea.member.model.vo.PwdMember" %>
    <% PwdMember pm= new PwdMember(); %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	h1{
		color:#87ceeb;
	}
	span{
		font-size:15px;
		color:#87ceeb;
	}
</style>
</head>
<body>


<%	pm=(PwdMember)(request.getAttribute("pm"));%>
<center>
<br><br>
<h1>Ticket Sea</h1>
<span><b><%= pm.getUserId() %></b></span> 님의 비밀번호가 <br>
[ <span><b><%= pm.getRanPwd() %></b></span> ]로 변경되었습니다.<br>
로그인 후 비밀번호를 변경해주시기 바랍니다.<br><br>
<button onclick="return pwdReturn">닫기</button>

</center>

<script>
	function pwdReturn(){
		window.close();
	}
</script>
</body>
</html>