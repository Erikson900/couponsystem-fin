package couponSystem.exceptions;

public class UniqueNameException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UniqueNameException() {
		super(" Cannot Update NAME for it's Unique.");
	}


}
