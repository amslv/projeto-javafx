package exception;

public class DuplicatedEntryException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4649840574352745281L;

	public DuplicatedEntryException() {
		super();
	}

	public DuplicatedEntryException(String message) {
		super(message);
	}
}
