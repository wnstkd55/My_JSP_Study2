<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
	Cookie[] cookies = request.getCookies();
	
	for(int i = 0; i<cookies.length; i++){
		cookies[i].setPath(request.getContextPath());
		cookies[i].setMaxAge(0);	//��� ��Ű�� ������ ����
		
		//Ŭ���̾�Ʈ�� ���� : response.addCookie(cookie[i]);
		response.addCookie(cookies[i]);
		
		out.println("��Ű���� ����");
	}
	
	//������ �̵�
	response.sendRedirect("cookie02.jsp");


%>
</body>
</html>