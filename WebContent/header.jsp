<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="kr.co.ticketsea.member.model.vo.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ticket Sea || 티켓씨</title>
  <style>

		#menu ul{padding: 0px; margin: 0px;}

        #header{
            width: 990px;
            height: 230px;
            box-sizing: padding-box;
        }
        #header .topnav {
            position: relative;
            background: #fafafa;
            border-bottom: 1px solid #f0f0f0;
            height: 21px;
            padding-top: 11px;
            box-sizing: padding-box;
        }
        #header .search_area {
        width: 70%;
		height: 43px;
        margin: 32px auto;
        position: relative;
        box-sizing: padding-box;
	   
        }
        
        .inner {
        width: 100%;
            margin: 0 auto;
            position: relative;
			text-align: right;
			box-sizing: padding-box;
        }
       
		#header .search_area .search {
    
    width: 400px;
    height: 40px;
	position: relative;
    
	float: left;
	
		box-sizing: padding-box;
	
		}
		#header .search_area>h1>a{
            display: inline;
			position:relative;
			float: left;
			padding-right: 100px;
			text-decoration: none;
			box-sizing: padding-box;
			text-align: center;
		}
        #menu {
            width: 100%;
            height: 64px;
            box-sizing: padding-box;
			padding: 0px;	
        }
		#menu ul{
			width: 992px;
			height: 66px;
		}
		#menu ul li {
			list-style: none;
			float: left;
			line-height: 60px;
			vertical-align: middle;
			text-align: center;
			background: #41b40a;
		}
		
		.menulink{
			text-decoration: none;
			color: white;
			display: block;
			width: 141px;
			font-size: 16px;
			font-weight: bold;
			box-sizing: padding-box;
			
		}
		legend{display: none;}
		fieldset{
			border: 0;
			padding: 0px;
			height: 100%;
			width: 400px;;
		}
		#searchline{
			height: 35px;;
			width: 230px;
			padding-left: 10px;
			font-size: 15px;
			font-weight: 600;
			border: 2px solid #41b40a;
            float: left;			
		}
		form{
			width: 180px;
			height: 100%;
		}
		
        #searchIcon{
            height: 41px;
        }
       
      
        
    </style>

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
							<a href="#" id="myPage">마이페이지</a>
							<a href="#" id="reserve">예매확인/취소</a><span>|</span>
							
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
					<a href="/TicketSea_MainPage.html" class="logo">Ticket Sea</a>
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
       
        <!--git test-->
    </div>


</body>
</html>