<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
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
			<%@ include file="Toast/Toast.jsp" %>
			<h2 class="login-header">Login to Internet Banking</h2>
			<%@include file="Login.html"%>
			<%
			Object invalid_creds = session.getAttribute("invalid_creds");
			if (invalid_creds != null && (boolean) invalid_creds) {
				out.println("Invalid Credentials");
			}
			%>
			<p style="text-align: center">New to Elite Bank? <a style="text-decoration:underline" href="register">Register here</a></p>
			</div>
		</section>
	</main>
	<img class="login-img" src="${pageContext.request.contextPath}/Images/login_hero.jpg"/>
</body>
</html>