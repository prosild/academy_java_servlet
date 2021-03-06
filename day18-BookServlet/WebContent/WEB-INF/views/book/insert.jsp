<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품 신규 등록</title>
<style type="text/css">
	table, tr, th, td {
		border: 1px solid black;
		text-align: center;
	}
</style>
</head>
<body>

	<h3>제품 신규 등록</h3>
	<hr>
	<form action="${contextPath}/main/insert" method="post">
		<table>
			<tr>
				<th colspan="2">책 정보 등록하기</th>
			</tr>
			<tr>
				<th>책 코드</th>
				<td>
					<input name="bookId" type="text" required="required">
				</td>
			</tr>
			<tr>
				<th>책 제목</th>
				<td>
					<input name="title" type="text" required="required">
				</td>
			</tr>
			<tr>
				<th>작가</th>
				<td>
					<input name="author" type="text" required="required">
				</td>
			</tr>
			<tr>
				<th>가격</th>
				<td>
					<input name="price" type="number">
				</td>
			</tr>
			<tr>
				<th>ISBN</th>
				<td>
					<input name="isbn" type="text">
				</td>
			</tr>
			<tr>
				<th>출판사</th>
				<td>
					<input name="publish" type="text">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="등록하기">
					<input type="reset" value="초기화">					
				</td>
			</tr>
		</table>
	</form>
</body>
</html>