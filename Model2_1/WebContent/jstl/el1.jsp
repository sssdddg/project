<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    session.setAttribute("stest", "session Value");
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- jstl/el1.jsp -->
<!-- EL(Expression Language) 표현어 -->
<%-- <%= %> ${ } --%>
<!-- 내장객체,연산자 -->
<form action="el2.jsp" method="post">
이름 : <input type="text" name="name">
<input type="submit" value="전송">
</form>
</body>
</html>