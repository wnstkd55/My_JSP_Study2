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
		String user_id = request.getParameter("id");
		String user_pw = request.getParameter("pass");
		
		if(user_id.equals("admin") && user_pw.equals("1234")){
			//��Ű ���� , Ŭ���̾�Ʈ HDD�� ��Ű�� ���� ����
			
			//��Ű�� �����ڿ��� ���� �̸��� ���� �Ҵ�;
			Cookie cookie_id = new Cookie("userID", user_id);
			Cookie cookie_pw = new Cookie("userPW", user_pw);
			
			//��Ű ����
			cookie_id.setPath(request.getContextPath());		//��Ű�� ����� ��� ����
			cookie_id.setMaxAge(60*60);		//3600�� == 1�ð�
			
			cookie_pw.setPath(request.getContextPath());
			cookie_pw.setMaxAge(60*60);
			
			
			//��Ű�� response ��ü�� ����ؼ� ������ ��Ű�� Client HDD�� ����
			response.addCookie(cookie_id);
			response.addCookie(cookie_pw);
			
			out.println("��Ű ������ �����Ͽ����ϴ�.");
			
			out.println(user_id + "�� ȯ���մϴ�.");
			
		}else{
			out.println("��Ű������ �����Ͽ����ϴ�.");
		}
	
	%>

</body>
</html>