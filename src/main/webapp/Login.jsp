<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<style><%@include file="/WEB-INF/CSS/style.css"%></style>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body class="login-main">
	<%@ include file="Header.jsp"%>
	<main class="login-form login-form-grid">
		<section>
		<div class="container">
			<h2 class="login-header">Login to Internet Banking</h2>
			<%@include file="Login.html"%>
			<%
			Object invalid_creds = session.getAttribute("invalid_creds");
			if (invalid_creds != null && (boolean) invalid_creds) {
				out.println("Invalid Credentials");
			}
			%>
			</div>
		</section>
	</main>
	<img class="login-img" src="${pageContext.request.contextPath}/Images/login_hero.jpg"/>
</body>
</html>