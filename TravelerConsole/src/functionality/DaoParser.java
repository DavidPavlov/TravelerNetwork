package functionality;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import dao.UserDao;
import exceptions.InvalidDataException;
import models.User;

public class DaoParser {

	public static final String PATH_TO_BASE = "jdbc:mysql://localhost/traveler_db?user=root&password=123456";
	public static final String PATH_TO_LOG = "log.txt";

	public static boolean addComment(User user, String comment) {
		return true;
		// TODO
	}

	public static boolean addDestination(User user, String name, String description, double longtitude,
			double lattitude) {
		return true;
		// TODO
	}

	public static boolean addHotel(User user, String name, double longtitude, double lattitude, String contact) {
		return true;
		// TODO
	}

	public static boolean addResturant(User user, String name, double longtitude, double lattitude,
			String workingHours) {
		return true;
		// TODO
	}

	public static User logIn(String email, String password) {
		UserDao userDao = new UserDao();
		userDao.connectToDB();
		if (userDao.validateUser(email, password)) {
			User user = userDao.getUser(email);
			return user;
		}
		return null;

	}

	public static boolean logOut() {
		return true;
		// TODO
	}

	public static boolean registerUser(String firstName, String lastName, String email, String password,
			String description) {
		try {
			User user = new User(firstName, lastName, password, email, description);
			UserDao dao = new UserDao();
			dao.connectToDB();
			boolean inserted = dao.insertUser(user);
			if (inserted) {
				printToLog("User with email: " + user.getEmail() + " registered succesfully");
			} else {
				printToLog("User cannot be registered");
			}

		} catch (InvalidDataException e) {
			printToLog("User data is invalid !");
		}

		return true;

	}

	private static void printToLog(String message) {

		File file = new File(PATH_TO_LOG);
		FileOutputStream out;
		try {
			out = new FileOutputStream(file, true);
			PrintStream logPrint = new PrintStream(out);
			logPrint.println(message);
			logPrint.close();
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

	}

}
