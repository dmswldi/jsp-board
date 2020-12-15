<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach items="${replyList }" var="reply">
<tr>
	<td>
		<form action="">
			<input type="text" name="no" value="${reply.articleNum }" hidden=true />
			<span style="display:inline-block;width:50px">${reply.memberid }</span>
			<span style="display:inline-block;width:10px;color:lightgrey"> | </span>
			<input id="body" type="text" value="${reply.body }" readonly style="border:none; width:70%" />
			<span style="color:grey">${reply.regDate }</span>
			<input id="submit_btn" type="submit" value="등록" class="btn btn-light p-0"/>

			<a id="modify_btn">수정</a>
			<a id="delete_btn">삭제</a>
		</form>
	</td>
</tr>
</c:forEach>	
