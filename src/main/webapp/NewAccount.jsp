<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ include file="AppLayout.jsp"%>
<%@ include file="Toast/Toast.jsp" %>
<h2 class="heading">Open a new account</h2>
<form action="new-account" method="POST">
	<label for="customer_id"><span>Customer ID</span>
		<input <%= ((Category)session.getAttribute("category")).toString().equalsIgnoreCase("customer") ? "readonly=true" : "" %> value="<%= (String)session.getAttribute("user_id") %>" name="customer_id" required="true"/>
	</label>
	<label><span>Branch</span>
	<select id="branch_select" name="branch">
		<c:forEach items="${branch_list}" var="branch">
			<option value="${branch.id}|${branch.minBal}">${branch.name} - ${branch.id }</option>
		</c:forEach>
	</select>
	</label>
	<label for="balance"><span>Balance</span>
		<input <%= ((Category)session.getAttribute("category")).toString().equalsIgnoreCase("customer") ? "readonly=true" : "" %> value="0.00" name="balance" required="true"/>
	</label>
<%@ include file="NewAccount.html" %>

<p id="branch-min-balance" data-show="false">*Min. Balance in this branch is Rs. <span id="min-balance-amt"></span></p>

<script>
	const accountSelect = document.getElementById("account-select");
	const branchMinBal =  document.getElementById("branch-min-balance")
	const balText = document.getElementById("min-balance-amt")
	
	function setAccount(value) {
		branchMinBal.setAttribute("data-show", value == "SAVINGS" ? "true" : "false");
	}
	
	if(accountSelect) {
		setAccount(accountSelect.value)
		accountSelect.addEventListener("change", (e) => setAccount(e.target.value));
	}
	
	
	function setMinBalance(value) {
		const branchInfo = value.split("|");
		const minBalance = branchInfo[1];
		branchMinBal.setAttribute("data-show", accountSelect.value === "SAVINGS" ? "true" : "false");
		balText.innerText = minBalance;
	}
	function setBranch(e) {
		setMinBalance(e.target.value);
	}
	const branchSelect = document.getElementById("branch_select");
	if(branchSelect) {
		setMinBalance(branchSelect.value);
		branchSelect.addEventListener("change", setBranch);
	}
	
</script>

	<button type="submit">Submit</button>
</form>
<%@ include file="AppLayoutEnd.jsp"%>