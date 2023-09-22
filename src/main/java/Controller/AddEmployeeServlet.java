package Controller;

import java.io.IOException;
import java.sql.PreparedStatement;
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

@WebServlet("/add-employee")
public class AddEmployeeServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			List<Branch> branchList = Dao.getBranchList("branch_id, branch_name");
			List<String> roles = Dao.getRoles();
			req.setAttribute("branch_list", branchList);
			req.setAttribute("roles", roles);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		RequestDispatcher rd = req.getRequestDispatcher("AddEmployee.jsp");
		rd.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String newEmpId = GenerateID.randomUUID(12);
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
		String pin = GenerateID.randomUUID(12);
		try {
			PreparedStatement addNewEmployee = Conn.getConnectionObj().prepareStatement("insert into employee values(?,?,?,?,?,?,?,?,?,?,?,?)");
			addNewEmployee.setString(1, newEmpId);
            addNewEmployee.setString(2, name);
            addNewEmployee.setString(3, email);
            addNewEmployee.setString(4,address);
            addNewEmployee.setString(5,city);
            addNewEmployee.setLong(6,phone);
            addNewEmployee.setLong(7,adhaar);
            addNewEmployee.setString(8, pan);
            addNewEmployee.setString(9, dob);
            addNewEmployee.setString(10,pin);
            addNewEmployee.setString(11,branch);
            addNewEmployee.setString(12,role);
            int insertCount = addNewEmployee.executeUpdate();
            HttpSession session = req.getSession();
            if(insertCount > 0) {
//            	String msg = String.format("Employee added successfully! Emp ID -> " + newEmpId + ", Emp Pin -> " + pin );
//            	new EmailNotification().sendMessage(email,"bank@gmail.com", msg);
//            	new SMSNotification().sendMessage(phone + "","9999888844", msg);
            	session.setAttribute("show_success", true);
            }
            else {
            	session.setAttribute("show_success", false);
                System.out.println("Something went wrong");
            }
            resp.sendRedirect("add-employee");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
