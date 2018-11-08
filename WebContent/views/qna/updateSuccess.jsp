<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<% String boardQ_no = request.getParameter("boardQ_no"); %>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<script>
	var boardQ_no = "<%=boardQ_no%>";
	alert("QNA 수정 완료");
	location.href="/qna.do?boardQ_no="+boardQ_no;
	

</script>

</body>
</html>




