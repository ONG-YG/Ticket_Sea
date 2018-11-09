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

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
</script>

<script>
$(function(){
	
	
	
	$.ajax({
		url : "/mainImg.do",
		type : "get",
		success : function(data){
			//JSONObject는 JSONObject.key 를 이용하여 value를 추출
			
			//배너 이미지
			$('#s_img_1').attr("src","/img/poster/"+data[1].poster+"");
			$('#s_img_2').attr("src","/img/poster/"+data[2].poster+"");
			$('#s_img_3').attr("src","/img/poster/"+data[3].poster+"");
			$('#s_img_4').attr("src","/img/poster/"+data[4].poster+"");
			
			$('#s_img_1_a').attr("href","/show.do?m_show_no="+data[1].no+"");
			$('#s_img_2_a').attr("href","/show.do?m_show_no="+data[2].no+"");
			$('#s_img_3_a').attr("href","/show.do?m_show_no="+data[3].no+"");
			$('#s_img_4_a').attr("href","/show.do?m_show_no="+data[4].no+"");
			
			//하단 Best 공연 및 전시
			//1
			$('#b_img_1').attr("src","/img/poster/"+data[1].poster+"");
			$('#b_a_1').attr("href","/show.do?m_show_no="+data[1].no+"");
			$('#b_strong_1').text(data[1].name);
			//2
			$('#b_img_2').attr("src","/img/poster/"+data[2].poster+"");
			$('#b_a_2').attr("href","/show.do?m_show_no="+data[2].no+"");
			$('#b_strong_2').text(data[2].name);
			//3
			$('#b_img_3').attr("src","/img/poster/"+data[3].poster+"");
			$('#b_a_3').attr("href","/show.do?m_show_no="+data[3].no+"");
			$('#b_strong_3').text(data[3].name);
			//4
			$('#b_img_4').attr("src","/img/poster/"+data[4].poster+"");
			$('#b_a_4').attr("href","/show.do?m_show_no="+data[4].no+"");
			$('#b_strong_4').text(data[4].name);
			//5
			$('#b_img_5').attr("src","/img/poster/"+data[5].poster+"");
			$('#b_a_5').attr("href","/show.do?m_show_no="+data[5].no+"");
			$('#b_strong_5').text(data[5].name);
			//6
			$('#b_img_6').attr("src","/img/poster/"+data[6].poster+"");
			$('#b_a_6').attr("href","/show.do?m_show_no="+data[6].no+"");
			$('#b_strong_6').text(data[6].name);
			//7
			$('#b_img_7').attr("src","/img/poster/"+data[7].poster+"");
			$('#b_a_7').attr("href","/show.do?m_show_no="+data[7].no+"");
			$('#b_strong_7').text(data[7].name);
			//8
			$('#b_img_8').attr("src","/img/poster/"+data[8].poster+"");
			$('#b_a_8').attr("href","/show.do?m_show_no="+data[8].no+"");
			$('#b_strong_8').text(data[8].name);
			//9
			$('#b_img_9').attr("src","/img/poster/"+data[9].poster+"");
			$('#b_a_9').attr("href","/show.do?m_show_no="+data[9].no+"");
			$('#b_strong_9').text(data[9].name);
			//10
			$('#b_img_10').attr("src","/img/poster/"+data[10].poster+"");
			$('#b_a_10').attr("href","/show.do?m_show_no="+data[10].no+"");
			$('#b_strong_10').text(data[10].name);
			//11
			$('#b_img_11').attr("src","/img/poster/"+data[11].poster+"");
			$('#b_a_11').attr("href","/show.do?m_show_no="+data[11].no+"");
			$('#b_strong_11').text(data[11].name);
			//12
			$('#b_img_12').attr("src","/img/poster/"+data[12].poster+"");
			$('#b_a_12').attr("href","/show.do?m_show_no="+data[12].no+"");
			$('#b_strong_12').text(data[12].name);
			
			//랭킹 리스트
			$('#r_img_1').attr("src","/img/poster/"+data[13].poster+"");
			$('#r_a_1').attr("href","/show.do?m_show_no="+data[13].no+"");
			
			$('#r_aa_1').text("1. "+data[13].name);
			$('#r_aa_1').attr("href","/show.do?m_show_no="+data[13].no+"");
			
			$('#r_aa_2').text("2. "+data[14].name);
			$('#r_aa_2').attr("href","/show.do?m_show_no="+data[14].no+"");
			
			$('#r_aa_3').text("3. "+data[15].name);
			$('#r_aa_3').attr("href","/show.do?m_show_no="+data[15].no+"");
			
			$('#r_aa_4').text("4. "+data[16].name);
			$('#r_aa_4').attr("href","/show.do?m_show_no="+data[16].no+"");
			
			$('#r_aa_5').text("5. "+data[17].name);
			$('#r_aa_5').attr("href","/show.do?m_show_no="+data[17].no+"");
			
			//최신 공연
			
			$('#l_img_1').attr("src","/img/poster/"+data[11].poster+"");
			$('#l_a_1').attr("href","/show.do?m_show_no="+data[11].no+"");
			
			$('#l_aa_1').text("1. "+data[11].name);
			$('#l_aa_1').attr("href","/show.do?m_show_no="+data[11].no+"");
			
			$('#l_aa_2').text("2. "+data[18].name);
			$('#l_aa_2').attr("href","/show.do?m_show_no="+data[18].no+"");
			
			$('#l_aa_3').text("3. "+data[19].name);
			$('#l_aa_3').attr("href","/show.do?m_show_no="+data[19].no+"");
			
			$('#l_aa_4').text("4. "+data[20].name);
			$('#l_aa_4').attr("href","/show.do?m_show_no="+data[20].no+"");
			
			$('#l_aa_5').text("5. "+data[21].name);
			$('#l_aa_5').attr("href","/show.do?m_show_no="+data[21].no+"");
			
			// 윙배너(왼쪽)
			$('#wl_img_1').attr("src","/img/poster/"+data[22].poster+"");
			$('#wl_img_1').attr("href","/show.do?m_show_no="+data[22].no+"");
			
			$('#wl_img_2').attr("src","/img/poster/"+data[23].poster+"");
			$('#wl_img_2').attr("href","/show.do?m_show_no="+data[23].no+"");
			
			$('#wl_img_3').attr("src","/img/poster/"+data[24].poster+"");
			$('#wl_img_3').attr("href","/show.do?m_show_no="+data[24].no+"");
			
			
			// 윙배너(오른쪽)
			$('#wr_img_1').attr("src","/img/poster/"+data[25].poster+"");
			$('#wr_a_1').attr("href","/show.do?m_show_no="+data[25].no+"");
			
			$('#wr_img_2').attr("src","/img/poster/"+data[26].poster+"");
			$('#wr_a_2').attr("href","/show.do?m_show_no="+data[26].no+"");
			
			
			
			
			
			
			
			
			
		},
		error : function(){console.log("ajax 통신 실패");}
	});
		
});
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
                       <a id=s_img_1_a href="#">
                           <img id="s_img_1" src="" width="990px" height="470px">
                       </a>
                    </li>
                    <li>
                       <a id=s_img_2_a href="#">
                           <img id="s_img_2" src="" width="990px" height="470px">
                       </a>
                    </li>
                    <li>
                       <a id=s_img_3_a href="#">
                           <img id="s_img_3" src="" width="990px" height="470px">
                       </a>
                    </li>
                    <li>
                       <a id=s_img_4_a href="#">
                           <img id="s_img_4" src="" width="990px" height="470px">
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
                    <h2>인기 순위</h2>
                </div>
                <div class="list">
                    <ul>
                        <li class="list_img">
                            <a id="r_a_1" href="#">
                                <img id="r_img_1" src="" width="178px" height="216px">
                            </a>
                        </li>
                        <!-- 이미지 -->
                        <li class="list_a">
                            <a id="r_aa_1" href="#">
                                1
                            </a>
                        </li>
                        <li class="list_a">
                            <a id="r_aa_2" href="#">
                                2
                            </a>
                        </li>
                        <li class="list_a">
                            <a id="r_aa_3" href="#">
                                3
                            </a>
                        </li>
                        <li class="list_a">
                            <a id="r_aa_4" href="#">
                                4
                            </a>
                        </li>
                        <li class="list_a">
                            <a id="r_aa_5" href="#">
                                5
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
                            <a id="l_a_1" href="#">
                                <img id="l_img_1" src="" width="178px" height="216px">
                            </a>
                        </li>
                        <!-- 이미지 -->
                        <li class="list_a">
                            <a id="l_aa_1" href="#">
                                1
                            </a>
                        </li>
                        <li class="list_a">
                            <a id="l_aa_2" href="#">
                                2
                            </a>
                        </li>
                        <li class="list_a">
                            <a id="l_aa_3" href="#">
                                3
                            </a>
                        </li>
                        <li class="list_a">
                            <a id="l_aa_4" href="#">
                                4
                            </a>
                        </li>
                        <li class="list_a">
                            <a id="l_aa_5" href="#">
                                5
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
                    <a id="b_a_1" href="#">
                        <p>
                            <img id="b_img_1" src="/img/main_1.jpg" width="234px" height="285">
                        </p>
                        <div>
                            <strong id="b_strong_1" class="mainStrong">청춘랜드 2018</strong>
                        </div>
                    </a>
                </li>
                <li>
                    <a id="b_a_2" href="#">
                        <p>
                            <img id="b_img_2" src="/img/main_1.jpg" width="234px" height="285">
                        </p>
                        <div>
                            <strong id="b_strong_2" class="mainStrong">청춘랜드 2018</strong>
                        </div>
                    </a>
                </li>
                <li>
                    <a id="b_a_3" href="#">
                        <p>
                            <img id="b_img_3" src="/img/main_1.jpg" width="234px" height="285">
                        </p>
                        <div>
                            <strong id="b_strong_3" class="mainStrong">청춘랜드 2018</strong>
                        </div>
                    </a>
                </li>
                <li>
                    <a id="b_a_4" href="#">
                        <p>
                            <img id="b_img_4" src="/img/main_1.jpg" width="234px" height="285">
                        </p>
                        <div>
                            <strong id="b_strong_4" class="mainStrong">청춘랜드 2018</strong>
                        </div>
                    </a>
                </li>
                <li>
                    <a id="b_a_5" href="#">
                        <p>
                            <img id="b_img_5" src="/img/main_1.jpg" width="234px" height="285">
                        </p>
                        <div>
                            <strong id="b_strong_5" class="mainStrong">청춘랜드 2018</strong>
                        </div>
                    </a>
                </li>
                <li>
                    <a id="b_a_6" href="#">
                        <p>
                            <img id="b_img_6" src="/img/main_1.jpg" width="234px" height="285">
                        </p>
                        <div>
                            <strong id="b_strong_6" class="mainStrong">청춘랜드 2018</strong>
                        </div>
                    </a>
                </li>
                <li>
                    <a id="b_a_7" href="#">
                        <p>
                            <img id="b_img_7" src="/img/main_1.jpg" width="234px" height="285">
                        </p>
                        <div>
                            <strong id="b_strong_7" class="mainStrong">청춘랜드 2018</strong>
                        </div>
                    </a>
                </li>
                <li>
                    <a id="b_a_8" href="#">
                        <p>
                            <img id="b_img_8" src="/img/main_1.jpg" width="234px" height="285">
                        </p>
                        <div>
                            <strong id="b_strong_8" class="mainStrong">청춘랜드 2018</strong>
                        </div>
                    </a>
                </li>
                <li>
                    <a id="b_a_9" href="#">
                        <p>
                            <img id="b_img_9" src="/img/main_1.jpg" width="234px" height="285">
                        </p>
                        <div>
                            <strong id="b_strong_9" class="mainStrong">청춘랜드 2018</strong>
                        </div>
                    </a>
                </li>
                <li>
                    <a id="b_a_10" href="#">
                        <p>
                            <img id="b_img_10" src="/img/main_1.jpg" width="234px" height="285">
                        </p>
                        <div>
                            <strong id="b_strong_10" class="mainStrong">청춘랜드 2018</strong>
                        </div>
                    </a>
                </li>
                <li>
                    <a id="b_a_11" href="#">
                        <p>
                            <img id="b_img_11" src="/img/main_1.jpg" width="234px" height="285">
                        </p>
                        <div>
                            <strong id="b_strong_11" class="mainStrong">청춘랜드 2018</strong>
                        </div>
                    </a>
                </li>
                <li>
                    <a id="b_a_12" href="#">
                        <p>
                            <img id="b_img_12" src="/img/main_1.jpg" width="234px" height="285">
                        </p>
                        <div>
                            <strong id="b_strong_12" class="mainStrong">청춘랜드 2018</strong>
                        </div>
                    </a>
                </li>
            </ul>
        </div>
        
        <div id="wingleft_b">
            <ul>
                <li>
                    <a id="wl_a_1" href="#">
                        <img id="wl_img_1" src="/img/98_170.jpg" width="98px" height="170px">
                    </a>
                </li>
                <li>
                    <a id="wl_a_1" href="#">
                        <img id="wl_img_2" src="/img/98_170.jpg" width="98px" height="170px">
                    </a>
                </li>
                <li>
                    <a id="wl_a_1" href="#">
                        <img id="wl_img_3" src="/img/98_170.jpg" width="98px" height="170px">
                    </a>
                </li>
            </ul>
        </div>
        
        <div id="wingright_b">
            <ul>
                <li>
                    <a id="wr_a_1" href="#">
                        <img id="wr_img_1" src="/img/98_125.jpg" width="98px" height="125px">
                    </a>
                </li>
                <li>
                    <a id="wr_a_1" href="#">
                        <img id="wr_img_2" src="/img/98_125.jpg" width="98px" height="125px">
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