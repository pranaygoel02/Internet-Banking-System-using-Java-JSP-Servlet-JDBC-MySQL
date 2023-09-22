<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ include file="AppLayout.jsp"%>
<%@ include file="OnlyAdminLock.jsp" %>
<%@ include file="Toast/Toast.jsp" %>
<%
ResultSet rs = (ResultSet) request.getAttribute("emp_details");
String selectedBranch = rs.getString("branch_id");
String selectedRole = rs.getString("emp_role");
System.out.println(selectedBranch);
System.out.println(selectedRole);
%>

<c:set var="selectedRole" value="<%= selectedRole %>" />
<c:set var="selectedBranch" value="<%= selectedBranch %>" />

<h2 class="heading">Employee Details</h2>
<form action="update-employee" method="POST">
	<label for="name"><span>Employee ID</span>
		<input name="emp_id" value="<%= rs.getString("emp_id") %>" readonly="readonly" required="true"/>
	</label>
	<label for="name"><span>Full Name</span>
		<input name="name" value="<%= rs.getString("emp_name") %>" required="true" />
	</label>
<label for="email"><span>Email</span>
		<input name="email" type="email" value="<%= rs.getString("emp_email")%>" required="true" />
	</label>
		<label for="phone"><span>Phone</span>
		<input name="phone" pattern="[0-9]{10}" value="<%= rs.getLong("emp_phone")%>" minlength="10" maxlength="10"
			required="true" />	</label>
	<label><span>Branch</span>
	<select name="branch">
    <c:forEach items="${branch_list}" var="branch">
        <option value="${branch.getId()}" <c:if test="${fn:contains(branch.getId(), selectedBranch) }">selected="selected"</c:if>>
            ${branch.getName()} - ${branch.getId()}
        </option>
    </c:forEach>
</select>
	</label>
	<label><span>Role</span>
	<select name="role">
		<c:forEach items="${roles}" var="role">
			<option value="${role}" <c:if test="${fn:contains(role, selectedRole) }">selected="selected"</c:if> >${role}</option>
		</c:forEach>
	</select>
	</label>
	<label for="city"><span>City</span>
		<input name="city" value="<%= rs.getString("emp_city") %>" required="true"/>
	</label>
<label for="address"><span>Address</span>
		<textarea name="address" required="true"><%= rs.getString("emp_addr")%></textarea>
	</label>
	
<label for="adhaar"><span>Adhaar Number</span>
		<input name="adhaar" maxlength="12" value="<%= rs.getLong("emp_adhaar")%>" required="true" />
	</label>
		
	<label for="pan"><span>Pan Number</span>
		<input name="pan" required="true" value="<%= rs.getString("emp_pan")%>" />
	</label>
<label for="dob"><span>DOB</span>
		<input name="dob" value="<%= rs.getString("emp_dob")%>" type="date" required="true" />
	</label>
	
	
	


	
	<button type="submit">Update</button>
</form>
<%@ include file="AppLayoutEnd.jsp"%>