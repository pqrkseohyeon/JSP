<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>

 <script>
  $(document).ready(function(){
		getData(1,"","");
		
		$("#btnSearch").on("click",function(){//검색버튼 클릭
			
			getData(1,$("#field").val(),$("#word").val());
		})
		
	})//document
	function getData(pageNum, field, word){
		$.get("BookCardList",
			  {"pageNum":pageNum, "field":field, "word":word}, 
			  function(d){
				 
			    $("#result").html(d);
		})
	}
  </script>

</head>
<body>

<div id="result"></div>
	<br/></br>
	<div align="center">
		<form name="search" id="search">
			<select name="field" id="field">
				<option value="title">제목</option>
				<option value="author">작가</option>
			</select>
			<input type="text" name="word" id="word">
			<button type="button"  class="btn btn-outline-secondary" id="btnSearch">검색</button>
			
		</form>
	</div>

</body>
</html>
<br/>
<%@ include file="/include/footer.jsp" %>