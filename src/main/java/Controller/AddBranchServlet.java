package Controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Connection.Conn;
import Model.GenerateID;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/add-branch")
public class AddBranchServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("AddBranch.jsp");
		rd.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String newBranchId = GenerateID.randomUUID(12);
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		long phone = Long.parseLong(req.getParameter("phone"));
		int pincode = Integer.parseInt(req.getParameter("pincode"));
		String city = req.getParameter("city");
		Double min_balance = Double.parseDouble(req.getParameter("min_balance"));
		try {
			PreparedStatement addNewBranch = Conn.getConnectionObj().prepareStatement("insert into branch(branch_id,branch_name,branch_email,branch_phone,branch_city,branch_addr,branch_pincode,min_balance) values(?,?,?,?,?,?,?,?)");
			addNewBranch.setString(1, newBranchId);
            addNewBranch.setString(2, name);
            addNewBranch.setString(3, email);
            addNewBranch.setLong(4,phone);
            addNewBranch.setString(5,city);
            addNewBranch.setString(6,address);
            addNewBranch.setDouble(7,pincode);
            addNewBranch.setDouble(8,min_balance);
            int insertCount = addNewBranch.executeUpdate();
            HttpSession session = req.getSession();
            if(insertCount > 0) {
            	String msg = String.format("Branch added successfully! Branch ID -> " + newBranchId);
//            	new EmailNotification().sendMessage(email,"bank@gmail.com", msg);
//            	new SMSNotification().sendMessage(phone + "","9999888844", msg);
            	session.setAttribute("show_success", true);
            }
            else {
            	session.setAttribute("show_success", false);
                System.out.println("Something went wrong");
            }
            resp.sendRedirect("add-branch");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
