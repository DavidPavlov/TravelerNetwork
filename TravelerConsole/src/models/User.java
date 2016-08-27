package models;

import java.util.ArrayList;

import exceptions.InvalidAuthorException;
import exceptions.InvalidDataException;
import exceptions.InvalidEmailException;
import exceptions.InvalidPasswordException;

public class User implements Cloneable {
	private static final String NAME_PATTERN = "^[A-Za-z]+$";
	private static final int MINIMUM_PASSWORD_LENGTH = 6;
	private static final String EMAIL_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9]+.[a-z.]+$";

	private String firstName;
	private String lastName;
	private String password; // instead of String for security
	private String email;
	private String description;
	private ArrayList<Destination> visitedPlaces;

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
		if (!(password == null) && password.length() >= MINIMUM_PASSWORD_LENGTH) {
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

	public ArrayList<Destination> getVisitedPlaces() {
		return (ArrayList<Destination>) this.visitedPlaces.clone();
	}

	public void addVisitedDestination(Destination destination) {
		this.visitedPlaces.add(destination);
	}

	public void makeAComment(Destination destination, String text) throws InvalidDataException, InvalidAuthorException {
		destination.addComment(new Comment(text, this));
	}

	public void likeAComment(Comment comment) {
		if (comment != null) {
			ArrayList<User> userLikersOfComment = comment.getUserLikers(); // all
																			// the
																			// users
																			// who
																			// like
																			// the
																			// comment
			for (int i = 0; i < userLikersOfComment.size(); i++) {
				if (userLikersOfComment.get(i) == this) { // if the current user
															// has already liked
															// the comment
					return; // do nothing
				}
			}
			comment.addLike(); // the comment is liked
			comment.addUserLiker(this); // the user is added to the list of
										// users who like the comment
		}
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
