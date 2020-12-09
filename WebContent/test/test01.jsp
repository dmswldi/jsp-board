<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>test01</h1>
<form action="${root }/test01" method="post">
name : <input type="text" name="name" />
<br>
<input type="submit" value="전송" />
</form>
</body>
</html>