<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="Model.Category, Model.SideNavLinks, Model.Link, Model.LinkGroup"%>
<!DOCTYPE html>
<nav data-open="close" id="navbar">
	<svg id="nav-menu-close-btn" style="position:absolute;right:16; z-index:100; top:20; transform: translateX(-50%)" xmlns="http://www.w3.org/2000/svg" width="32" height="32"
		fill="currentColor" class="bi bi-x-lg" viewBox="0 0 16 16">
  <path
			d="M2.146 2.854a.5.5 0 1 1 .708-.708L8 7.293l5.146-5.147a.5.5 0 0 1 .708.708L8.707 8l5.147 5.146a.5.5 0 0 1-.708.708L8 8.707l-5.146 5.147a.5.5 0 0 1-.708-.708L7.293 8 2.146 2.854Z" />
</svg>
	<ul>
		<%
		Category type = (Category) session.getAttribute("category");
		if (type != null) {
			List<LinkGroup> links = SideNavLinks.getLinks();
			for (LinkGroup linkGroup : links) {
				if (type.getLevel() <= linkGroup.blockingLevel) {
			out.println("<li><p class='group-link-label'>" + linkGroup.label + "</p>");
			for (Link link : linkGroup.links) {
				if (type.getLevel() >= link.min_blockingLevel && type.getLevel() <= link.max_blockingLevel)
					out.println("<a class='nav-link' href='" + link.link + "'>" + link.label + "</a>");
			}
			out.println("</li>");
				}
			}
		}
		%>
		<li><%@include file="Logout.jsp"%></li>
	</ul>
</nav>