package exceptions;

public class CannotConnectToDBException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "The connection to the DB could not initialize. Please check the settings again.";
	}
}
