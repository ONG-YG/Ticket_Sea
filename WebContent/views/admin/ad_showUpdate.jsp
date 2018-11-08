<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="kr.co.ticketsea.admin.show.model.vo.*"%>
<% 
	Show show = (Show)request.getAttribute("showData");
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>���� ���� </title>
<!-- ��Ÿ��  -->
<!-- �ܺ� ��Ÿ�� ��Ʈ �ҷ����� -->
    <link href="../../css/admin_common.css" rel="stylesheet" type="text/css">
    <link href="../../css/ad_showUpdate.css" rel="stylesheet" type="text/css">
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
                    <li><a href="#">��������</a>
                        <ul>
                             <li><a href="/adShowPlace.do">�������</a></li>
                            <li><a href="/adShowList.do">�������</a></li>
                        </ul>
                    </li>    
                    <li><a href="#">ȸ������</a>
                        <ul>
                            <li><a href="/adMemberList.do">ȸ�����</a></li>
                        </ul>
                    </li>
                    <li><a href="#">���Ű���</a>
                        <ul>
                            <li><a href="/adReserveList.do">���Ÿ��</a></li>
                        </ul>
                    </li>
                    <li><a href="#">�ұԸ����</a>
                        <ul>
                            <li><a href="/miniShowList.do">���δ�����</a></li>
                            <li><a href="/msApproveList.do">���οϷ����</a></li>
                        </ul>
                    </li>
                </ul>
                </div>
            </aside>
           <div class="content-header">
              <div id="content">
        <div class="content_wrap">
            <div class="top_area">
                <h2 class="main_title">�������</h2>
            </div>
            <div class="main_area">
                <div class="left_wrap">
                <div class="input_area">
                    <!--�̹�������-->
                    <div class="event_img_area">
                        <img class="show_post" src="../../img/ticketsea_poster.png" data-default-src="../../img/ticketsea_poster.png" alt="����������" style="width:160px; height:160px;">
                        <!--�̹��� ���ε� ��ư-->
                        <div class="upload_btn">
                            <input type="file" class="imgupload" title="���������;��ε�" value="�̹������ε�">
                            <button type="button" class="btn_change_img">��������</button>
                            <!--���� ���ε� �� ���� �����ϱ� ��ư ����-->
                            <!--<button type="button" class="btn_delete_upload">�����ϱ�</button>-->
                        </div>
                    </div>
                </div>
                </div>
                
                <div class="right_wrap">
                    <!--ī�װ� / ������-->
                    <fieldset class="edit_title">
                        <legend>
                            <h3 class="title">ī�װ�/������</h3>
                        </legend>
                        <div class="edit">
                            <div class="write_wrap">
                                <select class="category_select" id="category" name="sc_code">
                                    <%for(ShowCategory sc : scList) {%>
                                    <option value="<%=sc.getSc_code()%>"><%=sc.getSc_name()%></option>
                            		<%} %>
                                </select>
                                <input id="title" type="text" name="show_name" class="title_input" placeholder="�������� �Է��� �ּ���." value="<%=show.getShow_name() %>" maxlength="64" autofocus="autofocus">
                            </div>
                        </div>
                    </fieldset>
                    <fieldset class="edit_date">
                        <legend>
                          <h3 class="title">�����Ⱓ</h3>
                        </legend>
                        <div class="edit">
                        <!--���� �Ͻ�-->
                        <div class="start_time" id="edit_date_start_time">
                            <div class="write_wrap">
                                <input type="text" id="startEventDate" name="show_st_date" value="<%=show.getShow_st_date() %>" placeholder="������"> ~ <input type="text" id="endEventDate" name="show_ed_date" value="<%=show.getShow_ed_date() %>" placeholder="������">
                            </div>
                        </div>
                        </div>
                    </fieldset>
                    <fieldset class="edit_place">
                        <legend>
                          <h3 class="title">�������</h3>
                        </legend>
                        <div class="edit">
                           <select id="place" class="place_select" name="">
                                    <option value="1" selected="selected"><%=show.getSc_code() %></option>
                                    <option value="10000">���Ե��þ�Ƽ</option>
                                    <option value="10001"></option>
                          </select>
                        </div>
                    </fieldset>
                    <fieldset class="edit_artist">
                        <legend>
                          <h3 class="title">�⿬��</h3>
                        </legend>
                        <div class="edit">
                            <div class="write_wrap">
                                <input type="text" id="artist" name="artists" value="<%=show.getArtists() %>" placeholder="�⿬������ (,�� ����)">
                            </div>
                        </div>
                    </fieldset>
                    <fieldset class="edit_grade">
                        <legend>
                          <h3 class="title">�������</h3>
                        </legend>
                        <div class="edit">
                            <div class="write_wrap">
                                <input type="text" id="grade" name="show_grd" value="<%=show.getShow_grd() %>" placeholder="">
                            </div>
                        </div>
                        
                    </fieldset>
                     <fieldset class="edit_time">
                        <legend>
                          <h3 class="title">�����ð�</h3>
                        </legend>
                        <div class="edit">
                            <div class="write_wrap">
                                <input type="text" id="runTime" name="show_run" value="<%=show.getShow_run() %>" placeholder="">
                            </div>
                        </div>
                    </fieldset>
                     <fieldset class="edit_price">
                        <legend>
                          <h3 class="title">����</h3>
                        </legend>
                        <div class="edit">
                            <div class="write_wrap">
                                <input type="text" id="price" name="price" value="" placeholder="">
                            </div>
                        </div>
                    </fieldset>
                    <fieldset class="edit_detailInfo">
                        <legend>
                          <h3 class="title">��������</h3>
                        </legend>
                        <div class="edit">
                            <div class="write_wrap">
                            <!-- form �±� �ȿ� form   -->
                                 <!-- <form action="http://localhost/insert.html" method="post" enctype="multipart/form-data">
                                	<input type="file">
                                <input type="submit">
                                </form> -->
                            </div>
                        </div>
                    </fieldset>
                </div>
            </div>
            <div class="submit_area">
            <input type="submit" value="��������" style="float:right;" width="70px" height="40">
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