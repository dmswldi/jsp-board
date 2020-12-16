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
<title>가입 완료</title>
</head>
<body>
<u:navbar />

<div class="container">
	<div class="jumbotron">
	  <h1 class="display-6">${param.name }님, 회원 가입에 성공했습니다.</h1>
	  <hr class="my-4">
	  <p>게시글을 작성해보세요.</p>
	  <a class="btn btn-primary btn-lg" href="${root }/login.do" role="button">로그인하기</a>
	</div>
</div>
</body>
</html>