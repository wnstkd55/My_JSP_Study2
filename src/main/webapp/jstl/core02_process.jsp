<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<%
	String number = request.getParameter("number");
%>

	스크립트 릿 태그로 출력
	<%= number %> 	<p>
	
	
	
	 jstl로 출력
	<c:out value = "${param.number}"/>	<p>

	
<c:choose>
	<c:when test = "${param.number % 2 == 0 }">
		<c:out value = "${param.number }"/>는 짝수입니다.
	</c:when>
	<c:when test = "${param.number % 2 == 1 }">
		<c:out value = "${param.number }"/>는 홀수입니다.
	</c:when>
	<c:otherwise>
		숫자가 아닙니다.
	</c:otherwise>
</c:choose>

</body>
</html>