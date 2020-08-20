<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="../include/header.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>yes 365</title>

  <!-- Bootstrap core CSS -->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="css/modern-business.css" rel="stylesheet">
<script>
function getData(pageNum, field, word){
	$.get("BookList",
		  {"pageNum":pageNum, "field":field, "word":word}, 
		  function(d){
			 
		    $("#result").html(d);
	})
}
</script>
</head>

<body>



  <header>
    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
      <ol class="carousel-indicators">
        <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
      </ol>
      <div class="carousel-inner" role="listbox">
        <!-- Slide One - Set the background image for this slide in the line below -->
        <div class="carousel-item active" style="background-image: url('/yes365/img/banner.jpg')">
          <div class="carousel-caption d-none d-md-block">
            <h3>yes 365</h3>
            <p></p>
          </div>
        </div>
        <!-- Slide Two - Set the background image for this slide in the line below -->
        <div class="carousel-item" style="background-image: url('/yes365/img/banner2.jpg')">
          <div class="carousel-caption d-none d-md-block">
            <h3>yes 365</h3>
            <p></p>
          </div>
        </div>
        <!-- Slide Three - Set the background image for this slide in the line below -->
        <div class="carousel-item" style="background-image: url('/yes365/img/banner4.jpg')">
          <div class="carousel-caption d-none d-md-block">
            <h3>yes 365</h3>
            <p></p>
          </div>
        </div>
      </div>
      <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
      </a>
    </div>
  </header>

  <!-- Page Content -->
  <div class="container" align="center">
	<div class="title">
    <h1 class="my-4" >Welcome to yes365</h1>
	<p><strong>yes365는 휴식처이자,<br/>문화놀이터이자, 지식의 보고입니다.</strong><br/>항상 우리 곁에서 함께하는 모두의 소중한 친구입니다   :) </p><br/><br/><br/>
   </div>
   
    
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

</html>
<br/>
  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>
</html>
