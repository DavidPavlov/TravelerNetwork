package exceptions;

public class InvalidPasswordException extends InvalidDataException{
	@Override
	public String getMessage() {
		return "Invalid password, it must be at least 6 characters long";
	}
}
