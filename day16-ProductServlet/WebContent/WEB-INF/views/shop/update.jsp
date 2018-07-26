<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="shop.vo.Product" %>
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
	<form action="update" method="post">
		<table>
			<tr>
				<th>제품 코드</th>
				<td>
					<input name="prodCode" type="text" value="${product.prodCode}" readonly="readonly">
				</td>
			</tr>
			<tr>
				<th>제품 이름</th>
				<td>
					<input name="prodName" type="text" value="${product.prodName}">
				</td>
			</tr>
			<tr>
				<th>가격</th>
				<td>
					<input name="price" type="number" value="${product.price}">
				</td>
			</tr>
			<tr>
				<th>재고</th>
				<td>
					<input name="quantity" type="number" value="${product.quantity}">
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;">
					<a href="list">목록보기</a>
					<a href="detail?prodCode=${product.prodCode}">수정취소</a>
					<input type="submit" value="저장">
					<input type="reset" value="초기화">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>