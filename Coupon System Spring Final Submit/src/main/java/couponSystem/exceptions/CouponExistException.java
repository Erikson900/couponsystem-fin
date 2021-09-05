package couponSystem.exceptions;

public class CouponExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CouponExistException( String coup_name) {
		super("Coupon Title " + coup_name + " is already exist." + 
				 "Coupon Name is a unique value please try adding with a diffarent name.");
	}


}
