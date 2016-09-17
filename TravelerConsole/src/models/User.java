package models;

import java.util.ArrayList;

public class User implements Cloneable {

	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String description;
	private ArrayList<Destination> visitedPlaces;
	private String profilePic;

	public User(String firstName, String lastName, String password, String email, String description,
			String profilePic) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.description = description;
		this.visitedPlaces = new ArrayList<>();
		this.profilePic = profilePic;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getDescription() {
		return description;
	}

	public ArrayList<Destination> getVisitedPlaces() {
		return (ArrayList<Destination>) this.visitedPlaces;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void addVisitedPlace(Destination destination) {
		this.visitedPlaces.add(destination);
	}

}
