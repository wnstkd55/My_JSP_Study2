<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ page import = "com.model.LoginBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�α��� ���� ������</title>
</head>
<body>
	<p> �α��� �����߽��ϴ�.
	
	<p>
	<% 
		LoginBean bean = (LoginBean) request.getAttribute("bean"); // ����Ʈ �ʿ�
			// Mycontroller => request.setAttribute("bean", bean);  //"����",bean(��ü) => "bean"�� bean��ü ��ü�� ������
			// "bean" ������ �� ������
			// set���� �� ����, get���� �� ������
			
		out.println("���̵� : " + bean.getId()); //bean �� id������ ����
		
	%>
</body>
</html>