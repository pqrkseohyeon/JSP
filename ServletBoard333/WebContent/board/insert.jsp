<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method = "post" action = "insert">
		<table>
			<tr>
				<td>글쓴이</td>
				<td><input type = "text" id = "writer" name = "writer" size = "40"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type = "text" id = "subject" name = "subject" size = "40"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td>
					<textarea rows = "15" cols = "50" id = "content" name = "content"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan = "2" align = "center">
					<input type = "submit" value = "submit전송">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>