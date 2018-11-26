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
<title>Ticket Sea || 검색</title>
</head>
<body>
<style>
#footer{height: 153px;}
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
		height:20px;
		padding-bottom:15px;
		padding-top:15px;
		display:inline;
		float:left;
		background-color:#f4f4f4;
		
	
		
	
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
   height:700px;
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
		<br><br>
			<p id="resultText"> <b>검색 결과를 찾을수 없습니다.</b></p>
			
			
			<div id="detailBox">
			<ul>
				
					<li>단어의 철자가 정확한지 확인해 보세요.</li>
                    <li>한글을 영어로 혹은 영어를 한글로 입력했는지 확인해 보세요.</li>
                    <li>검색어의 단어 수를 줄이거나, 보다 일반적인 검색어로 다시 검색해 보세요.</li>
                    <li>두 단어 이상의 검색어인 경우, 띄어쓰기를 확인해 보세요. </li>
                 
				</ul>
		
			
				
		</div>
		
		
			
	</div>
</div>
	<div id="footer">
        <jsp:include page="/footer.jsp"/>
    </div>
</body>
</html>