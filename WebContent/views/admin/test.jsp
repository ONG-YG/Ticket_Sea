<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "kr.co.ticketsea.admin.show.model.vo.*" 
	import = "java.util.ArrayList"
%>

<%
	ArrayList<ShowPlace> spList = (ArrayList<ShowPlace>)request.getAttribute("showPlaceList");
	ArrayList<ShowCategory> scList = (ArrayList<ShowCategory>)request.getAttribute("showCTGList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
     ul, li, a{list-style: none; margin: 0px; padding: 0px;     text-decoration: none; color: black;}
    div{
        box-sizing : border-box;
    }
    #wrapper{overflow: hidden;width:1650px; height:1300px; border:1px solid black;}
    #header{ height:60px; background-color:;}
    #h_inner{width: 990px; height: 80%; margin: 5px auto; float:left; padding-left:20px;}
    #content-wrapper{height:1300px; border:1px solid black;}
    #c_inner{width: 100%; height: 100%; margin: 0px auto; padding: 0px;}
    .main-sidebar{
        height:100%;
        width: 15%;
        float:left;
        padding:0px;
        background-color:#004C63;
    }
    /*사이드바 서브메뉴 숨기기*/
    ul ul{display: none; width:100%;}
    ul li:hover>ul{display:block;}
    
    #side-menu ul>li{
        height:20%; padding-top:15px; padding-left:15px;padding-bottom: 15px;
        background-color:#004C63;
    }
    #side-menu ul>li li{
        background-color:#004554;
        
    }
    #side-menu ul>li>a{
        color:white;
        font-weight: 600;
    }
    .main_content{
        width:83%;
        height:100%;
        float:right;
        padding:0px;
        padding-right: 10px;
        border:1px solid red;
    }
    .main_content>h1{
        margin:0;
    }
    #icon{
        max-width:20%; max-height:80%; align-content: center;
    }
/*    #content-wrapper li ul{
        background-color: azure;
        display : none; 평상시에 서브메뉴 안보이게 하기
        height: auto;
        width:200px;
    }*/
    
    /*회원관리 페이지*/
    #content{
        overflow: hidden;
        border:1px solid black;
        border-style: dashed;
        padding: 20px 0;
        width:85%;
        height:1300px;
        display: block;
        position:relative;
        
    }
    .content_wrap{
        width:980px;
        height: 100%;
        margin:0 auto;
        border:1px solid black;
        box-sizing: border-box;
        display: inline-block;
        position:absolute;
        left:4%;
    }
    h2.main_title{
        display: inline-block;
        font-size: 23px;
        font-weight: normal;
    }
    #content .top_area{
        height:5%;
        border-bottom: 1px solid #d8d8d8;
    }
    
    /*회원테이블*/
    .memberTbl{
            border:1px solid #dedede;
            border-right:0;
            font-size:15px;
            margin:40px auto 12px;
            line-height:35px;
            width:900px;
            text-align: center;
            
        }
    
    .memberTbl tr:nth-child(2n){
        background-color: #F6F6F6;
        
    }
    .content_wrap .searchArea{
        height:10%;
        padding: 30px;
    }
    
    /* 버튼 스타일  */
	
</style>

