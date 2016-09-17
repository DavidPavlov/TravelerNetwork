package models;

import exceptions.InvalidDataException;
import exceptions.InvalidLocationException;

public class Sight {
	private String name;
	private Location location;
	private String description;

	public Sight(String name, Location location, String description)
			throws InvalidDataException, InvalidLocationException {
		super();
		this.setName(name);
		this.setLocation(location);
		this.setDescription(description);
	}

	public String getName() {
		return name;
	}

	private void setName(String name) throws InvalidDataException {
		if (!(name == null || name.isEmpty())) {
			this.name = name;
		} else {
			throw new InvalidDataException();
		}
	}

	public Location getLocation() throws CloneNotSupportedException {
		return location;
	}

	public void setLocation(Location location) throws InvalidLocationException {
		if (!(name == null || name.isEmpty())) {
			this.location = location;
		} else {
			throw new InvalidLocationException();
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if (description != null && !description.isEmpty()) {
			this.description = description;
		}
	}

}
