<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="kr.co.ticketsea.search.model.vo.*"
	import="java.util.ArrayList"
	import="kr.co.ticketsea.member.model.vo.*"
 %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TiceketSea || 검색 결과</title>
<style>
	label{
		text-decoration:none;
		text-decoration-line: none;
		
	}
</style>

<%
	// Controller(Servlet)에서 보내준값 가져오기
	PageData pd = (PageData)request.getAttribute("pageData");
	String keyword = (String)request.getAttribute("keyword");

	ArrayList<Search> list = null;
	String pageNavi = null;
	
	if(pd!=null){
	 list = pd.getList(); // 현재 페이지의 글 목록
	 pageNavi = pd.getPageNavi(); // 현재 navi Bar
	}
	
%>
</head>
<body>
   <style>
   #header_inner
   {width:990px; margin: 0px auto;}
   
   label>a {
   text-decoration: none;
   color :#87ceeb;
   }
   </style> 
   
   
<div id="header_inner">
<jsp:include page="/header.jsp"/>
</div>
<center>
	<h1>'<%=keyword%>' 검색 결과 </h1>
	
	<%if(pd!=null){ %>
	<table border="1">
		<tr>
			<th>공연번호</th><th>공연이름</th><th>배우 이름</th><th>포스터경로</th><th>공연 시작일</th>
		</tr>
		<%for(Search s : list){ %>
			<tr>
				<td><%=s.getmShowNo()%></td>
				<td><%=s.getmShowName() %></td>
				<td><%=s.getmArtists() %></td>
				<td><%=s.getmShowPoster()%></td>
				<td><%=s.getmShowStDate()%></td>
			</tr>
		<%} %>
	
	</table>
	<div style="width:315px; text-align:center;">
		<label><%=pageNavi%></label>
	</div>
<%}else{ %>

	<h3>검색 결과가 없습니다.</h3>
<%} %>
	
	<form style="display:inline;" action="/musicalSearchList.do" method="get">
		<input type="text" name="search" value="<%=keyword%>"/>
		<input type="submit" value="검색"/>
	</form>
	
	
	
	
	
	

</center>


</body>
</html>