<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "java.util.Enumeration" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h2> session ��ü�� ����� ��� ������ ���� �����ö�(getAttribute)</h2>

<%
	String name ;	//session ��ü�� ����� �ʵ��
	String value ; //session ��ü�� ����� �ʵ��� ��
	
		//session ��ü�� ��� ��� �ʵ带 �����´�.
	Enumeration en = session.getAttributeNames();
	
	int i = 0;
	
	while(en.hasMoreElements()){
		i++;
		name = en.nextElement().toString();		//session��ü�� ����� �������� �����´�.
		value = session.getAttribute(name).toString();
		
		out.println("������ ���� �Ӽ� �̸�["+i+"]: "+name+ "<br>");
		out.println("������ ���� �Ӽ� ��["+i+"]: "+value+ "<br>");
	}
%>

</body>
</html>