<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "kr.co.ticketsea.admin.show.model.vo.*" 
		import = "java.util.ArrayList"%>
		
<% 
	// Controller(Servlet)에서 보내준 값 가져오기
	PageData pd = (PageData)request.getAttribute("pageData");

	ArrayList<Show> list=pd.getList(); //현재 페이지의 글 목록
	String pageNavi = pd.getPageNavi(); //현재 navi Bar
	
	
%>
		
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공연목록</title>
<!-- 외부 스타일 시트 불러오기 -->
    <link href="/css/admin_common.css" rel="stylesheet" type="text/css">
    <link href="/css/ad_showList.css" rel="stylesheet" type="text/css">
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
                <div id="content">
           <div class="content_wrap">
              <div class="top_area">
                  <h2 class="main_title">공연정보</h2>
               </div>
               <div class="main_area">
                <div id="show_table">
                   <table class="showTbl">
                    <thead style="background-color:#E7E7E7">
                        <td width="15%">공연번호</td>
                        <td width="20%">공연명</td>
                        <td width="25%">츨연자</td>
                        <td width="15%">관람시간</td>
                        <td>공연장소</td>
                        <td>삭제</td>
                       </thead>
                       <%for(Show s : list) {%>
                       <tr>
                           <td><a href="/showUpdatePlace.do?m_show_no=<%=s.getM_show_no()%>"><%=s.getM_show_no() %></a></td>
                           <td><%=s.getShow_name() %></td>
                           <td><%=s.getArtists() %></td>
                           <td><%=s.getShow_run() %></td>
                           <td><%=s.getTh_name() %></td>
                           <td><input type="button" value="삭제" onclick="delActive('<%=s.getM_show_no()%>');"></td>
                           <script>
	                    	function delActive(showNo){
	                    		location.href="/adShowDelete.do?showNo="+showNo; //get방식으로 삭제할 회원번호 넘김
	                    	}
                     </script>
                       </tr>
                       <%} %>
                    </table>
                    
                    <!--회원목록 페이지 이동-->
                     <div class="paginate" style="text-align: center">
                    <label><%=pageNavi%></label>
                    </div>
                    
                    <!--회원검색-->
                    <div class="searchArea"> 
                    <form style="display:inline;" action="/showSearch.do" method="get">
						<input type="text" name="search"/>
						<input type="submit" value="검색"/>
					</form>
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