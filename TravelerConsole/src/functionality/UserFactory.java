package functionality;

import exceptions.InvalidDataException;
import models.User;

public class UserFactory {

	public static User createUser(String firstName, String lastName, String password, String email, String description)
			throws InvalidDataException {
		return new User(firstName, lastName, password, email, description);
	}
}
