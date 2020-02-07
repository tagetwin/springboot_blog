<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../include/nav.jsp"%>

<div class="container">

	<form>
		<div class="form-group">
			<label for="title">제목:</label>
			<input type="text" class="form-control" placeholder="Enter Title" id="title" />
			<input type="hidden" id="userId" value="${sessionScope.principal.id}"/>
		</div>
		
		<div class="form-group">
			<label for="content">내용:</label>
			<textarea class="form-control" rows="5" id="content"></textarea>
		</div>
	</form>

	<button id="write--submit" class="btn btn-primary">등록</button>

</div>

<script>
$('#write--submit').on('click', function(){
	
	var data ={
		title : $('#title').val(),
		content : $('#content').val(),
		userId : $('#userId').val()	
	} 
		

	$.ajax({
		type : 'POST',
		url : '/post/write/',
		data : JSON.stringify(data),
		contentType : 'application/json; charset=utf-8',
		dataType :'json'
		
	}).done(function(r){
		console.log(r);
		if(r.statusCode == 200){
			alert('글을 등록하였습니다.');
			location.href='/post';	
		}else{
			alert('글 등록에 실패하였습니다.');	
		}
		
	}).fail(function(r){
		console.log(r);
		console.log('통신실패');		
		
	});
	
});
</script>
<%@ include file="../include/footer.jsp"%>


