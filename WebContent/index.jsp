<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="kr.co.ticketsea.member.model.vo.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TICKET SEA</title>
<script
  src="https://code.jquery.com/jquery-3.3.1.js"
  integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
  crossorigin="anonymous">
</script>
<style>
    /* 전체 사이즈 조정 */
    
    ul, li{list-style: none; display: block; text-decoration-line: none;}
    a{text-decoration: none;}
    
    div{box-sizing: border-box;}
    #wrapper{overflow: hidden;min-width: 990px;min-height: 100%; border: 1px solid black;}
    #header{height: 210px; border: 1px solid black;}
    #container{height: 100%; border: 1px solid black;}
    #footer{height: 153px; border: 1px solid black;}
    
    /* 헤더 관련 */
    #h_inner{width: 990px; height: 100%; margin: 0px auto;}
    
    #menu ul{padding: 0px; margin: 0px;}
    
    
    #header h5{
    	display: block;
    	float: left;
    }
    #header .topnav {
        background: #fafafa;
        border-bottom: 1px solid #f0f0f0;
        height: 20px;
        padding-top: 11px;
        box-sizing: padding-box;
    }
    #header .search_area {
        width: 70%;
		height: 64px ;
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
        line-height: 10px;
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
		font-size: 12px;
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
		height: 30px;;
		width: 230px;
		padding-left: 10px;
		font-size: 15px;
		font-weight: 600;
		border: 2px solid #41b40a;
		margin-right: 30px;
	}
	form{
		width: 180px;
		height: 100%;
	}
    /* 전체 이외 사이즈 */
    
    #main_banner{width: 990px; height: 450px; margin: 0px auto;}
    #list_center{width: 990px; height: 320px;margin: 0 auto;}
    #rank_list{width: 50%;height: 100%;float: left;}
    #new_list{width: 50%;height: 100%;float: left;}
    #inner{width: 990px;height: 100%; margin: 0px auto;}
    .f_inner{width: 990px;margin: 0px auto;}
    
    /* 랭킹 리스트 관련 사이즈*/
    #rank_list .list{height: 230px;}    
    #rank_list .list ul{
        height: 230px;
        padding: 0;
        margin: 0;
    }
    #rank_list .list .list_img{
        width: 180px;
        height: 218px;
        float: left;
        margin-top: 3px;
        
        border: 1px solid grey;
        box-sizing: border-box;
    }
    #rank_list .list .list_img a{height: 100%; display: block;}
    
    #rank_list .list .list_a{
        height: 40px;
        width: 230px;
        float: left;
        margin: 2px 0 3px 20px;
        
        border: 1px solid grey;
        box-sizing: border-box;
    }
    #rank_list .list .list_a a{color: #767676; padding: 8px 0 8px 0; display: block; text-decoration: none;}
    
    /* 최신 리스트 관련 사이즈*/
    #new_list .list{height: 230px;}
    
    #new_list .list ul{
        height: 230px;
        padding: 0;
        margin: 0;
    }
    #new_list .list .list_img{
        width: 180px;
        height: 218px;
        float: left;
        margin-top: 3px;
        
        border: 1px solid grey;
        box-sizing: border-box;
    }
    #new_list .list .list_img a{height: 100%; display: block;}
    #new_list .list .list_a{
        height: 40px;
        width: 230px;
        float: left;
        margin: 2px 0 3px 20px;
        
        border: 1px solid grey;
        box-sizing: border-box;
    }
    #new_list .list .list_a a{color: #767676; padding: 8px 0 8px 0; display: block; text-decoration: none;}
    
    
    
    
    /* 슬라이드 배너 관련 */

    #slide{height:450px;position:relative;overflow:hidden;}
	#slide ul{width:400%;height:100%;transition:1s;margin: 0px; padding: 0px;}
	#slide ul:after{content:"";display:block;clear:both;}
	#slide li{float:left;width:25%;height:100%;list-style-type: none;}
    
	#slide input{display:none;} /*인풋타입 제거*/
	#slide label{display:inline-block;vertical-align:middle;width:10px;height:10px;border:2px solid #666;background:#fff;transition:0.3s;border-radius:50%;cursor:pointer;}
	#slide .bCheck{text-align:center;position:absolute;bottom:10px;left:0;width:100%;text-align:center;}
    
	#bCheck1:checked~ul{margin-left:0%;}
	#bCheck2:checked~ul{margin-left:-100%;}
	#bCheck3:checked~ul{margin-left:-200%;}
	#bCheck4:checked~ul{margin-left:-300%;}
    
	#bCheck1:checked~.bCheck>label:nth-child(1){background:#666;}
	#bCheck2:checked~.bCheck>label:nth-child(2){background:#666;}
	#bCheck3:checked~.bCheck>label:nth-child(3){background:#666;}
	#bCheck4:checked~.bCheck>label:nth-child(4){background:#666;}
    
    /* Best 공연 및 전시 관련 */
    .best_list{
        margin-left: -18px;
        display: block;
        list-style: none;
        height: 1100px;
        padding: 0px;
    }
    .best_list li{
        padding-bottom: 3px;
        margin: 0 0 15px 18px;
        float: left;
    }

    
    /* 윙 배너 관련 */
    #wingleft_b{
        position: fixed;
        z-index: 1000;
        top: 50%;
        left: 50%;
        margin-left: -613px;
        border: 0;
        margin-top: -220px;
    }
    
    #wingleft_b ul{padding: 0px;}

    #wingright_b{
        position: fixed;
        z-index: 1000;
        top: 50%;
        left: 50%;
        margin-left: 513px;
        border: 0;
        margin-top: -220px;
    }
    .mainStrong{
        color: black;
    }
    
    
    
    /* top 버튼 */
    #back_to_top{
        display: block;
        position: fixed;
        top: 50%;
        right: 0;
        margin-top: -22px;
        background-position: -270px -123px;
        width: 44px;
        height: 44px;
        z-index: 1000;
        border: 1px solid red;
    }
