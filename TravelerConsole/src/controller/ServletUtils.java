package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletUtils {

	public static void checkIfLoggedIn(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("user") == null) {
			response.reset();
			request.getRequestDispatcher("signin.html").forward(request, response);
		}
	}
}
