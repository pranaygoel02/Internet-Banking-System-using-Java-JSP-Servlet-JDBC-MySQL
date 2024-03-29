<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
<style>
<%@ include file="/WEB-INF/CSS/style.css"%>
</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@include file="Protected.jsp"%>
	<%@ include file="Header.jsp"%>
	<section class="layout">
		<%@ include file="Sidenav.jsp"%>
		<script>
	const navMenuBtn = document.getElementById("nav-menu-btn");
	const navbar = document.getElementById("navbar");
	const closeBtn = document.getElementById("nav-menu-close-btn");
	if(navMenuBtn && navbar) {
		
		function toggleNavOpenState () {
			const currState = navbar.getAttribute("data-open"); 
			console.log(currState);
			navbar.setAttribute("data-open", currState == 'open' ? 'close' : 'open' );
		}
		
		navMenuBtn.addEventListener("click", toggleNavOpenState)
		closeBtn.addEventListener("click", toggleNavOpenState)
	}
</script>
		<div>