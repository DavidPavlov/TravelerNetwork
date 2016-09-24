package functionality;

import java.util.Map;

import models.Comment;
import models.Destination;
import models.User;

public class ProjectManager {

	private static ProjectManager instance = new ProjectManager(); // Singleton

	private ProjectManager() {
		// Loading all Managers and the cache
		// UsersManager
		UsersManager.getInstance(); // no destination names in each user
		// DestinationsManager
		DestinationsManager.getInstance(); // no comment objects in each //
											// destination
		// CommentsManager
		CommentsManager.getInstance();

		for (Map.Entry<String, Destination> destinationEntry : DestinationsManager.getInstance()
				.getAllDestinations().entrySet()) { // for
													// each
													// destination
													// in
													// cache
			for (Comment c : CommentsManager.getInstance().getAllComments()) { // for
																				// each
																				// comment
				// in cache
				if (c.getPlaceName().equals(destinationEntry.getKey())) { // if
																			// name
																			// of
																			// comments'
																			// place
																			// equals
																			// name
																			// of
																			// destination
																			// from
																			// cache
					destinationEntry.getValue().addComment(c); // add
																// comment
																// to
																// destination's
																// cache
				}
			}
		}

		for (Map.Entry<String, Destination> destinationEntry : DestinationsManager.getInstance().getAllDestinations()
				.entrySet()) { // for each destination
			for (Map.Entry<String, User> userEntry : UsersManager.getInstance().getRegisterredUsers().entrySet()) { // for
																													// each
																													// user
				if (destinationEntry.getValue().getAuthorEmail().equals(userEntry.getKey())) { // if
																								// destination
																								// author's
																								// email
																								// equals
																								// user's
																								// email
					userEntry.getValue().addPlace(destinationEntry.getKey()); // add
																				// destination
																				// name
																				// to
																				// user's
																				// added
																				// places
				}
			}
		}

	}

	public static synchronized ProjectManager getInstance() {
		if (instance == null) {
			instance = new ProjectManager();
		}
		return instance;
	}

}
