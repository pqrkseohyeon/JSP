<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
 <script>
 $(document).ready(function(){
	 getData(1,"","");//페이지 로드시 전체리스트 보여주기
	 
	 $("#btnSearch").on("click",function(){//검색버튼 클릭
		 getData(1,$("#field").val(),$("#word").val());
	 })
	 
	 $("#send").click(function(){//전송버튼 클릭
		 if(${sessionScope.login==null}){
			 alert("로그인을 하세요");
			 
		 }
		 var name = $("#name").val();
		 var content = $("#content").val();
		 var grade = $("input:radio[name=grade]:checked").val();
		 var postString
		 ="name="+name+"&content="+content+"&grade="+grade;//? & 달고 가는걸 쿼리 스트링이라고 한다.
		 $.ajax({
			 type:"post",
			 url:"create.gb",
			 data:postString,//{"name":$("#name").val(), 등 여러개}//json 형태나 쿼리 스트링 형태나 둘 다 사용 가능
			 success:function(d){
				// alert(d);
				$("#result").html(d);
			 },
			 beforeSend:showRequest,//url에 가기전에 이걸 먼저 처리한다.
			 error:function(e){
				 alert("error:"+e);
			 }
		 });//ajax
	 })//send
 });//document
 
 //전체 내용보는 함수
 function getData(pageNum,field,word){
	 $.get("list.gb",
			 {"pageNum":pageNum,"field":field,"word":word},
			 function(d){
		 		$("#result").html(d);
	 })
 }
 function showRequest(){
	 if($("#name").val()==""){
		 alert("이름을 입력하세요");
		 $("#name").focus();
		 return false;
	 }
	 if($("#content").val()==""){
		 alert("내용을 입력하세요");
		 $("#content").focus();
		 return false;
	 }
	 if($("input:radio[name=grade]:checked").length==0){
		 alert("평가를 해주세요");
		 return false;
	 }
	 return true;
 }
 
 function textCount(obj,target){//target:nameCount,contentCount
	 var len=$("#"+obj.id).val().length;
 	 if(obj.size==len){
 		 alert("글자수 초과");
 		 return false;
 	 }
	 $("#"+target).text(len);
 }
 //상세보기
 function fview(num){
	 $.getJSON("view.gb",{"num":num},function(d){
		var htmlStr="";
		htmlStr+="<table>";
		htmlStr+="<tr>";
		htmlStr+="<td>이름</td>";
		htmlStr+="<td>"+d.name+"</td>";
		htmlStr+="</tr>";
		htmlStr+="<tr>";
		htmlStr+="<td>내용</td>";
		htmlStr+="<td>"+d.content+"</td>";
		htmlStr+="</tr>";
		htmlStr+="<tr>";
		htmlStr+="<td>작성일</td>";
		htmlStr+="<td>"+d.created+"</td>";
		htmlStr+="</tr>";
		htmlStr+="</table>";
		$("#view").html(htmlStr);
	 });
 }
 //삭제하기
 function fdelete(num, name){
	 if(confirm("["+name+"]의 게시물을 삭제할까요?")){
		 $.get("delete.gb?num="+num,function(d){
			 $("#result").html(d);
		 })
	 }	 
 }
 </script>

</head>
<body>
<c:if test="${sessionScope.login==null }">
<a href="login.jsp">로그인</a>
</c:if>

<c:if test="${sessionScope.login!=null}">
	${login}님 반갑습니다.
	<a href="logout.gb">로그아웃</a>
</c:if>
<br/><br/>
<form method="post" action="create.gb">
<table  align="center" width=900px>
<tr>
		<td align="center">글쓴이</td> 
		<td>
		<input type="text" id="name" maxlength = 20 name="name" onkeyup="textCount(this,'nameCount')">
		*20글자이내
		(<span id="nameCount" style="color:red">0</span>)
	  </td>
</tr>
<tr>
<td align="center">내 용</td>
	<td>
	<input type="text" maxlength="70" id="content" name="content" onkeyup="textCount(this,'contentCount')">
	*70글자이내
		(<span id="contentCount" style="color:red">0</span>)
	</td>
</tr>
<tr>
	<td align="center">평가 </td>
	<td><input type="radio" name="grade" value="excellent"
	checked="checked">아주잘함(excellent)
	<input type="radio" name="grade" value="good"> 잘함(good)
	<input type="radio" name="grade" value="normal"> 보통(normal)
	<input type="radio" name="grade" value="fail"> 노력(fail)
	</td>
</tr>
<tr>
	<td colspan="2">
 	<input type="submit" value="submit전송" > 
 	<input type="button" value="ajax버튼 전송" id="send"> 
</td>
</tr>
</table>
</form>
<br/><br/>
<div align="right">
<form name="search" id="search">
	<select name="field" id="field">
		<option value="name">이름</option>
		<option value="content">내용</option>
	</select>
	<input type="text" name="word" id="word">
	<input type="button" value="찾기" id="btnSearch">
</form>
</div>
<br/><br/><br/>

<div id="result" align="center"></div>
<hr>
<div id="view">

</div>


</body>
</html>