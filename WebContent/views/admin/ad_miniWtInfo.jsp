<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.co.ticketsea.admin.show.model.vo.*" %>
<% MiniShow ms = (MiniShow)request.getAttribute("miniShow");%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>관리자페이지</title>
<!-- 외부 스타일 시트 불러오기 -->
    <link href="/css/admin_common.css" rel="stylesheet" type="text/css">
    <link href="/css/ad_miniApInfo.css" rel="stylesheet" type="text/css">
</head>
</head>
<body>
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
                <div id="content">
           <div class="content_wrap">
              <div class="top_area">
                  <h2 class="main_title">소규모 공연 정보</h2>
               </div>
               <form action ="/miniShowRefuse.do?msNo=<%=ms.getBoardp_no() %>" method="post" id="updateForm" enctype="multipart/form-data">
               <div class="main_area">
                   <div class="memberInfo_table">
                    <table>
                        <tbody>
                            <tr>
                                <th>공연번호</th>
                                <td><%=ms.getBoardp_no() %></td>
                                <input type="hidden" name="msNo" value="<%=ms.getBoardp_no()%>"/>
                            </tr>
                            <tr>
                                <th>장르</th>
                                <td><%=ms.getBoardp_category()%></td>
                            </tr>
                            <tr>
                                <th>공연명</th>
                                <td><%=ms.getBoardp_title()%></td>
                            </tr>
                            <tr>
                                <th>작성자</th>
                                <td><%=ms.getBoardp_writer()%></td>
                            </tr>
                            <tr>
                                <th>아티스트</th>
                                <td><%=ms.getBoardp_artist()%></td>
                            </tr>
                            <tr>
                                <th>장소</th>
                                <td><%=ms.getBoardp_location()%></td>
                            </tr>
                            <tr>
                                <th>공연일</th>
                                <td><%=ms.getBoardp_date()%></td>
                            </tr>
                            <tr>
                                <th>가격</th>
                                <td><%=ms.getBoardp_price()%></td>
                            </tr>
                            <tr>
                            	<th>공연포스터</th>
                            	<td><%=ms.getBoardp_filename()%></td>
                            </tr>
                            <tr>
                                <th>공연소개</th>
                                <td><%=ms.getBoardp_contents()%></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                   <div id="msUpdate">
                    <button id="refuseBtn" onclick = "return refuseSubmit()">소규모공연 거부</button>
                   </div>
               </div>
               </form>
               </div>
                <script>
                function refuseSubmit(){
                	var artists = document.getElementById("artists").value; 
                	var place = document.getElementById("place").value; 
                	var price = document.getElementById("price").value; 
                	var intd = document.getElementById("intd").value; 
                	
                	if(artists==""){
                		alert("아티스트 정보를 입력하세요");
                		return false;
                	}else if(place=""){
                		alert("장소를 입력하세요");
                		return false;
                	}else if(!(/^[0-9]+$/.test(price))){
                		alert("가격을 입력해주세요");
            			return false;
                	}
                	else if(intd==""){
                		alert("공연정보를 입력하세요");
                		return false;
                	}else{
                		document.getElementById("refuseSubmit").submit();
                		return true;
                	}
                }
               </script>
          </div> 
        </div>
        </div>
	</div>
</body>
</html>