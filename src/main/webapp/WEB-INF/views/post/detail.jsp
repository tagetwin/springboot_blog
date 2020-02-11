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

		<div class="card-footer d-flex">
			<input type="hidden" id="id" value="${post.id}" />
			<c:if test='${post.userId eq sessionScope.principal.id }'>

				<button id="post--update--submit" class="btn btn-warning">수정</button>
				<button id="post--delete--submit" class="btn btn-danger ml-3">삭제</button>
			</c:if>
			<div class="ml-auto">
				<a href="/" class="btn btn-primary">목록</a>
			</div>
		</div>
	</div>
	<br />

	<c:if test="${not empty sessionScope.principal}">
		<div class="card">
			<div class="form-group">
				<div class="card-body">
					<input id="userId" type="hidden" value="${sessionScope.principal.id}" />
					<textarea required="required" class="form-control" rows="2" id="content"></textarea>
				</div>
				<div class="card-footer d-flex justify-content-end">
					<button id="comment--save--submit" class="btn btn-primary float-right">등록</button>
				</div>
			</div>
		</div>
	</c:if>
	<br />

	<div class="card">
		<div class="form-group">
			<div class="card-header">
				<h4 class="card-title">댓글 리스트</h4>
			</div>

			<div id="comment--items" class="card-body">
				<c:forEach var="comments" items="${comments}">
				
					<div id="comment--item--${comments.id}">
						<ul class="comment--item list-group">
							<li class="comment--username list-group-item d-flex justify-content-between align-items-center">${comments.username}
								<c:if test="${comments.userId eq sessionScope.principal.id}">
									<span id="del" class="badge badge-danger badge-pill" onclick="commentDelete(${comments.id})">삭제</span>
								</c:if>
							</li>
							<li class="comment--username list-group-item"><span class="comment--content">${comments.content}</span></li>
						</ul>
					</div>
					
				</c:forEach>
			</div>
		</div>
	</div>
</div>

<script src="/js/detail.js"></script>

<%@ include file="../include/footer.jsp"%>



