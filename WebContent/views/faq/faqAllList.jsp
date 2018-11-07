<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import = "kr.co.ticketsea.faq.model.vo.*" %>
    <%@ page import = "java.util.*" %>
    <%@ page import = "kr.co.ticketsea.member.model.vo.*" %>
    
    <%
	// Controller(Servlet)에서 보내준값 가져오기
	PageData pd = (PageData)request.getAttribute("pageData");

	ArrayList<Faq> list = pd.getList(); // 현재 페이지의 글 목록
	String pageNavi = pd.getPageNavi(); // 현재 navi Bar
	
%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ 리스트</title>
<script
  src="https://code.jquery.com/jquery-3.3.1.js"
  integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
  crossorigin="anonymous">
</script>
<style>
    /* 전체 사이즈 조정 */
    
    ul, li, a{list-style: none; margin: 0px; padding: 0px; text-decoration: none; color: black;}
    
    div{box-sizing: border-box;}
    #wrapper{overflow: hidden;min-width: 990px;min-height: 100%; border: 1px solid black;}
    #header{height: 193px; border: 1px solid black;}
    #container{height: 100%; border: 1px solid black; background: #f4f4f4;}
    #footer{height: 153px; border: 1px solid black;}
    
    /* 990px 고정 사이즈 */
    #h_inner{width: 990px; height: 100%; margin: 0px auto;}
    #c_inner{width: 990px; height: 1300px; margin: 0px auto; padding: 70px 0 250px 0px;}
    #f_inner{width: 990px; margin: 0px auto;}

    /* top 버튼 */
    #back_to_top{
        display: block;
        position: fixed;
        top: 50%;
        right: 0;
        margin-top: -22px;
        background-position: -270px -123px;
        width: 44px;
        height: 44px;
        z-index: 1000;
        background: skyblue;
    }
    
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
    #right_view>.r_line{
        border-bottom: 2px solid gray;
        margin-bottom: 50px;
    }
    
    /* rigth_view table 관리 */
    .r_table{
        border-width: 1px 0;
        border-style: solid;
        border-color: #c7c7c7;
    }
    .r_table table{
        width: 100%;
        border-spacing: 0;
    }
    .r_table th{
        
        border-bottom: 1px dashed #dedede;
        padding: 30px 0 30px 20px;
        text-align: left;
        line-height: 40px;
        letter-spacing: -1px;
        color: black;
        font-size: 14px;
        width: 200px;
    }
    .r_table td{
        text-align: center;
        border-bottom: 1px dashed #dedede;
        font-size: 12px;
        text-align: left;
    }
    .border_box{
        display: block;
        width: 90px;
        padding: 3px 0 1px;
        border: 1px solid #d4d4d4;
        text-align: center;
        line-height: 21px;
    }
    
    /* right_view 하단 버튼관리 */
    .list_btn{
        display: block;
        text-align: center;
        background: skyblue;
        width: 50px;
    }
    
    
    /* 하단 회원 약관 */
    .note{
        background: #f4f4f4;
        padding: 26px 22px 20px;
        margin-top: 50px;
        line-height: 20px;
    }
    .note h6{
        font-size: 12px;
        color: #767676;
        margin: 0;
        padding: 6px;
    }
    .note ol{
        margin: 0;
        padding: 0;
    }
    .note ol li {
    padding-left: 6px;
    color: #999;
    font-size: 11px;
    letter-spacing: -1px;
    }
    
   
    
    .pullMethod {
            background:#fbfbfb;
            text-align:left;
            padding:25px 30px;
            line-height:1.6;
        }
    
    .faqCategory{
            border:1px solid #dedede;
            border-right:0;
            font-size:13px;
            margin:10px auto 12px;
            line-height:45px;
            width:730px;
            text-align: center;
        }
        
        .faqContent{
            border:1px solid #dedede;
            border-right:0;
            font-size:13px;
            margin:10px auto 12px;
            line-height:45px;
            width:730px;
            text-align: center;
        }
    #header_inner{width:990px; margin: 0px auto;}
</style>
</head>
<body>

<div id="wrapper">
        <div id="header_inner">
            <jsp:include page="/header.jsp"/>
    </div>
    
    
    <div id="container">
        <div id="c_inner">
            <div id="c_inner_top">
                <div class="title1">
                    <h2>고객센터</h2>
                </div>
                <div class="title2"></div>
            </div>
            <ul id="left_menu">
                <li class="has_sub">
                    <span>고객센터</span>
                    <ul>
                        <li><a href="/noticeList.do"><strong>공지사항</strong></a></li>
                        <li><a href="/faqList.do"><strong>자주묻는 질문</strong></a></li>
                        <li><a href="/qnaList.do"><strong>질문게시판</strong></a></li>
                    </ul>
                </li>
            </ul>    
            <div id="right_view">
                <div class="r_line">
                    <h3>자주묻는 질문</h3>
                </div>
                
                     
            <table border=1px class="faqCategory">
                         <tr>
                           <td id = "faqConcert"><a href="#">공연</a></td>
                           <td id = "faqBook"><a href="#">예매 </a></td> 
                           <td id = "faqPayment"><a href="#">결제</a></td>
                           <td id = "faqID"><a href="#">회원</a></td>
                           <td id=" faqEtc"><a href="#">기타</a></td>
                         </tr>
                    </table>
                    
                    <br>
                    
                    <table border 1px class="faqContent">
                        <tr style="background-color: lightskyblue; color:white">
                            <td id = "contentCategory" style="width: 15%">분류</td>
                            <td id = "contentQuestion"  style="width: 85%">질문</td>
                        </tr>
                    
                        <% for (Faq f : list) { %>
                    
                        <tr>
                            <td><%= f.getBoardF_category() %></td>
                            <td><a href="/faq.do?boardF_no=<%=f.getBoardF_no()%>"><%=f.getBoardF_title()%></a></td>
                        </tr>
                        
                        <% } %> 
                        
                       
                        
             
            </table>    
            
                
                
           <div style="width:800px; text-align:center;">
		<label><%=pageNavi%></label>
		</div>
            <%
		session = request.getSession(false);
		Member m = (Member)session.getAttribute("member"); 
	%>       
            
           <%
		if(m!=null && m.getMemberId().equals("admin")){
		%>         
     	<form style="display:inline;" action="/views/faq/faqWrite.jsp">
		<input type="submit" value="글쓰기" style="width: 70px; height: 30px; float:right;"/> <br>
	</form>
	<%} %>
            <br>
            <div class="searchArea"> 
            <form style="display:inline;" action="/faqSearch.do" method="get">
                    <a href="#"><input type="submit" style="display: none"><img src="../../img/btn_search4.png" alt="검색" style="float: right"></a>
                
					<input type="text" class="textInp" name="search" id="search" style="float: right">
					<a href="javascript:search();"></a>
					</form>
				</div>
            
                
            </div>
        </div>
    </div>
    
    <a href="#" id="back_to_top">Top</a>
    
    <div id="footer">
        <jsp:include page="/footer.jsp"/>
    </div>
    
</div>

</body>
</html>