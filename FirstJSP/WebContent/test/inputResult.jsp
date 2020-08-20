<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
 request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="student" class="com.exam.StudentBean"/><%--불러올 때는 패키지명까지 정확하게 적어줘야한다. --%>
<jsp:setProperty property="*" name="student"/>
<%
	String[] h = student.getHobby();
	String strHobby="";
	for(int i=0; i<h.length;i++){
		strHobby += h[i]+" ";
	}
%>


</head>
<body>
이름 : <jsp:getProperty property="name" name="student"/>
<hr>
이름 :<%=student.getName() %><br>
학번 :<%=student.getStudentNum()%><br>
성별 :<%=student.getGender()%><br>
전공 :<%=student.getMajor() %><br>
취미 :<%=strHobby%>


</body>
</html>