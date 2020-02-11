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
			<c:forEach var="list" items="${list}" varStatus="status">
				<tr class="pno" >
					<td>${list.id}</td>
					<td>${list.title}</td>
					<td>${list.username}</td>
					<td><fmt:formatDate value="${list.createDate}" pattern="yyyy.MM.dd.HH:mm" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<!-- pagination{s} -->
	<div id="paginationBox" class="">
		<ul class="pagination justify-content-center">
			<c:if test="${pagination.prev}">
				<li class="page-item">
				<a href="#" class="page-link" onClick="fn_prev('${pagination.page}', '${pagination.range}', '${pagination.rangeSize}')">Prev</a>
				</li>
			</c:if>

			<c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" var="idx">
				<li class="page-item <c:out value="${pagination.page == idx ? 'active' : ''}"/> ">
				<a href="#" class="page-link" onClick="fn_pagination('${idx}', '${pagination.range}', '${pagination.rangeSize}')"> ${idx} </a></li>
			</c:forEach>

			<c:if test="${pagination.next}">
				<li class="page-item">
				<a href="#" class="page-link" onClick="fn_next('${pagination.range}','${pagination.range}', '${pagination.rangeSize}')">Next</a>
				</li>
			</c:if>
		</ul>
	</div>

	<!-- pagination{e} -->
</div>


<script src="/js/list.js"></script>

<%@ include file="../include/footer.jsp"%>



