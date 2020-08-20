<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <h3 align="center">회원리스트(회원수 : <span id="cntSpan">${count}</span>)</h3><br/>
  <table align="center" width="500px" border=1>
   	<thead>
   		<tr>
   			<th>번호</th>	
   			<th>seq</th>  				
   			<th>이름</th>
   			<th>평가</th>
   			<th>날짜</th>
   			<c:if test="${login!=null}">
   				<th>삭제</th>
   			</c:if>
   		</tr>
   	</thead>
   	<tbody>
   	
   	<c:forEach items="${guestbook}" var="gue" varStatus="st">
   	
   	<tr>
   		<td>${rowNo-st.index}</td>
   		<td>${gue.num}</td>
   		<td><a href="javascript:fview(${gue.num})">${gue.name}</a></td>
   		<td>${gue.grade}</td>
   		<td>${gue.created}</td>
   		<c:if test="${sessionScope.login!=null}">
   			<td><a href="javascript:fdelete(${gue.num},'${gue.name}')">삭제</a></td>
   		</c:if>
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
