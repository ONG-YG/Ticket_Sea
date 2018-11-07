<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="kr.co.ticketsea.mypage.model.vo.*" %>
    <%@ page import="java.util.ArrayList" %>
    
    <%
	// Controller(Servlet)에서 보내준값 가져오기
	ReservePageData pd = (ReservePageData)request.getAttribute("pd");

	ArrayList<PopupReserveList> pList = pd.getpList(); // 현재 페이지의 글 목록
	String pageNavi = pd.getPageNavi(); // 현재 navi Bar
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style>

a {
	color: black;
	text-decoration: none;
}

.r_table thead th {
	background: #f0f0f0;
	padding: 15px 0 15px 0;
	line-height: 11px;
	letter-spacing: -1px;
	color: grey; 
	
}

.r_table {
	width: 100%;
	border-spacing: 1;
}

.r_table td {
	text-align: center;
	border-top: 1px solid #ececec;
	color: dimgray;
	font-weight: bold;
	font-size: 14px; . paginate { padding-top : 20px;
	border-top: 1px solid #c7c7c7;
}

#paginate_inner {
	margin: 0 auto;
}
</style>

</head>
<body>
	<center>
		<h2>예매 내역 상세 보기</h2>
		<table style="text-align: center" class="r_table" >
			<thead>
				<tr>
					<th>번호</th>
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
				<%
					for (PopupReserveList prl : pList) {
				%>

				<tr>
					<td><%=prl.getNum()%></td>
					<td><%=prl.getTicketNo()%></td>
					<td><%=prl.getPsCnt()%></td>
					<td><%=prl.getmShowNo()%></td>
					<td><%=prl.getmShowName()%></td>
					<td><%=prl.getPsDate()%></td>
					<td><%=prl.getPsTime()%></td>
					<td><%=prl.getmShowRun()%>분</td>
					<td><%=prl.getTh1SeatGrd()%></td>
					<td><%=prl.getSeatTitle()%></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>

		<div class="paginate">
			<div id="paginate_inner" style="width: 315px; text-align: center;">
				<label><%=pageNavi%></label>
			</div>
		</div>
		
	</center>
</body>
</html>