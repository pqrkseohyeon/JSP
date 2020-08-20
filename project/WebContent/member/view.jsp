<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="../include/header.jsp" %>
<div class="container">
  
  <form action="update.me" method="post" id="frm">
    <div class="row">
    <div class="col">
    	<label for="userid">Id:</label>
      <input type="text" class="form-control" id="userid" readonly="readonly" name="userid" value="${member.userid}">
    </div>
    </div>
    <div class="form-group">
       <label for="name">Name:</label>
      <input type="text" class="form-control" id="name" name="name" value="${member.name}">
    </div>

        <div class="form-group">
     <label for="pwd">Password:</label>
      <input type="password" class="form-control" id="pwd" disabled="disabled" value="${member.pwd}">
    </div>
    <div class="form-group">
      <label for="email">Email:</label>
      <input type="text" class="form-control" id="email" name="email" value="${member.email}">
    </div>
    <div class="form-group">
    <label for="phone">Phone:</label>
      <input type="text" class="form-control" id="phone" name="phone" value="${member.phone}">
    </div>
    <div class="form-check">
  <label class="form-check-label">
    <input type="radio" class="form-check-input" name="admin" value="1">관리자
  </label>
</div>
<div class="form-check">
  <label class="form-check-label">
      
    <input type="radio" class="form-check-input" name="admin" value="0">일반회원
  </label>
</div>
		<script>
		if(${member.getAdmin()==0}){
			$("input:radio[value='0']").prop("checked",true);
		}else{
			$("input:radio[value='1']").prop("checked",true);
		}
		
		</script>
<br/>
     <button id="submit" class="btn btn-primary">업데이트</button>
		 <button id="reset" class="btn btn-primary">취소</button>
  </form>
</div>

<%@include file="../include/footer.jsp" %>
