package Controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Dao;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/customers")
public class SeeCustomersServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			ResultSet rs = (ResultSet)Dao.getCustomers();
			req.setAttribute("customer_set", rs);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = req.getRequestDispatcher("SeeCustomers.jsp");
		rd.forward(req, resp);
	}
}
