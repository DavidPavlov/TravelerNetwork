package exceptions;

public class InvalidEmailException extends InvalidDataException {
	@Override
	public String getMessage() {
		return "Invalid Email Entered !";
	}
}
