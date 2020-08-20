<%@page import="com.exam.ScoreBean3"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
request.setCharacterEncoding("utf-8");
String name = request.getParameter("name");
int kor=Integer.parseInt(request.getParameter("kor"));
int eng=Integer.parseInt(request.getParameter("eng"));
int math=Integer.parseInt(request.getParameter("math"));
ScoreBean3 sb = new ScoreBean3(name,kor,eng,math);

%>
</head>
<body>
<!-- scoreResult3.jsp -->
scoreResult3.jsp<hr>
이름:<%=sb.getName() %><br>
국어 :<%=sb.getKor() %><br>
영어 :<%=sb.getEng() %><br>
수학 :<%=sb.getMath() %><br>
총점 :<%=sb.getTot() %><br>
평균 :<%=sb.getAvg() %><br>
학점 :<%=sb.getGrade() %><br>

</body>
</html>