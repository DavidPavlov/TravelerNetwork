package functionality;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import dbModels.UserDao;
import exceptions.InvalidAuthorException;
import exceptions.InvalidCoordinatesException;
import exceptions.InvalidDataException;
import models.Comment;
import models.Destination;
import models.User;

public class UsersManager {

	// public static final String PATH_TO_BASE =
	// "jdbc:mysql://localhost/traveler_db?user=root&password=123456";
	public static final String PATH_TO_LOG = "log.txt";

	private static UsersManager instance; // Singleton
	private ConcurrentHashMap<String, User> registerredUsers; // user email and
																// user

	private UsersManager() {
		registerredUsers = new ConcurrentHashMap<>();
		Set<User> tempAllUsers = UserDao.getInstance().getAllUsers();
		for (User u : tempAllUsers) { // adds all users
										// from DB to
										// collection
			registerredUsers.put(u.getEmail(), u); // add user to cache
		}
	}

	public static synchronized UsersManager getInstance() {
		if (instance == null) {
			instance = new UsersManager();
		}
		return instance;
	}

	public boolean validateUser(String email, String password) { // validation
																	// of login
																	// input
		if (!registerredUsers.containsKey(email)) { // no such user
			return false;
		}
		return registerredUsers.get(email).getPassword().equals(password); // returns
																			// the
																			// result
																			// of
																			// the
																			// comparing
																			// of
																			// the
																			// login
																			// pass
																			// and
																			// the
																			// pass
																			// in
																			// the
																			// collection
	}

	public void registerUser(String firstName, String lastName, String email, String password, String description,
			String profilePic) {
		User user = new User(firstName, lastName, password, email, description, profilePic);
		registerredUsers.put(email, user); // adds the new user to the
											// collection
		UserDao.getInstance().saveUserToDB(user); // saves user to DB
	}

	public void addDestination(User user, String destinationName) {
		user.getAddedPlaces().add(destinationName);
	}

	public boolean addComment(String userEmail, String destinationName, String text) {
		if (!registerredUsers.containsKey(userEmail)) {
			return false;
		}
		try {
			Comment comment = new Comment(userEmail, destinationName, text, 0);
			CommentsManager.getInstance().saveComment(userEmail, destinationName, text);
			DestinationsManager.getInstance().getDestinationFromCache(destinationName).addComment(comment); // adds
																											// the
																											// comment
																											// to
																											// the
																											// destination

		} catch (InvalidDataException | InvalidAuthorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public boolean addDestination(User user, Destination destination) throws InvalidCoordinatesException {
		String destName = destination.getName();
		String destDescription = destination.getDescription();
		double destLattitude = destination.getLocation().getLattitude();
		double destLongitude = destination.getLocation().getLongitude();
		String destPicture = destination.getPicture();
		if (DestinationsManager.getInstance().addDestination(user, destName, destDescription, destLattitude,
				destLongitude, destPicture)) {
			addDestination(user, destName);
			return true;
		} else {
			return false;
		}
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

	public boolean updateUserInfo(String email, String password, String firstName, String lastName, String description,
			String profilePic) {
		if (!registerredUsers.containsKey(email)) { // no such user
			return false;
		}
		User user = registerredUsers.get(email); // takes the user with the
													// input email and updates
													// their fields
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setDescription(description);
		user.setProfilePic(profilePic);
		UserDao.getInstance().updateUserInDB(email, password, firstName, lastName, description, profilePic); // updates
		// the
		// DB
		// user:
		return true;
	}

	public User logIn(String email, String password) {
		if (validateUser(email, password)) { // valid input
			User user = registerredUsers.get(email);
			return user;
		}
		return null;
	}

	public User getUserFromCache(String userEmail) {
		if (!registerredUsers.containsKey(userEmail)) {
			return null; // no such user
		}
		return registerredUsers.get(userEmail); // returns the user
	}

	public ConcurrentHashMap<String, User> getRegisterredUsers() {
		ConcurrentHashMap<String, User> copy = new ConcurrentHashMap<>();
		copy.putAll(registerredUsers);
		return copy;
	}

	public boolean addUserToComment(String userEmail, Comment comment) {
		if (registerredUsers.containsKey(userEmail)) {
			comment.setAuthor(userEmail);
			return true;
		}
		return false;
	}

	public void likeAComment(String userEmail, Comment comment) {
		ArrayList<String> userLikersOfComment = comment.getUserLikers(); // all
																			// the
																			// users
																			// who
																			// like
																			// the
																			// comment
		for (int i = 0; i < userLikersOfComment.size(); i++) {
			if (userLikersOfComment.get(i) == userEmail) { // if the current
															// user
															// has already liked
															// the comment
				return; // do nothing
			}
		}
		comment.like(userEmail); // the comment is liked

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