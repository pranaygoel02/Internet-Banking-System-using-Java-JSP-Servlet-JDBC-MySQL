<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%
	if(session.getAttribute("show_success") != null) {
		if((boolean)session.getAttribute("show_success") == true) {
			out.println("<h2 class='toast toast-success'><i class='bi bi-check2-circle'></i> Success</h2>");
		}
		else {
			out.println("<h2 class='toast toast-danger'><i class='bi bi-x-circle'></i> Failed</h2>");
		}
	}
%>