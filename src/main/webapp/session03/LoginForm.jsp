<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Session</title>
</head>
<body>
<table border = "1" width = "90%">
	<tr>
		<td align = "center">
		<!-- 로그인 여부에 따른 메뉴변화 -->
		<% if(session.getAttribute("UserID") == null){ %>
			<a href = "../session03/LoginForm.jsp">로그인</a>
			
		<% } else { %>
			<a href = "../session03/Logout.jsp">로그아웃</a>
		<% } %>
		</td>
	</tr>
</table>

<h2> 로그인 페이지</h2>

<span style = "color:red; font-size: 1.2em;">
	<!-- 로그인 에러 메세지 출력 -->
	<%= request.getAttribute("LoginErrMsg") == null ? "" : request.getAttribute("LoginErrMsg")%>
</span>

<%
	if(session.getAttribute("UserId")==null){	//로그 아웃 상태일때
%>
	<!--  로그아웃 상태일때 HTML 처리 부분 -->
	
	<script>
		function vaildateForm (form){
			if(!form.user_id.value){
				alert("아이디를 입력하세요");
				return false;
			}if(form.user_pw.value == ""){
				alert("패스워드를 입력해주세요");
				return false;
			}
			
		}
	</script>
	<form action = "LoginProcess.jsp" method = "post" name = "loginFrm"
		onsubmit = "return validateForm(this);">
		<p> 아이디 : <input type = "text" name = "user_id">
		<p> 패스워드 : <input type = "password" name = "user_pw">
		<p> <input type = "submit" value = "로그인하기">
	</form>
<% }else { //로그인 상태일때 %>
	<!-- 로그인 상태일때 HTML처리부분 -->
		<% if(session.getAttribute("UserGrade").equals("vip")) { %>
		<span style = "background-color:red; font-size: 1.2em;"> vip등급 </span><br>
	<% } else if (session.getAttribute("UserGrade").equals("gold")){%>
		<span style = "background-color:yellow; font-size: 1.2em;"> gold등급 </span><br>
	<% } else if(session.getAttribute("UserGrade").equals("silver")){%>
		<span style = "background-color:blue; font-size: 1.2em;"> silver등급 </span><br>
	<% } %>
	
	<%= session.getAttribute("UserName") %>	회원님, 로그인 하셔습니다<br>
	<%if(session.getAttribute("UserId").equals("admin")) {%>
		관리자정보를 출력합니다.<br><br>
	<%} %>
	<a href = "Logout.jsp">[로그아웃]</a>
<% } %>
</body>
</html>