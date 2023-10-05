<%@page import="java.sql.ResultSet"%>
<%@page import="Classes.CategoryClass"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ include file="AppLayout.jsp"%>
<h1 class="">
	Hello,
	<%=session.getAttribute("user_name") != null ? (String) session.getAttribute("user_name")
		: (String) session.getAttribute("user_id")%></h1>
<p><%=((Category) session.getAttribute("category")).toString()%>
	ID:
	<%=(String) session.getAttribute("user_id")%></p>
<section class="ac-section">
	<c:forEach items="${account_list}" var="ac">
		<a href="account-info?account_no=${ac.no}" class="ac-card">
			<p>A/c No.: ${ac.no}</p>
			<h2>&#8377;${ac.balance}</h2>
			<p>${ac.type}<span data-status="${ac.status}">${ac.status}</span>
			</p>
		</a>
	</c:forEach>
</section>

<%@ include file="AppLayoutEnd.jsp"%>