<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main</title>
<script type="text/javascript">
	function fun1() {
		location.href = "info.jsp";
	}
	function fun2() {
		location.href = "update.jsp"
	}
	function fun3() {
			location.href = "delete.jsp";
	}
</script>
<style>
	body {
		padding : 0 ;
		margin : 0 ;
	}
	
	fieldset {
		border : none ;
	}
	
	div { 
 		border : 1px solid black ; 
		width : 350px ;
		height : 120px ;
		margin : 0 auto ;
		margin-top: 10px ;
	}
	
	input {
 	border : 1px solid black ; 
		line-height: 1.5 ;
		margin : 0 ;
		padding : 5 ;
		margin-bottom: 10px ;
		margin-right: 5px ;
	}
	
	h2 {
/* 	border : 1px solid red ; */
		padding : 0 ;
		margin : 0 ;
		display : inline;
		float : left ;
	}
	
	.a {
		position: relative;
		left : 230px ;
		bottom : 93px ;
	}
	
	
</style>
</head>
<body>
<div>
<fieldset>
<%
	String s = (String)session.getAttribute("id");
	if(s == null) {
		response.sendRedirect("./MemberLogin.me");
	}
%>
<%="<h2>"+s+"님<br> 로그인하셨습니다</h2>" %>
</fieldset>
<fieldset>
<input type = "button" value = "정보확인" onclick = "location.href = './MemberInfoAction.me'">
<input type = "button" value = "정보수정" onclick = "location.href = './MemberUpdateAction.me'">
<input type = "button" value = "회원탈퇴" onclick = "location.href = './MemberDelete.me'">
<input type = "button" value = "로그아웃" onclick = "location.href = './MemberLogoutAction.me'">
<input type = "button" value = "상품보기" onclick = "location.href = './GoodsList.go'">
</fieldset>
<fieldset class = "a">
<%
	if(s != null) {
		if(s.equals("admin")) {
			%>
				<input id = "adim" type = "button" value = "회원관리" onclick = "location.href = 'Logout.me'">
			<%
		}
	}
%>
</fieldset>
</div>
</body>
</html>