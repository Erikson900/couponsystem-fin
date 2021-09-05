package couponSystem.exceptions;

import java.util.Date;

import couponSystem.entities.Coupon_Type;

public class CouponsNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CouponsNotFoundException(Coupon_Type type) {
		super("Coupon of type" + type + " could not be found");
	}
	
	public CouponsNotFoundException(double price) {
		super("Coupon of price and above" + price + " could not be found");
	}
	
	public CouponsNotFoundException(Date date) {
		super("Coupon of type" + date + " could not be found");
	}
	
	public CouponsNotFoundException() {
		super("Coupon could not be found");
	}

}
