package Controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Dao;
import Model.Category;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		try {
			String user_name = Dao.getUserName((Category)session.getAttribute("category"),(String)session.getAttribute("user_id"));
			session.setAttribute("user_name", user_name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = req.getRequestDispatcher("Home.jsp");
		rd.forward(req, resp);	
	}
}
