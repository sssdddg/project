<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>InsertForm.jsp</title>
<link href = "test.css" rel = "stylesheet" >
<script type="text/javascript">
	function fun1() {
		
		var a = confirm("정말 회원가입 하시겠습니까?");
		if(a == true) {
			document.fr.submit();
		} else {
			document.fr.reset();
		}
		
	}
</script>
</head>
<body>
<form action = "./MemberJoinAction.me" method = "post"  class = "join"  name = "fr">
<div>
<fieldset>
<h2 class = "title">회원가입 페이지</h2>
</fieldset>
<fieldset>
<label>아이디 : </label><input type = "text" name = "id"  size = "16">
</fieldset>
<fieldset>
<label>비밀번호 : </label><input type = "password" name = "pass"  size = "16">
</fieldset>
<fieldset>
<label>비밀번호확인 : </label><input type = "password" name = "pass2"  size = "16">
</fieldset>
<fieldset>
<label>이름 : </label><input type = "text" name = "name"  size = "16">
</fieldset>
<fieldset>
<label>나이 : </label><input type = "text" name = "age"  size = "2">
</fieldset>
<fieldset>
<label>성별 : </label><input type = "radio" value = "man"  name = "gender" checked>남자
									<input type = "radio" value = "woman" name = "gender">여자
</fieldset>
<fieldset>
<label>이메일 주소 : </label><input type = "text" name = "email"  size = "25">
</fieldset>
<fieldset id = "btm">
<input type = "button" value = "회원가입" onclick = "fun1()">
<input type = "reset" value = "나가기" onclick = "location.href = 'Login.jps'">
</fieldset>
</div>
</form>
</body>
</html>