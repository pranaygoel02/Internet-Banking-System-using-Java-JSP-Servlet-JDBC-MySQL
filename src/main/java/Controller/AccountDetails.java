package Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Dao;

@WebServlet("/account")
public class AccountDetails extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accountNo = req.getParameter("account_no");
		try {
			ResultSet accDetail = Dao.getAccountDetails(accountNo, "status");
			if(accDetail.next()) {
				req.setAttribute("acc_status", accDetail.getString("status"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = req.getRequestDispatcher("SingleAccount.jsp");
		rd.forward(req, resp);
	}
}
