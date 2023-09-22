package Controller;

import java.io.IOException;
import java.sql.SQLException;

import Model.Dao;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/update-account-status")
public class UpdateAccountStatus extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accountNo = (String)req.getParameter("account_no");
		String status = (String) req.getParameter("account_status");
		System.out.println(accountNo + " " + status);
		HttpSession session = req.getSession();
		try {
			boolean updated = Dao.updateAccountStatus(status, accountNo);
			if (updated) {
				session.setAttribute("show_success", true);
			} else {
				session.setAttribute("show_success", false);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resp.sendRedirect("account?account_no=" + accountNo);
	}
}
