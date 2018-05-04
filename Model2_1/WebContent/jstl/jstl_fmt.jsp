<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- jstl/jstl_fmt.jsp -->
<fmt:setLocale value="en_US"/>
<fmt:formatNumber value="50000" type="currency"/><br>
<fmt:formatNumber value="0.15" type="percent"/><br>
<fmt:formatNumber value="500600300" pattern="###,###,###"/><br>

<jsp:useBean id="da" class="java.util.Date"/>
<fmt:formatDate value="${da }" type="date"/><br>
<fmt:formatDate value="${da }" type="time"/><br>
<fmt:formatDate value="${da }" type="both"/><br>
<fmt:formatDate value="${da }" pattern="yyyy-MM-dd"/><br>
</body>
</html>