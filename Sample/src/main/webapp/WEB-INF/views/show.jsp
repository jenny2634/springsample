<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

${totalCount} / ${totalPage}

<form action="http://localhost:8080/sample/show">
  <div class="form-group">
    <label for="search">검색어 :</label>
    <input type="text" class="form-control" id="search" name="search">
  </div>
  
  <button type="submit" class="btn btn-primary">검색</button>
</form>


<div class="list-group">
  
  <c:forEach items="${list}" var="item">
  <a href="#" class="list-group-item list-group-item-action">
   ${item.ID}/${item.TITLE}
  </a>
  </c:forEach>
 
</div>


<%-- <c:forEach begin="${startPage}" end="${endPage}" var="item">
	<c:if test="${item == param.page}">
		${item}
	</c:if>
	<c:if test="${item != param.page}">
		<a href="show?page=${item}">${item}</a>
	</c:if>
</c:forEach> --%>

<ul class="pagination">
	
	<li class="page-item"><a class="page-link" href="show?page=${startPage - 10}">Previous</a></li>
	
	
	<c:forEach begin="${startPage}" end="${endPage}" var="item">
		<c:if test="${item == param.page}">
			<li class="page-item active"><a class="page-link"
				href="show?page=${item}"> ${item}</a></li>
		</c:if>
		<c:if test="${item != param.page}">
			<li class="page-item"><a class="page-link"
				href="show?page=${item}"> ${item}</a></li>
		</c:if>
	</c:forEach>
	<li class="page-item"><a class="page-link" href="show?page=${startPage + 10}">Next</a></li>
</ul>

