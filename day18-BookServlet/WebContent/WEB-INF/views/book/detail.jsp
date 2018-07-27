<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="book.vo.Book" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품 상세 조회</title>
<style type="text/css">
	a{
		color: black;
		text-decoration: none;
	}
	table, tr, th, td{
		border: 1px solid black;
		text-align: center;
	}
</style>
</head>
<body>
	<h3>제품 상세 조회</h3>
	<hr>
	<table>
		<tr>
			<th colspan="2">책 정보 등록하기</th>
		</tr>
		<tr>
			<th>책 코드</th>
			<td>
				${book.bookId}
			</td>
		</tr>
		<tr>
			<th>책 제목</th>
			<td>
				${book.title}
			</td>
		</tr>
		<tr>
			<th>작가</th>
			<td>
				${book.author}
			</td>
		</tr>
		<tr>
			<th>가격</th>
			<td>
				<fmt:formatNumber value="${book.price}" type="currency" currencyCode="KRW" />
			</td>
		</tr>
		<tr>
			<th>ISBN</th>
			<td>
				${book.isbn}
			</td>
		</tr>
		<tr>
			<th>출판사</th>
			<td>
				${book.publish}
			</td>
		</tr>
		<tr>
			<td colspan="2" style="text-align: center;">
				<a href="${contextPath}/main/list">목록보기</a>
				<a href="${contextPath}/main/update?bookId=${book.bookId}">수정</a>
				<a href="${contextPath}/main/delete?bookId=${book.bookId}">삭제</a>
			</td>
		</tr>
	</table>	
</body>
</html>