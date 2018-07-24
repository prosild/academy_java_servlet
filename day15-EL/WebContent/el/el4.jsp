<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL (4) 내장객체의 사용</title>
</head>
<body>
<%
	// 이 페이지에 진입했을 때 리스트를 하나 생성
	java.util.List<String> stars = new java.util.ArrayList<>();

	stars.add("다현");
	stars.add("사나");
	stars.add("윤하");
	stars.add("트와이스");
	stars.add("에픽하이");
	
	// 생성된 목록을 request에 attribute로 추가
	request.setAttribute("stars", stars);
%>

	<h3>EL에서 내장객체인 request에 추가된 목록 객체를 추출</h3>
	<pre>
	JSP 내장객체 : request | EL 내장객체 : requestScope
	목록 객체에 대해서 [] (브래킷 연산자)로 값을 추출
	
	1. \${requestScope.stars[0]} = ${requestScope.stars[0]}
	2. \${requestScope["stars"][1]} = ${requestScope["stars"][1]}
	3. \${stars[2]} = ${stars[2]}
	   requestScope을 생략 가능. 다른 내장객체에 동일한 이름(stars)가
	   attribute로 추가된 것이 없을 때만
	4. \${stars["3"]} = ${stars["3"]}
	5. \${requestScope["stars"]["4"]} = ${requestScope["stars"]["4"]}
	</pre>

<%
	// 맵 객체를 생성
	java.util.Map<String, String> starMap = new java.util.HashMap<>();
	starMap.put("s1", "다현");
	starMap.put("s2", "사나");
	starMap.put("s3", "윤하");
	starMap.put("s4", "트와이스");
	starMap.put("s5", "에픽하이");
	
	// 맵 객체를 request에 attribute로 추가
	request.setAttribute("starMap", starMap);
%>
	<h3>EL에서 내장객체인 request에 추가된 맵 객체를 추출</h3>
	<pre>
	맵 객체에 대하여 [](브래킷 연산자)로 값을 추출
	1. \${requestScope.starMap["s1"]} = ${requestScope.starMap["s1"]}
	2. \${requestScope.starMap["s2"]} = ${requestScope.starMap["s2"]}
	3. \${starMap["s3"]} = ${starMap["s3"]}
	4. \${starMap["s4"]} = ${starMap["s4"]}
	5. \${starMap["s5"]} = ${starMap["s5"]}
	</pre>
	
	<hr>
	
	<h3>scriptlet, expression Tag로 추출 시</h3>
<%
	// request 객체에서 attribute를 추출(getAttribute() 메소드 사용)
	java.util.List<String> starNames = (java.util.List<String>)request.getAttribute("stars");
%>
	<pre>
	1. &lt;%= starNames.get(0) %&gt; = <%= starNames.get(0) %><br>
	2. &lt;%= starNames.get(1) %&gt; = <%= starNames.get(1) %><br>
	3. &lt;%= starNames.get(2) %&gt; = <%= starNames.get(2) %><br>
	4. &lt;%= starNames.get(3) %&gt; = <%= starNames.get(3) %><br>
	5. &lt;%= starNames.get(4) %&gt; = <%= starNames.get(4) %><br>
	</pre>
	
	<h3>scriptlet, expression Tag로 맵 추출 시</h3>
<%
	// request 객체에서 attribute를 추출
	java.util.Map<String, String> starNameMap = 
		(java.util.Map<String, String>)request.getAttribute("starMap");
%>
	<pre>
	1. &lt;%= starNameMap.get("s1") %&gt; = <%= starNameMap.get("s1") %><br>
	2. &lt;%= starNameMap.get("s2") %&gt; = <%= starNameMap.get("s2") %><br>
	3. &lt;%= starNameMap.get("s3") %&gt; = <%= starNameMap.get("s3") %><br>
	4. &lt;%= starNameMap.get("s4") %&gt; = <%= starNameMap.get("s4") %><br>
	5. &lt;%= starNameMap.get("s5") %&gt; = <%= starNameMap.get("s5") %><br>
	</pre>
</body>
</html>