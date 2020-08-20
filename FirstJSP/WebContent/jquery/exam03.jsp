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
	$("#b1").click(function(){
		$.get("data.txt",function(data,status){
			//alert("data:"+data+"\n status:"+status)
			if(status=="success"){
				alert("성공입니다.")
			}
			var str="데이터:"+data+"\n처리상태:"+status
			$("#result").text(str);
		}
		);
	})//b1
	
	$("#b2").click(function(){
		var htmlStr="";
		$.getJSON("data.txt",function(data){
			//alert(data.length);
			$.each(data,function(key,val){
				htmlStr+="회원번호:" +val.memberNumber+"<br>";
				htmlStr+="번호:" +val.irum+"<br>";
				htmlStr+="이미지:" +val.picture+"<hr>";
			})
			$("#result").html(htmlStr);
		});
	})//b2
});//document

</script>
</head>
<body>
<button id="b1">결과</button>
<button id="b2">결과2</button>
<div id="result"></div>
</body>
</html>