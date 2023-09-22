<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ include file="AdminEmpLock.jsp" %>
<%@ include file="AppLayout.jsp" %>
			<h2>See Customers</h2>
			<div class="fix-width">
			<table>
			<thead>
				<tr>
					<th>Customer ID</th>
					<th>Name</th>
					<th>Email</th>
					<th>Accounts</th>
					<th>Address</th>
					<th>Phone</th>
					<th>Adhaar</th>
					<th>Pan</th>
					<th>DOB</th>
				</tr>
			</thead>
			<tbody>
			<%
				ResultSet customer_set = (ResultSet)request.getAttribute("customer_set");
				if(customer_set == null) {
					out.println("Loading...");
				}
				while(customer_set.next()) {
					out.println("<tr>");
					out.println(String.format("<td><a title='Click to see more' href='customer?customer_id=%s'>%s</a></td>", customer_set.getString("customer_id"), customer_set.getString("customer_id")));
					out.println(String.format("<td>%s</td>", customer_set.getString("customer_name")));
					out.println(String.format("<td>%s</td>", customer_set.getString("customer_email")));
					out.println(String.format("<td>%d</td>", customer_set.getInt("accounts")));
					out.println(String.format("<td>%s</td>", customer_set.getString("customer_addr")));
					out.println(String.format("<td>%s</td>", customer_set.getString("customer_phone")));
					out.println(String.format("<td>%s</td>", customer_set.getString("customer_adhaar")));
					out.println(String.format("<td>%s</td>", customer_set.getString("customer_pan")));
					out.println(String.format("<td>%s</td>", customer_set.getString("customer_dob")));
					out.println("</tr>");
				}
			%>
			</tbody>
			</table>
			</div>
<%@ include file="AppLayoutEnd.jsp" %>