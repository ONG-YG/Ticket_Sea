<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
        <%@ page import = "kr.co.ticketsea.promo.model.vo.*" %>
    <%@ page import = "java.util.*" %>
    
    <%
	// Controller(Servlet)에서 보내준값 가져오기
	Show sh = (Show)request.getAttribute("show");

	ArrayList<Show> list = new ArrayList<Show> ();
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% for (Show s : list) { %>
                        <tr>
                          d
                           <td><a href="/show.do?show_no=<%=s.getShow_no()%>"><%=s.getShow_title()%></a></td>
                        </tr>
                        <% } %> 
</body>
</html>