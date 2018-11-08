<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="kr.co.ticketsea.member.model.vo.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" type="text/css" href="/css/TicketSea_mainPage.css" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TICKET SEA</title>
<script
  src="https://code.jquery.com/jquery-3.3.1.js"
  integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
  crossorigin="anonymous">
</script>
</head>
<body>
<div id="wrapper">
	<div id="header_inner">
		<jsp:include page="/header.jsp"/>
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


<script>
	function btn(){
		window.open("/adShowPlace.do","_blank","width=1800, height=1500");
	}

</script>


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
    
    <jsp:include page="/top_btn.jsp"/>

    
    <div id="footer">
        <jsp:include page="/footer.jsp"/>
    </div>
    
</div>

</body>
</html>