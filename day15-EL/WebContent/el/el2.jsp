<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL (2) 파라미터 다루기</title>
</head>
<body>

<%--
	EL은 기존 JSP의 scriptlet(<% %>), expression(<%= %>)이 했던
	일을 단순한게 표현 가능
--%>
<%
	// 이 페이지가 요청될 때, 전송된 파라미터 추출
	String name = request.getParameter("name");
	String address = request.getParameter("address");
	String id = request.getParameter("id");
%>

이 페이지를 요청할 때는 다음의 주소를 복사하여 요청합니다. <br>
http://localhost:8081/day15-EL/el/el2.jsp <br>
http://localhost:8081/day15-EL/el/el2.jsp?name=홍길동 <br>
http://localhost:8081/day15-EL/el/el2.jsp?name=홍길동&address=율도국 <br>
http://localhost:8081/day15-EL/el/el2.jsp?name=홍길동&address=율도국&id=gildong2 <br>
http://localhost:8081/day15-EL/el/el2.jsp?name=&address=&id= <br>

<h3>EL의 파라미터 추출</h3>
<pre>
1. \${param.name} = ${param.name}
2. \${param.address} = ${param.address}
3. \${param.id} = ${param.id}
</pre>

<h3>expression Tag의 파라미터 추출</h3>
<pre>
1. name = <%= name %>
2. address = <%= address %>
3. id = <%= id %>
</pre>

<hr>

<h3>EL의 파라미터 존재 여부 검사</h3>
<pre>
1. parameter name이 존재하는가? \${not empty param.name} = ${not empty param.name}
2. parameter address가 존재하는가? \${not empty param.address} = ${not empty param.address}
3. parameter id가 존재하는가? \${not empty param.id} = ${not empty param.id}
4. parameter name이 없는가? \${empty param.name} = ${empty param.name}
5. parameter address가 없는가? \${empty param.address} = ${empty param.address}
6. parameter id가 없는가? \${empty param.id} = ${empty param.id}
</pre>
</body>
</html>