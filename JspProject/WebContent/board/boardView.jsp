<%@page import="com.board.BoardVO"%>
<%@page import="com.board.BoardDAO"%>
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
BoardDAO dao = BoardDAO.getInstance();
BoardVO board = dao.boardView(num);
int ref = board.getRef();
int re_step = board.getRe_step();
int re_level = board.getRe_level();
String userid=(String)session.getAttribute("userid");
%>
</head>
<script>
	function del(){
		if(confirm("정말 삭제할까요?")){
			location.href="deletePro.jsp?num=<%=num%>";
		}
	}
</script>
<body>
<h2>글 내용 보기</h2>
<input type="hidden" name="num" id="num" value=<%=num%>>
<table border=1 width=500>
	<tr>
		<td>글번호</td>
		<td><%=board.getNum() %></td>
		<td>조회수</td>
		<td><%=board.getReadcount() %></td>
	</tr>
	<tr>
		<td>작성자</td>
		<td><%=board.getWriter() %></td>
		<td>작성일</td>
		<td><%=board.getReg_date() %></td>
	</tr>
	<tr>
		<td>글제목</td>
		<td colspan="3"><%=board.getSubject() %></td>
	</tr>
	<tr>
		<td>글내용</td>
		<td colspan="3"><%=board.getContent() %></td>
	</tr>
	<tr>
		<td colspan="4">
		<input type="button" value="글수정" onclick="location.href='updateForm.jsp?num=<%=num%>'">
		<input type="button" value="글삭제" onclick="del()">
		<input type="button" value="답글쓰기" onclick="location.href='writeForm.jsp?num=<%=num%>&ref=<%=ref%>&re_step=<%=re_step%>&re_level=<%=re_level%>'">
		<input type="button" value="글목록" onclick="location.href='list.jsp'">
		</td>
	</tr>
</table>

<br><br><br><br>
<div align="center">
	<textarea rows="5" cols="50" id="msg"></textarea>
	<input type="button" value="댓글쓰기" id="commentBtn">
</div>
<div id="area"></div><!-- 뿌려줄 영역 정하기 -->
<script>
var init=function(){
	$.getJSON("commentList.jsp?num="+$("#num").val(),function(data){
		var htmlStr ="<table>";
		$.each(data,function(key,val){
			htmlStr +="<tr>";
			htmlStr +="<td>"+val.msg+"</td>";
			htmlStr +="<td>"+val.userid+"</td>";
			htmlStr +="<td>"+val.regdate+"</td>";
			if('<%=userid%>'==val.userid){//로그인과 val.userid 같으면
				htmlStr +="<td>삭제</td>";
			}
			htmlStr +="</tr>";
		});
		htmlStr += "</table>";
		$("#area").html(htmlStr);
	})//getJSON
}//init()




$("#commentBtn").on("click",function(){
	$.ajax({
		type:"post",
		url:"commentInsert.jsp",
		data:{"msg":$("#msg").val(),"num":$("#num").val()},
		success:function(resp){
			if(resp.trim()=="1"){
				alert("로그인 하세요.");
				//location.href="../member/loginForm.jsp";//경로설정
				location.href="/JspProject/member/loginForm.jsp";//절대경로
			}else{//화면리스트 뿌리기
				init()
			}
		},
		error:function(e){
			alert("error:"+e);
		}
	});
});
init();//init 호출
</script>



</body>
</html>