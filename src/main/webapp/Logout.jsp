<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%= session.getAttribute("user_id") != null ?  "<a href='__logout' class='button logout-btn text-primary'>Logout</a>" : "" %>
