<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%@ page import = "common.DBConnPool" %>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

 <h3> DBCP Ŀ�ؼ� �׽�Ʈ </h3>
<% DBConnPool pool = new DBConnPool();    //Ŀ�ؼ� ��ü ���� 

	pool.close();    //Ŀ�ؼ� ��ü �ݳ�
%>


</body>
</html>