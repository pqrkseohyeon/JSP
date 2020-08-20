<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<body>
</body>
</html>

<script>
$("#send").click(function(){ //리뷰등록 버튼 클릭
	if($("#subject").val()==""){
		alert("제목을 입력하세요");
		$("#subject").focus();
		return false;
	}
	if($("#content").val()==""){
		alert("내용을 입력하세요");
		$("#content").focus();
		return false;
	}
	var num=$("#num").val();
	var title=$("#title").val();
	var userid=$("#userid").val();
	var content=$("#content").val();
	var subject=$("#subject").val();
	var postString="title="+title+"&num="+num+"&userid="+userid+"&content="+content+"&subject="+subject;
	$.ajax({
		type:	"post",
		url :	"/yes365/board/BoardInsert",
		data:	postString,
		success:function(d){
			alert("리뷰를 등록하였습니다.");
		},
		error: function(e){
			alert("error:"+e);
		}
	})//ajax
})//send
</script>