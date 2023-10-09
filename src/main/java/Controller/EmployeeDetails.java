package Controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Classes.Branch;
import Model.Dao;

@WebServlet("/employee")
public class EmployeeDetails extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String empId = req.getParameter("emp_id");
		try {
			ResultSet empDetail = Dao.getEmployeeDetails(empId, "*");
			List<Branch> branchList = Dao.getBranchList("branch_id, branch_name,min_balance");
			req.setAttribute("branch_list", branchList);
			if (empDetail.next()) {
				req.setAttribute("emp_details", empDetail);
			}
			List<String> roles = Dao.getRoles();
			req.setAttribute("roles", roles);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = req.getRequestDispatcher("SingleEmployee.jsp");
		rd.forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

}
