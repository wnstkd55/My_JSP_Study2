<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Java Bean ���</title>
</head>
<body>
	<jsp:useBean id = "date" class = "java.util.Date"/>
	
	<!-- Java Bean�� JSP ���������� *.java���������� ������ ó���ϵ��� JSP ���������� ���� -->
	
	<p> <%
		out.println("������ ��¥ �� �ð�");
	%>
	
	<p> <%= date %>
</body>
</html>