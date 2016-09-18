package models;

public class Location implements Cloneable {
	private double lattitude;
	private double longitude;

	public Location(double lattitude, double longitude) {
		this.setLattitude(lattitude);
		this.setLongitude(longitude);
	}

	public double getLattitude() {
		return lattitude;
	}

	private void setLattitude(double lattitude) {
		if (lattitude < 0 || lattitude > 90) {

		}
		this.lattitude = lattitude;
	}

	public double getLongitude() {
		return longitude;
	}

	private void setLongitude(double longitude) {
		if (longitude < 0 || longitude > 180) {

		}
		this.longitude = longitude;
	}

}
