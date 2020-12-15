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
<script>
$(function(){// jquery가 첫 form에만 적용되네...? ?????????
	$('#submit_btn').hide();// 여기서 하면 고정되나? 흠
	$('#modify_btn').css('cursor', 'pointer');
	$('#delete_btn').css('cursor', 'pointer');
	$('#modify_btn').hover(
			function(){
				$('#modify_btn').css('text-decoration','underline');
			}, function(){
				$('#modify_btn').css('text-decoration','none');
			}
	);
	$('#delete_btn').hover(
			function(){
				$('#delete_btn').css('text-decoration','underline');
			}, function(){
				$('#delete_btn').css('text-decoration','none');
			}
	);
	
	
	$('#modify_btn').click(function(){
		$('#body').removeAttr("readonly");
		$('#body').focus();
		$('#delete_btn').hide();
		$('#modify_btn').hide();
		$('#submit_btn').show();//
	});
})
</script>
<script>
$(function(){
	$('#deleteArticle_btn').click(function(){
		var con = confirm("Are you Sure?");
		if(con){
			location.href="${root }/article/delete.do?no=${articleData.article.number }";
		}
	});
});
</script>
<title>게시글 읽기</title>
</head>
<body>
<div class="container">
<table class="table table-bordered">
	<tr>
		<td>번호</td>
		<td>${articleData.article.number }</td>
	</tr>
	<tr>
		<td>작성자</td>
		<td>${articleData.article.writer.name }</td>
	</tr>
	<tr>
		<td>제목</td>
		<td><c:out value="${articleData.article.title }" /></td>
	</tr>
	<tr>
		<td>내용</td>
		<td><u:pre value="${articleData.content.content }" /></td>
	</tr>
	<tr>
		<td colspan="2">
			<c:set var="pageNo" value="${empty param.pageNo ? '1' : param.pageNo }" />
			<a href="list.do?pageNo=${pageNo }">[목록]</a>
			<c:if test="${authUser.id == articleData.article.writer.id }">
				<a href="modify.do?no=${articleData.article.number }">[게시글수정]</a>
				<a id="deleteArticle_btn">[게시글삭제]</a>
			</c:if>
		</td>
	</tr>
</table>

<table class="table table-hover">
<tr>
<u:isLogin>
	<td>
		<u:replyForm articleNo="${articleData.article.number }" />
	</td>
</u:isLogin>
</tr>

		<u:listReply />

</table>
</div>
</body>
</html>