<%@page import="java.sql.ResultSet"%>
<%@page import="Classes.CategoryClass"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ include file="AppLayout.jsp"%>
<div class="flex-row">
<%@ include file="User.jsp"%>
<p><%=((Category) session.getAttribute("category")).toString()%>
	ID:
	<%=(String) session.getAttribute("user_id")%></p>
</div>
	<c:if test="${account_list.size() > 0 }">
	<h2 class="sub-heading">Your Accounts</h2>
	</c:if>
<section class="ac-section">
	<c:forEach items="${account_list}" var="ac">
		<a href="account-info?account_no=${ac.no}&page=${1}" class="ac-card">
			<p>A/c No.: ${ac.no}</p>
			<h2>&#8377;${ac.balance}</h2>
			<p class="flex-row">${ac.type}<span data-status="${ac.status}">${ac.status}</span>
			</p>
		</a>
	</c:forEach>
</section>

<%@ include file="AppLayoutEnd.jsp"%>