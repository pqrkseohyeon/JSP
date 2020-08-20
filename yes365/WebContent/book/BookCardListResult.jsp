<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> member 도서목록</title>
<%@ include file="../include/header.jsp" %>

<head>
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
<body>
</br>
<div class="container">
  <a href="/yes365/book/BookCardList.jsp"><h3>도서목록</h3></a>
  <p>마음의 양식을 쌓으세요.</p>            
  <table class="table table-striped">
    <thead>
      <tr>
        <th>책커버</th>
        <th>제목</th>
        <th>저자명</th>
         <th>출판사</th>
         <th>출판일</th>
          <th>책가격</th>
        
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${dto}" var="dto">
    	
      <tr>
      	<td width="30%">
      	<a href="BookCardDetail?num=${dto.num}">
      	<img src="/yes365/upload/${dto.uploadFile}" width="30%">
      	</td>
      	<a href="BookCardDetail?num=${dto.num}">
        <td>${dto.title}</td>
        </a>
        <td>${dto.author}</td>
        <td>${dto.publisher}</td>
        <td>${dto.p_date}</td>
        <td>${dto.price}</td>
        
      </tr>
      
	</c:forEach>
      
    </tbody>
  </table>
	
<br/><br/>
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
