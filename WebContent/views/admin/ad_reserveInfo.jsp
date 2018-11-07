<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "kr.co.ticketsea.admin.reserve.model.vo.*" 
	import="kr.co.ticketsea.reserve.model.vo.*"
	import = "java.util.ArrayList"%>
<% ReserveApInfo rs=(ReserveApInfo)request.getAttribute("ReserveInfo"); 
	ArrayList<SelectedSeat> seatList = rs.getSeatInfo();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
<!-- 외부 스타일 시트 불러오기 -->
    <link href="../../css/admin_common.css" rel="stylesheet" type="text/css">
    <link href="../../css/ad_reserveInfo.css" rel="stylesheet" type="text/css">
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
                  <h2 class="main_title">예매정보</h2>
               </div>
               <div class="main_area">
                   <div class="reserveInfo_table">
                    <table>
                        <tbody>
                            <tr>
                                <th>예매번호</th>
                                <td><%=rs.getBk_no() %></td>
                            </tr>
                            <tr>
                                <th>공연명</th>
                                <td><%=rs.getM_show_name() %></td>
                            </tr>
                            <tr>
                                <th>예매일</th>
                                <td><%=rs.getBk_date() %></td>
                            </tr>
                            <tr>
                                <th>관람일시</th>
                                <td><%=rs.getPs_date() %></td>
                            </tr>
                            <tr>
                                <th>아이디</th>
                                <td><%=rs.getMember_id() %></td>
                            </tr>
                            <tr>
                                <th>예매자명</th>
                                <td><%=rs.getMember_name() %></td>
                            </tr>
                            <tr>
                            	<th>예매좌석</th>
                            	
                            	<td><%for(SelectedSeat st : seatList) {%>
                            	<%=st.getSeatGrd()%>석 <%=st.getSeatTitle()%><br><%}%></td>
                            </tr>
                            <tr>
                                <th>연락처</th>
                                <td><input type="text" id="bk_phone" name="bk_phone" value="<%=rs.getBk_phone()%>"/></td>
                            </tr>
                            <tr>
                                <th>이메일</th>
                                <td><input type="text" id="bk_email" name="bk_email" value="<%=rs.getBk_email()%>"/></td>
                            </tr>
                            <tr>
                                <th>총결제금액</th>
                                <td><%=rs.getBk_tot_price() %>원</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                   <div id="reserveCancel">
                    <button >예매취소</button>
                   </div>
               </div>
               </div>
          </div> 
        </div>
        </div>
	</div>
</body>
</html>