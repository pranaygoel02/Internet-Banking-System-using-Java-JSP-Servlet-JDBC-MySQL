package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/register")
public class RegisterNewCustomerServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String uid = (String) session.getAttribute("user_id");
		System.out.println(uid);
		if (uid != null) {
			resp.sendRedirect("home");
		} else {
			RequestDispatcher rd = req.getRequestDispatcher("RegisterNewCustomer.jsp");
			rd.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RegisterCustomer.register(req, resp, "login");
	}
}
