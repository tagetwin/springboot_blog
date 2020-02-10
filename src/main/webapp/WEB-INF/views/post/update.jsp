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
	<div class="d-flex justify-content-end">
	<button id="update--submit" class="btn btn-primary" value="${post.userId}">수정</button>
	</div>
</div>

<script src="/js/update.js"></script>
<%@ include file="../include/footer.jsp"%>


