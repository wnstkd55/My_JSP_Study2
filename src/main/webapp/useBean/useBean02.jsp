<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id = "bean" class = "dao.Calculator"></jsp:useBean>
	<!-- Calculator Ŭ������ bean ��ü�� ��� -->
	<!-- Calculator bean = new Calculator(); -->
	
	<%
		int m = bean.process(5);
	
		out.print("5�� 3������ : "+m);
	%>
	
	<p><p>
	5�� 3������ : <%= m %>
	
	
</body>
</html>