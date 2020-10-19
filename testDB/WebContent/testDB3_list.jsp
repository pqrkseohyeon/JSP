<%@ page import="db.beans.*, java.sql.*, java.util.*, java.io.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="QueryBean" scope="page" class="db.beans.QueryBean"/>
<jsp:setProperty name="QueryBean" property="*"/>

<%
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	
	request.setCharacterEncoding("UTF-8");
	
	QueryBean.getConnection();
	
	ArrayList resArr = new ArrayList();

    String strUser = request.getParameter("id") == null? "" : request.getParameter("id").trim(); //검색아이디
	
	try{
		resArr = QueryBean.getUserInfo(strUser); //sql문 실행
	}catch(SQLException e){
		out.print(e.toString());
	}finally{
		QueryBean.closeConnection();
	}
	//json형식의 값 전달 시작
	out.println("{");
	out.println("\"datas\":[");
	
	if(resArr.size()==0){ //db값이 없을 경우 json 끝맺음
		out.println("]");
		out.println("}");
		
	}else{ //db값이 존재할경우 컬럼값 표시
		out.print("{ ");
		out.print("\"ID\": \"" + (String)resArr.get(0)+"\", ");
		out.print("\"NAME\": \"" + (String)resArr.get(1)+"\", ");
		out.print("\"PHONE\": \"" + (String)resArr.get(2)+"\", ");
		out.print("\"GRADE\": \"" + (String)resArr.get(3)+"\", ");
		out.print("\"WRITE_TIME\": \"" + (String)resArr.get(4)+"\" ");
		out.print("} ");
		
		for(int i=5; i<resArr.size(); i+=5){ //컬럼이 5개
			out.print(",");
			out.print("{ ");
			out.print("\"ID\": \"" + (String)resArr.get(i)+"\", ");
			out.print("\"NAME\": \"" + (String)resArr.get(i+1)+"\", ");
			out.print("\"PHONE\": \"" + (String)resArr.get(i+2)+"\", ");
			out.print("\"GRADE\": \"" + (String)resArr.get(i+3)+"\", ");
			out.print("\"WRITE_TIME\": \"" + (String)resArr.get(i+4)+"\" ");
			out.print("} ");
		}
		out.println("]");
		out.println("}");
	}
%>



