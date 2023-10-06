package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Classes.Account;
import Classes.Transaction;
import Model.Dao;

@WebServlet("/account-info")
public class CustomerAccountInfoServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String accountNo = req.getParameter("account_no");
		String user_id = (String) session.getAttribute("user_id");
		int page = Integer.parseInt(req.getParameter("page"));
		int pageSize = 10;
		int offset = (page - 1) * pageSize;
		try {
			Account acc = (Account) Dao.getAccountDetails(accountNo, user_id, "*");
			int totalPages = (int) Math.ceil((double) Dao.getTotalTransactionCount(accountNo) / pageSize);
			System.out.println(totalPages);
			List<Transaction> transactions = Dao.getTransactions(accountNo, offset, pageSize);
			req.setAttribute("acc", acc);
			req.setAttribute("totalPages", totalPages);
			req.setAttribute("transactions", transactions);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = req.getRequestDispatcher("CustomerAccountInfo.jsp");
		rd.forward(req, resp);
	}
}
