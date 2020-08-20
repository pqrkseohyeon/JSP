<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>

<style>
.col-md-6{
margin-right:-150px; 
background-color: #f0f8ff;
}

h2{
color: purple;
}
.container-wrapper{
align: center;
}
h4{
color: #9932cc;
}
.info{
align:center;
}
.button{
	text-align: center;
	margin: 0 auto;
	padding: 20px;
	margin-bottom: 20px;
}
.content{
	width: 40%;
	height: auto;
	position: relative;
	display:inline;
	margin-bottom: 30px;
	margin-right: 20px;
}
.board{
	clear: both;
	width: 100%;
	height: auto;
}

#result{
background-color: gray;
}
</style>
<script>
$(document).ready(function(){
	getData(1, "", "", ${dto.num}); //페이지 로드 시 수강후기 리스트 보기
	
	//검색버튼 클릭
	$("#btnSearch").on("click", function(){
		getData(1, $("#field").val(), $("#word").val(), $("#num").val());
	})//btnSearch
	
	
	//리뷰등록 버튼 클릭
	$("#send").click(function(){ //리뷰등록 버튼 클릭
         if(${sessionScope.userid==null}){
              alert("로그인 하세요.");
              location.href="/yes365/member/login.me";
         }else{
	       	 	var num=$("#num").val();
				var userid=$("#userid").val();
				var content=$("#content").val();
				var subject=$("#subject").val();
				var postString="num="+num+"&userid="+userid+"&content="+content+"&subject="+subject;
	            $.ajax({
	                  type:    "post",
	                  url :    "/yes365/board/BoardInsert",
	                  data:    postString,
	                  success:function(d){
	                	   alert("리뷰가 등록되었습니다!");
	                	   $("#result").html(d);
	                       document.getElementById("wFrm").reset();
	                  },
	                  beforeSend: showRequest, //빈칸 확인
	                  error: function(e){
	                       alert("error:"+e);
	                  }
	            })//ajax
         }
     })//send
}); //document ready

//전체보기 함수
function getData(pageNum, field, word, num){
	$.get("/yes365/board/boardlist", 
			{"pageNum":pageNum, "field":field, "word":word, "num":num},
			function(d){
			$("#result").html(d);
	})
}//getData

//빈칸확인 함수
function showRequest(){
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
	return true;
}
</script>
<body>
<div class="container-wrapper">
	<div class="container">
	<br/>
<!-- 책 정보 -->
		<h2>도서 상세 페이지</h2>
		<br/><br/>
		<!-- 한 행의 두개의 칼럼 -->
		<div class="row">
			<div class="col-md-6">
				<img src="/yes365/upload/${dto.uploadFile}" width="60%">
			</div>
			
			<div class="col-md-6">
				<h3><${dto.title}></h3><hr>
				<p><strong> 저자명  </strong>&nbsp;&nbsp; ${dto.author}</p>
				<p><strong> 출판사  </strong>&nbsp;&nbsp; ${dto.publisher} </p>
				<p><strong> 출판일  </strong>&nbsp;&nbsp; ${dto.p_date} </p>
				<p><strong> 책가격  </strong>&nbsp;&nbsp; ${dto.price} 원 </p><hr>
				<p><strong> 반품/교환 방법 </strong>&nbsp;&nbsp; <br/>판매자와 배송 상품은 판매자와 반품/교환이 협의된 상품에 한해 가능합니다. </p><hr>
				<p><strong> 반품/교환 가능기간 </strong>&nbsp;&nbsp; 출고 완료 후 10일 이내의 주문 상품 </p><hr>
				<button type="button" class="btn btn-outline-info">카트에 넣기</button>
				<button type="button" class="btn btn-outline-info">바로구매</button>
				<button type="button" class="btn btn-outline-secondary">eBook 출간 알림신청</button>
				<br>
			</div>
		</div><hr><br/><br/>
		<div class="info">
		<h4><도서정보></h4>
		<br/>
		<p>${dto.info}</p>
		</div><br/><br/><hr>
<!-- 책 정보 끝 -->
		
<!-- 책리뷰 게시판 -->
		<h5>*회원리뷰*</h5><br/>
		<p>매주 10건의 우수리뷰를 선정하여 YES상품권 3만원을 드립니다.<br/>
		3,000원 이상 구매 후 리뷰 작성 시 일반회원 300원, 마니아회원 600원의 YES포인트를 드립니다.<br/>
		(CD/LP, DVD/Blu-ray, 패션 및 판매금지 상품, 예스24 앱스토어 상품 제외)
		</p>
<!-- 검색-->
		<div class="board">
		<br/>
		<div align="right" id="searchDiv">
		<form name="search" id="search">
			<select name="field" id="field">
				<option value="userid">작성자</option>
				<option value="subject">제목</option>
			</select>
			<input type="text" name="word" id="word">
			<input type="button" value="찾기" id="btnSearch" class="btn btn-outline-secondary btn-sm">
			<a href="#coll"  id="writeBtn" class="btn btn-outline-dark btn-sm" data-toggle="collapse">리뷰쓰기</a>
		</form>
		</div>
<!-- 검색 끝-->
<div id="result" align="center"></div>
<!-- 리뷰 글쓰기 시작 -->

<!-- 리뷰 글쓰기 끝 -->
<div id="coll" class="collapse">
		   <form action="/yes365/board/BoardInsert" method="post" id="wFrm">
			<br/><br/>
			  <!-- 책 리뷰  남길  해당 책번호 -->
			  <input type="hidden" id="num" name="num" value="${dto.num}">
			  
			  <div class="input-group mb-3"><div class="input-group-prepend">
			      <span class="input-group-text">도서명</span></div>
			      <input type="text" class="form-control" id="title" name="title" readonly="readonly" value="${dto.title}">
			  	  <div class="input-group-prepend"><span class="input-group-text">작성자</span></div>
			      <input type="text" class="form-control" id="userid" name="userid" readonly="readonly" value="${sessionScope.userid}">
			  </div>
			  <br/>
			  
			  <div class="input-group mb-3"><div class="input-group-prepend">
			      <span class="input-group-text">제목</span></div>
			      <input type="text" class="form-control" id="subject" name="subject">
			  </div>
			  <br/>
			  
			  <div class="input-group mb-3"><div class="input-group-prepend">
			      <span class="input-group-text">내용</span></div>
			      <textarea rows="5" cols="20" class="form-control" id="content" name="content"></textarea>
			  </div>
			  
			  <div class="button">
			 	  <input type="reset" class="btn btn-gray" value="취소">
			 	  <input type="button" class="btn btn-primary" id="send" value="리뷰등록">
			  </div>
			</form>
 <!-- 책 리뷰 게시판 끝 -->
</div><!-- div container 영역 끝 -->
</div><!-- div container-wrapper 영역 끝 -->
</div>
</body>


<br/><br/>
<%@ include file="/include/footer.jsp" %>