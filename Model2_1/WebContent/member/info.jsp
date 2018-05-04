<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="net.member.db.MemberBean"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	.join {
	width : 450px ;
	 border:1px solid black;
	 margin:0 auto;
	}
	fieldset {
		border : none ;
		border-bottom:1px solid black;
	}
	.title {
/* 	border : 1px solid ; */
	padding : 0 ;
	margin : 0 ;
	text-align: center;
	}
	div {
		margin: 0 auto;
		width : 400px ;
	}
	div #btm {
		border : none ;
		text-indent: 125px ;
	}
	.lab {
		 display:inline-block;
		 width:150px;
	}
</style>
<title>Info</title>
</head>
<body>
<%
	String id = (String)session.getAttribute("id");
	if(id == null) {
		response.sendRedirect("./MemberLogin.me");
	} 
	MemberBean mb = (MemberBean)request.getAttribute("mb");
%>

<form class = "join">
<div>
	<fieldset>
		<h2 class = "title">회원정보조회</h2>
	</fieldset>
	<fieldset>
		<label class = "lab">아이디 : </label><label><%=mb.getId()%></label>
	</fieldset>
	<fieldset>
		<label class = "lab">비밀번호 : </label><label><%=mb.getPass() %></label>
	</fieldset>
	<fieldset>
		<label class = "lab">이름 : </label><label><%=mb.getName()%></label>
	</fieldset>
	<fieldset>
		<label class = "lab">가입날짜 : </label><label><%=mb.getReg_date() %></label>
	</fieldset>
	<fieldset>
		<label class = "lab">E-Mail : </label><label><%=mb.getEmail() %></label>
	</fieldset>
	<fieldset  id = "btm">
		<input type = "button" value = "수정하기" onclick = "location.href = 'update.jsp'">
		<input type = "button" value = "나가기" onclick = "location.href = 'Main.jsp' ">
	</fieldset>
</div>
</form>


</body>
</html>