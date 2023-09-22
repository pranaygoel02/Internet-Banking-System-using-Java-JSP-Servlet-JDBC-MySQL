<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ include file="AppLayout.jsp"%>
<%@ include file="Toast/Toast.jsp" %>
<h2 class="heading">Open a new account</h2>
<form action="new-account" method="POST">
	<label for="customer_id"><span>Customer ID</span>
		<input readonly="readonly" value="<%= (String)session.getAttribute("user_id") %>" name="customer_id" required="true"/>
	</label>
<%@ include file="NewAccount.html" %>
	<button type="submit">Submit</button>
</form>
<%@ include file="AppLayoutEnd.jsp"%>