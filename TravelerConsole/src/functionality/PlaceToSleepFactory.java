package functionality;

import models.Location;
import models.PlaceToSleep;

public class PlaceToSleepFactory {

	public static PlaceToSleep createPlaceToSleep(String name, Location location, String contact) {
		return new PlaceToSleep(name, location, contact);
	}
}
