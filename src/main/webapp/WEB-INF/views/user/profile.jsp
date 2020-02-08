<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../include/nav.jsp"%>

<div class="container">

	<form action="/user/profile/" method="POST" enctype="multipart/form-data">
		<div class="form-group">
			<label for=username>유저네임:</label>
			<input type="text" class="form-control" placeholder="Enter Username" name="username" value="${sessionScope.principal.username}" readonly="readonly"/>
			<input type="hidden" class="form-control" name="id" value="${sessionScope.principal.id}" />
		</div>
		
		<div class="form-group">
			<label for="password">패스워드:</label>
			<input type="password" class="form-control"  placeholder="Enter Password" name="password"/>
		</div>
		
		<div class="form-group">
			<label for="email">이메일:</label>
			<input type="email" class="form-control" placeholder="Enter Email" name="email" value="${sessionScope.principal.email}" readonly="readonly"/>
		</div>
		
		<div class="form-group">
			<label for="profile">프로필 사진:</label>
			<img src="/media/${sessionScope.principal.profile}" width="200">
			<input type="file" class="form-control" name="profile" value="${sessionScope.principal.profile}" />
		</div>
		<button id="profile--submit" class="btn btn-primary">수정</button>	
	</form>

	

</div>

<script>
// $('#profile--submit').on('click', function(){

// 		var no = $('#id').val();
		
// 		data ={
// 				id: $('#').val(),
// 			password : $('#password').val(),
// 			profile : $('#profile').val()
// 		}
		
// 		$.ajax({
// 			type : 'PUT',
// 			url : '/user/profile/',
// 			data : JSON.stringify(data),
// 			contentType : 'application/json; charset=utf-8',
// 			dataType :'json'
			
// 		}).done(function(r){
// 			console.log('수정 성공');
// 			location.href='/post/detail/'+no;	
			
// 		}).fail(function(r){
// 			console.log(r);
// 			alert('접근이 올바르지 않습니다.');		
			
// 		});
	
// });
</script>
<%@ include file="../include/footer.jsp"%>


