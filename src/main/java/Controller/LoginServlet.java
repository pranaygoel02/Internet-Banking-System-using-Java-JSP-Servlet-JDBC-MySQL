package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Connection.Conn;
import Model.Authentication;
import Model.Category;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String uid = (String) session.getAttribute("user_id");
		System.out.println(uid);
		System.out.println(session.getAttribute("show_success"));
		if (uid != null) {
			resp.sendRedirect("home");
		} else {
			RequestDispatcher rd = req.getRequestDispatcher("Login.jsp");
			rd.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Connection conn = Conn.getConnectionObj();
			String uid = req.getParameter("uid");
			String password = req.getParameter("password");
			String category = req.getParameter("category");
			System.out.println(uid + " " + password);
			Category type = null;
			switch (category) {
			case "Admin":
				type = Category.ADMIN;
				break;
			case "Employee":
				type = Category.EMPLOYEE;
				break;
			case "Customer":
				type = Category.CUSTOMER;
				break;
			}
			boolean loggedin = Authentication.login(type, uid, password);
			System.out.println(loggedin);
			if (loggedin) {
				HttpSession session = req.getSession();
				session.setAttribute("user_id", uid);
				session.setAttribute("category", type);
				if (session.getAttribute("invalid_creds") != null)
					session.removeAttribute("invalid_creds");
				try {
					resp.sendRedirect("home");
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				HttpSession session = req.getSession();
				session.setAttribute("invalid_creds", true);
				resp.sendRedirect("login");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
