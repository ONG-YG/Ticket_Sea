<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="kr.co.ticketsea.admin.show.model.vo.*"%>
<% 
	Show show = (Show)request.getAttribute("showData");
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>공연 정보 </title>
<!-- 스타일  -->
<style>
       ul, li, a{list-style: none; margin: 0px; padding: 0px;     text-decoration: none; color: black;}
    div{
        box-sizing : border-box;
    }
    #wrapper{overflow: hidden;width:1250px; height:2000px; border:1px solid black;}
    #header{ height:60px; background-color:;}
    #h_inner{width: 990px; height: 80%; margin: 5px auto; float:left; padding-left:20px;}
    #content-wrapper{height:1500px; border:1px solid black;}
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
    .content-header{
        width:83%;
        height:100%;
        float:right;
        padding:0px;
        padding-right: 10px;
        border:1px solid red;
    }
    .content-header>h1{
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
    
    /*공연등록 페이지*/
    div{
        box-sizing: border-box;
        display: block;
    }
    #content{
        overflow: hidden;
        border:1px solid black;
        border-style: dashed;
        padding: 20px 0;
        width:1000px;
        height:1300px;
        display: block;
    }
    .content_wrap{
        width:980px;
        height: 100%;
        margin:0 auto;
        border:1px solid black;
        box-sizing: border-box;
        
    }
    
    .content_wrap form{
    	width:980px;
        height: 80%;
         z-index:-10;
    }
    h2.main_title{
        display: inline-block;
        font-size: 23px;
        font-weight: normal;
    }
    #content .main_area{
        padding:30px 10px;
        border:1px solid #d8d8d8;
        background-color:white;
        box-sizing: border-box;
        display: block;
        height:90%;
        
    }
    #content .top_area{
        height:5%;
        border-bottom: 1px solid #d8d8d8;
    }
    #content .submit_area{
        height:3%;
        border:1px solid red;
        display: block;
        padding-right:20px;
        padding-top:5px;
    }
    .event_img_area{
        float:left;
        width:160px;
        height:160px;
        padding-right: 30px;
        overflow: hidden;
        border:1px solid #d8d8d8;
        display: block;
    }
    .event_img_area .show_post{
        width:100%;
        height:160px;
        height: auto;
        
    }
    .event_img_area .upload_btn{
        top:50%;
        left:50%;
        padding:0;
        display: none;
        transform: translate(-50%, -50%);
    }
    .left_wrap{
        display: inline-block;
        width:200px;
        border:1px solid black;
        float:left;
        padding:0px;
    }
    .right_wrap{
        display: inline-block;
        width:750px;
        border:1px solid black;
        float:right;
        padding-right: 20px;
    }
    fieldset {
        padding: 20px 0 16px;
        width: 100%;
        display: block;
        border:none;
        border-bottom: 1px dashed black;
    }
    fieldset:first-child {
        padding-top: 0;
    }
    fieldset:last-child{
        border:none;
    }
    .right_wrap legend {
         margin-right: 0;
        padding-left: 5px;
    }
    fieldset legend {
        float: left;
        width: 130px;
        padding-top: 6px;
    }
    .edit_title h3.title {
    font-size: 16px;
    font-weight: normal;
    white-space: pre-line;
    }
    .edit{
        box-sizing:border-box;
        display: inline-block;
        height:50%;
        padding-top:18px;
    }
    
    .right_wrap .edit{
        width:590px;
    }
    .edit_title .category_select {
        width: 120px;
        margin-right: 4px;
        height:30px;
    }
    input[type="text"], input[type="password"] {
        padding: 0 9px;
        height: 30px; 
    }
    .edit_title .title_input {
        width: 300px;
    }
    
    .right_wrap h3.title {
        font-size: 16px;
        font-weight: normal;
        white-space: pre-line;
    }
    .edit_date .start_time{
        float:left;
        width:50%;
        vertical-align: top;
    }
    .edit_date input[type="text"], .edit_apply_date input[type="text"] {
    width: 110px;
    margin-right: 4px;
    }
    .edit_artist input[type="text"]{
        width:300px;
    }
    .edit_place input[type="text"]{
        width:300px;
        padding: 0 9px;
    }
    #place_search input[type="submit"]{
            width: 42px;
            height: 30px;
            line-height: 28px;
            display: inline-block;
            margin-left: -2px;
            margin-right: 3px;
            font-size: 11px;
            vertical-align: middle;

    }
