//폼 안에 버튼을 위치시켜야 할때
//버튼 타입을 버튼으로 준다
// function(e) 로 event를 받은 변수를 받고 e.preventDefault();
// 해당 태그가 갖고 있는 이벤트 실행을 방지한다.

$('#login--submit').on('click', function() {

	var data = {
		username : $('#username').val(),
		password : $('#password').val()
	}

	$.ajax({
		type : 'POST', // 로그인만 예외로 POST. GET을 쓰면 주소에 남기때문에
		url : '/user/login',
		data : data,
		contentType : 'application/x-www-form-urlencoded; charset=utf-8',
		dataType : 'json'

	}).done(function(r) {
		if(r.statusCode == 200){
			console.log(r);
			alert('로그인 성공');
			location.href = "/"
		}else{
			console.log(r);
			alert('로그인 실패');
		}

	}).fail(function(r) {
		console.log(r);
		alert('로그인 실패');

	});
})

// 엔터키 치면 바로 로그인 되게	
$("#password").keydown(function(key) {
	if (key.keyCode == 13) {//키가 13이면 실행 (엔터는 13)
		$('#login--submit').click();
	}
});