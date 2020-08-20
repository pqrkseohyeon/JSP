<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
request.setCharacterEncoding("utf-8");

String[] hobby=request.getParameterValues("hobby");
%>
</head>

<body>
이름 :<%=request.getParameter("name") %><br>
학번 :<%=request.getParameter("studentNum") %><br>
성별 :<%=request.getParameter("gender") %><br>
전공 :<%=request.getParameter("major") %><br>
<%
String str="";
if(hobby!=null){
	for(int i=0;i<hobby.length;i++){
		str += hobby[i]+" ";
	}
}

%>
취미:<%=str %>
</body>
</html>