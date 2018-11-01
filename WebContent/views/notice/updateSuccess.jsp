<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<% String boardN_no = request.getParameter("boardN_no"); %>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<script>
	var boardN_no = "<%=boardN_no%>";
	alert("공지사항 수정 완료");
	location.href="/notice.do?boardN_no="+boardN_no;
	

</script>

</body>
</html>




