<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import = "java.util.Date" %>
<%@page import = "java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h2> ���� ���� Ȯ��</h2>
<%
	SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");//��¥ ǥ�� ����
	long creationTime = session.getCreationTime();	//Unix �ð����� ���
	String creationTimeStr = dateFormat.format(new Date(creationTime));
	
	long lastTime = session.getLastAccessedTime();	//Unix �ð����� ���
	String lastTimeStr = dateFormat.format(new Date(lastTime));

%>


<ul>
	<li> ���� ���� �Ⱓ ���� ���� : <%= session.getMaxInactiveInterval() / 60 %> (��)</li>
	<li> ���� ���̵� : <%= session.getId() %>		</li>
	<li> ���� ������ ������ �ð� : <%= creationTimeStr %></li>
		<!--  Unix �ð����� ��� : 1970.01.01 ���� �ð� ������ (��) -->
	<li> ������ ��û �ð� : <%=lastTimeStr %></li>

</ul>
</body>
</html>