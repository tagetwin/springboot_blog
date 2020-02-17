$('#update--submit').on('click', function() {

	var no = $('#id').val();

	data = {
		id : no,
		title : $('#title').val(),
		content : $('#content').val()
	}

	$.ajax({
		type : 'PUT',
		url : '/post/update/',
		data : JSON.stringify(data),
		contentType : 'application/json; charset=utf-8',
		dataType : 'json'

	}).done(function(r) {
		alert('수정이 성공하였습니다.');
		location.href = '/post';

	}).fail(function(r) {
		alert('통신이 실패하였습니다.');

	});

});