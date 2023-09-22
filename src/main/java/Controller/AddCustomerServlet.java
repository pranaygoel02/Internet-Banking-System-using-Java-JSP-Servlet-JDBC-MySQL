package Controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Connection.Conn;
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

@WebServlet("/add-customer")
public class AddCustomerServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("AddCustomer.jsp");
		rd.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String newCustomerId = GenerateID.randomUUID(12);
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		long phone = Long.parseLong(req.getParameter("phone"));
		long adhaar = Long.parseLong(req.getParameter("adhaar"));
		String pan = req.getParameter("pan");		
		String dob = req.getParameter("dob");
		String pin = GenerateID.randomUUID(12);
		try {
			PreparedStatement addNewCustomer = Conn.getConnectionObj().prepareStatement("insert into customer values(?,?,?,?,?,?,?,?,?)");
			addNewCustomer.setString(1, newCustomerId);
            addNewCustomer.setString(2, name);
            addNewCustomer.setString(3, email);
            addNewCustomer.setString(4,address);
            addNewCustomer.setLong(5,phone);
            addNewCustomer.setLong(6,adhaar);
            addNewCustomer.setString(7, pan);
            addNewCustomer.setString(8, dob);
            addNewCustomer.setString(9,pin);
            int insertCount = addNewCustomer.executeUpdate();
            HttpSession session = req.getSession();
            if(insertCount > 0) {
            	String msg = String.format("Customer added successfully! Customer ID -> " + newCustomerId + ", Customer Pin -> " + pin );
            	new EmailNotification().sendMessage(email,"bank@gmail.com", msg);
            	new SMSNotification().sendMessage(phone + "","9999888844", msg);
            	session.setAttribute("show_success", true);
            }
            else {
            	session.setAttribute("show_success", false);
                System.out.println("Something went wrong");
            }
            resp.sendRedirect("add-customer");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
