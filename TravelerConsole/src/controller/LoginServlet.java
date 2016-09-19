package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import functionality.CommentsManager;
import functionality.UsersManager;
import models.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("userEmail");
		String password = request.getParameter("userPassword");
		User user = UsersManager.getInstance().logIn(email, password);
		CommentsManager.getInstance();
		if (user != null) { // the validation is successful
			request.getSession().setAttribute("user", user); // The user is
																// passed to the
																// current
																// session
			System.out.println("Login Successful!");
			response.sendRedirect("index.jsp");
		} else { // No such user name or invalid password
			System.out.println("No such username of invalid password!");
			response.sendRedirect("SignIn.jsp");
		}
	}

}
