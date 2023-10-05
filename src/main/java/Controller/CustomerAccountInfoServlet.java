package Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Classes.Account;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Dao;

@WebServlet("/account-info")
public class CustomerAccountInfoServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String accountNo = req.getParameter("account_no");
		String user_id = (String)session.getAttribute("user_id");
		try {
			Account acc= (Account) Dao.getAccountDetails(accountNo, user_id, "*");
			req.setAttribute("acc", acc);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = req.getRequestDispatcher("CustomerAccountInfo.jsp");
		rd.forward(req, resp);
	}
}
