package couponSystem.exceptions;

public class OwnerException extends Exception {

	/**
	 * object used in to be able to use this exception elsewhere Coupon ownership.
	 */
	private static final long serialVersionUID = 1L;

	public OwnerException( long id) {
		super("You don't own the object with an ID: " + id);
	}


}
