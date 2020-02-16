/**
 * 회원가입로직
 */

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
 				alert('회원가입 실패');
				
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
			alert('회원가입 실패');
		});

		
	});