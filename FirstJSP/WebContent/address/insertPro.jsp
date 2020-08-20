<%@page import="com.address.AddressDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--여기서는 로직만 처리 --%>
<% 
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="ad" class="com.address.Address"/>
<jsp:setProperty property="*" name="ad"/>
<%
	AddressDAO dao = AddressDAO.getInstance(); 
	dao.addrInsert(ad);
	response.sendRedirect("list.jsp");
%>
