package functionality;

import java.util.concurrent.ConcurrentHashMap;

import dbModels.DestinationDAO;
import exceptions.InvalidCoordinatesException;
import models.Destination;
import models.Location;
import models.User;

public class DestinationsManager {

	private static DestinationsManager instance; // Singleton
	private ConcurrentHashMap<String, Destination> allDestinations;

	private DestinationsManager() {
		allDestinations = new ConcurrentHashMap<>();
		for (Destination d : DestinationDAO.getInstance().getAllDestinations()) { //adds all destinations form DB to collection
			allDestinations.put(d.getName(), d);
		}
	}

	public static synchronized DestinationsManager getInstance() {
		if (instance == null) {
			instance = new DestinationsManager();
		}
		return instance;
	}

	public boolean validateDestination(String name) { // validation of login input
		if (!allDestinations.containsKey(name)) { // no such destination
			return false;
		}
		return true;
	}

	public void addDestination(User u, String name, String description, double latitude, double longitude) throws InvalidCoordinatesException {
		Destination destination = new Destination(name, description, new Location(latitude, longitude));
		allDestinations.put(name, destination); // adds the new destination to the collection
		DestinationDAO.getInstance().saveDestinationToDB(destination); // saves destination to DB
	}

	public static boolean addComment(User user, String comment) {
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

	public boolean updateDestinationInfo(String email, String password, String firstName, String lastName,
			String description) {
		if (!allDestinations.containsKey(email)) { // no such user
			return false;
		}
		Destination destination = allDestinations.get(name); // takes the user with the input email and updates their fileds
		destination.setPassword(password);
		destination.setFirstName(firstName);
		destination.setLastName(lastName);
		destination.setDescription(description);
		DestinationDAO.getInstance().updateDestinationInDB(email, password, firstName, lastName, description); // updates the DB user:
		return true;
	}

}
