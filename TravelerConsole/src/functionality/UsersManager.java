package functionality;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.concurrent.ConcurrentHashMap;

import dbModels.UserDao;
import models.Destination;
import models.User;

public class UsersManager {
	
//	public static final String PATH_TO_BASE = "jdbc:mysql://localhost/traveler_db?user=root&password=123456";
	public static final String PATH_TO_LOG = "log.txt";
	
	private static UsersManager instance; // Singleton
	private ConcurrentHashMap<String, User> registerredUsers;
	private UsersManager() {
		registerredUsers=new ConcurrentHashMap<>();
		for (User u: UserDao.getInstance().getAllUsers()) { //adds all users form DB to collection
			registerredUsers.put(u.getEmail(), u);
		}
	}
	
	public static synchronized UsersManager getInstance(){
		if (instance==null) {
			instance=new UsersManager();
		}
		return instance;
	}
	
	public boolean validateUser(String email, String password) { // validation of login input
		if (!registerredUsers.containsKey(email)) { // no such user
			return false;
		}
		return registerredUsers.get(email).getPassword().equals(password); // returns the result of the comparing of the login pass and the pass in the collection
	}
	
	public void registerUser(String firstName, String lastName, String email, String password, String description) {
		User user = new User(firstName, lastName, password, email, description);
		registerredUsers.put(email, user); // adds the new user to the collection
		UserDao.getInstance().saveUserToDB(user); // saves user to DB
	}
	
	public void addVisitedDestination(User user, Destination destination) {
		user.getVisitedPlaces().add(destination);
	}
	
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
	
	public boolean updateUserInfo(String email, String password, String firstName, String lastName, String description) {
		if (!registerredUsers.containsKey(email)) { // no such user
			return false;
		}
		User user=registerredUsers.get(email); // takes the user with the input email and updates their fileds
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setDescription(description);
		UserDao.getInstance().updateUserInDB(email, password, firstName, lastName, description); // updates the DB user:
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