package models;

import exceptions.InvalidDataException;
import exceptions.InvalidLocationException;

public class Activity {

	private String name;
	private double price;
	private String workingHours;
	private Location location;
	private String description;

	public Activity(String name, double price, String workingHours, Location location, String description)
			throws InvalidDataException, InvalidLocationException {
		super();
		this.setName(name);
		this.setPrice(price);
		this.setWorkingHours(workingHours);
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(String workingHours) throws InvalidDataException {
		if (!(name == null || name.isEmpty())) {
			this.workingHours = workingHours;
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
