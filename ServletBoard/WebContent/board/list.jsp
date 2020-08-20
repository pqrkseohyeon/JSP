<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
  
  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(document).ready(function(){//load하자마자 실행하라는 의미	
	getData(1,"","");
	
	 $("#btnSearch").on("click",function(){//검색버튼 클릭
		 getData(1,$("#field").val(),$("#word").val());
	 })

})//document

 //전체 내용보는 함수
 function getData(pageNum,field,word){
	 $.get("list",//값을 들고 다시 여기로 온다.
			 {"pageNum":pageNum,"field":field,"word":word},
			 function(d){
				 $("#result").html(d);
			
			 })
 }
</script>
</head>
<body>

<div id="result"></div>
<br/><br/>
<div align="center">
<form name="search" id="search">
	<select name="field" id="field">
		<option value="writer">이름</option>
		<option value="content">내용</option>
	</select>
	<input type="text" name="word" id="word">
	<input type="button" value="찾기" id="btnSearch">
</form>
</div>


</body>
</html>