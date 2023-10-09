<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ include file="AppLayout.jsp"%>
<%@ include file="AdminEmpLock.jsp" %>
<%@ include file="Toast/Toast.jsp" %>
<h2 class="heading">Add Customer</h2>
<form action="add-customer" method="POST">
<%@ include file="NewCustomerForm.jsp" %>
<%@ include file="AppLayoutEnd.jsp"%>