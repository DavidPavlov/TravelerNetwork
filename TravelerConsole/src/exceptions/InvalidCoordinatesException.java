package exceptions;

public class InvalidCoordinatesException extends InvalidDataException {
	@Override
	public String getMessage() {
		return "Invalid Coordinates Entered";
	}
}
