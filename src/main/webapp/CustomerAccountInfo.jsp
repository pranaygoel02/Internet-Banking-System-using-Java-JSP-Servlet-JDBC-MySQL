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
	<c:choose>
	<c:when test="${transactions.size() > 0 }">
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
	<div class="pagination">
		<div>
			<p>Page ${param.page} of ${totalPages}</p>
		</div>
		<div>
			<c:if test="${param.page > 1}">
				<a class="page-number" title="Previous Page"
					href="?account_no=${param.account_no}&page=${param.page - 1}"><</a>
			</c:if>
			<c:set var="endValue">
				<c:choose>
					<c:when test="${totalPages < param.page + 3 }">${totalPages}</c:when>
					<c:otherwise>${param.page + 3}</c:otherwise>
				</c:choose>
			</c:set>
			<c:set var="startValue">
				<c:choose>
					<c:when test="${param.page <= 3 }">${1}</c:when>
					<c:otherwise>${param.page}</c:otherwise>
				</c:choose>
			</c:set>


			<c:forEach begin="${startValue}" end="${endValue}" var="pageNumber">
				<c:choose>
					<c:when test="${param.page == pageNumber}">
						<span class="page-number current-page">${pageNumber}</span>
					</c:when>
					<c:otherwise>
						<a class="page-number" 
							href="?account_no=${param.account_no}&page=${pageNumber}">${pageNumber}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<c:if test="${param.page < totalPages}">
				<a class="page-number" title="Next Page"
					href="?account_no=${param.account_no}&page=${param.page + 1}">></a>
			</c:if>
		</div>
	</div>
	</c:when>
	<c:otherwise>No transactions to show.</c:otherwise>
	</c:choose>

</section>
<%@ include file="AppLayoutEnd.jsp"%>