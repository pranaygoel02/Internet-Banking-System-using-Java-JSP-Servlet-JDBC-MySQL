<%@page import="Util.FormatCurrency"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ include file="AdminEmpLock.jsp" %>
<%@ include file="AppLayout.jsp" %>
			<h2 class="heading">Branches</h2>
			<div class="fix-width">
			<table>
			<thead>
				<tr>
					<th>Branch ID</th>
					<th>Name</th>
					<th>Email</th>
					<th>Phone</th>
					<th>City</th>
					<th>Address</th>
					<th>Pin Code</th>
					<th>Min. Balance for Savings A/c <%@ include file="Ruppee.jsp" %> </th>
					<th>DOA</th>
				</tr>
			</thead>
			<tbody>
			<%
				ResultSet branch_set = (ResultSet)request.getAttribute("branch_set");
				if(branch_set == null) {
					out.println("Loading...");
				}
				while(branch_set.next()) {
					out.println("<tr>");
					out.println(String.format((Category)session.getAttribute("category") == Category.ADMIN ? "<td><a title='Click to see more' href='branch?branch_id=%s'>%s</a></td>" : "<td>%s</td>", branch_set.getString("branch_id"),branch_set.getString("branch_id")));
					out.println(String.format("<td>%s</td>", branch_set.getString("branch_name")));
					out.println(String.format("<td>%s</td>", branch_set.getString("branch_email")));
					out.println(String.format("<td>%s</td>", branch_set.getString("branch_phone")));
					out.println(String.format("<td>%s</td>", branch_set.getString("branch_city")));
					out.println(String.format("<td>%s</td>", branch_set.getString("branch_addr")));
					out.println(String.format("<td>%d</td>", branch_set.getInt("branch_pincode")));
					out.println(String.format("<td>%s</td>", FormatCurrency.getFormatted(branch_set.getDouble("min_balance"))));
					out.println(String.format("<td>%s</td>", branch_set.getDate("doc")));
					out.println("</tr>");
				}
			%>
			</tbody>
			</table>
			</div>
<%@ include file="AppLayoutEnd.jsp" %>