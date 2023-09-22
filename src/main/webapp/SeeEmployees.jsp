<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ include file="OnlyAdminLock.jsp" %>
<%@ include file="AppLayout.jsp" %>
			<h2>See Employees</h2>
			<div class="fix-width">
			<table>
			<thead>
				<tr>
					<th>Emp ID</th>
					<th>Name</th>
					<th>Branch ID</th>
					<th>Role</th>
					<th>Email</th>
					<th>City</th>
					<th>Phone</th>
					<th>Adhaar</th>
					<th>Pan</th>
				</tr>
			</thead>
			<tbody>
			<%
				ResultSet emp_set = (ResultSet)request.getAttribute("emp_set");
				while(emp_set.next()) {
					out.println("<tr>");
					out.println(String.format("<td><a title='Click to see more' href='employee?emp_id=%s'>%s</a></td>", emp_set.getString("emp_id"), emp_set.getString("emp_id")));
					out.println(String.format("<td>%s</td>", emp_set.getString("emp_name")));
					out.println(String.format("<td><a title='Click to see more' href='branch?branch_id=%s'>%s</a></td>", emp_set.getString("branch_id"), emp_set.getString("branch_id")));
					out.println(String.format("<td>%s</td>", emp_set.getString("emp_role")));
					out.println(String.format("<td>%s</td>", emp_set.getString("emp_email")));
					out.println(String.format("<td>%s</td>", emp_set.getString("emp_city")));
					out.println(String.format("<td>%s</td>", emp_set.getString("emp_phone")));
					out.println(String.format("<td>%s</td>", emp_set.getString("emp_adhaar")));
					out.println(String.format("<td>%s</td>", emp_set.getString("emp_pan")));
					out.println("</tr>");
				}
			%>
			</tbody>
			</table>
			</div>
<%@ include file="AppLayoutEnd.jsp" %>