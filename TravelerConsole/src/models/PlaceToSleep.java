package models;

public class PlaceToSleep {
	private String name;
	private Location location;
	private String contact;

	public PlaceToSleep(String name, Location location, String contact) {
		this.name = name;
		this.location = location;
		this.contact = contact;
	}

	public String getName() {
		return name;
	}

	public String getContact() {
		return contact;
	}

	public Location getLocation() throws CloneNotSupportedException {
		return location;
	}

}
