package Controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/__logout")
public class LogoutServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String uid = (String) session.getAttribute("user_id");
		System.out.println(uid);		
		session.removeAttribute("user_id");
		session.removeAttribute("category");
		session.invalidate();
		try {
			resp.sendRedirect("login");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
