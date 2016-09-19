package controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import functionality.DestinationsManager;
import models.Destination;

/**
 * Servlet implementation class DestinationPictureServlet
 */
@WebServlet("/DestinationPictureServlet")
public class DestinationPictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static void returnDestinationPic(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		if (request.getParameter("destination") == null) {
			return;
		}

		Destination dest = DestinationsManager.getInstance()
				.getDestinationFromCache(request.getParameter("destination"));
		File destinationPic = new File("destinationPhotos", dest.getPicture());
		response.setContentLength((int) destinationPic.length());
		String contentType = "image/"
				+ destinationPic.getName().split("[.]")[destinationPic.getName().split("[.]").length - 1];
		response.setContentType(contentType);
		OutputStream out = response.getOutputStream();
		Files.copy(destinationPic.toPath(), out);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		returnDestinationPic(request, response);

	}

}
