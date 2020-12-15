<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="articleNo" type="java.lang.Integer" %>

<form action="${root }/reply/add.do" method="post">
	<input type="number" name="no" value="${articleNo }" hidden=true />
	<input type="text" name="body" />
	<input type="submit" value="댓글 등록" />
</form>