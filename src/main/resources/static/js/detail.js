$('#comment--save--submit').on('click', function(){

	var content = $('#content').val();
	var userId = $('#userId').val();
	var postId = $('#id').val();

	data = {
		content : content,
		userId : userId,
		postId : postId
	};
	
	$.ajax({

		type : 'POST',
		url : '/comment/write',
		data : JSON.stringify(data),
		contentType : 'application/json; charset=utf-8',
		dataType : 'json'

	}).done(function(r){
		if(r.status.statusCode == 200){
			console.log(r);
			makeCommentItem(r);
			alert('댓글쓰기 성공');
		}

	}).fail(function(){
		alert('댓글등록실패');
	});
	
});

function makeCommentItem(r){
	var comment_item = `<div id="comment--item--${r.id}">`;
	comment_item += `<ul class="comment--item list-group">`;
	comment_item += `<li class="comment--username list-group-item d-flex justify-content-between align-items-center">${r.username}`;
	comment_item += `<span id="del" class="badge badge-primary badge-pill" onclick="commentDelete(${r.id})">삭제</span>`;
	comment_item += `<li class="comment--username list-group-item"><span class="comment--content">${r.content}</span></li>`;
	comment_item += `</ul>`;
	
	$('#comment--items').prepend(comment_item);
	
}

function commentDelete(commentId){
	
	$.ajax({
		type : 'DELETE',
		url : '/comment/delete/'+commentId,
		dataType :'json'
		
	}).done(function(r){
		console.log(r);
		if(r.statusCode == 200){
			alert('댓글을 삭제하였습니다.');
			$('#comment--item--'+commentId).remove();
		};
		
	}).fail(function(){
		alert('댓글삭제실패');
	});
	

};



$('body').on('click', '#comment--delete--submit', function(){
	alert('댓글 삭제');
});


var id = $('#id').val();

$('#post--delete--submit').on('click', function(){
	
	$.ajax({
		type : 'DELETE',
		url : '/post/delete/'+id,
		dataType :'json'
		
	}).done(function(r){
		alert('글을 삭제하였습니다.');
		location.href='/post';	
		
	}).fail(function(r){
		alert('접근이 올바르지 않습니다.');		
		
	});
	
});

$('#post--update--submit').on('click', function(){
		
	location.href='/post/update/'+id;	 
		
});