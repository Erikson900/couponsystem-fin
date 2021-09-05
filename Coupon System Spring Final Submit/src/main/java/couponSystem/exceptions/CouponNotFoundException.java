package couponSystem.exceptions;

public class CouponNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CouponNotFoundException( long id) {
		super("Coupon #" + id + " could not be found");
	}


}
