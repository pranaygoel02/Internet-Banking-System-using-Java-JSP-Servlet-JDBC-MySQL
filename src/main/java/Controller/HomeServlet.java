package Controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Model.Dao;
import Model.Category;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Classes.Account;
import Classes.Branch;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(session.getAttribute("user_id") != null) {
			try {
				Category c = (Category)session.getAttribute("category");
				String user_id = (String)session.getAttribute("user_id");
				String user_name = Dao.getUserName(c,user_id);
				session.setAttribute("user_name", user_name);
				if(c.toString().equalsIgnoreCase("customer")) {
					List<Account> accountList = Dao.getUserAccounts(user_id, "branch_name,branch.branch_id,account_type,account_no,status,balance,account.doc");
					req.setAttribute("account_list", accountList);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			RequestDispatcher rd = req.getRequestDispatcher("Home.jsp");
			rd.forward(req, resp);	
		}
		else {
			resp.sendRedirect("login");
		}
	}
}
