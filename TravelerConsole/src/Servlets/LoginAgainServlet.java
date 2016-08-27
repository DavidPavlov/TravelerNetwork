package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import functionality.DaoParser;
import models.User;

/**
 * Servlet implementation class LoginAgainServlet
 */
@WebServlet("/login_")
public class LoginAgainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("userEmail");
		String password = request.getParameter("password");
		User user = DaoParser.logIn(email, password);
		if (user!=null) { // the validation is successful
			request.getSession().setAttribute("user", user); // The user is passed to the current session
			System.out.println("Login Successful!");
			response.sendRedirect("Homepage.html");
		} else { // No such user name or invalid password
			System.out.println("No such username of invalid password!");
			response.sendRedirect("LoginAgainPage.html");
		}
	}

}
