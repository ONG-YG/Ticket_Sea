<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" type="text/css" href="/css/mypageAllSize.css" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 정보 수정</title>
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
        margin-bottom: 150px;
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
    
    /* right_view 하단 버튼관리 */
    .list_btn{
        margin-top: 50px;
        text-align: center;
    }
    .list_btn a{
        background-color: skyblue;
        width: 100%;
        border-radius: 20px;
        color: white;
        padding: 20px;
        font-size: 20px;
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
                        <li><a href="/reserveList.do">예매확인/취소</a></li>
                    </ul>
                </li>
                <li class="has_sub">
                    <span>활동 관리</span>
                    <ul>
                        <li><a href="/views/mypage/reviewMgr.jsp">후기 관리</a></li>
                        <li><a href="/views/mypage/faqMgr.jsp">나의 문의 내역</a></li>
                        <li><a href="#">소규모 공연 등록관리</a></li>
                    </ul>
                </li>
                <li class="has_sub1">
                    <span>회원정보관리</span>
                    <ul>
                        <li><a href="/views/mypage/memberUpdate.jsp">회원 정보 수정</a>
                        <li><a href="/views/mypage/memberDelete.jsp">회원 탈퇴</a></li>
                    </ul>
                </li>
            </ul>    
            <div id="right_view">
                <div class="r_line">
                    <h3>회원정보수정</h3>
                </div>
                <div class="r_table">
                    <table>
                        <tbody>
                            <tr>
                                <th>아이디</th>
                                <td>cho******@naver.com</td>
                            </tr>
                            <tr>
                                <th>연락처</th>
                                <td>010******78</td>
                            </tr>
                            <tr>
                                <th>주소</th>
                                <td>서울시 특별시</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="list_btn">
                    <a href="#">회원 정보 수정</a>
                </div>
            </div>
        </div>
    </div>
    
    <a href="#" id="back_to_top">Top</a>
    
    <div id="footer">
        <div id="f_inner">
            <jsp:include page="/footer.jsp"/>
        </div>    
    </div>
    
</div>

</body>
</html>