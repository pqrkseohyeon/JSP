<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>Insert title here</title>
<script>
$(document).ready(function(){
	$.getJSON("commentlist",
			{num:$("#num").val()},
			function(d){
				//alert(d.carr.length);
				var htmlStr="";
				$.each(d.carr,function(key,val){
					 htmlStr+=val.userid+" ";
					 htmlStr+=val.msg+" ";
					 htmlStr+=val.regdate+"<br>";
				});
		
				$("#commentResult").html(htmlStr);
		
	})
	$("#commentBtn").on("click",function(){
		$.ajax({
			type:"get",
			url:"commentInsert",
			data:{"msg":$("#msg").val(),"num":$("#num").val()},
			success:function(d){
				if(d.trim()==1){
					alert("로그인 하세요");
					location.href="login.jsp";
				}else{
					var htmlStr="";
					d = JSON.parse(d);
					$.each(d.carr,function(key,val){
						 htmlStr+=val.userid+" ";
						 htmlStr+=val.msg+" ";
						 htmlStr+=val.regdate+"<br>";
					});
			
					$("#commentResult").html(htmlStr);
				}
			},
			error:function(e){
				alert("error:"+e);
			}
		});
	})
})
</script>
</head>
<body>
<h2>글내용 보기</h2>
<input type="hidden" id="num" value="${board.num}">
<table>
	<tr>
		<td>글번호 </td>
		<td>${board.num}</td>
	</tr>
	<tr>
		<td>글쓴이 </td>
		<td>${board.writer}</td>
	</tr>
	<tr>
		<td>제목 </td>
		<td>${board.subject}</td>
	<tr>
		<td>내용
		<td>
		${board.content}</td>
	</tr>
		<tr>
		<td>조회수</td>
		<td>${board.readcount}</td>
	</tr>
	<tr>
		<td>작성일</td>
		<td>${board.reg_date}</td>
	</tr>
	<tr>
		<td colspan="2">
		<input type="button" value="수정">
		<input type="button" value="삭제">
		
		</td>
	</tr>
</table>
<br/><br/>
	<div align="center">
	<textarea rows="5" cols="50" id="msg"></textarea>
	<input type="button" value="write Commet" id="commentBtn">
	</div>
<div id="commentResult"></div>


</form>
</body>
</html>