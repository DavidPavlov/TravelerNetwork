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
		synchronized (this) {
			return profilePic;
		}
	}

	public void setProfilePic(String profilePic) {
		synchronized (this) {
			this.profilePic = profilePic;
		}
	}

	public String getFirstName() {
		synchronized (this) {
			return firstName;
		}
	}

	public String getLastName() {
		synchronized (this) {
			return lastName;
		}
	}

	public String getPassword() {
		synchronized (this) {
			return password;
		}
	}

	public String getEmail() {
		synchronized (this) {
			return email;
		}
	}

	public String getDescription() {
		synchronized (this) {
			return description;
		}
	}

	public ArrayList<Destination> getVisitedPlaces() {
		synchronized (this) {
			ArrayList<Destination> copy = new ArrayList<>();
			copy.addAll(visitedPlaces);
			return visitedPlaces;
		}
	}

	public void setFirstName(String firstName) {
		synchronized (this) {
			this.firstName = firstName;
		}
	}

	public void setLastName(String lastName) {
		synchronized (this) {
			this.lastName = lastName;
		}
	}

	public void setPassword(String password) {
		synchronized (this) {
			this.password = password;
		}
	}

	public void setEmail(String email) {
		synchronized (this) {
			this.email = email;
		}
	}

	public synchronized void setDescription(String description) {
		synchronized (this) {
			this.description = description;
		}
	}

	public synchronized void addVisitedPlace(Destination destination) {
		synchronized (this) {
			this.visitedPlaces.add(destination);
		}
	}

}
