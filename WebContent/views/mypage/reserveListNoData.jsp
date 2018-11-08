<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="kr.co.ticketsea.mypage.model.vo.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" type="text/css" href="/css/mypageAllSize.css" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>예매확인/취소</title>
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
    #left_menu .has_sub1 li{
        padding: 13px 0 0;
        height: 12px;
        border: none;
        font-size: 13px;
        font-weight: bold;
    }
    #left_menu .has_sub1 a{
        color: lightskyblue;
        
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
    .cancelBtn{
        background-color: skyblue;
        border-radius: 5px;
        color: white;
    }
    
    .paginate{
        
        padding-top: 20px;
        border-top: 1px solid #c7c7c7;
    }
    #paginate_inner{
    	margin : 0 auto;
    }
    
    #list_btn{
        background-color: #f0f0f0;
        border-radius: 5px;
        color: grey;
        padding: 2px;
        font-size: 13px;
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
    
    
    
    
    #btnForm{width: auto; height:auto;}
    #btnForm2{width: auto; height:auto;}
    
</style>
</head>
<body>
<div id="wrapper">
    
    <div id="header">
        <jsp:include page="/header.jsp"/>
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
                <li class="has_sub1">
                    <span>예매 관리</span>
                    <ul>
                        <li><a href="/reserveList.do">예매확인/취소</a>
                    </ul>
                </li>
                <li class="has_sub">
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
                    <h3>예매확인/취소</h3>
                </div>
                <div class="r_table">
                    <table>
                        <thead>
                            <tr>
                                <th>번호</th>
                                <th>예매번호</th>
                                <th>공연명</th>
                                <th>공연일</th>
                                <th>예매 상태</th>
                                <th>상세보기</th>
                            </tr>
                        </thead>
                        <tbody>        	
                        	

                        	<tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                        	
                        </tbody>
                    </table>
                    
                    
                </div>
                <div class="note">
                    <h6>티켓취소 안내</h6>
                    <ol>
                            <li>예매한 티켓 전체 취소, 혹은 신용카드 결제 시 부분 취소가 가능합니다.<br>단, 일부 상품의 경우도 부분취소가 불가합니다.</li>
                            <li>티켓이 배송된 이후에는 인터넷이나 고객센터를 통한 취소가 불가하며, 받으신 티켓을 취소일 전까지 NHN티켓링크 본사로 반송을 해주셔야 취소 가능합니다. (등기우편을 이용해주세요!)</li>
                            <li>예매 당일 자정까지 취소하실 경우는 예매수수료도 환불되며, 취소수수료가 부과되지 않습니다. 그 이후에 취소하실 경우는 예매수수료가 환불되지 않으며, 취소수수료는 정책에 따라 부과됩니다.</li>
                            <li>일부 경기의 경우 상황에 따라 일괄 취소 건이 발생할 수있으며, 일괄 취소 시에는 취소 수수료가 부과되지 않습니다.</li>
                            <li>티켓의 날짜/시간/좌석 등급/좌석 위치 변경은 불가합니다. 자세한 안내가 필요할 경우 고객센터를 이용해주세요.</li>
                            <li>구단 홈페이지에서 예매한 내역은 구단 홈페이지에서만 확인이 가능합니다.</li>
                        </ol>
                </div>
            </div>
        </div>
    </div>
    
    <jsp:include page="/top_btn.jsp"/>
    
    <div id="footer">
        <div id="f_inner">
            <jsp:include page="/footer.jsp"/>
        </div>    
    </div>
</div>
</body>
</html>