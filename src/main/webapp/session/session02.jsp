<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h2> session ��ü�� ������ �Ҵ�� �� ��������</h2>

<%
	//Session ��ü�� ���� �����ö� ObjectŸ���̹Ƿ� �ٿ� ĳ������ �ʿ�
	//��� ���������� ������ ������ �� ���� �����ͼ� null�ϰ�� : 
	String user_id = (String)session.getAttribute("userID");

	String user_pw = (String)session.getAttribute("userPW");
	
	out.println("<p>������ ������ �Ӽ��� 1 : "+user_id);
	out.println("<p>������ ������ �Ӽ��� 2 : " +user_pw);
%>
</body>
</html>