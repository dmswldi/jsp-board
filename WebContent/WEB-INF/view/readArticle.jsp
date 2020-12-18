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
<script>
$(function(){
	// 게시글
	$('#deleteArticle_btn').click(function(){
		var con = confirm("Are you Sure?");
		if(con){
			location.href="${root }/article/delete.do?no=${articleData.article.number }";
		}
	});
	
	// 댓글
	var body = "";
	
	$('.submit_btn').hide();
	$('.cancel_btn').hide();
	$('.submit_btn').css('cursor', 'pointer');
	$('.modify_btn').css('cursor', 'pointer');
	$('.delete_btn').css('cursor', 'pointer');
	$('.cancel_btn').css('cursor', 'pointer');
	
	$('.submit_btn').hover(
			function(){
				$(this).css('text-decoration','underline');
			}, function(){
				$(this).css('text-decoration','none');
			}
	);
	$('.modify_btn').hover(
			function(){
				$(this).css('text-decoration','underline');
			}, function(){
				$(this).css('text-decoration','none');
			}
	);
	$('.delete_btn').hover(
			function(){
				$(this).css('text-decoration','underline');
			}, function(){
				$(this).css('text-decoration','none');
			}
	);
	$('.cancel_btn').hover(
			function(){
				$(this).css('text-decoration','underline');
			}, function(){
				$(this).css('text-decoration','none');
			}
	);
	
	
	$('.modify_btn').click(function(){
		$(this).hide();
		$(this).next().hide();
		$(this).siblings(".cancel_btn").show();
		$(this).siblings(".form").find(".submit_btn").show();
		$(this).siblings(".form").find(".body").removeAttr("readonly");
		body = $(this).siblings(".form").find(".body").val();// 원래 댓글 변수에 저장
	});
	
	$('.delete_btn').click(function(){
		var con = confirm("Are you Sure?");
		if(con){
			var no = $(this).siblings(".form").find(".no").val();
			var id = $(this).siblings(".form").find(".id").val();
			location.href = "${root}/reply/delete.do?no=" + no + "&id=" + id;///// articleNo, replyid 넘겨주기
		}
	});
	
	$('.cancel_btn').click(function(){
		$(this).hide();
		$(this).prev().show();
		$(this).siblings(".modify_btn").show();
		$(this).siblings(".form").find(".submit_btn").hide();
		$(this).siblings(".form").find(".body").attr("readonly", "true").val(body);// 댓글 원래대로(수정 전)
	});

	// closest 위, find 아래
	
	
})
</script>
<style>
.left {
	width: 30%;
}

.right {
	width: 70%;
}
</style>
<title>게시글 읽기</title>
</head>
<body>
<u:navbar />

<div class="container">
	<div class="row">
		<div class="col-3"></div>
		<div class="col-6">
			<h5>글 상세보기</h5>
			<br />
			<table class="table table-striped">
				<tbody>					
					<tr>
						<th class="left"><i class="fab fa-slack-hash"></i></th>
						<td class="right">${articleData.article.number }</td>
					</tr>
					<tr>
						<th class="left"><i class="fas fa-user-edit"></i></th>
						<td class="right">${articleData.article.writer.name }</td>
					</tr>
					<tr>
						<th class="left">제목</th> <%-- 얘만 c:out??? --%>
						<td class="right"><c:out value="${articleData.article.title }" /></td>
					</tr>
					<tr>
						<th class="left">내용</th>
						<td class="right"><u:pre value="${articleData.content.content }" /></td>
					</tr>
				</tbody>
			</table>
			
			<c:set var="pageNo" value="${empty param.pageNo ? '1' : param.pageNo }" />
			<c:if test="${authUser.id == articleData.article.writer.id }">
				<button class="btn btn-danger float-right" id="deleteArticle_btn">삭제</button>
				<%-- 왜 순서가 거꾸로죠!!!!! --%>
				<button class="btn btn-light float-right" onclick="location.href='${root }/article/modify.do?no=${articleData.article.number }'">수정</button>
			</c:if>
			
			
		</div>
	</div>
		<table class="table table-hover mt-4">
			<tr>
				<u:isLogin> <%-- 그냥 다 보이게 하고 로그인하면 활성화하자 --%>
					<td>
						<u:replyForm articleNo="${articleData.article.number }" />
					</td>
				</u:isLogin>
			</tr>
		
				<u:listReply />
		</table>
</div>









<%--


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
 --%>
</body>
</html>