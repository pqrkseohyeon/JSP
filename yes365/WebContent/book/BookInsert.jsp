<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>
<%@include file="../include/adminView.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>도서등록</title>

  <!-- Bootstrap core CSS -->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="css/modern-business.css" rel="stylesheet">
<style>
.insert{
display:table;

background-color: #f0f8ff;
}
.control-group form-group{
display:table-cell;

vertical-align:middle;
}
</style>
</head>

<body>
	
    <!-- Contact Form -->
    <!-- In order to set the email address and subject line for the contact form go to the bin/contact_me.php file. -->
     <div class="col-lg-9 mb-4">
        <h2>도서등록</h2>
         <div class="row">
      <div class="col-lg-8 mb-4">
      <div class="insert">
        <form name="sentMessage" id="frm" novalidate method="post" action="BookInsert" enctype="multipart/form-data">
          <div class="control-group form-group">
            <div class="controls">
              <label>책제목:</label>
              <input type="text" class="form-control" id="title"  name="title" required data-validation-required-message="Please enter your name.">
              <p class="help-block"></p>
            </div>
          </div>
          <div class="control-group form-group">
            <div class="controls">
              <label>저자명:</label>
              <input type="text" class="form-control" id="author" name="author" required data-validation-required-message="Please enter your phone number.">
            </div>
          </div>
          <div class="control-group form-group">
            <div class="controls">
              <label>출판사:</label>
              <input type="text" class="form-control" id="publisher" name="publisher" required data-validation-required-message="Please enter your email address.">
            </div>
          </div>
           <div class="control-group form-group">
            <div class="controls">
              <label>출판일:</label>
              <input type="text" class="form-control" id="p_date" name="p_date" required data-validation-required-message="Please enter your email address.">
            </div>
          </div>
           <div class="control-group form-group">
            <div class="controls">
              <label>도서 가격:</label>
              <input type="text" class="form-control" id="price" name="price" required data-validation-required-message="Please enter your email address.">
            </div>
          </div>
          <div id="image-holder"></div>
           <div class="control-group form-group">
            <div class="controls">
              <label>책 이미지:</label><br/>
				<input type="file" name="uploadFile" name="uploadFile"><br/>
			
            </div>
          </div>
          <div class="control-group form-group">
            <div class="controls">
              <label>도서 정보:</label>
              <textarea rows="10" cols="100" class="form-control" id="info" name="info" required data-validation-required-message="Please enter your message" maxlength="999" style="resize:none"></textarea>
            </div>
          </div>
          <div id="success"></div>
          <!-- For success/fail messages -->
          <button id ="send"  class="btn btn-outline-secondary">책 업로드</button>
          <button type="reset" class="btn btn-outline-danger">취소</button>
        </form>
      </div>
        
       
      </div>
    </div>
   </div>



</body>
</html>

<script>
$(document).ready(function(){
	$("#send").click(function(){
		if($("#title").val()==""){
			alert("도서명을 입력하세요");
			$("#title").focus();
			return false;
		}
		if($("#author").val()==""){
			alert("저자명을 입력하세요");
			$("#author").focus();
			return false;
		}
		if($("#publisher").val()==""){
			alert("출판사를 입력하세요");
			$("#publisher").focus();
			return false;
		}
		if($("#p_date").val()==""){
			alert("출판일을 입력하세요");
			$("#publisher").focus();
			return false;
		}
		if($("#price").val()==""){
			alert("도서 가격을 입력하세요");
			$("#price").focus();
			return false;			
		}
		if($("#info").val()==""){
			alert("도서 정보를 입력하세요");
			$("#price").focus();
			return false;
		}
		alert("도서가 등록되었습니다. 도서 목록으로 이동합니다.");
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
 <%@include file="../include/footer.jsp" %>