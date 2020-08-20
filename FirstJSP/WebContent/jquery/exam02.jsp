<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/jquery-3.5.1.min.js"></script>
<script>
//비동기적  ajax 기술 사용, 콜백 함수를 불러서
$(document).ready(function(){
	$("#postBtn").click(function(){
		$.post("process.jsp",//post라는 함수를 이용해서 process.jsp에 뿌린다
				//{"id" : $("#id").val(),//전달되어야 할 값은 {key,value}안에 적는다.
				{"id":document.getElementById("id").value,//위의 방식이랑 결과값은 똑같다.
				"pwd":$("#pwd").val(),//id,pwd값을 가지고 간다.
				"method" :"post"},
				function(data){//콜백함수, 돌아오는 결과 값을 data에 뿌린다.
					
					$("#postResult").html(data);
				}
		);//post
		
	});//postBtn
	
	$("#getBtn").click(function(){
		$.get("process.jsp",{
								"id" : $("#id").val(),
								"pwd" : $("#pwd").val(),
								"method":"get"
								},
								function(ret){
									$("#getResult").html(ret);
								}
		)//get
	})//getBtn
	
	$("#loadBtn").click(function(){
		$("#loadResult").load("process.jsp",{
										"id" : $("#id").val(),
										"pwd" : $("#pwd").val(),
										"method":"load"
										}
/* 									function(data){//콜백함수 값을 바꾸지 않을거면 필요없다. 바꿀거면 사용
											
										} */
		);//load						
	})//loadBtn
	
	$("#ajaxBtn").click(function(){
		$.ajax({
			type :"post",
			url  :"process.jsp",
			data :{//달고가야할값
				"id" : $("#id").val(),
				"pwd" : $("#pwd").val(),
				"method":"ajax"
			},
			success:function(d){
				$("#ajaxResult").html(d);
			},
			error:function(e){
				alert("에러:"+e);
			}		
		});//ajax
	});//ajaxBtn
	
	
});//document
</script>
</head>
<body>

id : <input type="text" id="id" name="id"><br>
pwd : <input type="password" id="pwd" name="pwd"><br>
<input type="button" id="postBtn" value="post전송">
<input type="button" id="getBtn" value="get전송">
<input type="button" id="loadBtn" value="load전송">
<input type="button" id="ajaxBtn" value="ajax전송">
<br>
<div id="getResult"></div>
<div id="postResult"></div>
<div id="loadResult"></div>
<div id="ajaxResult"></div>
</body>
</html>