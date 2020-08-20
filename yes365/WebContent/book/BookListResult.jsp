<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> admin 도서목록</title>
  <title>도서목록</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<style>
table {
  border-spacing: 10px;
  border-collapse: separate;
}
table td {
  width: 30px;
 
}
</style>
</head>

<div class="container">
<br/>
<h5><a href="BookList.jsp">도서목록(<span id="cntSpan">${count}</span>)</a></h5>
<div class="back2">
<table class="table table-striped">
    <thead>
      <tr>
      	<th>책번호</th>
        <th>책커버</th>
        <th>제목</th>
        <th>저자명</th>
         
         <th>출판일</th>
         
        
      </tr>
    </thead>
    <tbody>
    	<c:forEach items="${book}" var="book" varStatus="st">
      <tr>
        <td>${rowNo-st.index}</td>
       	<td width="30%">     	
      	<img src="/yes365/upload/${book.uploadFile}" width="30%">
      	</td>
        <td><a href="BookDetail?num=${book.num}">${book.title}</a></td>
        <td>${book.author}</td>
        <td>${book.p_date}</td>
      
      </tr>
      </c:forEach>
    </tbody>
  </table>
   <div align = "center">
	  	<c:if test = "${pu.startPage>pu.pageBlock}"> <!-- 이전-->
	  		<a href = "javascript:getData(${pu.startPage-pu.pageBlock},'${pu.field}','${pu.word}')">[이전]</a>
	  	</c:if>
	  	<c:forEach begin ="${pu.startPage}" end = "${pu.endPage}" var = "i"> <!-- 이전-->
  			<c:if test ="${i==pu.currentPage}"> <!-- 현재페이지-->
 				<c:out value = "${i}"/>
  			</c:if>
  			<c:if test = "${i!=pu.currentPage}"> <!-- 현재페이지 아닌 경우 링크 부여-->
  				<a href = "javascript:getData(${i},'${pu.field}','${pu.word}')">${i}</a>
  			</c:if>
	  	</c:forEach>
	  	<c:if test = "${pu.endPage < pu.totPage}"> <!-- 다음-->
	  		<a href = "javascript:getData(${pu.endPage+1},'${pu.field}','${pu.word}')">[다음]</a>
	  	</c:if>
	  </div> 
</div>

</body>
</html>
