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
	try{
	Cookie[] cookies = request.getCookies();

	out.println("���� ����� ��Ű ����" + cookies.length + "<p>");
	out.println("============<p>");

	for(int i = 0; i < cookies.length; i++){
		out.println("��Ű�� �Ӽ��̸� ["+i+"] : " + cookies[i].getName() + "<br>");
		out.println("��Ű�� �Ӽ��� ["+i+"] : " + cookies[i].getValue() + "<br>");
		out.println("��Ű�� ��ȿ�ð� :" + cookies[i].getMaxAge()+"<br>");
		
		out.println("=================== <p>");
		}	
	}catch(Exception e){
		e.printStackTrace();
		out.println("��Ű ó���� ���� �߻�, ���ΰ�ħ�� �ٽ��ϼ���");
		response.sendRedirect("cookie02.jsp");
	}
	out.println("<p><p> request.getContextPath(): "+request.getContextPath());

%>

</body>
</html>