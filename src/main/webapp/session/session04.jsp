<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<h2> session ������ Ư�� �Ӽ�(�ʵ�)�� ���� ����</h2>

<h4>============ ���� ������ �����ϱ� �� =========</h4>
<%
	String user_id = (String)session.getAttribute("userID");
	String user_pw = (String)session.getAttribute("userPW");
	
	out.println("������ �����̸�(userID) : "+user_id);
	out.println("������ ���Ǻ�й�ȣ(userPW) : "+user_pw);
	
	//session ��ü�� ����� Ư�� �Ӽ�(�ʵ�)�� ����(�ϳ��� ����)
	//��ٱ��Ͽ� ���� ���� session�� ���� �� Ư�� ���� ������ �� ���.
	session.removeAttribute("userID");
%>

<h4>============ ���� ������ �����ϱ� �� =========</h4>
<%
	user_id = (String)session.getAttribute("userID");
	user_pw = (String)session.getAttribute("userPW");
	
	out.println("������ �����̸�(userID) : "+user_id);
	out.println("������ ���Ǻ�й�ȣ(userPW) : "+user_pw);
%>

</body>
</html>