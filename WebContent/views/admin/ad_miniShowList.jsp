<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "kr.co.ticketsea.admin.show.model.vo.*" 
	import = "java.util.ArrayList"%>
<%

	MiniPgData mpd = (MiniPgData)request.getAttribute("miniPgData");
	ArrayList<MiniShow> list=mpd.getList(); 
	String pageNavi = mpd.getPageNavi();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 외부 스타일 시트 불러오기 -->
    <link href="../../css/admin_common.css" rel="stylesheet" type="text/css">
    <link href="../../css/ad_miniShowList.css" rel="stylesheet" type="text/css">
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
                  <h2 class="main_title">소규모공연</h2>
               </div>
               <div class="main_area">
                <div id="member_table">
                   <table class="miniShowTbl">
                    <thead style="background-color:#E7E7E7">
                        <td width="15%">공연번호</td>
                        <td width="20%">공연명</td>
                        <td width="10%">작성자</td>
                        <td width="20%">공연장소</td>
                        <td width="15%">작성일</td>
                        <td>승인여부</td>
                       </thead>
                       <%for(MiniShow ms : list) {%>
                       <tr>
                           <td><a href="/miniShowWtInfo.do?msNo=<%=ms.getMs_no() %>"><%=ms.getMs_no() %></a></td>
                           <td><%=ms.getMs_name() %></td>
                           <td><%=ms.getMs_userId() %></td>
                           <td><%=ms.getMs_place() %></td>
                           <td><%=ms.getMs_wrtdate() %></td>
                           <td><input type="button" value="승인" onclick="approve('<%=ms.getMs_no()%>');"/>&nbsp;&nbsp;<input type="button" value="거부"/></td>
                       </tr>
                        <%} %>
                    </table>
                    
                    <!--회원목록 페이지 이동-->
                    <div class="paginate" style="text-align: center">
                    <label><%=pageNavi%></label>
                    </div>
                    
                    <!--회원검색-->
                    <div class="searchArea"> 
                    <a href="#"><img src="../../img/btn_search4.png" alt="검색" style="float: right"></a>
                
					<input type="text" class="textInp" name="searchValue" id="searchValue" style="float: right">
					<a href="javascript:search();"></a>
				</div>
                </div>
               </div>
               </div>
          </div> 
        </div>
        </div>
	</div>
	<script>
		function approve(showNo){
			if(window.confirm("공연을 승인하시겠습니까?")){
				location.href="/miniShowApprove.do?msNo="+showNo;
			}else{
				return false;
			}
		}
	</script>
</body>
</html>