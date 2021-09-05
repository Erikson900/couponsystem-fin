package couponSystem.exceptions;

public class CompanyNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CompanyNotFoundException( long id) {
		super("Company #" + id + " could not be found");
	}


}
