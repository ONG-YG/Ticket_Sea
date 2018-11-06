<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script
  src="https://code.jquery.com/jquery-3.3.1.js"
  integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
  crossorigin="anonymous">
</script>
<style>
    /* 전체 사이즈 조정 */
    
    ul, li, a{list-style: none; margin: 0px; padding: 0px; text-decoration: none; color: black;}
    
    div{box-sizing: border-box;}
    #wrapper{overflow: hidden;min-width: 990px;min-height: 100%; border: 1px solid black;}
    #header{height: 193px; border: 1px solid black;}
    #container{height: 100%; border: 1px solid black; background: #f4f4f4;}
    #footer{height: 153px; border: 1px solid black;}
    
    /* 990px 고정 사이즈 */
    #h_inner{width: 990px; height: 100%; margin: 0px auto;}
    #c_inner{width: 990px; height: 3000px; margin: 0px auto; padding: 70px 0 250px 0px;}
    #f_inner{width: 990px; margin: 0px auto;}

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
        background: skyblue;
    }
    
    /* inner 공간 분할 */
    #c_inner_top{
        width: 100%;
        height: 100px;
        margin-bottom: 20px;
        background: white;
        border: 1px solid skyblue;
    }
    #c_inner_top .title1{
        width: 100px;
        float: left;
        height: 100px;
        width: 166px;
        text-align: center;
        background: skyblue;
    }
    #c_inner_top .title1 h2{
        margin: 0;
        padding-top: 30px;
        color: white;
    }
    #c_inner_top .title2{
        width: 60px;
        float: left;
        height: 100px;
    }
    
    #left_menu{
        width: 166px;
        float: left;
        height: 100%;
        display: block;
        border-bottom: 1px solid #dedede;
        border-top: 1px solid #dedede;
        border-left: 1px solid #dedede;
        box-sizing: border-box;
        margin: 0;
        padding-top: 20px;
        padding-left: 0;
        background: white;
    }
    #right_view{
        width: 990px;
        float: left;
        border: 1px solid #dedede;
        height: 100%;
        padding: 30px;
        background: white;
    }
    
   
    .border_box{
        display: block;
        width: 90px;
        padding: 3px 0 1px;
        border: 1px solid #d4d4d4;
        text-align: center;
        line-height: 21px;
    }
    
    #infoHeader{
        border : 1px solid #dedede;
        width : 100%;
        height : 25%;
    }
    
    #infoHeader > #i_H_title{
        width : 100%;
        height: 15%;
        padding : 40px;
        font-size : 30px;
    }
    
    #infoHeader > #i_H_pic{
        border : 1px solid #dedede;
        width : 50%;
        height: 80%;
        float : left;
        padding : 30px 50px 30px;
    }
    
    #infoHeader > #i_H_info{
        border : 1px solid #dedede;
        width : 50%;
        height: 80%;
        float : left;
        padding : 30px;
        font-size : 13px;
    }
    
    #infoContent {
        border : 1px solid #dedede;
        width : 100%;
        height : 75%;
        padding : 30px;
    }
    
    #infoContent > #i_C_category{
        border : 0px solid #dedede;
        width : 100%;
    }
    
    #i_C_category > #i_C_Category_a {
        border : 1px solid #dedede;
        background-color: ;
        width : 33.3333%;
        height : 100%;
        float : left;
        text-align: center;
        font-size: 18px;
        padding : 20px;
    }
    #i_C_category > #i_C_Category_b {
        border : 1px solid #dedede;
        width : 33.3333%;
        height : 100%;
        float : left;
        text-align: center;
        font-size: 18px;
        padding : 20px;
    }
    #i_C_category > #i_C_Category_c {
        border : 1px solid #dedede;
        width : 33.3333%;
        height : 100%;
        float : left;
        text-align: center;
        font-size: 18px;
        padding : 20px;
    }
    
    #infoContent > #i_C_content{
        border : 1px solid #dedede;
        width : 100%;
        padding : 30px;
        height: 95%;
    }
    
    
    
    .detail_info_tbl{
        border:1px solid #dedede; 
        width:100%;
        height:40%;
        background:#f2f2f0;
        font-size:12px;
        letter-spacing:-1px;
        color:#767676;
    }
    
    .review_list{border-width:2px 0 1px;border-style:solid;border-color:#999}
    
    body,input,textarea,select,button,table,p,div{font-family:'돋움',dotum,sans-serif;font-size:12px;color:#444}
    
    
    
    
    
    .review_list{border-width:2px 0 1px;border-style:solid;border-color:#999}
.review_list li{border-top:1px solid #e1e1e1;padding:22px 0 25px;line-height:18px;}
.review_list li:first-child{border:0}
.review_list li.no_data{height:153px;line-height:153px;text-align:center;}
<<<<<<< HEAD:WebContent/views/promo/promo.jsp
.review_list li .review_info{margin:-1px 0 7px -2px;overflow:hidden}
=======
.review_list li .review_info{margin:-15px 0 7px -40px;overflow:hidden}
>>>>>>> e7df2293a6343e300548246144f599d1fc495dba:WebContent/views/board/board_concertInfo.html
.review_list li .review_info:after{display:block;clear:both;content:''}
.review_list li .review_info .star_average{float:none}
.review_list li .review_info .star_average dt{display:block;visibility:hidden;overflow:hidden;position:absolute;top:-1000em;left:0}
.review_list li .review_info .star_average dd{height:12px;line-height:12px;padding-right:8px;letter-spacing:0;vertical-align:top;}
<<<<<<< HEAD:WebContent/views/promo/promo.jsp
.review_list li .review_info .star_average dd.review_user{font-size:12px;font-weight:bold;color:#444}
=======
.review_list li .review_info .star_average dd.review_user{font-size:12px;font-weight:bold;color:#444;}
>>>>>>> e7df2293a6343e300548246144f599d1fc495dba:WebContent/views/board/board_concertInfo.html
.review_list li .review_info .star_average dd.review_date{color:#999}
.review_list li .review_info .star_average dd.review_delete{height:15px;margin-top:-2px;}
.review_list .review_info a.delete{background-position:-327px -16px;width:16px;height:15px;vertical-align:top;}
/*.review_list+.paging{margin-top:30px}*/
.review_list li div.reply{overflow:hidden;background:#f0f0f0;padding:0 30px;margin:15px 0 7px}
.review_list li div.reply li{background:url(../img/bg_dotline.png) repeat-x -2px -2px;border:none;padding:23px 0 25px}
.review_list li div.reply li:first-child{background:none}
.review_list li div.reply li .review_info{background:url(../img/sp_theater.png) no-repeat -472px -110px;padding-left:20px;margin-bottom:11px}
.review_list li div.reply li .star_average{float:none}
.review_list li div.reply li p{padding-left:20px}
<<<<<<< HEAD:WebContent/views/promo/promo.jsp
=======
    
    
    
    
    
    
>>>>>>> e7df2293a6343e300548246144f599d1fc495dba:WebContent/views/board/board_concertInfo.html
    
</style>
</head>

<body>
<script>
	
    function reserve(){
        //alert("예매시작");
        var showNo = 20000; //////////////////////////////////////////////////// showNo 받아오는 코드 추가할 것
        //location.href="/dateCntSelect.do?showNo="+showNo;  /////////////////// showNo 받아오는 코드 추가할 것
        window.open("/dateCntSelect.do?showNo="+showNo, "reservePopUp", "width=1010, height=625");
        
        return false;
    }
</script>

<div id="wrapper">
    <div id="header">
        <div id="h_inner">
            <h1>TICKET SEA</h1>
        </div>
    </div>
    
    
    <div id="container">
        <div id="c_inner">
            <div id="c_inner_top">
                <div class="title1">
                    <h2>소규모 공연</h2>
                </div>
                <div class="title2"></div>
            </div>
           
            <div id="right_view">
                
                
                
                <div id="infoHeader">
                    <div id="i_H_title">
                        PUMA BLUE 첫 내한 공연 (풀 밴드셋) 
                    </div>
                    
                    <div id="i_H_pic">
                        <img src="../../img/poster_ex.jpg" style="width:350px; height:450px; ">
                    </div>
                    
                    <div id="i_H_info">
                        <div>
                        <strong><div id = "info_tit">부제</div></strong> 가을비 속 일그러진 네온사인, 우울함을 속삭이는 남부런던 출신의 재즈루키
                        </div>
                        <br>
                        <div>
                        <strong><div id = "info_tit">장소</div></strong>Pulp Seoul
                        </div>
                        <br>
                        <div>
                        <strong><div id = "info_tit">날짜</div></strong>2018.11.06 ~ 2018.11.06
                        </div>
                        <br>
                        <div>
                        <strong><div id = "info_tit">관람시간</div></strong>110분
                        </div>
                        <br>
                        <div>
                        <strong><div id = "info_tit">관람등급</div></strong>만 15세 이상
                        </div>
                        <br>
                        <div>
                        <strong><div id = "info_tit">가격</div></strong>50,000원
                        </div>
                        <br><br><br>
                        <input type="button" value="예매하기" style="width:250px; height:80px; background-color:skyblue; color:white; float:right;" onclick="return reserve()">
                        
                    </div>
                </div>
                
        <div id="infoContent">
                    <div id="i_C_category">
                        
                        <div id="i_C_Category_a" >
                            <button onclick="cateAclick();">상세정보</button>
                        </div>
                        
                        <div id="i_C_Category_b">
                            <button onclick="cateBclick();">공연장 정보</button>
                        </div>
                        
                        <div id="i_C_Category_c">
                            <button onclick="cateCclick();">공연 후기</button>
                        </div>
                    </div>
                    
            <style>
            
                
            </style>
                    
                    <div id="i_C_content_a">
                        
                          <img src="../../img/poster_ex.jpg" style="width:700px; height:850px; padding : 50px;">
                        
                        
                        
                        <table class="detail_info_tbl">
                            
                            <colgroup>
                                <col style="width: 122px">
                                <col style="width: 225px;">
                                <col style="width: 80px">
                                <col>
                            </colgroup>
                            <tbody>
                            <tr>
                                <th scope="row">주최/기획</th>
                                <td>FAKE VIRGIN ENT</td>
                                <th scope="row">고객문의</th>
                                <td></td>
                            </tr>
                            <tr>
                                <th scope="row">공연시간</th>
                                <td>110분</td>
                                <th scope="row">관람등급</th>
                                <td>만 15세 이상                                    </td>
                            </tr>
                            <tr>
                                <th scope="row">작가/출연자</th>
                                <td></td>
                                <th scope="row">공연장소</th>
                                <td>Pulp Seoul</td>
                            </tr>
                            <tr>
                                <th scope="row">예매수수료</th>
                                <td>1000원</td>
                                <th scope="row">배송료</th>
                                <td>현장수령</td>
                            </tr>
                            <tr>
                                <th scope="row">유효기간/이용조건</th>
                                <td colspan="3">2018.11.06 ~ 2018.11.06 이용 가능</td>
                            </tr>
                            <tr>
                                <th scope="row">예매취소조건</th>
                                <td colspan="3"><span style="color: rgb(118, 118, 118); letter-spacing: -1px; font-family: '돋움',dotum,sans-serif; font-size: 11px;">
                취소일자에 따라 아래와 같이 취소수수료가 부과됩니다. 예매일 기준보다 관람일 기준이 우선 적용됩니다.<br>
                단, 예매 당일 밤 12시 이전 취소 시에는 취소수수료가 없으며 예매수수료도 환불됩니다. (취소기한 내에 한함)<br><br>
                예매후 7일 이내 : 취소수수료 없음<br>
                예매후 8일 ~ 관람일 10일 전 : 뮤지컬/콘서트/클래식 등 공연 장당 4,000원, 연극/전시 등 입장권 장당 2,000원<br>
                (단, 최대 티켓금액의 10% 이내)<br>
                관람일 9일 전 ~ 7일 전 : 티켓금액의 10%<br>
                관람일 6일 전 ~ 3일 전 : 티켓금액의 20%<br>
                관람일 2일 전 ~ 1일 전 : 티켓금액의 30%<br>
                공연 취소 시 : 없음<br>
                </span>       
                                </td>
                            </tr>
                            <tr>
                                <th scope="row">취소환불방법</th>
                                <td colspan="3"><span style="color: rgb(118, 118, 118); letter-spacing: -1px; font-family: '돋움',dotum,sans-serif; font-size: 11px;">
                <strike>&#8203;</strike>'마이페이지 &gt; 예매/취소내역'에서 취소마감시간 이내에 취소할 수 있습니다. <br>
                단, 티켓이 배송된 이후에는 인터넷 취소가 불가하며 취소마감 시간 이전에 티켓이 아래 주소로 반송되어야 합니다.<br>
                - 주소 : 13487, 경기도 성남시 분당구 대왕판교로645번길 16 NHN엔터테인먼트 플레이뮤지엄<br>
                - 받는사람 : NHN 티켓링크 환불담당자<br>
                - 연락처: 1588-7890<br>
                취소수수료는 도착일자 기준으로 부과되며 배송료는 환불되지 않습니다.<br>
                </span>


                                </td>
                            </tr>
                            </tbody>
                        </table>
                                    </div>
                    <div id="i_C_content_b" style="display:none;">
                        <div id="map" style="width:500px;height:400px;"></div>
                        <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b8639cd941f4df4218630f1cf5bd7d14"></script>
                        
                        <script>
                            var container = document.getElementById('map');
                            var options = {
                                center: new daum.maps.LatLng(33.450701, 126.570667),
                                level: 3
                            };

                            var map = new daum.maps.Map(container, options);
	                   </script>
            
                        
                    </div>
            
            <style>
                #c_title{
                    height: 40px;
                    padding : 10px;
                    margin : 100px 0px 10px;
                    font-family : '돋움',dotum,san-serif;
                    color : #444;
                    font-size : 18px;
                    font-weight: bold;
                }
                
                #c_guide{
                    height : 80px;
                    padding : 10px;
                    margin : 0px 0px 0px;
                    font-size : 12px;
                    color:#999;
                }
                #c_text{
                    border : 1px solid black;
                    margin : 30px 0px 0px;
                    height : 70px;
                    background-color: #d5d5d5;
                }
                
                .write_review{
                    clear:both;
                    height:100px;
                    overflow:hidden;
                    padding-top:9px
                }
                
                .write_review textarea{
                    border:px solid #d4d4d4;
                    width:528px;
                    height: 200px;
                    padding:16px 18px 7px;
                    line-height:18px;
                    overflow-y:auto;
                    color:#999
                }
                .write_review_button{
                    margin-left:-5px;
                    *margin-top:1px;
                    color:red;
                    padding: 37px 0px 0px; 
                    margin:10px;
                    width:70px; height:50px; resize:none;
                    color:white;
                    background-color: skyblue;
                }
                
            </style>
            
                <div id="i_C_content_c" style="display:none;">
                    <div id="c_title">
                    네티즌 후기
                    </div>
                    
                    <div id="c_guide">
                    네티즌 후기 게시판에 티켓 양도와 매매성 등 글과 같이 게시판 성격에 벗어난 글을 게재할 경우 사전 통보 없이 삭제될 수 있습니다. <br>
                    티켓 양도 및 매매의 경우 전화번호, 이메일 등의 개인정보가 악용될 소지가 있으므로 게재를 삼가 주시기 바랍니다. <br>
                    운영 규정을 지속적으로 어기는 게시글을 게재할 경우 티켓링크 게시판 이용이 제한될 수 있습니다.
                    </div>
                    
                            <div class="write_review">
                                <textarea title="후기 작성하기" id="reviewContent" name="reviewContent"
                                          style="width:700px; height: 25px; resize:none; margin:0px; 0px; 0px;" maxlength="3000"
                                          placeholder="주민번호, 전화번호, 이메일 등 개인정보를 남기면 타인에 의해 악용될 소지가 있습니다."></textarea>
<<<<<<< HEAD:WebContent/views/promo/promo.jsp
                                <input type="button" style="width:70px; height:50px; resize:none;" value="후기작성">
=======
                                <input type="button" class="write_review_button"  value="후기작성">
>>>>>>> e7df2293a6343e300548246144f599d1fc495dba:WebContent/views/board/board_concertInfo.html
                            </div>

            
        <div class="review_list">
                    
                    
        <ul id="reviewUl" style="word-break: break-all;">
            
            <li>
                <div class="review_info">
                    <dl class="star_average">
                        <dt>별점</dt>
                        <dd class="grade_star">
                            <span class="star_gauge" style="width: 100%"></span>
                        </dd>
                        <dt>아이디</dt>
                        <dd class="review_user">jm***@ha</dd>
                        <dt>날짜</dt><dd class="review_date">2018.10.05 16:27</dd>
                    </dl>
                </div>
                재미있어요
            </li>
            
            <li>
                <div class="review_info">
                    <dl class="star_average">
                        <dt>별점</dt>
                        <dd class="grade_star">
                            <span class="star_gauge" style="width: 100%"></span>
                        </dd>
                        <dt>아이디</dt>
                        <dd class="review_user">jm***@ha</dd>
                        <dt>날짜</dt><dd class="review_date">2018.10.05 16:27</dd>
                    </dl>
                </div>
<<<<<<< HEAD:WebContent/views/promo/promo.jsp
                너무 재밌어
=======
                뮤지컬 삼총사를 재밌게 봐서 아이언 마스크도 관람했어요! 배우분들도 다 훌륭했지만 그 중 가장 좋았던 것은 검술 액션이였어요. 정말 긴장감있게 액션 연기를 잘하시더라구요ㅠㅠ 재관람하고 싶은 작품이에요!!
            </li>
            
            <li>
                <div class="review_info">
                    <dl class="star_average">
                        <dt>별점</dt>
                        <dd class="grade_star">
                            <span class="star_gauge" style="width: 100%"></span>
                        </dd>
                        <dt>아이디</dt>
                        <dd class="review_user">jm***@ha</dd>
                        <dt>날짜</dt><dd class="review_date">2018.10.05 16:27</dd>
                    </dl>
                </div>
                뮤지컬 삼총사를 재밌게 봐서 아이언 마스크도 관람했어요! 배우분들도 다 훌륭했지만 그 중 가장 좋았던 것은 검술 액션이였어요. 정말 긴장감있게 액션 연기를 잘하시더라구요ㅠㅠ 재관람하고 싶은 작품이에요!!
            </li>
            
            <li>
                <div class="review_info">
                    <dl class="star_average">
                        <dt>별점</dt>
                        <dd class="grade_star">
                            <span class="star_gauge" style="width: 100%"></span>
                        </dd>
                        <dt>아이디</dt>
                        <dd class="review_user">jm***@ha</dd>
                        <dt>날짜</dt><dd class="review_date">2018.10.05 16:27</dd>
                    </dl>
                </div>
                뮤지컬 삼총사를 재밌게 봐서 아이언 마스크도 관람했어요! 배우분들도 다 훌륭했지만 그 중 가장 좋았던 것은 검술 액션이였어요. 정말 긴장감있게 액션 연기를 잘하시더라구요ㅠㅠ 재관람하고 싶은 작품이에요!!
            </li>
            
            <li>
                <div class="review_info">
                    <dl class="star_average">
                        <dt>별점</dt>
                        <dd class="grade_star">
                            <span class="star_gauge" style="width: 100%"></span>
                        </dd>
                        <dt>아이디</dt>
                        <dd class="review_user">jm***@ha</dd>
                        <dt>날짜</dt><dd class="review_date">2018.10.05 16:27</dd>
                    </dl>
                </div>
                뮤지컬 삼총사를 재밌게 봐서 아이언 마스크도 관람했어요! 배우분들도 다 훌륭했지만 그 중 가장 좋았던 것은 검술 액션이였어요. 정말 긴장감있게 액션 연기를 잘하시더라구요ㅠㅠ 재관람하고 싶은 작품이에요!!
>>>>>>> e7df2293a6343e300548246144f599d1fc495dba:WebContent/views/board/board_concertInfo.html
            </li>
            
  
            
            </ul>
    </div>
            
            

                </div>

          </div>
                
                <script> // 공연 컨텐츠 카테고리 클릭 함수
                         icca = document.getElementById("i_C_content_a");
                         iccb = document.getElementById("i_C_content_b");
                         iccc = document.getElementById("i_C_content_c");
                    
                        function cateAclick(){
                            icca.style.display="block";
                            iccb.style.display="none";
                            iccc.style.display="none";
                            return false;
                        }
                        
                        function cateBclick(){
                            iccb.style.display="block";
                            icca.style.display="none";
                            iccc.style.display="none";
                            return false;
                        }
                    
                        function cateCclick(){
                            iccc.style.display="block";
                            icca.style.display="none";
                            iccb.style.display="none";
                            return false;
                        }
                    </script>

                            </div>



                        </div>
                    </div>

    
    <a href="#" id="back_to_top">Top</a>
    
    <div id="footer">
        <div id="f_inner">
            <strong class="footer_logo">TICKET SEA</strong>
        
            <div class="f_menu">
                <a href="#">사이트 소개</a><span>|</span>
                <a href="#">개인정보 처리방침</a><span>|</span>
                <a href="#">이용약관</a><span>|</span>
                <a href="#">고객센터</a><span>|</span>
                <a href="#">티켓판매안내</a><span>|</span>
                <a href="#">광고안내</a>
            </div>
        
            <p class="copy">Copyright © 옹가네 Corporation. All rights reserved.</p>
        </div>    
    </div>
    
</div>
</body>
</html>