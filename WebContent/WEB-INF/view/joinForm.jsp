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
<title>가입</title>
</head>
<body>
<u:navbar />

<div class="container">
	<div class="row">
		<div class="col-3"></div>
		<div class="col-6">
		
<h5>회원가입</h5>
<br />
<form action="${root }/join.do" method="post">
  <div class="form-group">
    <label for="id">아이디</label>
    <input type="text" name="id" class="form-control" id="id" value="${param.id }">
    <c:if test="${errors.id }">
    	<small id="id" class="form-text text-muted">ID를 입력하세요.</small>
    </c:if>
    <c:if test="${errors.duplicateId }">
    	<small id="id" class="form-text text-muted">이미 사용중인 ID입니다.</small>
    </c:if>
  </div>
  <div class="form-group">
    <label for="name">이름</label>
    <input type="text" name="name" class="form-control" id="name" value="${param.name }">
    <c:if test="${errors.name }">
    	<small id="name" class="form-text text-muted">name을 입력하세요.</small>
    </c:if>
  </div>
  <div class="form-group">
    <label for="password">암호</label>
    <input type="password" name="password" class="form-control" id="password" value="${param.password }">
    <c:if test="${errors.password}">
    	<small id="password" class="form-text text-muted">password을 입력하세요.</small>
    </c:if>
  </div>
  <div class="form-group">
    <label for="confirmPassword">확인</label>
    <input type="password" name="confirmPassword" class="form-control" id="confirmPassword" value="${param.confirmPassword }">
    <c:if test="${errors.confirmPassword}">
    	<small id="confirmPassword" class="form-text text-muted">password와 confirmPasword가 일치하지 않습니다.</small>
    </c:if>
  </div>
  <button type="submit" class="btn btn-primary">회원가입</button>
</form>

		</div>
		<div class="col-3"></div>
	</div>
</div>
</body>
</html>