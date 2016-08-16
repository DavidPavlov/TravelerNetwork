package exceptions;

public class InvalidLocationException extends Exception {

	@Override
	public String getMessage() {
		return "Invalid Location Entered!";
	}
}
