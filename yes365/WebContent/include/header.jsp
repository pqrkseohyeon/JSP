<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>yes 365</title>

<!-- Bootstrap core CSS -->
<link href="/yes365/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="/yes365/css/modern-business.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<style>

</style>
</head>

<body>

	<!-- Navigation -->
	<nav
		class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="/yes365/BookMain.jsp" >yes 365</a>
			<button class="navbar-toggler navbar-toggler-right" type="button"
				data-toggle="collapse" data-target="#navbarResponsive"
				aria-controls="navbarResponsive" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<a class="nav-link" href="/yes365/BookMain.jsp">홈</a>
					<a class="nav-link" href="/yes365/book/BookCardList.jsp">도서목록</a>

					<c:choose>
						<c:when test="${empty sessionScope.userid}">
							<li class="nav-item"><a class="nav-link"
								href="/yes365/member/login.me">로그인</a></li>
							<li class="nav-item"><a class="nav-link"
								href="/yes365/member/insert.me">회원가입</a></li>
						</c:when>
						<c:otherwise>	
							<li class="nav-item"><a class="nav-link"
								href="/yes365/member/memberView.me">내계정</a></li>
							<li class="nav-item"><a class="nav-link"
								href="/yes365/member/logout.me">로그아웃</a></li>
						</c:otherwise>	
					</c:choose>
						<c:if test="${sessionScope.admin=='admin'}">
							<li class="nav-item"><a class="nav-link" href="/yes365/member/adminView.jsp">관리자</a></li>
						
						</c:if >

				
				</ul>
	</nav>