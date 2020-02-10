<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../include/nav.jsp"%>

<div class="container">

	<form:form action="/user/profile/" method="PUT" enctype="multipart/form-data">
		<div class="form-group">
			<label for=username>유저네임:</label>
			<input type="text" class="form-control" placeholder="Enter Username" value="${sessionScope.principal.username}" readonly="readonly"/>
			<input type="hidden" class="form-control" name="id" value="${sessionScope.principal.id}" />
		</div>
		
		<div class="form-group">
			<label for="password">패스워드:</label>
			<input type="password" class="form-control"  placeholder="Enter Password" name="password" required="required"/>
		</div>
		
		<div class="form-group">
			<label for="email">이메일:</label>
			<input type="email" class="form-control" placeholder="Enter Email" value="${sessionScope.principal.email}" readonly="readonly"/>
		</div>
		
		<div class="form-group">
			<label for="profile">프로필 사진:</label>
			<input type="file" class="form-control" name="profile" />
			<p class="my__profile">${sessionScope.principal.profile}</p>
		</div>
		<button id="profile--submit" class="btn btn-primary">수정</button>	
	</form:form>

	

</div>

<script>
</script>
<%@ include file="../include/footer.jsp"%>


