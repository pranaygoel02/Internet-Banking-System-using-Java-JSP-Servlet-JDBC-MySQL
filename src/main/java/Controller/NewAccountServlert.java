package Controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Connection.Conn;
import Model.Dao;
import Model.GenerateID;
import Util.EmailNotification;
import Util.SMSNotification;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Classes.Branch;


@WebServlet("/new-account")
public class NewAccountServlert extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(session.getAttribute("user_id") != null) {
			try {				
				List<Branch> branchList = Dao.getBranchList("branch_id, branch_name");
				req.setAttribute("branch_list", branchList);
			}
			catch(ClassNotFoundException e){
				e.printStackTrace();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			RequestDispatcher rd = req.getRequestDispatcher("NewAccount.jsp");
			rd.forward(req, resp);
		}
		else {
			resp.sendRedirect("login");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String newAccountId = GenerateID.randomUUID(12);
		String customerId = (String)req.getParameter("customer_id");
		String branch = (String)req.getParameter("branch");
		String account_type = (String)req.getParameter("account_type");		
		System.out.println(account_type + " " + newAccountId + "  " + customerId);
		try {
			ResultSet rs = Dao.getCustomerDetails(customerId, "customer_email,customer_phone");
			String email = null;
			Long phone = null;
			while(rs.next()) {
				email = rs.getString("customer_email");
				phone = rs.getLong("customer_phone");
			}
			PreparedStatement addNewAccount = Conn.getConnectionObj().prepareStatement("insert into account(account_type, account_no, balance, customer_id, status, branch_id) values(?,?,?,?,?,?)");
			addNewAccount.setString(1, account_type);
            addNewAccount.setString(2, newAccountId);
            addNewAccount.setInt(3, 0);
            addNewAccount.setString(4,customerId);
            addNewAccount.setString(5,"PROCESSING");
            addNewAccount.setString(6,branch);
            int insertCount = addNewAccount.executeUpdate();
            HttpSession session = req.getSession();
            if(insertCount > 0) {
            	String msg = String.format("New Account added successfully! Account ID -> " + newAccountId);
            	if(email != null) new EmailNotification().sendMessage(email,"bank@gmail.com", msg);
            	if(phone != null) new SMSNotification().sendMessage(phone + "","9999888844", msg);
            	session.setAttribute("show_success", true);
            }
            else {
            	session.setAttribute("show_success", false);
                System.out.println("Something went wrong");
            }
            resp.sendRedirect("new-account");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
