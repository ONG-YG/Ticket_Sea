<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="kr.co.ticketsea.member.model.vo.*" %>
    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
        
 <style>
 
.newRankPoster{ width:178px;
				height:216px;
					display:none;
			}
#newRankposter1{
		width:178px;
		height:216px;
		display:block;
		
}



 </style>
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

<script>

$(document).ready(function(){
 	//실시간 랭킹
	$.ajax({
		url : "/rankShow.do",
		type : "get",
		success :function(data){
			var data = data;
			var newPoster1 = data[0].poster;
			
			$("#rankSub1").html("1. "+data[0].subject);
			$("#rankSub2").html("2. "+data[1].subject);
			$("#rankSub3").html("3. "+data[2].subject);
			$("#rankSub4").html("4. "+data[3].subject);
			$("#rankSub5").html("5. "+data[4].subject);
			
			$("#rankPoster").attr("src","/img/poster/"+data[0].poster);
			
			
			//178 216
			$("#rankNo1").attr("href","/show.do?m_show_no="+data[0].no);
			$("#rankNo2").attr("href","/show.do?m_show_no="+data[1].no);
			$("#rankNo3").attr("href","/show.do?m_show_no="+data[2].no);
			$("#rankNo4").attr("href","/show.do?m_show_no="+data[3].no);
			$("#rankNo5").attr("href","/show.do?m_show_no="+data[4].no);
			
			$("#rankSub1").attr("href","/show.do?m_show_no="+data[0].no);
			$("#rankSub2").attr("href","/show.do?m_show_no="+data[1].no);
			$("#rankSub3").attr("href","/show.do?m_show_no="+data[2].no);
			$("#rankSub4").attr("href","/show.do?m_show_no="+data[3].no);
			$("#rankSub5").attr("href","/show.do?m_show_no="+data[4].no);
			
			
			
		},
		error : function(){
			console.log("ajax 통신 에러");
		}
		
		
	});
 	
	$.ajax({
		url : "/rankShow.do",
		type : "get",
		success :function(data){
			var data = data;
			var newPoster1 = data[0].poster;
			$("#rankPoster").attr("width","178px");
			$("#rankPoster").attr("height","216px");
			$("#rankSub1").hover(function(){
				$("#rankPoster").attr("src","/img/poster/"+data[0].poster);
				
			},function(){
				$("#rankPoster").attr("src","/img/poster/"+data[0].poster);
			});
			$("#rankSub2").hover(function(){
				$("#rankPoster").attr("src","/img/poster/"+data[1].poster);
			},function(){
				$("#rankPoster").attr("src","/img/poster/"+data[1].poster);	
			});
			$("#rankSub3").hover(function(){
				$("#rankPoster").attr("src","/img/poster/"+data[2].poster);
			},function(){
				$("#rankPoster").attr("src","/img/poster/"+data[2].poster);	
			});
			$("#rankSub4").hover(function(){
				$("#rankPoster").attr("src","/img/poster/"+data[3].poster);
			},function(){
				$("#rankPoster").attr("src","/img/poster/"+data[3].poster);	
			});
			$("#rankSub5").hover(function(){
				$("#rankPoster").attr("src","/img/poster/"+data[4].poster);
			},function(){
				$("#rankPoster").attr("src","/img/poster/"+data[4].poster);	
			});
			
			
		},
		error : function(){
			console.log("ajax 통신 에러");
		}
		
		
	});
	
	
	
	
	
	// 최신 랭킹
				$.ajax({
					url : "/newestShow.do",
					type : "get",
					success :function(data){
						var data = data;
						var newPoster1 = data[0].poster;
						
						$("#newRankSub1").html("1. "+data[0].subject);
						$("#newRankSub2").html("2. "+data[1].subject);
						$("#newRankSub3").html("3. "+data[2].subject);
						$("#newRankSub4").html("4. "+data[3].subject);
						$("#newRankSub5").html("5. "+data[4].subject);
						
						$("#newRankPoster").attr("src","/img/poster/"+data[0].poster);
						
						
						//178 216
						$("#newRankNo1").attr("href","/show.do?m_show_no="+data[0].no);
						$("#newRankNo2").attr("href","/show.do?m_show_no="+data[1].no);
						$("#newRankNo3").attr("href","/show.do?m_show_no="+data[2].no);
						$("#newRankNo4").attr("href","/show.do?m_show_no="+data[3].no);
						$("#newRankNo5").attr("href","/show.do?m_show_no="+data[4].no);
						
						$("#newRankSub1").attr("href","/show.do?m_show_no="+data[0].no);
						$("#newRankSub2").attr("href","/show.do?m_show_no="+data[1].no);
						$("#newRankSub3").attr("href","/show.do?m_show_no="+data[2].no);
						$("#newRankSub4").attr("href","/show.do?m_show_no="+data[3].no);
						$("#newRankSub5").attr("href","/show.do?m_show_no="+data[4].no);
						
						
						
					},
					error : function(){
						console.log("ajax 통신 에러");
					}
					
					
				});
				
				$.ajax({
					url : "/newestShow.do",
					type : "get",
					success :function(data){
						var data = data;
						var newPoster1 = data[0].poster;
						
						$("#newRankPoster").attr("width","178px");
						$("#newRankPoster").attr("height","216px");
						$("#newRankSub1").hover(function(){
							$("#newRankPoster").attr("src","/img/poster/"+data[0].poster);
						},function(){
							$("#newRankPoster").attr("src","/img/poster/"+data[0].poster);
						});
						$("#newRankSub2").hover(function(){
							$("#newRankPoster").attr("src","/img/poster/"+data[1].poster);
						},function(){
							$("#newRankPoster").attr("src","/img/poster/"+data[1].poster);	
						});
						$("#newRankSub3").hover(function(){
							$("#newRankPoster").attr("src","/img/poster/"+data[2].poster);
						},function(){
							$("#newRankPoster").attr("src","/img/poster/"+data[2].poster);	
						});
						$("#newRankSub4").hover(function(){
							$("#newRankPoster").attr("src","/img/poster/"+data[3].poster);
						},function(){
							$("#newRankPoster").attr("src","/img/poster/"+data[3].poster);	
						});
						$("#newRankSub5").hover(function(){
							$("#newRankPoster").attr("src","/img/poster/"+data[4].poster);
						},function(){
							$("#newRankPoster").attr("src","/img/poster/"+data[4].poster);	
						});
						
						
						
					},
					error : function(){
						console.log("ajax 통신 에러");
					}
					
					
				});
				
	});



	</script>
        <div id="list_center">
            <div id="rank_list">
                <div class="header">
                    <h2>실시간 랭킹</h2>
                </div>
                <div class="list">
                    <ul>
                        <li class="list_img">
                            <a href="#" id="rankNo">
                                <img src="" class="rankPoster" id="rankPoster">
                                 </a>
                           
                           
                        </li>
                        <!-- 이미지 -->
                        <li class="list_a">
                            <a href="" id="rankSub1">
                                1. 포항 BIG K-POP FESTIVAL
                            </a>
                        </li>
                        <li class="list_a">
                            <a href="" id="rankSub2">
                                2. 포항 BIG K-POP FESTIVAL
                            </a>
                        </li>
                        <li class="list_a">
                            <a href="" id="rankSub3">
                                3. 포항 BIG K-POP FESTIVAL
                            </a>
                        </li>
                        <li class="list_a">
                            <a href="" id="rankSub4">
                                4. 포항 BIG K-POP FESTIVAL
                            </a>
                        </li>
                        <li class="list_a">
                            <a href="" id="rankSub5">
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
                             <a href="#" id="newRankNo1">
                                <img src="" class="newRankPoster" id="newRankPoster">
                                 </a>
                         
                        </li>
                        <!-- 이미지 -->
                        <li class="list_a">
                            <a href="" id="newRankSub1">
                                1. 최신 랭킹
                            </a>
                        </li>
                        <li class="list_a">
                            <a href="" id="newRankSub2">
                                2. 최신 랭킹
                            </a>
                        </li>
                        <li class="list_a">
                            <a href="" id="newRankSub3">
                                3. 최신 랭킹
                            </a>
                        </li>
                        <li class="list_a">
                            <a href="" id="newRankSub4">
                                4. 최신 랭킹
                            </a>
                        </li>
                        <li class="list_a">
                            <a href="" id="newRankSub5">
                                5. 최신 랭킹
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
    
    <a href="#" id="back_to_top">Top</a>
    
    <div id="footer">
        <jsp:include page="/footer.jsp"/>
    </div>
    
</div>

</body>
</html>