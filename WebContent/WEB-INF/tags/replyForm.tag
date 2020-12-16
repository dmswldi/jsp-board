<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="articleNo" type="java.lang.Integer" %>


<div class="container">
	<form action="${root }/reply/add.do" method="post">
	  <div class="form-group">
	  	<input type="number" name="no" class="form-control" value="${articleNo }" hidden=true />
	    <input type="text" name="body" class="form-control">
	    <button type="submit" class="btn btn-primary form-control">댓글 등록</button>	<%-- inline으로... --%>
	  </div>
	</form>
</div>