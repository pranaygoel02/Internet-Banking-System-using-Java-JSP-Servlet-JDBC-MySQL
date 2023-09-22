<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ include file="AppLayout.jsp"%>
<%@ include file="OnlyAdminLock.jsp" %>
<%@ include file="Toast/Toast.jsp" %>
<h2 class="heading">Add Employee</h2>
<form action="add-employee" method="POST">
	<label for="name"><span>Full Name</span>
		<input name="name" required="true"/>
	</label>
	<label for="email"><span>Email</span>
		<input name="email" type="email" required="true"/>
	</label>
	<label for="phone"><span>Phone</span>
	<input name="phone" pattern="[0-9]{10}" minlength="10" maxlength="10" required="true"/>
	</label>
	<label><span>Branch</span>
	<select name="branch">
		<c:forEach items="${branch_list}" var="branch">
			<option value="${branch.id}">${branch.name} - ${branch.id }</option>
		</c:forEach>
	</select>
	</label>
	<label><span>Role</span>
	<select name="role">
		<c:forEach items="${roles}" var="role">
			<option value="${role}">${role}</option>
		</c:forEach>
	</select>
	</label>
	<label for="city"><span>City</span>
		<input name="city" required="true"/>
	</label>
	<label for="address"><span>Address</span>
		<textarea name="address" required="true"></textarea>
	</label>
	
	<label for="adhaar"><span>Adhaar Number</span>
		<input name="adhaar" maxlength="12" required="true"/>
	</label>
	<label for="pan"><span>Pan Number</span>
		<input name="pan" required="true"/>
	</label>
	<label for="dob"><span>DOB</span>
		<input name="dob" type="date" required="true"/>
	</label>
	<button type="submit">Submit</button>
</form>
<%@ include file="AppLayoutEnd.jsp"%>