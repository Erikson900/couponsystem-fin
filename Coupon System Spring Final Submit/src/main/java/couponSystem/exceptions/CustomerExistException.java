package couponSystem.exceptions;

public class CustomerExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerExistException( String cust_name) {
		super("Customer Name " + cust_name + " is already exist." + 
				 "Customer Name is a unique value please try adding with a diffarent name.");
	}


}
