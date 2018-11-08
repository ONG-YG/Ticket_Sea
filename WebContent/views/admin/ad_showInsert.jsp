<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "kr.co.ticketsea.admin.show.model.vo.*" 
	import = "java.util.ArrayList"
%>
<%@page import="kr.co.ticketsea.member.model.vo.Member"%>

<%
	ArrayList<ShowPlace> spList = (ArrayList<ShowPlace>)request.getAttribute("showPlaceList");
	ArrayList<ShowCategory> scList = (ArrayList<ShowCategory>)request.getAttribute("showCTGList");
	
	Member m = (Member)session.getAttribute("member");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel=StyleSheet type="text/css" href="/css/admin_common.css">
<link rel=StyleSheet type="text/css" href="/css/ad_showInsert.css">


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
</script>

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
                <h2 class="main_title" style="text-align:left">공연등록</h2>
            </div>
             <!-- form 태그 -->
       		<form action="/showInsert.do" method="post" enctype="multipart/form-data" id="updateForm">
            <div class="main_area">
                <div class="left_wrap">
                <div class="input_area">
                    <!--이미지영역-->
                    <div class="event_img_area">
                        <img class="show_post" id="postImg" src="/img/ticketsea_poster.png" data-default-src="/img/ticketsea_poster.png" alt="공연포스터" style="width:140px; height:160px;">
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
                                <input id="title" type="text" name="show_name" class="title_input" placeholder="공연명을 입력해 주세요." maxlength="64" autofocus="autofocus">
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
                           <select id="place" class="place_select" name="th_no">
                           <%for(ShowPlace sp : spList) {%>
                             <option value="<%=sp.getTh_no()%>"><%=sp.getTh_name()%></option>
                           <%} %>
                          </select>
                          <input type="button" id="placeSearch" onclick="placeSearch();" value="주소검색"/> 
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
                                <input type="text" id="grade" name="show_grd" value="" placeholder="관람 등급을 입력해주세요">
                            </div>
                        </div>
                        
                    </fieldset>
                     <fieldset class="edit_time">
                        <legend>
                          <h3 class="title">관람시간</h3>
                        </legend>
                        <div class="edit">
                            <div class="write_wrap">
                                <input type="text" id="runTime" name="show_run" value="" placeholder="관람 시간을 입력해주세요">
                            </div>
                        </div>
                    </fieldset>
                    <fieldset class="edit_comm">
                        <legend>
                          <h3 class="title">수수료</h3>
                        </legend>
                        <div class="edit">
                            <div class="write_wrap">
                                <input type="text" id="comm" name="comm" value="" placeholder="">
                            </div>
                        </div>
                    </fieldset>
                  <fieldset class="edit_poster">
                        <legend>
                          <h3 class="title">공연포스터</h3>
                        </legend>
                        <div class="edit">
                            <div class="write_wrap">
                           		<input type="file" id="show_poster" name="show_poster" accept=".jpg, .gif, .png"'/>
                            </div>
                        </div>
                    </fieldset>
                    <fieldset class="edit_detailInfo">
                        <legend>
                          <h3 class="title">세부정보</h3>
                        </legend>
                        <div class="edit">
                            <div class="write_wrap">
                           		<input type="file" id="showDtInfo" name="showDtInfo" accept=".jpg, .gif, .png"/> 
                            </div>
                        </div>
                    </fieldset>
	            </div>
	            </div>
	            <div class="submit_area">
                    <button id="updateBtn" onclick = "return showCheck();">공연등록</button>
	            </div>
            </form>
                </div>
                
            	</div>
        	</div>
    	</div>
          </div> 
      </div>
	
	<!-- script -->
	
	<script>
		/* file onchange */
		var sel_file;
		
		$(document).ready(function(){
			$("#show_poster").on("change",handleImgFileSelect);
			$("#placeSearch").click(function(){
		    	location.href="/views/admin/placeInsert.jsp";
		    });
			function handleImgFileSelect(e){
				var files=e.target.files;
				var filesArr=Array.prototype.slice.call(files);
				
				filesArr.forEach(function(f){
					if(!f.type.match("image.*")){ //이미지 확장자만 가능
						alert("이미지 확장자만 가능합니다.");
						return;
					}
					sel_file=f;
					
					var reader=new FileReader();
					reader.onload = function(e){
						$("#postImg").attr("src",e.target.result);
					}
					reader.readAsDataURL(f);
				});
			}
			
			/* submit */

			function showCheck(){
				var category= document.getElementById("category").value;
				var title= document.getElementById("title").value;
				var startEventDate=document.getElementById("startEventDate").value;
				var endEventDate= document.getElementById("endEventDate").value;
				var place= document.getElementById("place").value;
				var artist= document.getElementById("artist").value;
				var grade= document.getElementById("grade").value;
				var runTime= document.getElementById("runTime").value;
				var price= document.getElementById("price").value;
				var comm= document.getElementById("comm").value;
				
				
				if(category==""||category==null)
		        {
				     alert("공연카테고리를 선택하세요");
						return false;
				}else if(title==""||title==null)
				{
						alert("공연명을 입력하세요");
						return false;
		        }
		       else if(!(/^(19|20)\d{2}.(0[1-9]|1[012]).(0[1-9]|[12][0-9]|3[0-1])$/.test(startEventDate)))
		            //년,월,일 .으로 구분
		            {
		        		alert("시작일을 입력해주세요(yyyy.mm.dd)");
						return false;
		            }
		        else if(!(/^(19|20)\d{2}.(0[1-9]|1[012]).(0[1-9]|[12][0-9]|3[0-1])$/.test(endEventDate)))
		        	//년,월,일 .으로 구분
		        {
		        	alert("종료일을 입력해주세요(yyyy.mm.dd)");
					return false;
		        } 
		        else if(artist==""){
		            alert("출연자를 입력하세요");
					return false;
		        }
		        else if(!(/^[ㄱ-ㅎ|가-힣|0-9|\s]+$/g.test(grade)))
		            {   
		                 alert("관람등급을 입력하세요");
		                return false;
		            }
		        else if(!(/^[0-9]+$/g.test(runTime)))
		            {
		                alert("관람시간을 입력하세요(숫자만)");
		                return false;
		            }
		        else if(!(/^[0-9]+$/g.test(price)))
				{
		               	alert("가격을 입력하세요");
		                return false;
		        }else if(!(/^[0-9]+$/g.test(comm)))
				{
		           	alert("수수료를 입력하세요");
		            return false;
		    	}
		        else//모든 검사 만족시 true 반환
		        {
		        	document.getElementById("updateForm").submit();
		            return true;
		        }
			
		} 
		});
		

	</script>
</body>
</html>