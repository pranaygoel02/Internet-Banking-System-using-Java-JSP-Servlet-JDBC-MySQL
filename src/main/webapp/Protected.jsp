<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%
if ((String) session.getAttribute("user_id") == null) {
	response.sendRedirect("login");
}
%>
