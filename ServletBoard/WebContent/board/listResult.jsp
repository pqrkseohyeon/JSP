<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
  <h2 align="center">회원 리스트(<span id="cntSpan">${count}</span>)</h2><br/><br/>
  <table class="table">
    <thead>
      <tr>
      	<th>글번호</th>
        <th>글쓴이</th>
        <th>제목</th>
        <th>내용</th>
        <th>날짜</th>
      </tr>
    </thead>
    <tbody>
    
    <c:forEach items="${board}" var="board" varStatus = "st">
      <tr>
      	<td>${board.num}</td>
        <td>${board.writer}</td>
        <td>${board.subject}</td>
        <td>${board.content}</td>
        <td>${board.reg_date}</td>
       </tr>
        </c:forEach>
    </tbody>
  </table>
   <div align="center">
 	<c:if test="${pu.startPage>pu.pageBlock}"><!-- 이전 -->
 	<a href="javascript:getData(${pu.startPage-pu.pageBlock},'${pu.field}','${pu.word}')">[이전]</a>
 	</c:if>
 	<c:forEach begin="${pu.startPage}" end="${pu.endPage}" var="i"><!-- 페이지 출력 -->
 	<c:if test="${i==pu.currentPage}"><!-- 현재 페이지 -->
 	<c:out value="${i}"/>
 	</c:if>
 	<c:if test="${i!=pu.currentPage}"><!-- 현재페이지 아닌 경우 링크 부여 -->
 	<a href="javascript:getData(${i},'${pu.field}','${pu.word}')">${i}</a>
 	</c:if>
 
 	
 	</c:forEach>
 	<c:if test="${pu.endPage<pu.totPage }"><!--다음-->
 	<a href="javascript:getData(${pu.endPage+1},'${pu.field}','${pu.word}')">[다음]</a>
 	</c:if>
 </div>
  
</div>

    