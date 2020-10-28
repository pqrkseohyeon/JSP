<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>
<head>
	<title>Login V18</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="/yes365/images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css2/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/yes365/css/util.css">
	<link rel="stylesheet" type="text/css" href="/yes365/css/main.css">
<!--===============================================================================================-->

</head>
<style>
.login100-more{
background-image:url("/yes365/img/lo.png");
background-size:30%;
background-repeat: no-repeat;
color: white;

}
</style>
<body style="background-color: white;">
	<div  align="center"> 
	<div class="limiter" >
		<div class="container-login100">
			<div class="wrap-login100">
				<form class="login100-form validate-form" action="login.me" method="post">
					<span class="login100-form-title p-b-43">
						yes 365 로그인
					</span>
					
					
					<div class="wrap-input100 validate-input" data-validate = "Valid email is required: ex@abc.xyz">
						<input class="input100" type="text" id="userid" placeholder="Enter id"  name="userid">
						<span class="focus-input100"></span>
						<span class="label-input100"></span>
					</div>
					
					
					<div class="wrap-input100 validate-input" data-validate="Password is required">
						<input class="input100" type="password" id="pwd"  placeholder="Enter password" name="pwd_check"></br>
						<span class="focus-input100"></span>
						<span class="label-input100"></span>
					</div>

					<div class="flex-sb-m w-full p-t-3 p-b-32">
						<div class="contact100-form-checkbox">
							<input class="input-checkbox100" id="ckb1" type="checkbox" name="remember">
							<label class="label-checkbox100" for="ckb1">
								Remember me
							</label>
						</div>

						<div>
							<a href="#" class="txt1">아이디찾기 ㅣ</a>
							<a href="#" class="txt1"> 비밀번호찾기 ㅣ</a>
							<a href="../member/join.jsp" class="txt1"> 회원가입 </a>
						</div>
					</div>
			

					<div class="container-login100-form-btn">
						<button type="button" class="login100-form-btn" id="loginBtn">
							로그인
						</button>
					</div>
					



				</form>

				<div class="login100-more">
		</div>
	</div>
	</div>
	</div>
	</body>
<script>
$("#loginBtn").click(function(){
	if($("#userid").val()==""){
		alert("ID 입력");
		$("#userid").focus();
		return false;
	}
	if($("#pwd").val()==""){
		alert("PWD 입력");
		$("#pwd").focus();
		return false;
	}
	$.ajax({
		type     :"post",
		url      :"login.me",
		data     :{"userid":$("#userid").val(),"pwd":$("#pwd").val()},
		success  :function(value){
			//alert(value.trim())
		switch(value.trim()){
		case "0" : alert("Welcome to yes365"); location.href="/yes365/BookMain.jsp"; break;
		case "1" : alert("관리자 로그인"); location.href="/yes365/BookMain.jsp"; break;
		case "2" : alert("비밀번호 오류"); break;
		case "-1": alert("회원이 아닙니다. 회원가입을 해주세요."); location.href="/yes365/member/insert.me"; break;
		default: alert(value.trim());
				
				}
			},
		error:function(e){
			alert("error:"+e)
		}
	})//ajax
})
</script>
	

	
	
<!--===============================================================================================-->
	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/bootstrap/js/popper.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/daterangepicker/moment.min.js"></script>
	<script src="vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
	<script src="js/main.js"></script>

  <%@include file="../include/footer.jsp" %>