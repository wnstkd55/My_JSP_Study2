<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<H2> ���� ���� Ȯ��</H2>
	<h4>=====Web.xml ������ ���� ���� ���======</h4>
���� ���� �Ⱓ ���� ���� : <%= session.getMaxInactiveInterval()/60 %> ��

<p><p>

<h4>==========���� ���� ���� ���� ===========</h4>
<%
	session.setMaxInactiveInterval(60*60);

	int time = session.getMaxInactiveInterval() / 60;
	
	out.println("���� ��ȿ �ð�" + time + "��");
%>


</body>
</html>