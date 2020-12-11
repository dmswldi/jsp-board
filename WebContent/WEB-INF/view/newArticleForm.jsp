<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>게시글 쓰기</title>
</head>
<body>
<div class="container">
<form action="write.do" method="post">
<h1>게시글 작성</h1>
<p>
	제목 : <br />
	<input type="text" name="title" value="${param.title }" />
	<c:if test="${errors.title }">제목을 입력하세요.</c:if>
</p>
<p>
	내용 : <br />
	<textarea name="content" cols="30" rows="10">${param.content }</textarea>
</p>
<input type="submit" value="새 글 등록" />
</form>
</div>
</body>
</html>