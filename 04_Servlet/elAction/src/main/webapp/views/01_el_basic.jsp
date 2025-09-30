<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.el.model.vo.Person"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>1. 기존 방식대로 JSP문법을 사용하여 각 scope의 데이터를 출력</h1>

    <%
        //request scope
        String classRoom = (String)request.getAttribute("classRoom");
    	Person student = (Person)request.getAttribute("student");
    	
    	//session scope
    	String academy = (String)session.getAttribute("academy");
    	Person lecture = (Person)session.getAttribute("lecture");
    %>

    <p>
        학원명 : <%=academy%>
        강의장 : <%=classRoom%>
        강사 : <%=lecture.getName()%>, <%=lecture.getAge()%>, <%=lecture.getGender()%> <br><br>

        수강생 정보
        <ul>
            <li>이름 : <%=student.getName()%></li>
            <li>나이 : <%=student.getAge()%></li>
            <li>성별 : <%=student.getGender()%></li>
        </ul>
    </p>
</body>
</html>