</style>
</head>
<body>
<div id="wrapper">
    <div id="header">
    	<div id="h_inner">
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
							<a href="/reserveList.do" id="myPage">마이페이지</a><span>|</span>
							
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
    </div>  
    <div id="container">
        <div id="main_banner">
            <div id="slide">
                <input type="radio" name="bCheck" id="bCheck1" checked>
	            <input type="radio" name="bCheck" id="bCheck2">
	            <input type="radio" name="bCheck" id="bCheck3">
	            <input type="radio" name="bCheck" id="bCheck4">
	            <ul> 
                    <li>
                       <a href="#">
                           <img src="/img/ban2.jpg">
                       </a>
                    </li>
                    <li>
                       <a href="#">
                           <img src="/img/ban2.jpg">
                       </a>
                    </li>
                    <li>
                       <a href="#">
                           <img src="/img/ban3.jpg">
                       </a>
                    </li>
                    <li>
                       <a href="#">
                           <img src="/img/ban4.jpg">
                       </a>
                    </li>
	            </ul>
                
	            <p class="bCheck">
		            <label for="bCheck1"></label>
		            <label for="bCheck2"></label>
		            <label for="bCheck3"></label>
		            <label for="bCheck4"></label>
	            </p>
            </div>
        </div>
        
        <div id="list_center">
            <div id="rank_list">
                <div class="header">
                    <h2>실시간 랭킹</h2>
                </div>
                <div class="list">
                    <ul>
                        <li class="list_img">
                            <a href="#">
                                <img src="/img/178_216.jpg">
                            </a>
                        </li>
                        <!-- 이미지 -->
                        <li class="list_a">
                            <a href="#">
                                1. 포항 BIG K-POP FESTIVAL
                            </a>
                        </li>
                        <li class="list_a">
                            <a href="#">
                                2. 포항 BIG K-POP FESTIVAL
                            </a>
                        </li>
                        <li class="list_a">
                            <a href="#">
                                3. 포항 BIG K-POP FESTIVAL
                            </a>
                        </li>
                        <li class="list_a">
                            <a href="#">
                                4. 포항 BIG K-POP FESTIVAL
                            </a>
                        </li>
                        <li class="list_a">
                            <a href="#">
                                5. 포항 BIG K-POP FESTIVAL
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
            <div id="new_list">
                <div class="header">
                    <h2>최신공연</h2>
                </div>
                <div class="list">
                    <ul>
                        <li class="list_img">
                            <a href="#">
                                <img src="/img/178_216.jpg">
                            </a>
                        </li>
                        <!-- 이미지 -->
                        <li class="list_a">
                            <a href="#">
                                1. 포항 BIG K-POP FESTIVAL
                            </a>
                        </li>
                        <li class="list_a">
                            <a href="#">
                                2. 포항 BIG K-POP FESTIVAL
                            </a>
                        </li>
                        <li class="list_a">
                            <a href="#">
                                3. 포항 BIG K-POP FESTIVAL
                            </a>
                        </li>
                        <li class="list_a">
                            <a href="#">
                                4. 포항 BIG K-POP FESTIVAL
                            </a>
                        </li>
                        <li class="list_a">
                            <a href="#">
                                5. 포항 BIG K-POP FESTIVAL
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>

        <div id="inner">
            <h3>Best 공연 및 전시</h3>
            <ul class="best_list">
                <li>
                    <a href="#">
                        <p>
                            <img src="/img/main_1.jpg" width="234px" height="285">
                        </p>
                        <div>
                            <strong class="mainStrong">청춘랜드 2018</strong>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <p>
                            <img src="/img/main_2.jpg" width="234px" height="285">
                        </p>
                        <div>
                            <strong class="mainStrong">양방언 UTOPIA 2018</strong>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <p>
                            <img src="/img/main_3.jpg" width="234px" height="285">
                        </p>
                        <div>
                            <strong class="mainStrong">카즈미 타테이시 트리오</strong>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <p>
                            <img src="/img/main_4.jpg" width="234px" height="285">
                        </p>
                        <div>
                            <strong class="mainStrong">첫 단독 내한 공연</strong>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <p>
                            <img src="/img/main_5.jpg" width="234px" height="285">
                        </p>
                        <div>
                            <strong class="mainStrong">공연제목 5</strong>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <p>
                            <img src="/img/main_6.jpg" width="234px" height="285">
                        </p>
                        <div>
                            <strong class="mainStrong">공연제목 6</strong>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <p>
                            <img src="/img/main_7.jpg" width="234px" height="285">
                        </p>
                        <div>
                            <strong class="mainStrong">공연제목 7</strong>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <p>
                            <img src="/img/main_8.jpg" width="234px" height="285">
                        </p>
                        <div>
                            <strong class="mainStrong">공연제목 8</strong>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <p>
                            <img src="/img/main_9.jpg" width="234px" height="285">
                        </p>
                        <div>
                            <strong class="mainStrong">공연제목 9</strong>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <p>
                            <img src="/img/main_10.jpg" width="234px" height="285">
                        </p>
                        <div>
                            <strong class="mainStrong">공연제목 10</strong>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <p>
                            <img src="/img/main_11.jpg" width="234px" height="285">
                        </p>
                        <div>
                            <strong class="mainStrong">공연제목 11</strong>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <p>
                            <img src="/img/main_12.jpg" width="234px" height="285">
                        </p>
                        <div>
                            <strong class="mainStrong">공연제목 12</strong>
                        </div>
                    </a>
                </li>
            </ul>
        </div>
        
        <div id="wingleft_b">
            <ul>
                <li>
                    <a href="#">
                        <img src="/img/98_170.jpg" width="98px" height="170px">
                    </a>
                </li>
                <li>
                    <a href="#">
                        <img src="/img/98_170.jpg" width="98px" height="170px">
                    </a>
                </li>
                <li>
                    <a href="#">
                        <img src="/img/98_170.jpg" width="98px" height="170px">
                    </a>
                </li>
            </ul>
        </div>
        
        <div id="wingright_b">
            <ul>
                <li>
                    <a href="#">
                        <img src="/img/98_125.jpg" width="98px" height="125px">
                    </a>
                </li>
                <li>
                    <a href="#">
                        <img src="/img/98_125.jpg" width="98px" height="125px">
                    </a>
                </li>
            </ul>
        </div>
    </div>
    
    <a href="#" id="back_to_top">Top</a>
    
    <div id="footer">
    <div class="f_inner">
        <strong class="footer_logo">TICKET SEA</strong>
        
        <div class="f_menu">
            <a href="#">사이트 소개</a><span>|</span>
            <a href="#">고객센터</a><span>|</span>
            <a href="#">티켓판매안내</a><span></span>
        </div>
        
        <p class="copy">Copyright © 옹가네 Corporation. All rights reserved.</p>
    </div>    
    </div>
    
</div>


</body>
</html>