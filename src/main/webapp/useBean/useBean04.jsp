<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id = "person" class = "dao.Person" scope = "request"/>
	
	<p> ���̵� : <%= person.getId() %>
	<p> �̸� : <%=person.getName() %>
	<p>
	<p>
	
		<%
			//Setter ����
			person.setId(20220530);
			person.setName("������");
		%>
		
		<jsp:include page = "useBean03.jsp"/>
</body>
</html>