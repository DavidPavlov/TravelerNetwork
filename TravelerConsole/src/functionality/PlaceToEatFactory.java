package functionality;

import models.Location;
import models.PlaceToEat;

public class PlaceToEatFactory {
	public static PlaceToEat createPlaceToEat(String name, String workingHours, Location location) {
		return new PlaceToEat(name, workingHours, location);
	}
}
