<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h2> ���ǿ� ����� ��� �Ӽ�(�ʵ�)�� ����(�α׾ƿ�) : session.invalidate()</h2>

<h4>============ ���� ������ �����ϱ� �� =========</h4>
<%
	String user_id = (String)session.getAttribute("userID");
	String user_pw = (String)session.getAttribute("userPW");
	
	out.println("������ �����̸�(userID) : "+user_id);
	out.println("������ ���Ǻ�й�ȣ(userPW) : "+user_pw);

	if(request.isRequestedSessionIdValid()==true){
		out.println("������ ��ȿ�մϴ�.");
	}else{
		out.println("������ ��ȿ���� �ʽ��ϴ�.");
	}
	session.invalidate();		//������ ��� �ʵ��� ���� �Ѳ����� ����(//logout)
	
%>
<h4>============ ���� ������ ���� �� =========</h4>
<%
if(request.isRequestedSessionIdValid()==true){
	out.println("������ ��ȿ�մϴ�.");
}else{
	out.println("������ ��ȿ���� �ʽ��ϴ�.");
}

%>
</body>
</html>