<%@page import="net.board.db.BoardBean"%>
<%@page import="java.util.List"%>
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
int count = ((Integer)request.getAttribute("count")).intValue();
String pageNum = (String)request.getAttribute("pageNum");
List<BoardBean> boardList = (List<BoardBean>)request.getAttribute("boardList");
int pageCount = ((Integer)request.getAttribute("pageCount")).intValue();
int pageBlock = ((Integer)request.getAttribute("pageBlock")).intValue();
int startPage = ((Integer)request.getAttribute("startPage")).intValue();
int endPage = ((Integer)request.getAttribute("endPage")).intValue();
%>
<!-- board/list.jsp -->
<h1>게시판 글목록 [전체글개수 : <%=count %>]</h1>
<h1><a href="./BoardWrite.bo">게시판 글쓰기</a> <a href="./BoardFileWrite.bo">파일 업로드</a></h1>
<table border="1">
<tr><td>번호</td><td>제목</td><td>작성자</td><td>날짜</td><td>조회수</td></tr>
<%
for(int i=0; i<boardList.size(); i++){
	BoardBean bb = boardList.get(i);	//제너릭 사용해서 형변환 할 필요없음
%>
<tr><td><%=bb.getNum() %></td>
	<td>
	<%
		if(bb.getRe_lev()>0){	//답글이면
			%><img alt="blank" src="./image/level.gif" width="<%=bb.getRe_lev()*10 %>">
			<img alt="re" src="./image/re.gif">	<%//들여쓰기 공백이미지 10 20 30, 답글 이미지
		}
	%>
	<a href="./BoardContent.bo?num=<%=bb.getNum() %>&pageNum=<%=pageNum %>"><%=bb.getSubject() %></a></td>
	<td><%=bb.getName() %></td><td><%=bb.getDate() %></td><td><%=bb.getReadcount() %></td></tr>
<%	
}
%>

</table>
<%
if(count != 0){
		
	//이전
	if(startPage > pageBlock){
		%><a href="./BoardList.bo?pageNum=<%=startPage-1 %>">[이전]</a>&nbsp;<%
	}
	
	//1~10	11~20	21~30
	for(int i=startPage; i<=endPage; i++){
		%>&nbsp;<a href="./BoardList.bo?pageNum=<%=i %>"><%=i %></a>&nbsp;<%
	}
	
	//다음
	if(endPage < pageCount){
		%>&nbsp;<a href="./BoardList.bo?pageNum=<%=startPage+pageBlock %>">[다음]</a><%
	}
}
%>
</body>
</html>