<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List.jsp(파일 첨부 게시판 : MVC)</title>
<style> a{text-decoration:none;}</style>
</head>
<body>
	<h2>파일 첨부형 게시판 목록 보기(List)</h2>
	
	<!-- 검색폼  -->
	<form method = "get">
	<table border = "1" width = "90%">
		<tr>
			<td align = "center">
				<select name = "searchField">
					<option value = "title"> 제목 </option>
					<option value = "content"> 내용</option>
				</select>
				<input type = "text" name = "searchWard"/>
				<input type = "submit" value = "검색하기"/>
			</td> 
		</tr>
	</table>
	</form>
	<!-- 목록테이블 -->	
	<table border = "1" width = "90%">
		<tr>
			<td width = "10%"> 번호</td>
			<td width = "*"> 제목 </td>
			<td width = "15%"> 작성자 </td>
			<td width = "10%"> 조회수 </td>
			<td width = "15%"> 작성일 </td>
			<td width = "8%"> 첨부 </td>
		</tr>
		<c:choose>
			<c:when test="${empty boardLists }">		<!-- 계시물이 없을때 -->
				<td colspan = "6" align = "center">
					등록된 게시물이 없습니다.
				</td>
			</c:when>
			<c:otherwise>	<!-- 계시물이 존재할때 -->
				<c:forEach items = "{$boardLists}" var = "row" varStatus = "loop">
					<tr align = center>
						<td><!-- 번호 -->
							${map.totalCount- (((map.pageNum-1)*map.pageSize) + loop.index) };
						</td>
						<td align="left"><!-- 제목(링크) -->
							<a href = "../mvcboard/view.do?idx=${row.idx }">${row.title }</a>
						</td>
						<td><!-- 작성자 -->
							${row.name }
						</td>
						<td><!-- 조회수 -->
							${row.visitcount }
						</td>
						<td><!-- 작성일 -->
							${row.firstdate }
						</td>
						<td><!-- 첨부파일 -->
							<c:if test = "${not empty row.ofile }">
								<a href = "../mvcboard/download.do?ofile=${row.ofile }&sfile=${row.sfile}&idx=${row.idx}">[Down]</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		
		
	</table>
</body>
</html>