<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
		<tbody>
		<c:forEach var="list" items="${list}" varStatus="status">
			<tr class="pno" onclick="location.href='/post/detail/${list.id}'">
				<td>${list.id}</td>
				<td><a href="/post/detail/${list.id}">${list.title}</a></td>
				<td>${list.username}</td>
				<td><fmt:formatDate value="${list.createDate}" pattern="yyyy.MM.dd.HH:mm"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>

<%@ include file="../include/footer.jsp"%>



