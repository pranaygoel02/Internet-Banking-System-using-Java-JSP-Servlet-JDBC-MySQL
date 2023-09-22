<%@page import="Model.Category"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%
if ((Category) session.getAttribute("category") != null && (Category) session.getAttribute("category") != Category.ADMIN) {
	response.sendRedirect("home");
}
%>
