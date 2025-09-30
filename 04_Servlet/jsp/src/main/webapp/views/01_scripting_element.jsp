<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Document</title>
</head>
<body>
	<h1>스크립팅 원소</h1>
	<!-- HTML 주석 -->
	<%-- JSP 주석 --%>
	
	<%
		// 스크립틀릿 : 변순 선언 및 로직 처리
		int sum = 0;
		for(int i = 1; i <= 100; i++){
			sum += i;
		}
	%>
	
	<p>
		스크립틀릿으로 직접 출력 <% out.println(sum); %> <br>
		표현식을 이용한 출력 : <%= sum %>
	</p>
	
	<%
		String[] nameArr = {"최지원", "김수민", "박지수"};
	%>
	
	<h5>배열의 길이 : <%=nameArr.length %></h5>
	<h5>배열의 값 : <%=String.join(", ", nameArr) %></h5>
	
	<h4>반복문을 통해서 html요소를 반복적으로 화면에 출력할 수 있다.</h4>
	<ul>
		<% for(int i=0; i<nameArr.length; i++){ %>
			<li><%=nameArr[i] %></li>
		<% } %>
		
		<br>
		
		<% for(String name : nameArr){ %>
			<li><%=name %></li>
		<% } %>
	</ul>
</body>
</html>