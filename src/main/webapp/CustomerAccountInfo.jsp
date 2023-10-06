<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ include file="AppLayout.jsp"%>
<%@ include file="Toast/Toast.jsp"%>
<h2 class="heading">Account Info</h2>
<div class="ac-card ac-info-card">
	<div>
	<p>Account Number: ${acc.no}</p>
	<p>Date of Opening: ${acc.doc}</p>
	<p>Account Type: ${acc.type}</p>
	</div>
	<div>
	<p>Available Balance</p>
	<h2 class="ac-balance">&#8377;${acc.balance}</h2>
	<span data-status="${acc.status}">${acc.status}</span>
	</div>
</div>
<section id="transactions">
	<h2>Transactions</h2>
	<div class="fix-width">
		<table>
			<thead>
				<tr>
					<th>Transaction ID</th>
					<th>Type</th>
					<th>Account Number</th>
					<th>Account Holder Name</th>
					<th>Amount (&#8377;)</th>
					<th>Date-Time</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${transactions}" var="t">
					<tr>
						<td>${t.id}</td>
						<td>${t.type}</td>
						<td>${t.acc}</td>
						<td>${t.name}</td>
						<td data-type="${t.type}">${t.amount}</td>
						<td>${t.doc}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</section>
<%@ include file="AppLayoutEnd.jsp"%>