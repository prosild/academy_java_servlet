<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
</style>
</head>
<body>
	<h3>제품 상세 조회</h3>
	<hr>
	<form action="${contextPath}/main/update" method="post">
		<table>
			<tr>
				<th>책 코드</th>
				<td>
					<input name="bookId" type="text" value="${book.bookId}" readonly="readonly">
				</td>
			</tr>
			<tr>
				<th>책 제목</th>
				<td>
					<input name="title" type="text" required="required" value="${book.title}">
				</td>
			</tr>
			<tr>
				<th>작가</th>
				<td>
					<input name="author" type="text" required="required" value="${book.author}">
				</td>
			</tr>
			<tr>
				<th>가격</th>
				<td>
					<input name="price" type="number" value="${book.price}">
				</td>
			</tr>
			<tr>
				<th>ISBN</th>
				<td>
					<input name="isbn" type="text" value="${book.isbn}">
				</td>
			</tr>
			<tr>
				<th>출판사</th>
				<td>
					<input name="publish" type="text" value="${book.publish}">
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;">
					<a href="${contextPath}/main/list">목록보기</a>
					<a href="${contextPath}/main/detail?bookId=${book.bookId}">수정취소</a>
					<input type="submit" value="저장">
					<input type="reset" value="초기화">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>