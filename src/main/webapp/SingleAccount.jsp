<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ include file="AppLayout.jsp"%>
<%@ include file="AdminEmpLock.jsp" %>
<%@ include file="Toast/Toast.jsp" %>
<%
String acc_status = (String) request.getAttribute("acc_status");
%>
<h2 class="heading">Customer Details</h2>
<form action="update-account-status" method="POST">
	<label for="name"><span>Account No</span>
		<input name="account_no" value="<%= request.getParameter("account_no") %>" readonly="readonly" required="true"/>
	</label>
	<label>
	<span>Status</span>
	<%@ include file="AccountStatusSelect.html" %>
	</label>
	<button type="submit">Update</button>
</form>
<script>
	document.getElementsByTagName("SELECT")[0].value = "<%= acc_status %>";
</script>
<%@ include file="AppLayoutEnd.jsp"%>