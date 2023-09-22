<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ include file="AppLayout.jsp" %>
			<h2 class="text-xl font-bold">Hello, <%= session.getAttribute("user_name") != null ? (String)session.getAttribute("user_name") : (String)session.getAttribute("user_id") %></h2>
			Home Layout
<%@ include file="AppLayoutEnd.jsp" %>