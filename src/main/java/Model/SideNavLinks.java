package Model;

import java.util.ArrayList;
import java.util.List;

public class SideNavLinks {

	private static List<LinkGroup> links = new ArrayList<>();

	private SideNavLinks() {

		List<Link> branchLinks = new ArrayList<Link>();
		branchLinks.add(new Link("See All Branches", "branches", 1, 2));
		branchLinks.add(new Link("Add Branch", "add-branch", 1, 1));

		List<Link> employeeLinks = new ArrayList<>();
		employeeLinks.add(new Link("See All Employees", "employees", 1, 1));
		employeeLinks.add(new Link("Add Employee", "add-employee", 1, 1));

		List<Link> customerLinks = new ArrayList<>();
		customerLinks.add(new Link("See All Customers", "customers", 1, 2));
		customerLinks.add(new Link("Add Customer", "add-customer", 1, 2));

		List<Link> accountLinks = new ArrayList<>();
		accountLinks.add(new Link("See Accounts", "accounts", 1, 2));
		accountLinks.add(new Link("New Account", "new-account", 1, 3));

		List<Link> quickLinks = new ArrayList<>();
		quickLinks.add(new Link("Home", "home", 1, 3));
		quickLinks.add(new Link("Transaction", "transaction", 3, 3));

		links.add(new LinkGroup("Quick Links", quickLinks, 3));
		links.add(new LinkGroup("Branch", branchLinks, 2));
		links.add(new LinkGroup("Employee", employeeLinks, 1));
		links.add(new LinkGroup("Customer", customerLinks, 2));
		links.add(new LinkGroup("Account", accountLinks, 3));
	}

	public static List<LinkGroup> getLinks() {
		if (links.isEmpty()) {
			new SideNavLinks();
		}
		return links;
	}
}