</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

       <div class="content-header">
              <div id="content">
              
        <div class="content_wrap">
            <div class="top_area">
                <h2 class="main_title">공연등록</h2>
            </div>
            <!-- form 태그 -->
       	<form action="/showInsert.do" method="post">
            <div class="main_area">
                <div class="left_wrap">
                <div class="input_area">
                    <!--이미지영역-->
                    <div class="event_img_area">
                        <img class="show_post" src="../../img/ticketsea_poster.png" data-default-src="../../img/ticketsea_poster.png" alt="공연포스터" style="width:160px; height:160px;">
                        <!--이미지 업로드 버튼-->
                        <div class="upload_btn">
                            <input type="file" class="imgupload" title="공연포스터업로드" value="이미지업로드">
                            <button type="button" class="btn_change_img">사진변경</button>
                            <!--파일 업로드 후 사진 삭제하기 버튼 생김-->
                            <!--<button type="button" class="btn_delete_upload">삭제하기</button>-->
                        </div>
                    </div>
                </div>
                </div>
                
                <div class="right_wrap">
                    <!--카테고리 / 공연명-->
                    <fieldset class="edit_title">
                        <legend>
                            <h3 class="title">카테고리/공연명</h3>
                        </legend>
                        <div class="edit">
                            <div class="write_wrap">
                                <select class="category_select" id="category" name="sc_code">
                                    <%for(ShowCategory sc : scList) {%>
                                    <option value="<%=sc.getSc_code()%>"><%=sc.getSc_name()%></option>
                            		<%} %>
                                </select>
                                <input id="title" type="text" name="show_name" class="title_input" placeholder="공연명을 입력해 주세요." value="" maxlength="64" autofocus="autofocus">
                            </div>
                        </div>
                    </fieldset>
                    <fieldset class="edit_date">
                        <legend>
                          <h3 class="title">공연기간</h3>
                        </legend>
                        <div class="edit">
                        <!--시작 일시-->
                        <div class="start_time" id="edit_date_start_time">
                            <div class="write_wrap">
                                <input type="text" id="startEventDate" name="show_st_date" value="" placeholder="시작일"> ~ <input type="text" id="endEventDate" name="show_ed_date" value="" placeholder="종료일">
                            </div>
                        </div>
                        </div>
                    </fieldset>
                    <fieldset class="edit_place">
                        <legend>
                          <h3 class="title">공연장소</h3>
                        </legend>
                        <div class="edit">
                           <select id="place" class="place_select" name="">
                           <%for(ShowPlace sp : spList) {%>
                                    <option value="<%=sp.getTh_no()%>"><%=sp.getTh_name()%></option>
                            <%} %>
                          </select>
                        </div>
                    </fieldset>
                    <fieldset class="edit_artist">
                        <legend>
                          <h3 class="title">출연자</h3>
                        </legend>
                        <div class="edit">
                            <div class="write_wrap">
                                <input type="text" id="artist" name="artists" value="" placeholder="출연자정보 (,로 구분)">
                            </div>
                        </div>
                    </fieldset>
                    <fieldset class="edit_grade">
                        <legend>
                          <h3 class="title">관람등급</h3>
                        </legend>
                        <div class="edit">
                            <div class="write_wrap">
                                <input type="text" id="grade" name="show_grd" value="" placeholder="">
                            </div>
                        </div>
                        
                    </fieldset>
                     <fieldset class="edit_time">
                        <legend>
                          <h3 class="title">관람시간</h3>
                        </legend>
                        <div class="edit">
                            <div class="write_wrap">
                                <input type="text" id="runTime" name="show_run" value="" placeholder="">
                            </div>
                        </div>
                    </fieldset>
                     <fieldset class="edit_price">
                        <legend>
                          <h3 class="title">가격</h3>
                        </legend>
                        <div class="edit">
                            <div class="write_wrap">
                                <input type="text" id="price" name="price" value="" placeholder="">
                            </div>
                        </div>
                    </fieldset>
                    <fieldset class="edit_detailInfo">
                        <legend>
                          <h3 class="title">세부정보</h3>
                        </legend>
                        <div class="edit">
                            <div class="write_wrap">
                            <!-- form 태그 안에 form   -->
                                 <!-- <form action="http://localhost/insert.html" method="post" enctype="multipart/form-data">
                                	<input type="file">
                                <input type="submit">
                                </form> -->
                            </div>
                        </div>
                    </fieldset>
                </div>
                
            </div>
	            <div class="submit_area">
	            <input type="submit" value="작성" style="float:right;" onclick="return showCheck();" width="70px" height="40px">
	            </div>
            </form>
        	</div>
    	</div>
          </div> 
</body>
</html>