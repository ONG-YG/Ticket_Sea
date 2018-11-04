<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
     ul, li, a{list-style: none; margin: 0px; padding: 0px;     text-decoration: none; color: black;}
    div{
        box-sizing : border-box;
    }
    #wrapper{overflow: hidden;width:1650px; height:1500px; border:1px solid black;}
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
    .memberTbl{
            border:1px solid #dedede;
            border-right:0;
            font-size:15px;
            margin:40px auto 12px;
            line-height:35px;
            width:900px;
            text-align: center;
            
    }
    .reserveInfo_table{
        border-width: 1px 0;
        border-style: solid;
        border-color: #c7c7c7;
    }
    .reserveInfo_table table{
        width: 100%;
        border-spacing: 0;
    }
    .reserveInfo_table th{
        
        border-bottom: 1px dashed #dedede;
        padding: 30px 0 30px 20px;
        text-align: left;
        line-height: 40px;
        letter-spacing: -1px;
        color: black;
        font-size: 16px;
        width: 200px;
    }
    .reserveInfo_table td{
        text-align: center;
        border-bottom: 1px dashed #dedede;
        font-size: 14px;
        text-align: left;
    }
    .reserveInfo_table input[type="text"]{
        height:25px;
        font-size:14px;
    }
    
    #reserveCancel{
        padding-top:40px;
        text-align: center;
    }
    /*버튼 스타일*/
    button{
      background:#4ABFD3;
      color:#fff;
      border:none;
      position:relative;
      height:60px;
      font-size:1.6em;
      font-weight: 600;
      padding:0 2em;
      cursor:pointer;
      transition:800ms ease all;
      outline:none;
    }
    button:hover{
      background:#fff;
      color:#4ABFD3;
    }
    button:before,button:after{
      content:'';
      position:absolute;
      top:0;
      right:0;
      height:2px;
      width:0;
      background: #4ABFD3;
      transition:400ms ease all;
    }
    button:after{
      right:inherit;
      top:inherit;
      left:0;
      bottom:0;
    }
    button:hover:before,button:hover:after{
      width:100%;
      transition:800ms ease all;
    }
</style>
<title>Insert title here</title>
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
                                <td>T123456</td>
                            </tr>
                            <tr>
                                <th>예매일</th>
                                <td>2018.10.14</td>
                            </tr>
                            <tr>
                                <th>관람일시</th>
                                <td>2018.12.25(화)</td>
                            </tr>
                            <tr>
                                <th>아이디</th>
                                <td>test432</td>
                            </tr>
                            <tr>
                                <th>예매자명</th>
                                <td>지창욱</td>
                            </tr>
                            <tr>
                                <th>연락처</th>
                                <td>010-1234-5678</td>
                            </tr>
                            <tr>
                                <th>주소</th>
                                <td>서울시 강남구</td>
                            </tr>
                            <tr>
                                <th>결제정보</th>
                                <td>157,500원</td>
                            </tr>
                            <tr>
                                <th>예매상태</th>
                                <td>예매완료</td>
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