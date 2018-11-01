<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="kr.co.ticketsea.member.model.vo.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ticket Sea || 티켓씨</title>
  <link rel=StyleSheet type="text/css" href="/css/header.css"> 

</head>
<body>
   <!--wrap div-->
    <div id="wrap">
        <!--header div-->
        <div id="header">
            <!--top navi div-->
            <div class="topnav">
                <div class="inner">
                    <div class="login">
                        <div id="login" style="">
                        	<% //로그인 세션
								session = request.getSession(false);
								Member member = (Member)session.getAttribute("member");	
								if(member!=null){ //로그인 성공시
							%>
							<a href="/logout.do" id="logout">로그아웃</a>
							<a href="/reserveList.do" id="myPage">마이페이지</a>
							<a href="/reserveList.do" id="reserve">예매확인/취소</a><span>|</span>
							
							<%}else{ %>
							<a href="#"  class="btn login" 
							onclick="window.open('/views/member/login.jsp','로그인','width=430,height=440'); return false;"  id="loginBtn">로그인</a>
							 <a href="#" id="joinBtn"
                   			 onclick="window.open('/views/member/memberJoin.jsp','회원가입','width=430,height=750'); return false;">회원가입</a><span>|</span> 
							<% } %>
							
							<a href="#" >고객센터</a>
						</div>
                    </div>
                </div>
            </div>
            <!--search_area div-->
            <div class="search_area">
				<!--logo div-->
				<h1>
					<a href="/index.jsp" class="logo">Ticket Sea</a>
				</h1>
				<div class="search">
					
					<form name="searchForm" action="" method="GET">
                <fieldset>
                    <legend>검색창</legend>
                    <input type="text" id="searchline" name="query" title="검색어 입력" value="">
                    <a href="#" class="btn_search"><img id="searchIcon"  src="/img/searchicon.PNG" width="41px"
                    ></a>
                </fieldset>
                        
            		</form>
        		</div>
				  
			</div>
            <!--menu div-->
            <div id="menu">
				<ul>
					<li><a class="menulink">&nbsp;</a></li>
					<li><a class="menulink" href="#">뮤지컬</a></li>
					<li><a class="menulink" href="#">콘서트</a></li>
					<li><a class="menulink" href="#">소규모공연</a>		</li>
					<li><a class="menulink" href="#">랭킹</a></li>
					<li><a class="menulink" href="#">고객센터</a>		</li>
					<li><a class="menulink">&nbsp;</a></li>
				</ul>
			</div>
        </div>
       
       
    </div>


</body>
</html>