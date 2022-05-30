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
	
	<p> 아이디 : <%= person.getId() %>
	<p> 이름 : <%=person.getName() %>
	<p>
	<p>
	
		<%
			//Setter 주입
			person.setId(20220530);
			person.setName("김유신");
		%>
		
		<jsp:include page = "useBean03.jsp"/>
</body>
</html>