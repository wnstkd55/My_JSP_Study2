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
	<p> ���̵� : <jsp:getProperty property="id" name="id"/>
		<!-- person��ü�� id �÷��� getter�� ���ؼ� ����(getId()) -->
	<p> �̸� : <jsp:getProperty property="name" name="person"/>
		<!--  person��ü�� name�� getter�� ���ؼ� ����(getName()) --> %>
	<p><p><p>
	
	<p> ���̵� : <%= person.getId() %>
	<p> �̸�: <%= person.getName() %>
	
	
	<p><p><p>
	
	<p> ���̵� : <% out.println(person.getId()); %>
	<p> �̸� : <% out.println(person.getName()); %>
	
</body>
</html>