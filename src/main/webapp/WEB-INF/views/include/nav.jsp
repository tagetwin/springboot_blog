<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>블로그</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<style>
td{
	cursor : pointer;
}
.comment--item{
	border-style: solid;
	background-color: antiquewhite;
	margin-bottom: 4px;
}

</style>


</head>

<body>

	<nav class="navbar navbar-expand-md bg-dark navbar-dark">
		<!-- Brand -->
		<a class="navbar-brand" href="/">YNDG블로그</a>

		<!-- Toggler/collapsibe Button -->
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>

		<!-- Navbar links -->
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav">

				<c:choose>
					<c:when test="${not empty sessionScope.principal}">
						<li class="nav-item"><a class="nav-link" href="/post/write">글쓰기</a></li>
						<li class="nav-item"><a class="nav-link" href="/user/profile/${sessionScope.principal.id}">회원정보수정</a></li>
						<li class="nav-item"><a class="nav-link" href="/user/logout">로그아웃</a></li>
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a class="nav-link" href="/user/join">회원가입</a></li>
						<li class="nav-item"><a class="nav-link" href="/user/login">로그인</a></li>
					</c:otherwise>
				</c:choose>

			</ul>
		</div>
	</nav>
	<br />
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	