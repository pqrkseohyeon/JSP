<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/jquery-3.5.1.min.js"></script>
<script>
$(document).ready(function(){
	$("button").click(function(){
		$("#displayArea").html("<img src='1.gif' border=0>");
		alert($(this).text());//값이 없으면 가져오고, 값이 있으면 값을 바꾼다
		$(this).text("여기도 바뀔까")
	});//button
	
	$("#txtBtn").on("click", function(){
		alert($("#txt").val())
		$("#txt").val("txt 내용 변경");
	});//txtBtn
});//document

</script>
</head>
<body>
<div id="displayArea">이곳의 내용이 변경</div>
<button>표시</button><br>
<input type="text" id="txt" value="초기값"><br>
<input type="button" value="txt클릭" id="txtBtn">

</body>
</html>