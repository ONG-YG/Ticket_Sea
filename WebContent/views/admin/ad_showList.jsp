<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공연목록</title>
	<style>
	     ul, li, a{list-style: none; margin: 0px; padding: 0px;     text-decoration: none; color: black;}
	    div{
	        box-sizing : border-box;
	    }
	    #wrapper{overflow: hidden;width:1250px; height:2000px; border:1px solid black;}
	    #header{ height:60px; background-color:;}
	    #h_inner{width: 990px; height: 80%; margin: 5px auto; float:left; padding-left:20px;}
	    #content-wrapper{height:1500px; border:1px solid black;}
	    #c_inner{width: 100%; height: 100%; margin: 0px auto; padding: 0px;}
	    .main-sidebar{
	        height:100%;
	        width: 15%;
	        float:left;
	        padding:0px;
	        background-color:#004C63;
	    }
	    /*사이드바 서브메뉴 숨기기*/
	    ul ul{display: none; width:100%;}
	    ul li:hover>ul{display:block;}
	    
	    #side-menu ul>li{
	        height:20%; padding-top:15px; padding-left:15px;padding-bottom: 15px;
	        background-color:#004C63;
	    }
	    #side-menu ul>li li{
	        background-color:#004554;
	        
	    }
	    #side-menu ul>li>a{
	        color:white;
	        font-weight: 600;
	    }
	    .main_content{
	        width:83%;
	        height:100%;
	        float:right;
	        padding:0px;
	        padding-right: 10px;
	        border:1px solid red;
	    }
	    .main_content>h1{
	        margin:0;
	    }
	    #icon{
	        max-width:20%; max-height:80%; align-content: center;
	    }
	/*    #content-wrapper li ul{
	        background-color: azure;
	        display : none; 평상시에 서브메뉴 안보이게 하기
	        height: auto;
	        width:200px;
	    }*/
	    
	    /*회원관리 페이지*/
	    #content{
	        overflow: hidden;
	        border:1px solid black;
	        border-style: dashed;
	        padding: 20px 0;
	        width:85%;
	        height:1300px;
	        display: block;
	    }
	    .content_wrap{
	        width:980px;
	        height: 100%;
	        margin:0 auto;
	        border:1px solid black;
	        box-sizing: border-box;
	        
	    }
	    h2.main_title{
	        display: inline-block;
	        font-size: 23px;
	        font-weight: normal;
	    }
	    #content .top_area{
	        height:5%;
	        border-bottom: 1px solid #d8d8d8;
	    }
	    
	    /*회원테이블*/
	    .showTbl{
	            border:1px solid #dedede;
	            border-right:0;
	            font-size:15px;
	            margin:40px auto 12px;
	            line-height:35px;
	            width:900px;
	            text-align: center;
	            
	        }
	    
	    .showTbl tr:nth-child(2n){
	        background-color: #F6F6F6;
	        
	    }
	    .content_wrap .searchArea{
	        height:10%;
	        padding: 30px;
	    }
	</style>
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
                            <li><a href="ad_showInsert.html">공연등록</a></li>
                            <li><a href="/adShowList.do">공연수정</a></li>
                            <li><a href="#">공연삭제</a></li>
                        </ul>
                    </li>    
                    <li><a href="#">회원관리</a>
                        <ul>
                            <li><a href="ad_memberList.html">회원목록</a></li>
                        </ul>
                    </li>
                    <li><a href="#">예매관리</a>
                        <ul>
                            <li><a href="ad_reserveList.html">예매목록</a></li>
                        </ul>
                    </li>
                    <li><a href="#">소규모공연</a>
                        <ul>
                            <li><a href="ad_miniShowList.html">승인대기공연</a></li>
                            <li><a href="#">승인완료공연</a></li>
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
                        <td width="15%">공연시간</td>
                        <td>시작일</td>
                        <td>종료일</td>
                       </thead>
                       <tr>
                           <td><a href="ad_showUpdate.html">S123456</a></td>
                           <td>지킬앤하이드</td>
                           <td>조승우,홍광호,윤공주,아이비</td>
                           <td>170분</td>
                           <td>2018.11.13</td>
                           <td>2019.05.19</td>
                       </tr>
                       <tr>
                           <td><a href="ad_showUpdate.html">S123457</a></td>
                           <td>넬 크리스마스콘서트</td>
                           <td>넬</td>
                           <td>120분</td>
                           <td>2018.12.22</td>
                           <td>2018.12.24</td>
                       </tr>
                    </table>
                    
                    <!--회원목록 페이지 이동-->
                     <div class="paginate" style="text-align: center">
                    <a class="prev"><span class="blind">이전</span></a>
                    <strong>1</strong>
                    <a href="#" class="">2</a>
                    <a href="#">다음</a>
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
</body>
</html>