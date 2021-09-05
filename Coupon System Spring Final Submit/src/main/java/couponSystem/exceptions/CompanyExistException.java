package couponSystem.exceptions;

public class CompanyExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CompanyExistException( String comp_name) {
		super("Company Name " + comp_name + " is already exist." + 
				 "Company Name is a unique value please try adding with a diffarent name.");
	}


}
