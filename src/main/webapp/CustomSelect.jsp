<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script></script>
<style>
.flex-col {
	flex-direction: column;
}

.flex-row {
	flex-direction: row;
}

.flex {
	display: flex;
	gap: 0.5rem;
}

.select-option-wrapper {
	display: grid;
	grid-template-rows: 0fr;
	transition: grid-template-rows 0.5s ease-in-out;
	-webkit-user-select: none;
	user-select: none;
}

.select-option-wrapper>div {
	overflow: hidden;
}

.select-option-wrapper[data-open="close"] {
	grid-template-rows: 0fr;
}

.select-option-wrapper[data-open="open"] {
	grid-template-rows: 1fr;
	max-height: 400px;
	overflow-y: auto;
}

input[type="radio"] {
	display: none;
}

input[type="radio"]:checked+label, input[type="radio"]+label:hover {
	background: blue;
	color: white;
}
</style>
</head>
<body>
	<form method="POST" action="test">
		<div id="branch-select" class="select-wrapper">
			<p class="flex flex-row" class="select-ans-wrapper">
				<span class="select-ans">Select</span>
				<button class="select-toggle">Open</button>
			</p>
			<div data-open="close" class="select-option-wrapper">
				<div class="select-options flex flex-col">
					<%
					ResultSet branch_set = (ResultSet) request.getAttribute("branch_options");
					while (branch_set.next()) {
						out.println(String.format("<input id=%s type='radio' name='branch-ans' value=%s />",
						branch_set.getString("branch_id"), branch_set.getString("branch_id")));
						out.println(String.format("<label for=%s >%s</label>", branch_set.getString("branch_id"),
						branch_set.getString("branch_id") + "-" + branch_set.getString("branch_name")));
					}
					%>
				</div>
			</div>
		</div>
		<div id="customer-select" class="select-wrapper">
			<p class="flex flex-row" class="select-ans-wrapper">
				<span class="select-ans">Select</span>
				<button class="select-toggle">Open</button>
			</p>
			<div data-open="close" class="select-option-wrapper">
				<div class="select-options flex flex-col">
					<%
					ResultSet account_set = (ResultSet) request.getAttribute("customer_options");
					while (account_set.next()) {
						out.println(String.format("<input id=%s type='radio' name='customer-ans' value=%s />",
						account_set.getString("customer_id"), account_set.getString("customer_name")));
						out.println(String.format("<label for=%s >%s</label>", account_set.getString("customer_id"),
						account_set.getString("customer_name")));
					}
					%>
				</div>
			</div>
		</div>
		<script>
		
		document.querySelectorAll(".select-wrapper .select-toggle").forEach((ele) => {
			ele.addEventListener("click", (e) => {
				e?.preventDefault();
				const selectToggleBtn = e.target;
				const selectDiv = ele.parentElement.nextElementSibling
				const open = selectDiv.dataset.open == "open"
				console.log(selectToggleBtn,selectDiv, open)
					if(open) {
						selectDiv.setAttribute("data-open","close");
						selectToggleBtn.innerText="Open"
					}
					else {
						selectDiv.setAttribute("data-open","open");
						selectToggleBtn.innerText="Close"
					}
				const selectAns = ele.parentElement.querySelector("span")
				console.log(selectAns)
				selectDiv.addEventListener("click", (e) => {
					if(e.target.tagName === "INPUT") {
						console.log("value ",e.target.value);
						selectAns.innerText = e.target.value
					}
				})
			})
		})
		
		</script>
		<button type="submit">Submit</button>
	</form>
</body>
</html>