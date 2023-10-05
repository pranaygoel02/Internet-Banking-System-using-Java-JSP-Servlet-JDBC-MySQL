<%@page import="Util.FormatCurrency"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ include file="AdminEmpLock.jsp" %>
<%@ include file="AppLayout.jsp" %>
			<h2 class="heading">See Accounts</h2>
			<div class="fix-width">
			<table>
			<thead>
				<tr>
					<th>Account No</th>
					<th>Customer ID</th>
					<th>Category</th>
					<th>Balance <%@ include file="Ruppee.jsp" %> </th>
					<th>DOC</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody>
			<%
				ResultSet account_set = (ResultSet)request.getAttribute("account_set");
				if(account_set == null) {
					out.println("Loading...");
				}
				while(account_set.next()) {
					out.println("<tr>");
					out.println(String.format("<td><a title='Click to see more' href='account?account_no=%s'>%s</a></td>", account_set.getString("account_no"), account_set.getString("account_no")));
					out.println(String.format("<td><a title='Click to see more' href='customer?customer_id=%s'>%s</a></td>", account_set.getString("customer_id"), account_set.getString("customer_id")));
					out.println(String.format("<td>%s</td>", account_set.getString("account_type")));
					out.println(String.format("<td>%s</td>", FormatCurrency.getFormatted(account_set.getDouble("balance"))));
					out.println(String.format("<td>%s</td>", account_set.getString("doc")));
					out.println(String.format("<td>%s</td>", account_set.getString("status")));
					out.println("</tr>");
				}
			%>
			</tbody>
			</table>
			</div>
<%@ include file="AppLayoutEnd.jsp" %>