<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ include file="AppLayout.jsp"%>
<%@ include file="OnlyAdminLock.jsp" %>
<%@ include file="Toast/Toast.jsp" %>
<%
ResultSet rs = (ResultSet) request.getAttribute("branch_details");
%>
<h2 class="heading">Branch Details</h2>
<form action="update-branch" method="POST">
	<label for="branch_id" ><span>Branch ID</span>
		<input name="branch_id" readonly="readonly" value="<%= rs.getString("branch_id")%>" required="true"/>
	</label>
	<label for="name" ><span>Branch Name</span>
		<input name="name" value="<%= rs.getString("branch_name")%>" required="true"/>
	</label>
	<label for="email" ><span>Email</span>
		<input  value="<%= rs.getString("branch_email")%>" name="email" type="email" required="true"/>
	</label>
	<label for="phone" ><span>Phone</span>
	<input name="phone" value="<%= rs.getString("branch_phone")%>" pattern="[0-9]{10}" minlength="10" maxlength="10" required="true"/>
	</label>
	<label for="city" ><span>City</span>
		<input name="city" value="<%= rs.getString("branch_city")%>" required="true"/>
	</label>
	<label for="address"><span>Address</span>
		<textarea name="address" required="true"><%= rs.getString("branch_addr")%></textarea>
	</label>
	<label for="pincode"><span>Pin Code</span>
		<input name="pincode" value="<%= rs.getString("branch_pincode")%>" required="true" />
	</label>
	<label for="min_balance"><span>Min. Balance for Savings A/c</span>
		<input name="min_balance" required="true" value="<%= rs.getString("min_balance")%>" />
	</label>
	<button type="submit">Update</button>
</form>

<%@ include file="AppLayoutEnd.jsp"%>