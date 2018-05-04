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
request.setCharacterEncoding("utf-8");


//num, re_ref, re_lev, re_seq 파라미터 가져오기
int num = Integer.parseInt(request.getParameter("num"));
int re_ref = Integer.parseInt(request.getParameter("re_ref"));
int re_lev = Integer.parseInt(request.getParameter("re_lev"));
int re_seq = Integer.parseInt(request.getParameter("re_seq"));
%>
<h1>답글쓰기</h1>
<form action="./BoardReWriteAction.bo" method="post">
<input type="hidden" name="num" value="<%=num %>">
<input type="hidden" name="re_ref" value="<%=re_ref %>">
<input type="hidden" name="re_lev" value="<%=re_lev %>">
<input type="hidden" name="re_seq" value="<%=re_seq %>">


<table border="1">
<tr><td>글쓴이</td>
	<td><input type="text" name="name"></td></tr>
<tr><td>비밀번호</td>
	<td><input type="password" name="pass"></td></tr>
<tr><td>제목</td>
	<td><input type="text" name="subject"></td></tr>
<tr><td>내용</td>
	<td><textarea cols="20" rows="10" name="content"></textarea></td></tr>
</table>

<input type="submit" value="답글작성">
<input type="reset" value="다시쓰기">
<input type="button" value="뒤로가기" onclick="history.back()">

</form>
</body>
</html>