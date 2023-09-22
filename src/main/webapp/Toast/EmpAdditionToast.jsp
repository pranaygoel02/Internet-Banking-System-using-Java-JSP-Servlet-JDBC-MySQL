<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%
	if(session.getAttribute("emp_addition_success") != null) {
		if((boolean)session.getAttribute("emp_addition_success") == true) {
			out.println("<h2 class='toast toast-success'>Success</h2>");
		}
		else {
			out.println("<h2 class='toast toast-danger'>Failed</h2>");
		}
	}
%>