package couponSystem.exceptions;

public class CustomerNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerNotFoundException( long id) {
		super("Customer #" + id + " was not found");
	}


}
