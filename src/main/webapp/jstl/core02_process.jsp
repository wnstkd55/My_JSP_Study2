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

	��ũ��Ʈ �� �±׷� ���
	<%= number %> 	<p>
	
	
	
	 jstl�� ���
	<c:out value = "${param.number}"/>	<p>

	
<c:choose>
	<c:when test = "${param.number % 2 == 0 }">
		<c:out value = "${param.number }"/>�� ¦���Դϴ�.
	</c:when>
	<c:when test = "${param.number % 2 == 1 }">
		<c:out value = "${param.number }"/>�� Ȧ���Դϴ�.
	</c:when>
	<c:otherwise>
		���ڰ� �ƴմϴ�.
	</c:otherwise>
</c:choose>

</body>
</html>