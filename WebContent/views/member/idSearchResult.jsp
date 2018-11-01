<%@page import="com.sun.org.apache.bcel.internal.classfile.Attribute"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
		span {	font-size="15px";
				 color="#87ceeb";}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ticket Sea | ID 찾기</title>
</head>
<body>



<center>
<br><br>
	<h2>아이디 찾기</h2>
	<br><Br>
	<h3>회원님의  아이디는 [<span><%= request.getAttribute("id")%></span>] 입니다.</h3><br>
	<button onclick="return returnLgn();">닫기</button>
	

</center>

<script>
	function returnLgn(){
		window.close();
	}
</script>


</body>
</html>