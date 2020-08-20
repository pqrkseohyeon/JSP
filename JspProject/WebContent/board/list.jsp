
<%@page import="com.board.BoardVO"%>
<%@page import="com.board.BoardDAO"%>
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
	
<%
	request.setCharacterEncoding("utf-8");
	String pageNum = request.getParameter("pageNum");
	if(pageNum==null){
		pageNum="1";
	}
	int currentPage = Integer.parseInt(pageNum);
	BoardDAO dao = BoardDAO.getInstance();
	int pageSize=5;
	int startRow=(currentPage-1)*pageSize+1;
	int endRow=currentPage*pageSize;
	String field="",word="";
	ArrayList<BoardVO>arr=null;
	int count=0;
	if(request.getParameter("word")!=null&&!request.getParameter("word").equals("")){
		field=request.getParameter("field");
		word = request.getParameter("word");
		arr = dao.boarList(field,word,startRow,endRow);
		count = dao.boardCount(field,word);
	}else{
		arr=dao.boarList(startRow, endRow);
		count = dao.boardCount();//53
	}
	String userid =(String)session.getAttribute("userid");
	
%>
</head>
<body>
<div align="right" style="margin-right:20px">
<%
if(userid!=null){
%>
	<%=userid %>반갑습니다. 
	<a href="../member/logout.jsp">로그아웃</a>
<%
}
%>
<br>
		전체 게시글 수 :<span id="cntSpan"><%=count %></span><br>
		<a href="writeForm.jsp">글쓰기</a>
</div>
<br><br>
<table class="table">
	<thead class="thead-dark">
	<tr>
		<th scope="col">번호</th>
		<th >제목</th>
		<th>작성자</th>
		<th>작성일</th>
		<th>조회수</th>
		<th>IP주소</th>
		
		
	</tr>
	</thead>
	<tbody>
	<%
		for(BoardVO vo:arr){
	%>
			<tr>
				<td><%=vo.getNum()%></td>
				<td><a href="boardView.jsp?num=<%=vo.getNum()%>"><%=vo.getSubject()%></a></td>
				<td><%=vo.getWriter()%></td>
				<td><%=vo.getReg_date()%></td>
				<td><%=vo.getReadcount()%></td>
				<td><%=vo.getIp()%></td>
			</tr>
	
	<%		
		}
	%>
	</tbody>
</table>
<br><br>
<form action="list.jsp" name="search" method="get">
	<table>
		<tr>
			<td align=center>
			<select name="field" size=1>
				<option value="subject">제 목
				<option value="writer">작성자
			</select>
			<input type="text" size=16 name="word">
			<input type="submit" value="찾기">
			</td>
		</tr>
	</table>
</form>
<div align="center">
	<%

		if(count>0){    //   11= 53/5 +(53%5==0)
			int pageCount=count/pageSize+(count%pageSize==0?0:1);//총 페이지의 수
			int pageBlock=3; //한 페이지에 보여줄 페이지 블럭(링크) 수
			int startPage=(int)((currentPage-1)/pageBlock)*pageBlock+1;//한 페이지에 보여줄 시작 및 끝번호
			int endPage = startPage+pageBlock-1;//12
			if(endPage>pageCount){//마지막 페이지가 총 페이지 수 보다 크면 endPage를 pageCount로 할당
				endPage = pageCount; //endpage=11
			}		
			if(startPage>pageBlock){//이전
	%>
				<a href="list.jsp?pageNum=<%=startPage-pageBlock%>&field=<%=field%>&word=<%=word%>">[이전]</a>
	<%
			}	
			//for
			for(int i=startPage; i<=endPage;i++){
	%>
				<a href="list.jsp?pageNum=<%=i%>&field=<%=field%>&word=<%=word%>"><%=i%></a>
	<% 
			}
			if(endPage<pageCount){//다음
	%>
				<a href="list.jsp?pageNum=<%=startPage+pageBlock%>&field=<%=field%>&word=<%=word%>">[다음]</a>
	<%
			}
		}
	
	%>


</div>






</body>
</html>