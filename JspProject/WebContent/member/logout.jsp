<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	session.invalidate();//모든 session을 지운다.
	//session.removeAttribute("userid");//하나만 지운다.
	response.sendRedirect("loginForm.jsp");
%>