<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="Model.Category, Model.SideNavLinks, Model.Link, Model.LinkGroup"%>
<!DOCTYPE html>
<nav>
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
					out.println("<a href='" + link.link + "'>" + link.label + "</a>");
			}
			out.println("</li>");
				}
			}
		}
		%>
	</ul>
</nav>