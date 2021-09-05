package couponSystem.exceptions;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor

@Component
public class LoginException extends Exception {

	
	private static final long serialVersionUID = 1L;
	private String errorValue;
	private long errorVal;
	
	public LoginException(String message, String errorValue) {
		super(message);
		this.setErrorValue(errorValue);
	}
	public LoginException(String message, long errorValue) {
		super(message);
		this.setErrorVal(errorValue);
	}
	public LoginException(String message) {
		super(message);
		this.errorValue = "UserName or Password is incorrect.";
	}
	
}
