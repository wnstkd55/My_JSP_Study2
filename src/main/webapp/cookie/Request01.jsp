<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Request ��ü�� ������ �޼ҵ��</title>
</head>
<body>

	<p> Ŭ���̾�Ʈ IP : <%= request.getRemoteAddr() %>
	<p> ��û ���� ���� : <%= request.getContentLength() %>
	<p> ��û ���� ���ڵ� : <%= request.getCharacterEncoding() %>
	<p> ��û ���� ������ ���� : <%= request.getContentType() %>
	<p> ��û ���� �������� : <%=request.getProtocol() %>
	<p> ��û ���� URL : <%= request.getRequestURL() %>
	<p> ��û ���� URI : <%= request.getRequestURI() %>
	<p> ���ؽ�Ʈ ��� : <%= request.getContextPath() %>
	<p> ���� ���� �̸� : <%= request.getServerName() %>
	<p> ���� ���� ��Ʈ : <%= request.getServerPort() %>
	<p> ������ �������� ��� : <%= request.getRealPath("cookie") %>
	
	
</body>
</html>