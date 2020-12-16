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
<title>회원제 게시판 예제</title>
</head>
<body>
<u:navbar />

<div class="container">
		
<div class="jumbotron">
  <h1 class="display-4">Hello, strangers!</h1>
  <p class="lead">회원제 게시판을 이용해보세요. 다른 사람들과 자신의 글을 공유해보세요.</p>
  <hr class="my-4">
  <p>회원가입부터 시작해보세요.</p>
  <a class="btn btn-primary btn-lg" href="${root }/join.do" role="button">회원가입</a>
</div>

</div>

<%--
<div class="container">
 <u:isLogin>
 	${authUser.name }님, 안녕하세요.
	<a href="logout.do">[로그아웃하기]</a> <%-- contextPath 이후로 href 작성!!! --%>
<%--	<a href="article/write.do">[게시글작성하기]</a> <%-- /article 하면 포트번호 뒤에 붙음 ,,,,, 절대 경로 --%>
<%--
	<a href="article/list.do">[게시글목록보기]</a>
	<a href="article/mylist.do">[내가쓴글목록보기]</a>
	<a href="changePwd.do">[암호변경하기]</a>
	<a href="removeMember.do">[회원탈퇴하기]</a>
 </u:isLogin>
 <u:notLogin>
	<a href="join.do">[회원가입하기]</a>
	<a href="login.do">[로그인하기]</a>
	<a href="article/list.do">[게시글목록보기]</a>
 </u:notLogin>
 --%>

</div>
</body>
</html>