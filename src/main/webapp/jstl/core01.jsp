<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<!-- JSTL ����� -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	browser ��������
	<c:set var="browser" value = "${header['User-Agent']}" />
	
	<br>
	<c:out value = "${browser}"/>
	
	<p> browser ������ ������
		<c:remove var = "browser"/>
		<c:out value = "${browser}"/>
	
	
</body>
</html>