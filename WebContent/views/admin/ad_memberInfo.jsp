<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "kr.co.ticketsea.member.model.vo.*" %>
   
<% Member m = (Member)request.getAttribute("Member");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원정보페이지</title>
    <!-- 외부 스타일 시트 불러오기 -->
    <link href="../../css/admin_common.css" rel="stylesheet" type="text/css">
    <link href="../../css/ad_memberInfo.css" rel="stylesheet" type="text/css">
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
                  <h2 class="main_title">회원정보</h2>
               </div>
               <form action ="/adMemberUpdate.do" method="post" id="updateForm">
               <div class="main_area">
                   <div class="memberInfo_table">
                    <table>
                        <tbody>
                            <tr>
                                <th>회원번호</th>
                                <td><%=m.getMemberNo() %></td>
                                <input type="hidden" name="member_no" value="<%=m.getMemberNo() %>"/>
                            </tr>
                            <tr>
                                <th>아이디</th>
                                <td><%=m.getMemberId() %></td>
                            </tr>
                            <tr>
                                <th>이름</th>
                                <td><%=m.getMemberName()%></td>
                            </tr>
                            <tr>
                                <th>연락처</th>
                                <td><input type="text" id="phone" name="phone" value="<%=m.getMemberPhone()%>"></td>
                            </tr>
                            <tr>
                                <th>주소</th>
                                <td><input type="text" id="address" name="address" value="<%=m.getMemberAddr()%>"></td>
                            </tr>
                            <tr>
                                <th>이메일</th>
                                <td><input type="text" id="email" name="email" value="<%=m.getMemberEmail()%>"></td>
                            </tr>
                            <tr>
                            	<th>회원상태</th>
                            	<td><%=m.getMemberActive() %></td>
                            </tr>
                            <tr>
                                <th>가입일</th>
                                <td><%=m.getMemberJoinDate() %></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                   <div id="memUpdate">
                    <button id="updateBtn" onclick = "return updateSubmit();">회원정보 수정</button>
                   </div>
               </div>
               </form>
               </div>
               <script>
                function updateSubmit(){
                	var phone = document.getElementById("phone").value;
                	var address = document.getElementById("address").value; 
                	var email = document.getElementById("email").value; 
                	
                	if(phone==""){
                		alert("연락처를 입력하세요");
                		return false;
                	}else if(address==""){
                		alert("주소를 입력하세요");
                		return false;
                	}else if(!(/^[a-z0-9]{4,20}@/.test(email))||email==""){
                		alert("이메일을 입력하세요");
                		return false;
                	}else{
                		document.getElementById("updateForm").submit();
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