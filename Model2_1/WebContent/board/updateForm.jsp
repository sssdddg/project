<%@page import="net.board.db.BoardBean"%>
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
String pageNum = request.getParameter("pageNum");
BoardBean bb = (BoardBean)request.getAttribute("bb");
%>
<!-- board/updateForm.jsp -->
<h1>게시판 글수정</h1>
<form action="./BoardUpdateAction.bo?pageNum=<%=pageNum %>" method="post">
<input type="hidden" name="num" value="<%=bb.getNum() %>">
<table border="1">
<tr><td>글쓴이</td>
	<td><input type="text" name="name" value="<%=bb.getName() %>"></td></tr>
<tr><td>비밀번호</td>
	<td><input type="password" name="pass"></td></tr>
<tr><td>제목</td>
	<td><input type="text" name="subject" value="<%=bb.getSubject() %>"></td></tr>
<tr><td>내용</td>
	<td><textarea cols="20" rows="10" name="content"><%=bb.getContent() %></textarea></td></tr>
<tr><td colspan="2">
		<input type="submit" value="글수정">
		<input type="button" value="취소" onclick="location.href='./BoardContent.bo?num=<%=bb.getNum() %>&pageNum=<%=pageNum %>'">
</td></tr>
</table>
</form>
</body>
</html>