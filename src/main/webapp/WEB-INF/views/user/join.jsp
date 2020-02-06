<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../include/nav.jsp"%>

<div class="container">

	<form>
		<div class="form-group">
			<label for=username>유저네임:</label>
			<input type="text" class="form-control" placeholder="Enter username" id="username" />
		</div>
		
		<div class="form-group">
			<label for="password">패스워드:</label>
			<input type="password" class="form-control" placeholder="Enter password" id="password" />
		</div>
		
		<div class="form-group">
			<label for="email">이메일:</label>
			<input type="email" class="form-control" placeholder="Enter email" id="email" />
		</div>
	</form>
	<button id="join--submit" class="btn btn-primary">회원가입</button>

</div>


<%@ include file="../include/footer.jsp"%>


