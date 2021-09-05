package couponSystem.exceptions;

import java.nio.channels.NoConnectionPendingException;

import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javassist.NotFoundException;

@ControllerAdvice
public class CustomExceptionHandler {
//-------------------------------General Exceptions----------------------------------------------------------------------

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<Object> NullPointerException(NullPointerException e) {
		WebApiError WebApiError = new WebApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		return new ResponseEntity<Object>(WebApiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NoConnectionPendingException.class)
	public ResponseEntity<Object> NotConnectedError(NoConnectionPendingException e) {
		WebApiError WebApiError = new WebApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		return new ResponseEntity<Object>(WebApiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(HibernateException.class)
	public ResponseEntity<Object> HibernateException(HibernateException e) {
		WebApiError WebApiError = new WebApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		return new ResponseEntity<Object>(WebApiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> NotFoundException(NotFoundException e) {
		WebApiError WebApiError = new WebApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		return new ResponseEntity<Object>(WebApiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(OwnerException.class)
	public ResponseEntity<Object> OwnerException(OwnerException e) {
		System.out.println(e.getMessage());
		WebApiError WebApiError = new WebApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		return new ResponseEntity<Object>(WebApiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UniqueNameException.class)
	public ResponseEntity<Object> UniqueNameException(UniqueNameException e) {
		System.out.println(e.getMessage());
		WebApiError WebApiError = new WebApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		return new ResponseEntity<Object>(WebApiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}
//--------------------------------Login Exceptions----------------------------------------------------------------------	

	@ExceptionHandler(LoginException.class)
	public ResponseEntity<Object> LoginException(LoginException e) {
		System.err.println(e.getMessage());
		WebApiError message = new WebApiError(HttpStatus.INTERNAL_SERVER_ERROR,e.getErrorValue());
		return new ResponseEntity<Object>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}

//--------------------------------Company Exceptions----------------------------------------------------------------------	

	@ExceptionHandler(CompanyNotFoundException.class)
	public ResponseEntity<Object> CompanyNotFoundException(CompanyNotFoundException e) {
		System.out.println(e.getMessage());
		WebApiError WebApiError = new WebApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		return new ResponseEntity<Object>(WebApiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(CompanyExistException.class)
	public ResponseEntity<Object> CompanyExistException(CompanyExistException e) {
		System.out.println(e.getMessage());
		WebApiError WebApiError = new WebApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		return new ResponseEntity<Object>(WebApiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

//--------------------------------Customer Exceptions----------------------------------------------------------------------

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<Object> CustomerNotFoundException(CustomerNotFoundException e) {
		WebApiError WebApiError = new WebApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		return new ResponseEntity<Object>(WebApiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(CustomerExistException.class)
	public ResponseEntity<Object> CustomerExistException(CustomerExistException e) {
		System.out.println(e.getMessage());
		WebApiError WebApiError = new WebApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		return new ResponseEntity<Object>(WebApiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
//--------------------------------Coupon Exceptions----------------------------------------------------------------------	

	@ExceptionHandler(CouponExistException.class)
	public ResponseEntity<Object> CouponExistExeption(CouponExistException e) {
		System.out.println(e.getMessage());
		WebApiError WebApiError = new WebApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		return new ResponseEntity<Object>(WebApiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(CouponNotFoundException.class)
	public ResponseEntity<Object> CouponNotfoundException(CouponNotFoundException e) {
		WebApiError WebApiError = new WebApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		return new ResponseEntity<Object>(WebApiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(CouponsNotFoundException.class)
	public ResponseEntity<Object> CouponsNotFoundException(CouponsNotFoundException e) {
		System.out.println(e.getMessage());
		WebApiError WebApiError = new WebApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		return new ResponseEntity<Object>(WebApiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	

}
