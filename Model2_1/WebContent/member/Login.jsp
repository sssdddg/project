<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main</title>
<link href = "test.css" rel = "stylesheet">

</head>
<body>
<form action = "./MemberLoginAction.me" method = "post" name = "fr" class = "join">
<div>
<fieldset>
<h2 class = "title">로그인 페이지</h2>
</fieldset>
<fieldset>
<label>아이디 : </label><input type = "text"  name = "id"  size = "16">
</fieldset>
<fieldset>
<label>비밀번호 : </label><input type = "password" name = "pass" size = "16" >
</fieldset>
<fieldset id = "btm">
<input type = "submit" value = "로그인">
<input type = "button" value = "회원가입" onclick = "location.href='./MemberJoin.me'" >
</fieldset>
</div>
</form>

</body>
</html>