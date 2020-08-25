<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>
<%@include file="../include/adminView.jsp" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
a{
	text-decoration: none;
}
a:hover{
	text-decoration: none;
}
.back2{
	margin-top: 20px;
	background-color: #f0f8ff;
	border-radius: 20px;
}
#frm{
	margin: 0 auto;
	width: 50%;
}
.button{
	text-align: center;
	margin: 0 auto;
	padding: 20px;
}

.thumb-image{
	float:left;
	width:200px;
	position:relative;
	padding:5px;
}
img{
	width:170px;
}
</style>

<div class="container">
<br/>
<h5>도서수정</h5>
<div class="back2">
<form action="BookUpdate" method="post" id="frm" enctype="multipart/form-data">
  <br/><br/>
  	<div id="image-holder"></div>
      <img src="/yes365/upload/${dto.uploadFile}">
      <br/><br/>
  <div class="input-group mb-3">
    <div class="input-group-prepend">
      <span class="input-group-text">도서 이미지</span>
    </div>
      <input type="file" class="form-control" id="uploadFile" name="uploadFile">
      <input type="text" class="form-control" value="${dto.uploadFile}">
  </div>
  <br/>
  

  <div class="input-group mb-3">
    <div class="input-group-prepend">
      <span class="input-group-text">도서명</span>
    </div>
      <input type="text" class="form-control" id="title" name="title" value="${dto.title}">
  </div>
  <br/>
   
  <div class="input-group mb-3">
    <div class="input-group-prepend">
      <span class="input-group-text">글쓴이</span>
    </div>
      <input type="text" class="form-control" id="author" name="author" value="${dto.author}">
  </div>
  <br/>
  
  <div class="input-group mb-3">
    <div class="input-group-prepend">
      <span class="input-group-text">출판사</span>
    </div>
      <input type="text" class="form-control" id="publisher" name="publisher" value="${dto.publisher}">
  </div>
  <br/>
  
  <div class="input-group mb-3">
    <div class="input-group-prepend">
      <span class="input-group-text">출판일</span>
    </div>
      <input type="text" class="form-control" id="p_date" name="p_date" value="${dto.p_date}">
  </div>
  <br/>
  
  <div class="input-group mb-3">
    <div class="input-group-prepend">
      <span class="input-group-text">책가격</span>
    </div>
      <input type="text" class="form-control" id="price" name="price" value="${dto.price}">
  </div>
  <br/>
  
  
  <div class="input-group mb-3">
    <div class="input-group-prepend">
      <span class="input-group-text">책 정보</span>
    </div>
      <textarea rows="5" cols="20" class="form-control" id="info" name="info">${dto.info}</textarea>
  </div>
  <br/>
  
  
  <div class="button">
 	  <button  id="send"  class="btn btn-outline-secondary">수정</button>
 	  <input type="button" class="btn btn-outline-danger" value="책삭제" onclick="delBookCheck('${dto.num}')">
  </div>
<input type="hidden" id="num" name="num" value="${dto.num}">
</form>
</div>
</div>
<br/><br/>
<%@ include file="../include/footer.jsp" %>
<script>

function delBookCheck(num){
	 if(confirm("삭제하시겠습니까? 삭제된 도서정보는 되돌릴 수 없습니다.")){
    	location.href="BookDelete?num="+num;
    }
}
$(document).ready(function(){
	$("#send").click(function(){
		if($("#title").val()==""){
			alert("도서명을 입력하세요");
			$("#title").focus();
			return false;
		}
		if($("#authoer").val()==""){
			alert("저자명을 입력하세요");
			$("#authoer").focus();
			return false;
		}
		if($("#publisher").val()==""){
			alert("출판사를 입력하세요");
			$("#publisher").focus();
			return false;
		}
		if($("#p_date").val()==""){
			alert("출판일을 입력하세요");
			$("#p_date").focus();
			return false;
		}
		if($("#price").val()==""){
			alert("책 가격을 입력하세요");
			$("#price").focus();
			return false;
		}
		if($("#info").val()==""){
			alert("책 정보를 입력하세요");
			$("#info").focus();
			return false;
		}
		$("#frm").submit();
	})//send
	
	$("#uploadFile").on('change', function () {
        if (typeof (FileReader) != "undefined") {
            var image_holder = $("#image-holder");
            image_holder.empty();
            var reader = new FileReader();
            reader.onload = function (e) {
                $("<img />", {
                    "src": e.target.result,
                    "class": "thumb-image"
                }).appendTo(image_holder);
            }
            image_holder.show();
            reader.readAsDataURL($(this)[0].files[0]);
        } else {
            alert("이 브라우저에서 지원하지 않는 확장자 입니다.");
        }
    });
});//document
</script>