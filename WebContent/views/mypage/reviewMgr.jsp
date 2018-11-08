<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="kr.co.ticketsea.member.model.vo.*" %>
    <%@ page import="kr.co.ticketsea.mypage.model.vo.*" %>
    <%@ page import="java.util.ArrayList" %>
    
    <%
	// Controller(Servlet)에서 보내준값 가져오기
	ReservePageData pd = (ReservePageData)request.getAttribute("reviewPd");

	ArrayList<ReviewMgr> list = pd.getReviewList(); // 현재 페이지의 글 목록
	String pageNavi = pd.getPageNavi(); // 현재 navi Bar
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" type="text/css" href="/css/mypageAllSize.css" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>후기 관리</title>
<script
  src="https://code.jquery.com/jquery-3.3.1.js"
  integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
  crossorigin="anonymous">
</script>
<style>
    
    /* inner 공간 분할 */
    #c_inner_top{
        width: 100%;
        height: 100px;
        margin-bottom: 20px;
        background: white;
        border: 1px solid skyblue;
    }
    #c_inner_top .title1{
        width: 100px;
        float: left;
        height: 100px;
        width: 166px;
        text-align: center;
        background: skyblue;
    }
    #c_inner_top .title1 h2{
        margin: 0;
        padding-top: 30px;
        color: white;
    }
    #c_inner_top .title2{
        width: 60px;
        float: left;
        height: 100px;
    }
    
    #left_menu{
        width: 166px;
        float: left;
        height: 100%;
        display: block;
        border-bottom: 1px solid #dedede;
        border-top: 1px solid #dedede;
        border-left: 1px solid #dedede;
        box-sizing: border-box;
        margin: 0;
        padding-top: 20px;
        padding-left: 0;
        background: white;
    }
    #right_view{
        width: 822px;
        float: left;
        border: 1px solid #dedede;
        height: 100%;
        padding: 30px;
        background: white;
    }
    
    /* left_menu 관리 */
    #left_menu li{
        width: 132px;
        padding: 17px 0 15px 34px;
        border-bottom: 1px dashed #dedede;
        
    }
    #left_menu .has_sub li{
        padding: 13px 0 0;
        height: 12px;
        border: none;
    }
    #left_menu .has_sub li a{
        color: darkgrey;
        font-size: 11px;
    }
    #left_menu .has_sub1 li:first-child{
        padding: 13px 0 0;
        height: 12px;
        border: none;
        font-size: 13px;
    }
    #left_menu .has_sub1 li:first-child a{
        color: lightskyblue;
        font-weight: bold;
    }
    #left_menu .has_sub1 li:nth-child(2n+0){
        padding: 13px 0 0;
        height: 12px;
        border: none;
    }
    #left_menu .has_sub1 li:nth-child(2n+0) a{
        color: darkgrey;
        font-size: 11px;
    }
    #left_menu .has_sub1 li:last-child{
        padding: 13px 0 0;
        height: 12px;
        border: none;
    }
    #left_menu .has_sub1 li:last-child a{
        color: darkgrey;
        font-size: 11px;
    }
    #left_menu span{
        color: #444;
        font-weight: bold;
    }

    
    
    /* right_view 내용 관리 */
    .r_line{
        border-bottom: 2px solid gray;
        margin-bottom: 20px;
    }
    .r_top{
        padding: 30px 0 10px 0;
        margin-bottom: 20px;
        color: #999;
        font-size: 12px;
    }
    .r_table thead th{
        background: #f0f0f0;
        padding: 15px 0 15px;
        line-height: 11px;
        letter-spacing: -1px;
        color: grey;
    }
    .r_table table{
        width: 100%;
        border-spacing: 0;
    }
    
    .r_table td{
        text-align: center;
        border-top: 1px solid #ececec;
        color: dimgray;
        font-weight: bold;
        font-size: 14px;
    }
    .paginate{
        padding-top: 20px;
        border-top: 1px solid #c7c7c7;
    }
    #paginate_inner{
    	margin : 0 auto;
    }
    .pagi_num{
        border: 1px solid skyblue;
        color: skyblue;
        padding: 5px 10px;
        margin: 2px;
        
    }
    
    
</style>
</head>
<body>
<div id="wrapper">
    <div id="header">
        <div id="h_inner">
            <jsp:include page="/header.jsp"/>
        </div>        

    </div>
    
    
    <div id="container">
        <div id="c_inner">
            <div id="c_inner_top">
                <div class="title1">
                    <h2>마이페이지</h2>
                </div>
                <div class="title2"></div>
            </div>
            <ul id="left_menu">
                <li class="has_sub">
                    <span>예매 관리</span>
                    <ul>
                        <li><a href="/reserveList.do">예매확인/취소</a>
                    </ul>
                </li>
                <li class="has_sub1">
                    <span>활동 관리</span>
                    <ul>
                        <li><a href="/reviewMgr.do">후기 관리</a></li>
                        <li><a href="/qnaMgr.do">나의 문의 내역</a></li>
                        <li><a href="#">소규모 공연 등록관리</a></li>
                        
                    </ul>
                </li>
                <li class="has_sub">
                    <span>회원정보관리</span>
                    <ul>
                        <li><a href="/myMemberUpdate.do">회원 정보 수정</a></li>
                        <li><a href="/views/mypage/memberDelete.jsp">회원 탈퇴</a></li>
                        
                    </ul>
                </li>
            </ul>    
            <div id="right_view">
                <div class="r_line">
                    <h3>후기 관리</h3>
                </div>
                <div class="r_top">
                    고객님께서 남겨주신 후기를 확인하실 수 있습니다.<br>
                    티켓링크 운영정책에 위반되거나, 후기의 성격에 맞지 않는 글을 고객님께 사전통보 없이 삭제될 수 있습니다.
                    
                </div>
                <div class="r_table">
                    <table>
                        <thead>
                            <tr>
                                <th>번호</th>
                                <th>공연명</th>
                                <th>댓글 내용</th>
                                <th>작성일</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%	for(ReviewMgr rm: list){ %>

                        	<tr>
                                <td><%=rm.getNum() %></td>
                                <td><%=rm.getmShowName() %></td>
                                <td><%=rm.getShowcommentContents() %></td>
                                <td><%=rm.getShowcommentDate() %></td>
                            </tr>
                        	<%} %>
                        </tbody>
                    </table>
                    
                    
                    <div class="paginate">
                    	<div id="paginate_inner" style="width:315px; text-align:center;">
							<label><%=pageNavi%></label>
						</div>
                    </div>
                    
                    
                </div>
            </div>
        </div>
    </div>
    
    <jsp:include page="/top_btn.jsp"/>
    
    <div id="footer">
        <jsp:include page="/footer.jsp"/>    
    </div>
</div>    


</body>
</html>