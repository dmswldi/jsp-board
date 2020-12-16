<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<title>Insert title here</title>
</head>
<body>
	<u:navbar />
	
<div class="container">
	<div class="row">
		<div class="col-3"></div>
		<div class="col-6">
			<h5>로그인</h5>
			<c:if test="${errors.idOrPwNotMatch}">
		    	<small class="form-text text-muted">아이디와 암호가 일치하지 않습니다.</small>
		    </c:if>
			
			<form action="${root }/login.do" method="post">
			  <div class="form-group">
				  <label for="id">아이디</label>
				  <input type="text" class="form-control" id="id" name="id" value="${param.id }" />
			  	  <c:if test="${errors.id}">
			    	  <small class="form-text text-muted">ID를 입력하세요.</small>
			      </c:if>
			  </div>
			  <div class="form-group">
				  <label for="password">패스워드</label>
				  <input type="password" class="form-control" id="password" name="password" />
				  <c:if test="${errors.password}">
			    	  <small class="form-text text-muted">암호를 입력하세요.</small>
			      </c:if>
			  </div>
			  <button class="btn btn-primary">로그인</button>
			  
			</form>
		</div>
		<div class="col-3"></div>
	</div>
</div>
	
</body>
</html>