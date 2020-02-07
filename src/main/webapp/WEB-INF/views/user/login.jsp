<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../include/nav.jsp"%>

<div class="container">

	<form>
		<div class="form-group">
			<label for="username">유저네임:</label>
			<input type="text" class="form-control" placeholder="Enter username" id="username" />
		</div>
		
		<div class="form-group">
			<label for="password">패스워드:</label>
			<input type="password" class="form-control" placeholder="Enter password" id="password" />
		</div>
		
	</form>

	<button id="login--submit" class="btn btn-primary">로그인</button>

</div>

<script>
//폼 안에 버튼을 위치시켜야 할때
//버튼 타입을 버튼으로 준다
// function(e) 로 event를 받은 변수를 받고 e.preventDefault();
// 해당 태그가 갖고 있는 이벤트 실행을 방지한다.

	
	$('#login--submit').on('click', function(){
		
		var data = {
			username : $('#username').val(),
			password : $('#password').val()
		}

		$.ajax({
			type : 'POST', // 로그인만 예외로 POST. GET을 쓰면 주소에 남기때문에
			url : '/user/login',
			data : JSON.stringify(data),
			contentType : 'application/json; charset=utf-8',
			dataType : 'json'
			
		}).done(function(r){
			alert('로그인 성공');
			location.href = "/"
			
		}).fail(function(r){
			alert('로그인 실패');
			
		});
	})
	
// 엔터키 치면 바로 로그인 되게
	
	
</script>


<%@ include file="../include/footer.jsp"%>


