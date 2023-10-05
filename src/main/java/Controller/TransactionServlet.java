package Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Classes.Account;
import Connection.Conn;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Model.Category;
import Model.Dao;
import Model.GenerateID;

@WebServlet("/transaction")
public class TransactionServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(session.getAttribute("user_id") != null) {
			try {
				String user_id = (String)session.getAttribute("user_id");
				List<Account> accountList = Dao.getUserAccounts(user_id, "account_type,account_no,status,balance", "SAVINGS", "ACTIVE");
				req.setAttribute("account_list", accountList);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		RequestDispatcher rd = req.getRequestDispatcher("Transaction.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String user_id = (String)session.getAttribute("user_id"); 
		if(user_id != null) {
			try {				
				String newTransactionId = GenerateID.randomUUID(12);
				String from_ac = ((String)req.getParameter("from-ac")).split("[-]")[0].trim();
				String to_ac = (String)req.getParameter("to-ac");
				Double amt = Double.parseDouble(req.getParameter("amt"));
				System.out.println(newTransactionId + " " + user_id + " " + from_ac + " " + to_ac + " " + amt);
				PreparedStatement addNewTransaction = Conn.getConnectionObj().prepareStatement("insert into transaction(transaction_id,account_no_from, account_no_to,amount) values(?,?,?,?)");
				addNewTransaction.setString(1, newTransactionId);
	            addNewTransaction.setString(2, from_ac);
	            addNewTransaction.setString(3, to_ac);
	            addNewTransaction.setDouble(4,amt);
	            int insertCount = addNewTransaction.executeUpdate();
	            if(insertCount > 0) {
	            	
	            	//TODO: handle transaction credit and debit amount
	            	
	            	String msg = String.format("Transaction made successfully! Transaction ID -> " + newTransactionId);
//	            	new EmailNotification().sendMessage(email,"bank@gmail.com", msg);
//	            	new SMSNotification().sendMessage(phone + "","9999888844", msg);
	            	session.setAttribute("show_success", true);
	            }
	            else {
	            	session.setAttribute("show_success", false);
	                System.out.println("Something went wrong");
	            }
			}
			catch(ClassNotFoundException e) {
				e.printStackTrace();;
			}
			catch(SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				session.setAttribute("show_success", false);
				session.setAttribute("toast_msg", e.getMessage());
			}
		}
		resp.sendRedirect("transaction");	
	}
}
