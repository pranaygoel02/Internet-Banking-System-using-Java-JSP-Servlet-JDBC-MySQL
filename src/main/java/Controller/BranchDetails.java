package Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Dao;

@WebServlet("/branch")
public class BranchDetails extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String branchId = req.getParameter("branch_id");
		try {
			ResultSet branchDetails = Dao.getBranchDetails(branchId, "*");
			if(branchDetails.next()) {
				req.setAttribute("branch_details", branchDetails);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = req.getRequestDispatcher("SingleBranch.jsp");
		rd.forward(req, resp);
	}
}
