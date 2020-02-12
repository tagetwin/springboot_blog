<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../include/nav.jsp"%>

<div class="container">

	<form>
		<div class="form-group">
			<label for=username>유저네임:</label><div id="username_check"></div>
			<input type="text" class="form-control" placeholder="Enter username" id="username" maxlength="15" />
		</div>
		
		
		<div class="form-group">
			<label for="password">패스워드:</label><div id="password_check"></div>
			<input type="password" class="form-control" placeholder="Enter password" id="password" maxlength="15" />
		</div>
		
		
		<div class="form-group">
			<label for="email">이메일:</label><div id="email_check" class="text-danger"></div>
			<input type="email" class="form-control" placeholder="Enter email" id="email" maxlength="30" />
		</div>
		
		
	</form>
	<button id="join--submit" class="btn btn-primary">회원가입</button>

</div>

<script src="/js/join.js"></script>


<%@ include file="../include/footer.jsp"%>


