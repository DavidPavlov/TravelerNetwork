package models;

import java.util.ArrayList;

import exceptions.InvalidDataException;
import exceptions.InvalidEmailException;
import exceptions.InvalidPasswordException;

public class User {
	private static final String NAME_PATTERN = "^[A-Za-z]+$";
	private static final int MINIMUM_PASSWORD_LENGTH = 6;
	private static final String EMAIL_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9]+.[a-z.]+$";

	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String description;
	private ArrayList<Place> visitedPlaces;

	public User(String firstName, String lastName, String password, String email, String description)
			throws InvalidDataException {
		super();
		this.setFirstName(firstName);

		this.setLastName(lastName);

		this.setPassword(password);
		this.setEmail(email);
		this.description = description;
		this.visitedPlaces = new ArrayList<>();
	}

	public String getFirstName() {
		return firstName;
	}

	private void setFirstName(String firstName) throws InvalidDataException {
		if (!(firstName == null || firstName.isEmpty()) && firstName.matches(NAME_PATTERN)) {
			this.firstName = firstName;
		} else {
			throw new InvalidDataException();
		}
	}

	public String getLastName() {
		return lastName;
	}

	private void setLastName(String lastName) throws InvalidDataException {
		if (!(lastName == null || lastName.isEmpty()) && lastName.matches(NAME_PATTERN)) {
			this.lastName = lastName;
		} else {
			throw new InvalidDataException();
		}
	}

	public String getPassword() {
		return password;
	}

	private void setPassword(String password) throws InvalidPasswordException {
		if (!(password == null || password.isEmpty()) && password.length() >= MINIMUM_PASSWORD_LENGTH) {
			this.password = password;
		} else {
			throw new InvalidPasswordException();
		}
	}

	public String getEmail() {
		return email;
	}

	private void setEmail(String email) throws InvalidEmailException {
		if (!(email == null || email.isEmpty()) && email.matches(EMAIL_PATTERN)) {
			this.email = email;
		} else {
			throw new InvalidEmailException();
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
