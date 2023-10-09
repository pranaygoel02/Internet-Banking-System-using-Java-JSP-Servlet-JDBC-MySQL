<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%
	if(session.getAttribute("show_success") != null) {
		if((boolean)session.getAttribute("show_success") == true) {
			out.println("<h2 id='toast' class='toast toast-success'><i class='bi bi-check2-circle'></i> Success. " + (String)session.getAttribute("toast_msg") + "</h2>");
		}
		else {
			out.println("<h2 id='toast' class='toast toast-danger'><i class='bi bi-x-circle'></i> Failed. " + (String)session.getAttribute("toast_msg") + "</h2>");
		}
	}
	session.removeAttribute("show_success");
	session.removeAttribute("toast_msg");
%>
<script>
	const toastDiv = document.getElementById("toast");
	if(toastDiv) {
		setTimeout(() => {
			toastDiv.style.display = "none";
		},5000)
	}
</script>
