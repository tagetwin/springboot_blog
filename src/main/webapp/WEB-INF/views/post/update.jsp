<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../include/nav.jsp"%>

<div class="container">

	<form>
		<div class="form-group">
			<label for="title">제목:</label>
			<input id="id" type="hidden" value="${post.id}"/>
			<input type="text" class="form-control" placeholder="Enter Title" id="title" value="${post.title}"/>
		</div>
		
		<div class="form-group">
			<label for="content">내용:</label>
			<textarea class="form-control" rows="5" id="content">${post.content}</textarea>
		</div>
	</form>

	<button id="update--submit" class="btn btn-primary" value="${post.userId}">수정</button>

</div>

<script>
$('#update--submit').on('click', function(){

		var no = $('#id').val();
		
		data ={
			id : no,
			title : $('#title').val(),
			content : $('#content').val()
		}
		
		$.ajax({
			type : 'PUT',
			url : '/post/update/',
			data : JSON.stringify(data),
			contentType : 'application/json; charset=utf-8',
			dataType :'json'
			
		}).done(function(r){
			alert('수정이 성공하였습니다.');
			location.href='/post/detail/'+no;	
			
		}).fail(function(r){
			alert('통신이 실패하였습니다.');		
			
		});
	
});
</script>
<%@ include file="../include/footer.jsp"%>


