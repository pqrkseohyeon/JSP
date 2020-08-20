<%@page import="com.address.AddressDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
int num = Integer.parseInt(request.getParameter("num"));
AddressDAO dao = AddressDAO.getInstance();
dao.addrDelete(num);
response.sendRedirect("list.jsp");
%>