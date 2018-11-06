<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="kr.co.ticketsea.mypage.model.vo.*" %>
    <%@ page import="java.util.ArrayList" %>
    
    <%
	// Controller(Servlet)에서 보내준값 가져오기
	ReservePageData pd = (ReservePageData)request.getAttribute("pd");

	ArrayList<PopupReserveList> pList = pd.getpList(); // 현재 페이지의 글 목록
	System.out.println(pList);
	String pageNavi = pd.getPageNavi(); // 현재 navi Bar
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>No</th>
				<th>티켓 번호</th>
				<th>공연번호</th>
				<th>공연회차</th>
				<th>공연명</th>
				<th>공연일</th>
				<th>공연시간</th>
				<th>관람 런타임</th>
				<th>좌석 등급</th>
				<th>좌석 번호</th>
			</tr>
		</thead>
		<tbody>
			<%	for(PopupReserveList prl: pList){ %>

			<tr>
				<td><%=prl.getNum()%></td>
				<td><%=prl.getTicketNo() %></td>
				<td><%=prl.getPsCnt() %></td>
				<td><%=prl.getmShowNo() %></td>
				<td><%=prl.getmShowName() %></td>
				<td><%=prl.getPsDate() %></td>
				<td><%=prl.getPsTime() %></td>
				<td><%=prl.getmShowRun() %></td>				
				<td><%=prl.getTh1SeatGrd() %></td>
				<td><%=prl.getSeatTitle() %></td>
			</tr>
			<%} %>
		</tbody>
	</table>
</body>
</html>