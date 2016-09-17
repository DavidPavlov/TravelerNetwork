package models;

import exceptions.InvalidCoordinatesException;

public class Location implements Cloneable {
	private double lattitude;
	private double longitude;

	public Location(double lattitude, double longitude) throws InvalidCoordinatesException {
		this.setLattitude(lattitude);
		this.setLongitude(longitude);
	}

	public double getLattitude() {
		return lattitude;
	}

	private void setLattitude(double lattitude) throws InvalidCoordinatesException {
		if (lattitude < 0 || lattitude > 90) {
			throw new InvalidCoordinatesException();
		}
		this.lattitude = lattitude;
	}

	public double getLongitude() {
		return longitude;
	}

	private void setLongitude(double longitude) throws InvalidCoordinatesException {
		if (longitude < 0 || longitude > 180) {
			throw new InvalidCoordinatesException();
		}
		this.longitude = longitude;
	}

}
