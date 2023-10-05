<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ include file="AppLayout.jsp" %>
<%@ include file="Toast/Toast.jsp" %>
			<h2 class="heading">Transaction</h2>
			<form method="POST" action="transaction">
			<label for="from-ac"><span>From A/c</span>
			<select name="from-ac">
			<c:forEach items="${account_list}" var="ac">
        		<option>
        		${ac.no} - Rs. ${ac.balance}
        		</option>
    		</c:forEach>
			</select>
			</label>
			<label for="to-ac"><span>To A/c</span>
			<input type="text" name="to-ac" placeholder="Enter receiver account number"/>
			</label>
			<label for="amt"><span>Amount</span>
			<input type="text" name="amt" placeholder="Enter amount"/>
			</label>
			<button type="submit">Transfer</button>
			</form>
<%@ include file="AppLayoutEnd.jsp" %>