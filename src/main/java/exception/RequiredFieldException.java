package exception;

public class RequiredFieldException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4863936916785186697L;

	public RequiredFieldException() {
		super();
	}

	public RequiredFieldException(String message) {
		super(message);
	}
}
