package models;

public class PlaceToEat {
	private String name;
	private String workingHours;
	private Location location;

	public PlaceToEat(String name, String workingHours, Location location) {
		super();
		this.name = name;
		this.workingHours = workingHours;
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public String getWorkingHours() {
		return workingHours;
	}

	public Location getLocation() throws CloneNotSupportedException {
		return location;
	}

}