</style>
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<div id="h_inner">
                <div id="icon">
                    <a href="../admin/admin_page.html"><img width="180" height="50" alt="Ticket Sea" src="../../img/ticket_admin.png"></a>

                </div>
			</div>
		</div>
        <div id="content-wrapper">
            <div id="c_inner">
            <aside class="main-sidebar">
                <div id="side-menu">
                 <ul>
                    <li><a href="#">공연관리</a>
                        <ul>
                             <li><a href="ad_showInsert.jsp">공연등록</a></li>
                            <li><a href="/adShowList.do">공연목록</a></li>
                        </ul>
                    </li>    
                    <li><a href="#">회원관리</a>
                        <ul>
                            <li><a href="/adMemberList.do">회원목록</a></li>
                        </ul>
                    </li>
                    <li><a href="#">예매관리</a>
                        <ul>
                            <li><a href="/adReserveList.do">예매목록</a></li>
                        </ul>
                    </li>
                    <li><a href="#">소규모공연</a>
                        <ul>
                            <li><a href="/miniShowList.do">승인대기공연</a></li>
                            <li><a href="/msApproveList.do">승인완료공연</a></li>
                        </ul>
                    </li>
                </ul>
                </div>
            </aside>
           <div class="content-header">
              <div id="content">
        <div class="content_wrap">
            <div class="top_area">
                <h2 class="main_title">공연등록</h2>
            </div>
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
                                    <option value="1" selected="selected" >카테고리 선택</option>
                                    <option value="MSC">뮤지컬</option>
                                    <option value="CNT">콘서트</option>
                                </select>
                                <input id="title" type="text" name="show_name" class="title_input" placeholder="공연명을 입력해 주세요." value="<%=show.getShow_name() %>" maxlength="64" autofocus="autofocus">
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
                                <input type="text" id="startEventDate" name="show_st_date" value="<%=show.getShow_st_date() %>" placeholder="시작일"> ~ <input type="text" id="endEventDate" name="show_ed_date" value="<%=show.getShow_ed_date() %>" placeholder="종료일">
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
                                    <option value="1" selected="selected"><%=show.getSc_code() %></option>
                                    <option value="10000">샤롯데시어티</option>
                                    <option value="10001"></option>
                          </select>
                        </div>
                    </fieldset>
                    <fieldset class="edit_artist">
                        <legend>
                          <h3 class="title">출연자</h3>
                        </legend>
                        <div class="edit">
                            <div class="write_wrap">
                                <input type="text" id="artist" name="artists" value="<%=show.getArtists() %>" placeholder="출연자정보 (,로 구분)">
                            </div>
                        </div>
                    </fieldset>
                    <fieldset class="edit_grade">
                        <legend>
                          <h3 class="title">관람등급</h3>
                        </legend>
                        <div class="edit">
                            <div class="write_wrap">
                                <input type="text" id="grade" name="show_grd" value="<%=show.getShow_grd() %>" placeholder="">
                            </div>
                        </div>
                        
                    </fieldset>
                     <fieldset class="edit_time">
                        <legend>
                          <h3 class="title">관람시간</h3>
                        </legend>
                        <div class="edit">
                            <div class="write_wrap">
                                <input type="text" id="runTime" name="show_run" value="<%=show.getShow_run() %>" placeholder="">
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
            <input type="submit" value="공연수정" style="float:right;" width="70px" height="40">
            </div>
            </div>
        </div>
    </div>
          </div> 
        </div>
        </div>
	</div>
</body>
</html>