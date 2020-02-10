<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../include/nav.jsp"%>

<div class="container">

	<div class="card">
		<div class="card-header">
			<h4 class="card-title">${post.title}</h4>
		</div>

		<div class="card-body">
			<p class="card-text">${post.content}</p>
		</div>

		<div class="card-footer"> 
			<c:if test='${post.userId eq sessionScope.principal.id }'>
				<input type="hidden" id="id" value="${post.id}" />
				<button id="post--update--submit" class="btn btn-warning">수정</button>
				<button id="post--delete--submit" class="btn btn-danger">삭제</button>
			</c:if>

			<a href="/" class="btn btn-primary">목록</a>
		</div>
	</div>
	<br />

	<div class="card">
		<div class="form-group">
			<div class="card-body">
				<textarea class="form-control" rows="2" id="content"></textarea>
			</div>
			<div class="card-footer">
				<button id="comment--save--submit" class="btn btn-primary">등록</button>
			</div>
		</div>
	</div>
	<br />

	<div class="card">
		<div class="form-group">
			<div class="card-header">
				<h4 class="card-title">댓글 리스트</h4>
			</div>

			<div class="comment--items card-body">

				<div class="comment--item">
					<span class="comment--content">댓글내용1</span> <span id="comment--delete--submit" value="1">X</span>
				</div>

				<div class="comment--item">
					<span class="comment--content">댓글내용2</span> <span id="comment--delete--submit" value="2">X</span>
				</div>

				<div class="comment--item">
					<span class="comment--content">댓글내용3</span> <span id="comment--delete--submit" value="3">X</span>
				</div>

			</div>

		</div>
	</div>
</div>
<script>
$('#comment--save--submit').on('click', function(){

	var reply = $('#content').val();
	var userId = '';
	var postId = '';

	data = {
		comment : reply,
		userId : '',
		postId : ''
	};

	$.ajax({

		type : 'POST',
		url : '/comment/write',
		data : JSON.stringify(data),
		contentType : 'application/json; charset=utf-8',
		dataType : 'json'

	}).DONE(function(r){

		var res= '';
		res += "<div class='comment--item'>";
		res += "<span class='comment--content'>"+ r.comment +"</span>"
		res += "<span id='comment--delete--submit value='" + r.userId + "'>X</span></div>"

	$('.comment--items').prepend(res);

	}).fail(function(r){
		alert('통신에러');

	});

	
});

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
</script>

<%@ include file="../include/footer.jsp"%>


