<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "kr.co.ticketsea.member.model.vo.*"
	import = "kr.co.ticketsea.admin.member.model.vo.*"
	import = "java.util.ArrayList"
%>

<%
	//Controller에서 보내준 값 가져오기
	MemberPageData mpd= (MemberPageData)request.getAttribute("pageData");
	String keyword = (String)request.getAttribute("keyword");	
	ArrayList<Member> list = null;
	String pageNavi = null;
	
	if(mpd!=null){
		list=mpd.getList();
		pageNavi=mpd.getPageNavi();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원정보리스트</title>

<!-- 외부 스타일 시트 불러오기 -->
    <link href="/css/admin_common.css" rel="stylesheet" type="text/css">
    <link href="/css/ad_memberList.css" rel="stylesheet" type="text/css">
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
                  <h2 class="main_title" >회원관리</h2>
               </div>
               <div class="main_area">
                <div id="member_table">
                   <table class="memberTbl">
                    <thead style="background-color:#E7E7E7">
                        <td width="10%">회원번호</td>
                        <td width="15%">아이디</td>
                        <td width="10%">이름</td>
                        <td width="15%">연락처</td>
                        <td width="20%">이메일</td>
                        <td>활동여부</td>
                        <td width="10%">가입일</td>
                        <td>삭제</td>
                        
                       </thead>
                  <%if(mpd!=null){%>
                       <%for(Member m : list) {%>
                       <tr>
                           <td><a href="adMember.do?memberNo=<%=m.getMemberNo()%>"><%=m.getMemberNo()%></a></td>
                           <td><%=m.getMemberId() %></td>
                           <td><%=m.getMemberName() %></td>
                           <td><%=m.getMemberPhone() %></td>
                           <td><%=m.getMemberEmail() %></td>
                           <td><%=m.getMemberGender() %></td>
                           <td><%=m.getMemberJoinDate() %></td>
                           <td><input type="button" value="삭제" onclick="delActive('<%=m.getMemberNo()%>');"></td>
                     <script>
                    	function delActive(memberNo){
                    		location.href="/adMemberDelete.do?memberNo="+memberNo; //get방식으로 삭제할 회원번호 넘김
                    	}
                     </script>
                       </tr>
                        <%} %>
                      <%}else{ %>
                      <h2>검색결과가 없습니다.</h2>
					<%} %>
                    </table>
                    <!--회원목록 페이지 이동-->
                     <div class="paginate" style="text-align: center">
                    	 <label><%=pageNavi%></label>
                    </div>
                    
                    <!--회원검색-->
                    <div class="searchArea"> 
                	<form style="display:inline;" action="/memberSearch.do" method="get">
						<input type="submit" value="검색"  style="float:right"/>
						<input type="text" name="search"  value="<%=keyword %>" style="float:right"/>
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