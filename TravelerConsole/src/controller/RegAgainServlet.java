package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.InvalidDataException;
import functionality.UsersManager;
import models.User;

/**
 * Servlet implementation class RegAgainServlet
 */
@WebServlet("/register")
public class RegAgainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int MINIMUM_PASSWORD_LENGTH = 6; // FOR TEST PURPOSES - Min Password length  
    

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String inputPassword = request.getParameter("userPassword");
		if (!(inputPassword == null) && inputPassword.length() >= MINIMUM_PASSWORD_LENGTH) { // checks if password is correct
			String firstName = request.getParameter("userFirstName");
			String lastName = request.getParameter("userLastName");
			String email = request.getParameter("userEmail");
			String description = request.getParameter("userDescription");
			UsersManager.registerUser(firstName, lastName, email, inputPassword, description);
			System.out.println("User Registration Successful!");
			response.sendRedirect("SuccRegPage.html");
		} else {
			System.out.println("Registration failed! Password is incorrect!");
			response.sendRedirect("RegAgainPage.html"); // redirects to regAgainPage
		}
	}

}
