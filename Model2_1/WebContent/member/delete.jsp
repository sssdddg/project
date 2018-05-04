<%@page import="net.member.db.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String id = (String)session.getAttribute("id");
	if(id == null) {
		response.sendRedirect("./MemberLogin.me");
	} 
%>
<form action = "MemberDeleteAction.me" method = "post" name = "fr" class = "join">
<div>
<fieldset>
<h2 class = "title">회원정보 삭제확인</h2>
</fieldset>
<fieldset>
<label>아이디 : </label><input type = "text"  name = "id"  size = "16" value = "<%=id%>" readonly>
</fieldset>
<fieldset>
<label>비밀번호 : </label><input type = "password" name = "pass" size = "16" >
</fieldset>
<fieldset id = "btm">
<input type = "submit" value = "삭제하기">
<input type = "button" value = "취소" onclick = "fun2()" >
</fieldset>
</div>
</form>
</body>
</html>