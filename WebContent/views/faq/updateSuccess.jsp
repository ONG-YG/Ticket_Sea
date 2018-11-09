<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<% String boardF_no = request.getParameter("boardF_no"); %>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<script>
	var boardF_no = "<%=boardF_no%>";
	alert("FAQ 수정 완료");
	location.href="/faq.do?boardF_no="+boardF_no;
	

</script>

</body>
</html>




