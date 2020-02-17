<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="../include/nav.jsp"%>

<div class="container">

	<table class="table table-hover">
		<thead class="thead-dark">
			<tr>
				<th>#</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성시간</th>
			</tr>
		</thead>
		<tbody id="tbody">
			<c:forEach var="list" items="${list}">
				<tr>
					<td>${list.id}</td>
					<td><a class="read" href="${list.id}">${list.title}</a></td>
					<td>${list.username}</td>
					<td><fmt:formatDate value="${list.createDate}" pattern="yyyy.MM.dd.HH:mm" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div id="pagenate">
		<ul class="pagination justify-content-center">
			<c:if test="${pageMaker.prev}">
				<li class="page-item"><a href="${pageMaker.startPage-1}" class="page-link">Prev</a></li>
			</c:if>

			<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
				<li class="page-item <c:out value="${pageMaker.cri.page == idx ? 'active' : ''}"/> "><a href="${idx}" class="page-link">${idx}</a></li>
			</c:forEach>

			<c:if test="${pageMaker.next}">
				<li class="page-item"><a href="${pageMaker.endPage+1}" class="page-link">Next</a></li>
			</c:if>
		</ul>
	</div>

	<!-- searchForm -->
	<form id='searchForm' action="/post" method='get'>
		<div class="row">
			<div class="form-group col-2">
				<select name='type' class="form-control">
					<option value="" <c:out value="${pageMaker.cri.type == null?'selected':''}"/>>--</option>
					<option value="T" <c:out value="${pageMaker.cri.type eq 'T'?'selected':''}"/>>제목</option>
					<%-- 				<option value="C" <c:out value="${pageMaker.cri.type eq 'C'?'selected':''}"/>>내용</option> --%>
					<option value="W" <c:out value="${pageMaker.cri.type eq 'W'?'selected':''}"/>>작성자</option>
					<%-- 				<option value="TC" <c:out value="${pageMaker.cri.type eq 'TC'?'selected':''}"/>>제목 or 내용</option> --%>
					<option value="TW" <c:out value="${pageMaker.cri.type eq 'TW'?'selected':''}"/>>제목 or 작성자</option>
					<%-- 				<option value="TWC" <c:out value="${pageMaker.cri.type eq 'TWC'?'selected':''}"/>>제목 or 내용 or 작성자</option> --%>
				</select>
			</div>
			<div class="col-8">
				<input class="form-control" type='text' name='keyword' value="${pageMaker.cri.keyword}">
				<input type='hidden' name='page' value="${pageMaker.cri.page}">
				<input type='hidden' name='perPageNum' value="${pageMaker.cri.perPageNum}">
			</div>
			<div class="col-2">
				<button type="button" id='s_btn' class="btn btn-info">Search</button>
			</div>
		</div>
	</form>

</div>
<form id="jobForm">
	<input type="hidden" name="page" value="${pageMaker.cri.page}" />
	<input type="hidden" name="perPageNum" value="${pageMaker.cri.perPageNum}" />
	<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}" />
	<input type="hidden" name="type" value="${pageMaker.cri.type}" />
</form>

<script src="/js/list.js"></script>
<%@ include file="../include/footer.jsp"%>



