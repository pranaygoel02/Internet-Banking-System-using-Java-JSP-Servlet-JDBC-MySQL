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
		<div class="container" style="padding-inline: 10rem">
			<%@ include file="Toast/Toast.jsp" %>
			<h2 class="login-header">Register</h2>
			<form action="register" method="POST">
			<%@ include file="NewCustomerForm.jsp" %>
			</form>
			<p style="text-align: center">Already a customer? <a style="text-decoration:underline" href="login">Login</a></p>
			</div>
		</section>
	</main>
	<img class="login-img" src="${pageContext.request.contextPath}/Images/login_hero.jpg"/>
</body>
</html>