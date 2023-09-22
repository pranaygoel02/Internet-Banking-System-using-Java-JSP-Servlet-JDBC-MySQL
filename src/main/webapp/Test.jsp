<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="test" method="post">
		Select a Category:&nbsp; <select name="branch">
			<c:forEach items="${listCategory}" var="branch">
				<option value="${branch.id}">${branch.name}</option>
			</c:forEach>
		</select> <br />
		<br /> <input type="submit" value="Submit" />
	</form>
</body>
</html>