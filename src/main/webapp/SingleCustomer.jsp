<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ include file="AppLayout.jsp"%>
<%@ include file="AdminEmpLock.jsp" %>
<%@ include file="Toast/Toast.jsp" %>
<%
ResultSet rs = (ResultSet) request.getAttribute("customer_details");
%>
<h2 class="heading">Customer Details</h2>
<form action="update-customer" method="POST">
	<label for="name"><span>Customer ID</span>
		<input name="customer_id" value="<%= rs.getString("customer_id") %>" readonly="readonly" required="true"/>
	</label>
	<label for="name"><span>Full Name</span>
		<input name="name" value="<%= rs.getString("customer_name") %>" required="true" />
	</label>
	<label for="email"><span>Email</span>
		<input name="email" type="email" value="<%= rs.getString("customer_email")%>" required="true" />
	</label>
	<label for="address"><span>Address</span>
		<textarea name="address" required="true"><%= rs.getString("customer_addr")%></textarea>
	</label>
	<label for="phone"><span>Phone</span>
		<input name="phone" pattern="[0-9]{10}" value="<%= rs.getLong("customer_phone")%>" minlength="10" maxlength="10"
			required="true" />	</label>
	<label for="adhaar"><span>Adhaar Number</span>
		<input name="adhaar" maxlength="12" value="<%= rs.getLong("customer_adhaar")%>" required="true" />
	</label>
	<label for="pan"><span>Pan Number</span>
		<input name="pan" required="true" value="<%= rs.getString("customer_pan")%>" />
	</label>
	<label for="dob"><span>DOB</span>
		<input name="dob" value="<%= rs.getString("customer_dob")%>" type="date" required="true" />
	</label>
	<button type="submit">Update</button>
</form>

<%@ include file="AppLayoutEnd.jsp"%>