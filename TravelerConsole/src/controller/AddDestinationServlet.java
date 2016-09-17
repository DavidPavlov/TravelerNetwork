package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import exceptions.InvalidCoordinatesException;
import functionality.DestinationsManager;
import models.Location;
import models.User;

@WebServlet("/AddDestinationServlet")
@MultipartConfig
public class AddDestinationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getSession().getAttribute("user") == null) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}

		String name = request.getParameter("name");
		String longitude = request.getParameter("long");
		String lattitude = request.getParameter("lat");
		String description = request.getParameter("description");
		Location location = null;
		try {
			location = new Location(Double.parseDouble(longitude), Double.parseDouble(lattitude));
		} catch (NumberFormatException e) {
			System.out.println("invalid coordinates type");
		} catch (InvalidCoordinatesException e) {
			System.out.println(e.getMessage());
		}
		Part destinationImage = request.getPart("picture");

		if (name != null && description != null && location != null) {
			if (!name.trim().equals("") && !description.trim().equals("")) {
				if (destinationImage != null) {
					InputStream picStream = destinationImage.getInputStream();
					File dir = new File("destinationPhotos");
					if (!dir.exists()) {
						dir.mkdir();
					}
					File destinationPicFile = new File(dir,
							name + "-image." + destinationImage.getContentType().split("/")[1]);
					Files.copy(picStream, destinationPicFile.toPath());

					try {
						DestinationsManager.getInstance().addDestination(
								((User) request.getSession().getAttribute("user")), name, description,
								Double.parseDouble(lattitude), Double.parseDouble(longitude),
								destinationPicFile.getName());
					} catch (NumberFormatException | InvalidCoordinatesException e) {
						System.out.println("Invalid destination Data");
					}
					System.out.println("Destination Registration Successful!");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("AddDestination.jsp").forward(request, response);
				}
			} else {
				request.getRequestDispatcher("AddDestination.jsp").forward(request, response);
			}

		}
	}

}
