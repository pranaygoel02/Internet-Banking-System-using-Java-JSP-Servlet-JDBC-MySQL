package Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import Model.Dao;

@WebServlet("/update-branch")
public class UpdateBranchServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String branchId = req.getParameter("branch_id"); 
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		long phone = Long.parseLong(req.getParameter("phone"));
		int pincode = Integer.parseInt(req.getParameter("pincode"));
		String city = req.getParameter("city");
		Double min_balance = Double.parseDouble(req.getParameter("min_balance"));
		try {
			
            HttpSession session = req.getSession();
            boolean updated = Dao.updateBranchDetails(name, email, phone, city, address, pincode, min_balance, branchId);
            if(updated) {
//            	String msg = String.format("Branch added successfully! Branch ID -> " + newBranchId);
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
