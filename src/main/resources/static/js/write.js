$('#write--submit').on('click', function() {

	var data = {
		title : $('#title').val(),
		content : $('#content').val()
	}

	$.ajax({
		type : 'POST',
		url : '/post/write/',
		data : JSON.stringify(data),
		contentType : 'application/json; charset=utf-8',
		dataType : 'json'

	}).done(function(r) {
		console.log(r);
		if (r.statusCode == 200) {
			alert('글을 등록하였습니다.');
			location.href = '/post';
		} else {
			alert('글 등록에 실패하였습니다.');
		}

	}).fail(function(r) {
		console.log(r);
		
		var check = r.responseJSON;
		
		console.log(check.title);
		if(check.title != null){
			$('#title_check').append(`<small>${check.title}</small>`)
		}
		if(check.title != null){
			$('#content_check').append(`<small>${check.content}</small>`)
		}
		
		
		console.log('통신실패');

	});

});