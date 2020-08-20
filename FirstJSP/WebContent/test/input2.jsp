<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src = "https://code.jquery.com/jquery-3.5.1.min.js"></script><%--cdm 방법 --%>

<script >
/*
$(document).ready(function(){
	$("#btn").click(function(){
		alert("test");
	});
})
*/
$(function(){
	$("#btn").on("click",function(){
		if($("#name").val()==""){
			alert("이름을 입력하세요");
			return false;
		}//if
		if($("#studentNum").val()==""){
			alert("학번을 입력하세요");
			return false;
		}//if
		if($("input[name='hobby']:checked").length==0){
			alert("취미를 입력하세요");
			return false;
		}//if	
		//frm.submit(); 
		$("#frm").submit();
	}//click
	);//btn

})//function


</script>

 
</head>
<body>
	<form action="inputResult.jsp"method="post" name="frm" id="frm">
	이름 : <input type="text"name="name"id="name"><br>
	학번 : <input type="text"name="studentNum"id="studentNum"><br>
	
	성별 : 
		<input type="radio"name="gender"value="man"checked id = "man">
		<label for="man">남자</label>
		<input type="radio"name="gender"value="woman"id=feman">
		<label for="feman">여자</label><br>
	<!-- 
	<input type="radio"name="gender"value="man"checked>남자
	<input type="radio"name="gender"value="woman">여자<br><br>
	 -->
	전공 : <select name="major" id="major">
		<option value="국문학과"selected>국문학과</option>
		<option value="영문학과">영문학과</option>
		<option value="수학과">수학과</option>
		<option value="정치학과">정치학과</option>
		<option value="체육학과">체육학과</option>
	</select><br><br>
	취미<br>
	<input type="checkbox"name="hobby"value="운동">운동
	<input type="checkbox"name="hobby"value="운동1">운동1
	<input type="checkbox"name="hobby"value="운동2">운동2
	<input type="checkbox"name="hobby"value="운동3">운동3
	<input type="checkbox"name="hobby"value="운동4">운동4<br><br>
	
	<input type="button" value="보내기" id="btn">
	<input type="reset"value="취소">
</form>

</body>
</html>