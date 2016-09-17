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
	private ConcurrentHashMap<String, String> allDestinationsAndAuthors;

	private DestinationsManager() {
		allDestinationsAndAuthors = DestinationDAO.getInstance().getAllDestinationsAndAuthors(); // adds
																									// destinations
																									// and
																									// authors
																									// to
																									// collection
		allDestinations = new ConcurrentHashMap<>();
		for (Destination d : DestinationDAO.getInstance().getAllDestinations()) { // adds
																					// all
																					// destinations
																					// form
																					// DB
																					// to
																					// collection
			allDestinations.put(d.getName(), d);
		}
	}

	public static synchronized DestinationsManager getInstance() {
		if (instance == null) {
			instance = new DestinationsManager();
		}
		return instance;
	}

	public boolean validateDestination(String name) { // validation of input
		if (!allDestinations.containsKey(name)) { // no such destination
			return false;
		}
		return true;
	}

	public boolean addDestination(User user, String name, String description, double lattitude, double longitude,
			String picture) throws InvalidCoordinatesException {
		if (UsersManager.getInstance().validateUser(user.getEmail(), user.getPassword())) { // if
			// the
			// user
			// exists
			// in
			// the
			// collection
			Destination destination = new Destination(name, description, new Location(lattitude, longitude), picture,
					user);
			allDestinations.put(name, destination); // adds the new destination
													// to the collection
			DestinationDAO.getInstance().saveDestinationToDB(user, destination); // saves
																					// destination
																					// to
																					// DB
			return true;
		}
		return false; // no such user
	}

	public Destination getDestinationFromCache(String destinationName) {
		if (!allDestinations.containsKey(destinationName)) {
			return null; // no such destination
		}
		return allDestinations.get(destinationName); // returns the destination
	}

	public boolean updateDestinationInfo(String name, String description, double longitude, double lattitude,
			String picture) throws InvalidCoordinatesException {
		if (!allDestinations.containsKey(name)) { // no such destination
			return false;
		}
		Destination destination = allDestinations.get(name); // takes the
																// destination
																// and updates
																// its fields
		destination.setDescription(description);
		destination.setLocation(new Location(lattitude, longitude));
		DestinationDAO.getInstance().updateDestinationInDB(name, description, longitude, lattitude, picture); // updates
																												// the
																												// DB
																												// user:
		return true;
	}

	public String getDestinationAuthor(String destinationName) {
		return allDestinationsAndAuthors.get(destinationName);
	}

}
