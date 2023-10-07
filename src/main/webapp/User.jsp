<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<h1 class="heading">
	Hello,<br/>
	<%=session.getAttribute("user_name") != null ? (String) session.getAttribute("user_name")
		: (String) session.getAttribute("user_id")%></h1>