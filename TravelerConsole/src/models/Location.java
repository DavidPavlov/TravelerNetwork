package models;

public class Location {
	private double lattitude;
	private double longitude;

	public Location(double lattitude, double longitude) {
		this.setLattitude(lattitude);
		this.setLongitude(longitude);
	}

	public double getLattitude() {
		synchronized (this) {
			return lattitude;
		}
	}

	private void setLattitude(double lattitude) {
		synchronized (this) {
			if (lattitude < 0 || lattitude > 90) {

			}
			this.lattitude = lattitude;
		}
	}

	public double getLongitude() {
		synchronized (this) {
			return longitude;
		}
	}

	private void setLongitude(double longitude) {
		synchronized (this) {
			if (longitude < 0 || longitude > 180) {

			}
			this.longitude = longitude;
		}
	}

}
