<%@page import="com.member.MemberVO"%>
<%@page import="com.member.MemberDAOImpl"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	<style>
		div.divCss{
			text-align:right;
			background-color: darkgray;
			padding-right: 20px;
		}
		a:hover{text-decoration:none; }
		a:link{text-decoration:none; }
		a:visited{text-decoration:none; }
	</style>
	<script src="member.js"></script>
<%
	request.setCharacterEncoding("utf-8");
	MemberDAOImpl dao = MemberDAOImpl.getInstance();
	ArrayList<MemberVO>arr=dao.memberList();
	String suserid = (String)session.getAttribute("userid");//기본형이 object이라서 String으로 캐스팅 해줘야한다.
	int count = dao.memberCount();
%>
</head>
<body>
<div align="right">
	<a href="memberView.jsp"><%=suserid%></a>관리자님 반갑습니다.
	<a href="logout.jsp">로그아웃</a><br>
	<a href="../board/list.jsp">게시판 글 보기</a>
		전체 회원 수 :<span id="cntSpan"><%=count %></span>
</div>
<br><br>
<table class="table">
	<thead class="thead-dark">
	<tr>
		<th scope="col">이름</th>
		<th scope="col">아이디</th>
		<th scope="col">전화번호</th>
		<th scope="col">이메일</th>
		<th scope="col">구분</th>
		<th scope="col">삭제</th>
		
		
	</tr>
	</thead>
<tbody>
<%
	for(MemberVO vo:arr){
		String mode=vo.getAdmin()==0?"일반회원":"관리자";//변수를 선언해서 0이면 일반회원 아니면 관리자
%>
		<tr>
			<td><%=vo.getName()%></td>
			<td><%=vo.getUserid()%></td>
			<td><%=vo.getPhone()%></td>
			<td><%=vo.getEmail()%></td>
			<td><%=mode%></td>
			<td><a href="javascript:del('<%=vo.getUserid()%>','<%=mode%>')">삭제</a></td>
			
		</tr>

<%		
	}
%>
</tbody>
</table>

</body>
</html>