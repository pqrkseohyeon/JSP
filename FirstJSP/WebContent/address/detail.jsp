 <%@page import="com.address.Address"%>
<%@page import="com.address.AddressDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<%
	request.setCharacterEncoding("utf-8");
	int num = Integer.parseInt(request.getParameter("num"));
	AddressDAO dao = AddressDAO.getInstance();
	Address address=dao.addrDetail(num);
%>
<script>
//jquery 이용
$(document).ready(function(){//밑에 선언되어 있어서 뭔지 모르기 때문에 load해주는게 $(document).ready 이다.
	$("#deleteBtn").click(function(){ // deleteBtn을 클릭하면
		if(confirm("정말 삭제할까요?")){
			//location.href="deletePro.jsp?num=<%=num%>";	
			$(location).attr("href","deletePro.jsp?num=<%=num%>");
		}	
	});
})

function del(){
	if(confirm("정말 삭제할까요?")){
		location.href="deletePro.jsp?num=<%=num%>";			
	}	
}
//매개변수 있는 함수
function dels(no){
	if(confirm("정말삭제할까요?")){
		location.href="deletePro.jsp?num="+no;
	}
}
function zipfinder(){//새로운창을 띄워준다.
	window.open("zipCheck.jsp","","width=700 height=400");
}



</script>
</head>
<body>

<form action="updatePro.jsp" method="post" name="frm">
<input type="hidden" name="num" value=<%=num %>> <%--사용자한테는 보여지지않지만, 서버에는 값을 전달해준다. --%>

		주소록 수정하기<br>

		이름 <input type="text" name="name" value="<%=address.getName()%>"><br>
		우편번호
		<input type="text" name="zipcode" size="3" value="<%=address.getZipcode()%>" >
		<input type ="button" value="검색" onclick="zipfinder()"><br>
		주소 <input type="text" name="addr" size="50" value="<%=address.getAddr()%>"><br>
		전화번호<input type="text" name="tel" value="<%=address.getTel()%>"><br>
		
		<input type="submit" value="수정">
		<input type="button" value="삭제" onclick="del()">
		<input type="button" value="매개변수삭제" onclick="dels(<%=num%>)">
		<input type="button" value="jquery삭제" id="deleteBtn">
				
		<input type="reset" value="취소">

</form>

</body>
</html>