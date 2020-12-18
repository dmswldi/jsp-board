<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach items="${replyList }" var="reply"> <%-- ReadArticleHandler에서 req에 set --%>
<tr>
	<td>
		<form action="${root}/reply/modify.do" class="form" style="display:inline"> <%-- replyid, body(변경 내용) --%> 
			<input type="text" name="no" class="no" value="${reply.articleNum }" hidden=true />
			<input type="text" name="id" class="id" value="${reply.id }" hidden=true />
			<input type="text" name="memberid" value="${reply.memberid }" hidden=true />
			<span style="display:inline-block; width:50px">${reply.memberid }</span>
			<span style="display:inline-block; width:10px; color:lightgrey"> | </span>
			<input class="body" name="body" type="text" value="${reply.body }" readonly style="border:none; width:70%" />
			<span style="color:grey">${reply.regDate }</span>
			<input type="submit" value="등록" class="submit_btn btn btn-light p-0"/>
		</form>
		<c:if test="${authUser.id eq reply.memberid }" > <%-- authUser가 쓴 댓글만 수정, 삭제 가능 --%>
			<button class="modify_btn btn btn-light p-0">수정</button>
			<button class="delete_btn btn btn-light p-0">삭제</button>
			<button class="cancel_btn btn btn-light p-0">취소</button>
		</c:if>
	</td>
</tr>
</c:forEach>	
