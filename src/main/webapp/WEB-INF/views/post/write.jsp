<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../include/nav.jsp"%>

<div class="container">

	<form>
		<div class="form-group">
			<label for="title">제목:</label><div id="title_check"></div>
			<input type="text" class="form-control" placeholder="Enter Title" id="title" />
		</div>
		
		<div class="form-group">
			<label for="content">내용:</label><div id="content_check"></div>
			<textarea class="form-control" rows="5" id="content"></textarea>
		</div>
	</form>
	<div class="d-flex justify-content-end">
	<button id="write--submit" class="btn btn-primary">등록</button>
	</div>

</div>

<script src="/js/write.js"></script>
<%@ include file="../include/footer.jsp"%>


