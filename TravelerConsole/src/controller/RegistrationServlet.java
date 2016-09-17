package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import functionality.UsersManager;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String NAME_PATTERN = "^[A-Za-z]+$";
	private static final int MINIMUM_PASSWORD_LENGTH = 6;
	private static final String EMAIL_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9]+.[a-z.]+$";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String inputPassword = request.getParameter("userPassword");

		String firstName = request.getParameter("userFirstName");
		String lastName = request.getParameter("userLastName");
		String email = request.getParameter("userEmailAddress");
		String description = request.getParameter("userDescription");

		if (validateData(firstName, lastName, email, inputPassword)) {
			UsersManager.getInstance().registerUser(firstName, lastName, email, inputPassword, description);
			System.out.println("User Registration Successful!");
			response.sendRedirect("index.jsp");
		} else {
			System.out.println("Registration failed! Password is incorrect!");
			RequestDispatcher view = request.getRequestDispatcher("signup.html");
			view.forward(request, response);
		}
	}

	private static boolean validateData(String firstName, String lastName, String email, String password) {
		if ((firstName != null && lastName != null && email != null && password != null)) {
			return firstName.matches(NAME_PATTERN) && lastName.matches(NAME_PATTERN) && email.matches(EMAIL_PATTERN)
					&& password.length() >= MINIMUM_PASSWORD_LENGTH;
		}
		return false;

	}

}
