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

@WebServlet("/update-employee")
public class UpdateEmployeeServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String empId = req.getParameter("emp_id");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		String city = req.getParameter("city");
		String branch = req.getParameter("branch");
		String role = req.getParameter("role");
		long phone = Long.parseLong(req.getParameter("phone"));
		long adhaar = Long.parseLong(req.getParameter("adhaar"));
		String pan = req.getParameter("pan");		
		String dob = req.getParameter("dob");
		try {
			HttpSession session = req.getSession();
			boolean updated = Dao.updateEmployeeDetails(name, email, address, phone, adhaar, pan, dob, city, branch, role, empId);
            if(updated) {
//            	String msg = String.format("Employee added successfully! Emp ID -> " + newEmpId + ", Emp Pin -> " + pin );
//            	new EmailNotification().sendMessage(email,"bank@gmail.com", msg);
//            	new SMSNotification().sendMessage(phone + "","9999888844", msg);
            	session.setAttribute("show_success", true);
            }
            else {
            	session.setAttribute("show_success", false);
                System.out.println("Something went wrong");
            }
            resp.sendRedirect("employee?emp_id=" + empId);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
