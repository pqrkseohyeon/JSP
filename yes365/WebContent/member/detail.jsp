<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@include file="../include/header.jsp" %>
  <div class="container">
<br/><br/><br/>
      <ol class="breadcrumb">
      <li class="breadcrumb-item">
     	   <h2>회원정보</h2> 
      </li>
      
    </ol>
  
  
  <form action="update.me" method="post" id="frm">
        <div class="row">
    <div class="col">
    	<label for="userid">Id:</label>
      <input type="text" class="form-control" size="50" id="userid" placeholder="Enter id" name="userid" value="${member.userid}" readonly="readonly">
    </div>
      
    </div>
    <div class="form-group">
      <label for="name">Name:</label>
      <input type="text" class="form-control" id="name" placeholder="Enter name" name="name">
    </div>
    <div class="form-group">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pwd">
    </div>
        <div class="form-group">
      <label for="pwd_check">Password Confirm:</label>
      <input type="password" class="form-control" id="pwd_check" placeholder="Enter password Confirm" name="pwd_check">
    </div>
    <div class="form-group">
      <label for="email">Email:</label>
      <input type="email" class="form-control" id="email" placeholder="Enter Email" name="email">
    </div>
    <div class="form-group">
      <label for="phone">Phone:</label>
      <input type="text" class="form-control" id="phone" placeholder="Enter Phone" name="phone">
    </div>
       <div class="input-group mb-3">
    <div class="input-group-prepend">
      <span class="input-group-text">우편번호</span>
    </div>
      <input type="text" id="sample6_postcode" name="sample6_postcode" readonly="readonly" class="form-control">
    <div class="col align-self-end" >
      <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호" class="btn btn-primary"><br>
   	</div>
  </div>
  <br/>  

  <div class="input-group mb-3">
    <div class="input-group-prepend">
      <span class="input-group-text">주소</span>
    </div>
    <input type="text" id="addr" name="addr" placeholder="주소" class="form-control"><br>
  </div>

  <div class="input-group mb-3">
	<input type="text" id="detailAddr" name="detailAddr" placeholder="상세주소" class="form-control">
	<input type="text" id="extraAddr" name="extraAddr" placeholder="참고항목" class="form-control">
  </div>
  <button id="submit" class="btn btn-primary">업데이트</button>
		 <button id="reset" class="btn btn-primary">취소</button>
	</br></br>
  </form>
</div>

<%@include file="../include/footer.jsp" %>