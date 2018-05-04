<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- jstl/jstl_core.jsp -->
<!-- JSTL(JSP Standard Tag Library) -->
<!-- standard.jar , jstl.jar 설치 -->
<c:set var="test" value="Hello JSTL"/>
변수 설정후 출력 : <c:out value="${test }"/>
<br>
<c:remove var="test"/>
변수 삭제후 출력 : <c:out value="${test }"/>
<br>
<c:catch var="err">
<%=10/0 %>
</c:catch>
잡아낸 오류 : <c:out value="${err }"/>
<br>
<c:if test="${5<10 }">
<h3>5는 10보다 작다</h3>
</c:if>

<c:if test="${5>10 }">
<h3>5는 10보다 크다</h3>
</c:if>

<c:choose>
	<c:when test="${5+10==50 }">
		<h3>5+10은 50이다</h3>
	</c:when>
	<c:otherwise>
		<h3>5+10은 50이 아니다</h3>
	</c:otherwise>
</c:choose>

<c:forEach var="i" begin="1" end="10" step="2">
	${i }
</c:forEach>
<br>
<c:forTokens var="alpa" items="a,b,c,d,e" delims=",">
	${alpa }
</c:forTokens>
<br>
<c:set var="data" value="홍길동;김길동;고길동"/>
<c:forTokens var="na" items="${data}" delims=";">
	${na }
</c:forTokens>
</body>
</html>