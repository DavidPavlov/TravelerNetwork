package functionality;

import exceptions.InvalidDataException;
import models.User;

public class UserFactory {

	public static User createUser(String firstName, String lastName, String password, String email, String description)
			throws InvalidDataException {
		char[] pass = password.toCharArray();
		return new User(firstName, lastName, pass, email, description);
	}
}
