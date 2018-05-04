<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- jstl/el2.jsp -->
<h3>세션 : ${sessionScope.stest }</h3>
<h3>파라미터 : ${param.name }</h3>
<h3>\${10%3 }=${10%3 }</h3>		<!-- 출력결과 : ${10%3}=1 -->
<h3>\${10>3 }=${10>3 }</h3>		<!-- 출력결과 : ${10>3}=true -->
</body>
</html>