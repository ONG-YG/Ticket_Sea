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
<!-- 외부 스타일 시트 불러오기 -->
    <link href="../../css/admin_common.css" rel="stylesheet" type="text/css">
    <link href="../../css/ad_showUpdate.css" rel="stylesheet" type="text/css">
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
                             <li><a href="/adShowPlace.do">공연등록</a></li>
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
                                    <%for(ShowCategory sc : scList) {%>
                                    <option value="<%=sc.getSc_code()%>"><%=sc.getSc_name()%></option>
                            		<%} %>
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