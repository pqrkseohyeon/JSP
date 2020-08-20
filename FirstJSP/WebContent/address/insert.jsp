<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function zipfinder(){//새로운창을 띄워준다.
	window.open("zipCheck.jsp","","width=700 height=400");
}
</script>
</head>
<body>
	<a href="list.jsp">전체보기</a><%--href 어디로 가라는 명령 --%>
	<form action="insertPro.jsp" method="post" name="frm" >

		주소록 등록하기<br>
		이름 <input type="text" name="name" id="name"><br>
		우편번호
		<input type="text" name="zipcode" size="3" id="zipcode">
		<input type ="button" value="검색" onclick="zipfinder()"><br>
		주소 <input type="text" name="addr" size="50" id="addr"><br>
		전화번호<input type="text" name="tel" id="tel"><br>
		<input type="submit" value="등록">
		<input type="reset" value="취소">

	</form>

</body>
</html>