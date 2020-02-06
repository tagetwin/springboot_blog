<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../include/nav.jsp"%>

<div class="container">

	<form>
		<div class="form-group">
			<label for=username>유저네임:</label>
			<input type="text" class="form-control" placeholder="Enter username" id="username" maxlength="15" />
		</div>
		<div id="username_check" class="text-danger"></div>
		
		<div class="form-group">
			<label for="password">패스워드:</label>
			<input type="password" class="form-control" placeholder="Enter password" id="password" maxlength="15" />
		</div>
		<div id="password_check" class="text-danger"></div>
		
		<div class="form-group">
			<label for="email">이메일:</label>
			<input type="email" class="form-control" placeholder="Enter email" id="email" maxlength="30" />
		</div>
		<div id="email_check" class="text-danger"></div>
		
	</form>
	<button id="join--submit" class="btn btn-primary">회원가입</button>

</div>

<script>

	$('#join--submit').on('click', function(){

		$('#username_check, #password_check, #email_check').empty();
		
		var data = {
			username : $('#username').val(),
			password : $('#password').val(),
			email : $('#email').val()
		};

		$.ajax({
			type : 'POST',
			url : '/user/join',
			data : JSON.stringify(data),
			contentType : 'application/json; charset=utf-8', 
			dataType:'json'
			
		}).done(function(r){

			if(r.statusCode == 200){
				alert('회원가입 성공');
				location.href = '/user/login'

			}else {
				if(r.msg == '아이디중복'){
					console.log(r.msg);
					console.log(r);
					$('#username_check').append('<small>'+r.msg+'</small>');
// 					alert('아이디가 중복되었습니다.');	
				}
// 				alert('회원가입 실패');
				
			}
			
		}).fail(function(r){
// 			console.log(r);

			var check =r.responseJSON;
			
			if(check.username != null){
				$('#username_check').append('<small>'+check.username+'</small>');
			}

			if(check.password != null){
				$('#password_check').append('<small>'+check.password+'</small>');	
			}			
			
			$('#email_check').empty();
			if(check.email != null){
				$('#email_check').append('<small>'+check.email+'</small>');
			}
			
		});

		
	});

</script>


<%@ include file="../include/footer.jsp"%>


