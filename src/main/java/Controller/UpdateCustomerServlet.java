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
import Model.GenerateID;

@WebServlet("/update-customer")
public class UpdateCustomerServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String customerId = req.getParameter("customer_id");
		System.out.println(customerId);
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		long phone = Long.parseLong(req.getParameter("phone"));
		long adhaar = Long.parseLong(req.getParameter("adhaar"));
		String pan = req.getParameter("pan");		
		String dob = req.getParameter("dob");
		String pin = GenerateID.randomUUID(12);
		try {
			HttpSession session = req.getSession();
			boolean updated = Dao.updateCustomerDetails(name, email, address, phone, adhaar, pan, dob, customerId);
            if(updated) {
//            	String msg = String.format("Customer added successfully! Customer ID -> " + newCustomerId + ", Customer Pin -> " + pin );
//            	new EmailNotification().sendMessage(email,"bank@gmail.com", msg);
//            	new SMSNotification().sendMessage(phone + "","9999888844", msg);
            	session.setAttribute("show_success", true);
            }
            else {
            	session.setAttribute("show_success", false);
                System.out.println("Something went wrong");
            }
            resp.sendRedirect("customer?customer_id=" + customerId);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
