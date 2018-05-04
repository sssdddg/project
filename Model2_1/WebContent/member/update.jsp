<%@page import="net.member.db.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update</title>
<link href = "test.css" rel = "stylesheet" >
<script type="text/javascript">
	function fun2() {
		if(document.fr.pass.value == document.fr.pass2.value) {
			
		} else {
			alert("비밀번호를 일치시켜주세요");
			document.fr.pass.select();
		}
		
		var a = confirm("정말 수정하시겠습니까?");
		if(a == true) {
			document.fr.submit();
		} 
	}
</script>
</head>
<body>
<%
	String id = (String)session.getAttribute("id");
	if(id == null) {
		response.sendRedirect("./MemberLogin.me");
	}
	
	MemberBean mb = (MemberBean)request.getAttribute("mb");
	
%>


<form action = "MemberUpdate.me" method = "post" name = "fr" class = "join">
<div>
<fieldset>
<h2 class = "title">회원정보수정</h2>
</fieldset>
<fieldset>
<label>아이디 : </label><input type = "text" name = "id"  size = "16" value = "<%=mb.getId() %>" readonly>
</fieldset>
<fieldset>
<label>비밀번호 : </label><input type = "password" name = "pass"  size = "16" >
</fieldset>
<fieldset>
<label>비밀번호확인 : </label><input type = "password" name = "pass2"  size = "16" >
</fieldset>
<fieldset>
<label>이름 : </label><input type = "text" name = "name"  size = "16" value = "<%=mb.getName()%>">
</fieldset>
<fieldset>
<label>이메일 주소 : </label><input type = "text" name = "email"  size = "25" value = "<%=mb.getEmail()%>">
</fieldset>
<fieldset id = "btm">
<input type = "button" value = "수정완료" onclick = "fun2()">
<input type = "button" value = "취소" onclick = "location.href = 'Main.jsp'">
</fieldset>
</div>
</form>
</body>
</html>