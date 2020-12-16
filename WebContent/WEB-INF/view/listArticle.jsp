<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<style>
.num-col {
	width: 10%;
}

.title-col {
	width: 65%;
}

.read-col {
	width: 10%;
}

.writer-col {
	width: 15%;
}
</style>
<title>게시글 목록</title>
</head>
<body>
<u:navbar />

<div class="container">
	<div class="row">
		<div class="col-3"></div>
		<div class="col-6">
			<h5>게시글 목록</h5>
			<br />
			<div class="list-container">
				<table class="table table-striped">
					<thead>
						<tr>
							<th class="num-col"><i class="fab fa-slack-hash"></i></th>
							<th class="title-col">제목</th>
							<th class="read-col"><i class="fas fa-eye"></i></th>
							<th class="writer-col"><i class="fas fa-user-edit"></i></th>
						</tr>
					</thead>
				
				
					<c:if test="${articlePage.hasNoArticles() }">
						<tr>
							<td colspan="4">게시글이 없습니다.</td>
						</tr>
					</c:if>
				
				
					<tbody>
						<c:forEach var="article" items="${articlePage.content }">
							<tr>
								<td class="text-right">${article.number }</td>
								<td>
									<a href="${root }/article/read.do?no=${article.number }&pageNo=${articlePage.currentPage }">
										<c:out value="${article.title }" />
									</a>
								</td>
								<td class="text-right">${article.readCount }</td>
								<td>${article.writer.name }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
			<nav aria-label="Page navigation example">
			  <ul class="pagination">
			  	<c:if test="${articlePage.startPage > 5 }">
				    <li class="page-item">
				      <a class="page-link" href="${root }/article/list.do?pageNo=${articlePage.startPage -5 }">Previous</a>
				    </li>
			    </c:if>
			    
			    <c:forEach var="pNo" begin="${articlePage.startPage }" end = "${articlePage.endPage }">
					<li class="page-item"><a class="page-link" href="${root }/article/list.do?pageNo=${pNo }">${pNo }</a></li>
				</c:forEach>
			
				<c:if test="${articlePage.endPage < articlePage.totalPages }">
				    <li class="page-item">
				      <a class="page-link" href="${root }/article/list.do?pageNo=${articlePage.startPage + 5 }">Next</a>
				    </li>
			    </c:if>
			  </ul>
			</nav>
			
			
		</div>
	</div>
</div>
</body>
</html>