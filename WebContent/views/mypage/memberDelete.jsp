<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" type="text/css" href="/css/mypageAllSize.css" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원탈퇴</title>
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
    #left_menu .has_sub1 li:last-child{
        padding: 13px 0 0;
        height: 12px;
        border: none;
        font-size: 13px;
    }
    #left_menu .has_sub1 li:last-child a{
        color: lightskyblue;
        font-weight: bold;
    }
    #left_menu .has_sub1 li:first-child{
        padding: 13px 0 0;
        height: 12px;
        border: none;
    }
    #left_menu .has_sub1 li:first-child a{
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
        margin-bottom: 20px;
    }
    #right_view .r_top{
        padding: 30px 0 30px 0;
        background-color: #f0f0f0;
        text-align: center;
        font-size: 18px;
    }
    
    /* right_view 하단 버튼관리 */
    .list_btn{
        margin-top: 50px;
        text-align: center;
    }
    .list_btn button{
        background-color: skyblue;
        border-radius: 15px;
        color: white;
        padding: 10px;
        font-size: 20px;
    }
    
    
</style>
</head>
<script>
    $(document).ready(function(){
        $('.cancelBtn').click(function(){
        	
        	var chk = false;

        	if($('input:checkbox[id="checkbox1"]').is(":checked")){
        		chk = true;
        	}else{
        		chk = false;
        	}
        	
        	if(chk){
        		if(confirm("정말로 탈퇴하시겠습니까?")){
                    location.href="/memberDelete.do";
                }else{                
                }
        	}else{
        		alert("동의 후 탈퇴가 가능합니다.");
        	}
        });
    })
</script>
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
                        <li><a href="/reviewMgr.do">후기 관리</a></li>
                        <li><a href="/qnaMgr.do">나의 문의 내역</a></li>
                        <li><a href="/promoMgr.do">소규모 공연 등록관리</a></li>
                    </ul>
                </li>
                <li class="has_sub1">
                    <span>회원정보관리</span>
                    <ul>
                        <li><a href="/myMemberUpdate.do">회원 정보 수정</a>
                        <li><a href="/views/mypage/memberDelete.jsp">회원 탈퇴</a></li>
                    </ul>
                </li>
            </ul>    
            <div id="right_view">
                <div class="r_line">
                    <h3>회원탈퇴</h3>
                </div>
                <div class="r_top">
                    이용에 불편을 드려 죄송합니다.<br>
                    회원 탈퇴시 정보가 복귀되지 않습니다.
                </div>
                <h4>탈퇴 전 유의사항</h4>
                <textarea rows="10" cols="105" style="resize:none;">
1.회원탈퇴 시 고객님의 회원정보는 영구 삭제되며, NHN티켓링크 서비스 관련 정보 역시 삭제됩니다.
    ① 예매권 : 등록된 모든 예매권 삭제 후 이용 불가
    ② 마일리지 : 현재 적립/전환된 모든 현금성 마일리지 삭제 후 환원 불가
    ③ 할인쿠폰 : 등록한 모든 할인쿠폰 삭제 후 재등록 불가
    ④ 티켓 : 구매한 티켓 복구 및 환원 불가
    ⑥ 기타 : 회원으로 활동하며 누적 사용한 정보
2.전자상거래에서의 소비자 보호를 위해 고객님의 예매 관련정보는 5년간 보관하며, 이후 완전 파기됩니다.
3.작성하신 게시물은 삭제되지 않으며, 삭제가 필요한 경우 게시물을 직접 삭제한 후 회원탈퇴를 하시기 바랍니다.
4.회원탈퇴 후에도 다시 NHN티켓링크 회원으로 가입하실 수 있습니다. 그러나 탈퇴 전 회원 정보에 대한 이전이나 연결된 정보 저장 등은 불가능합니다.
5.NHN티켓링크 메일은 예약발송이 되므로 회원탈퇴 후에도 약 5일 동안은 NHN티켓링크 관련 메일이 전송되니 이 점 양해 부탁 드립니다.
                </textarea>
                <div class="mberck">
                    <span>
                        <input type="checkbox" id="checkbox1" />
                    </span>
                    위 내용을 모두 확인하였으며 동의합니다.
                </div>
                <div class="list_btn">
                    <button class="cancelBtn">회원 탈퇴</button>
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