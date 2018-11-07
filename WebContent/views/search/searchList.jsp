<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="kr.co.ticketsea.search.model.vo.*"
	import="java.util.ArrayList"
	import="kr.co.ticketsea.member.model.vo.*"
 %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TiceketSea || 검색 </title>
<style>
#inner{
	background-color:#f4f4f4;
	margin: 0px auto;
	height:100%;
	
}
	em{
	color:#87ceeb;
	}
	label{
		text-decoration:none;
		text-decoration-line: none;
		
	}
	#detailBox>ul>li{
		list-style:none;
		width:800px;
		height:144px;
		padding-bottom:15px;
		padding-top:15px;
		display:inline;
		float:left;
		background-color:white;
		
		border-style: none none dotted none;
		border-color: #f4f4f4;
		
		
	
	}
	.result_info{
		width:805px;
		height:134px;
	}
	.tit{
		display:inline;
		width:560px;
		height:28px;
		margin:0px;
		float:left;
		font-size:17px;
	}
	.bx_tit{
		
		
		display:inline;
		width:644px;
		height:98px;
	}
	img{
		float:left;
		margin-right:20px;
	}
		imgbox{
		display:block;
   float:left;
   width:110px;
   height:134px;
   }
	 
   #header_inner
   {width:990px; margin: 0px auto;}
   
   label>a {
   text-decoration: none;
   color :#87ceeb;
   }
   
   dt{
   	width:51px;
   	height: 21px;
   	display:inline;
   	text-decoration:none;
   }
   dd{
   	width:590px;
   	height:21px;
   	display:inline;
   	margin:0px;
   	text-decoration:none;
   }
   dl{
   display:block;
   width:800px;
	height:70px;
	text-decoration:none;
   
   }
   #detailBox{
   width:900px;
 	height:100%;
   padding-top: 30px;
    padding-right: 29px;
    padding-bottom: 0px;
    padding-left: 29px;
    margin:0px auto;
   }
   .reserveBtn{
   	float:right;
   	width:110px;
   	height:42px;
   	background-color:#87ceeb;
   	color:white;
   }
   #resultText{
   	
   	margin: 0px auto;
   	display:block;
   	width:800px;
   }
   #detailBox>ul>li>a{
  	 text-decoration: none;
  	 color: black;
  	 font-size:13px;
   }
   #result_wrap{
   height:1871px;
   }
   #pageNavi{
   margin:0px auto;
   width:200px;
   }
</style>

<%
	// Controller(Servlet)에서 보내준값 가져오기
	PageData pd = (PageData)request.getAttribute("pageData");
	String keyword = (String)request.getAttribute("keyword");

	ArrayList<Search> list = null;
	String pageNavi = null;
	
	if(pd!=null){
	 list = pd.getList(); // 현재 페이지의 글 목록
	 pageNavi = pd.getPageNavi(); // 현재 navi Bar
	}
	
%>
</head>
<body>
  
   
   
   
<div id="header_inner">
<jsp:include page="/header.jsp"/>
</div>

	

<div id="inner">
		<div id="result_wrap">
			<p id="resultText"><br><br><em><b><%= keyword %></b></em>&nbsp;&nbsp;에 대한 검색 결과입니다.</p>
			
			
			<div id="detailBox">
				<ul><% for(Search s : list)
						{	%>
				
					<li><a href="" class="result_info">
                                                        <span class="img_box">
                                                            <img src="/img/poster/<%=s.getmShowPoster()%>" width="110px" height="134px">
                                                            
                                                        	
                                                        </span>
                                                        <div class="bx_tit">
                                                            <span class="info_tit">
                                                                <strong class="tit"><%=s.getmShowName() %></strong>
                                                            </span><br><br>
                                                            <dl>
                                                                <dt>아티스트 :</dt> 	<button class="reserveBtn">예매하기</button>
                                                                <dd><%=s.getmArtists() %></dd><br>
                                                                <dt>기&nbsp;&nbsp;&nbsp;간 :</dt>
                                                                <dd>
                                                                    <%=s.getmShowStDate() %>
                                                                    ~
                                                                    <%=s.getmShoEdDate() %>
                                                                </dd><br>
                                                                
                                                                
                                                                    <dt>관람시간 :</dt>
                                                                    <dd><%=s.getmShowRun() %> 분</dd>
                                                               
                                                               
                                                            </dl>
                                                            
                                                        </div>
                                                         
                                          </a></li>
                                          <%} %>
				</ul>
				<div id="pageNavi">
		<label><%=pageNavi%></label>
	</div>
			</div>
			
				
		</div>
		
			
	</div>
			
</body>
</html>