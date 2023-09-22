<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ include file="AppLayout.jsp"%>
<%@ include file="OnlyAdminLock.jsp" %>
<%@ include file="Toast/Toast.jsp" %>
<h2 class="heading">Add Branch</h2>
<form action="add-branch" method="POST">
	<label for="name"><span>Branch Name</span>
		<input name="name" required="true"/>
	</label>
	<label for="email"><span>Email</span>
		<input name="email" type="email" required="true"/>
	</label>
	<label for="phone"><span>Phone</span>
	<input name="phone" pattern="[0-9]{10}" minlength="10" maxlength="10" required="true"/>
	</label>
	<label for="city"><span>City</span>
		<input name="city" required="true"/>
	</label>
	<label for="address"><span>Address</span>
		<textarea name="address" required="true"></textarea>
	</label>
	<label for="pincode"><span>Pin Code</span>
		<input name="pincode" required="true" />
	</label>
	<label for="min_balance"><span>Min. Balance for Savings A/c</span>
		<input name="min_balance" required="true" />
	</label>
	<button type="submit">Submit</button>
</form>
<%@ include file="AppLayoutEnd.jsp"%>