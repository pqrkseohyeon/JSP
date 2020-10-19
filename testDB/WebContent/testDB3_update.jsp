<%@page import="java.sql.SQLException"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:useBean id="QueryBean" scope="page" class="db.beans.QueryBean" />
<jsp:setProperty property="*" name="QueryBean" />

<%
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	
	String id = request.getParameter("id") == null ? "1234" : request.getParameter("id").trim();
	String name = request.getParameter("name") == null ? "김길동" : request.getParameter("name").trim();
	String phone = request.getParameter("phone") == null ? "" : request.getParameter("phone").trim();
	String grade = request.getParameter("grade") == null ? "" : request.getParameter("grade").trim();

	request.setCharacterEncoding("UTF-8");

	QueryBean.getConnection();

	int result=0;

	try {
		result = QueryBean.updateUserInfo(id, name, phone, grade);
		
	} catch (SQLException e) {
		out.print(e.toString());
	} finally {
		QueryBean.closeConnection();
	}

	out.println("[");
	out.println("{");
	out.println("	 \"RESULT_OK\" :\""+result+"\"");
	out.println("}");
	out.println("]");
	 



%>

