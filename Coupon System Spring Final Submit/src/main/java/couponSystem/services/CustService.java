package couponSystem.services;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.constraints.Future;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import couponSystem.entities.Coupon;
import couponSystem.entities.Coupon_Type;
import couponSystem.entities.Customer;
import couponSystem.entities.User;
import couponSystem.exceptions.CouponNotFoundException;
import couponSystem.exceptions.CouponsNotFoundException;
import couponSystem.exceptions.LoginException;
import couponSystem.login.ClientType;
import couponSystem.repositories.CoupRepository;
import couponSystem.repositories.CustRepository;

/**
 * This service contains the methods that a customer client uses.
 * @author Erik
 *
 */

@Service
@Validated
public class CustService {

	@Autowired
	CustRepository custRepository;
	
	@Autowired
	CoupRepository coupRepository;
	
	/**
	 * Customer login data check.
	 * @param userName
	 * @param password
	 * @param clientType
	 * @return
	 * @throws LoginException
	 */
	public User login(String userName, String password, ClientType clientType) throws LoginException {
		if(!userName.isEmpty() && !password.isEmpty() && userName != null && password != null) {
			Optional<Customer> custLogging = custRepository.findCustomerByCustNameAndPassword(userName, password);
			if(custLogging != null) {
				System.out.println("Login Success");
				return new User(custLogging.get().getId(), userName, password, clientType);
			}
		}else {
			throw new LoginException();
		}
		return null;
	}
	
	/**
	 * Purchase method for the client makes sure the coupon is not already purchased, expired or old out.
	 * @param coupon
	 * @param cust_id
	 * @throws Exception
	 */
	@Modifying
	@Transactional
	public void purchaseCouponById(Coupon coupon, long cust_id) throws Exception{
		List<Coupon> couponCheck = custRepository.findAllCustomerCoupons(cust_id);
		//if(couponCheck.get().contains(coupon)) 
		for(Coupon c : couponCheck){
			if(c.getId() == coupon.getId() && c.getTitle().equalsIgnoreCase(coupon.getTitle())) {
				throw new Exception("you allready purchase this coupon ");
			}
		}									
		if (coupon.getEndDate().after(new Date(System.currentTimeMillis()))) {
			if (coupon.getAmount() > 0) {
					List<Customer> customers = (List<Customer>) coupon.getCustomer();
					System.err.println(cust_id);
					Customer cust = custRepository.findById((long) cust_id).get();
					List<Coupon> customersCoupon = (List<Coupon>) cust.getCoupons();
					customersCoupon.add(coupon);
					coupon.setAmount(coupon.getAmount()-1);
					customers.add(cust);
					custRepository.save(cust);
					coupRepository.save(coupon);
				}else {
					throw new Exception("Coupon " + coupon.getTitle() + " Sold out. sorry.");
				}
			}else {
				throw new Exception("The coupon you wish to purchase is already expierd");
			}					
	}
				
	//--------------------------------------GETTERS-----------------------------------------------------------------------
	
	public Coupon getCouponById(@Positive long coup_id, @Positive long cust_id) throws CouponNotFoundException {
		Coupon Coup = custRepository.findCustomerCouponById(coup_id, cust_id);
		if (Coup == null) {
			throw new CouponNotFoundException(coup_id);
		}
		return Coup;
	}
	
	public List<Coupon> getCustomerCoupons(@Positive long cust_id) throws CouponsNotFoundException {
		List<Coupon> coupons = custRepository.findAllCustomerCoupons(cust_id);
		if(coupons.isEmpty()) {
			throw new CouponsNotFoundException();
		}
		return coupons;
	}
	
	public List<Coupon> getCustomerCouponsByPrice(@Positive double price, @Positive long cust_id) throws CouponsNotFoundException{
		List<Coupon> coupons = custRepository.findAllCustomerCouponsbyPrice(price, cust_id);
		if(coupons.isEmpty()) {
			throw new CouponsNotFoundException(price);
		}
		return coupons;
	}
	
	public List<Coupon>getCustomerCouponsByType(Coupon_Type type, @Positive long cust_id) throws CouponsNotFoundException{
		List<Coupon> coupons = custRepository.findAllCustomerCouponsByType(type, cust_id);
		if(coupons.isEmpty()) {
			throw new CouponsNotFoundException(type);
		}
		return coupons;
	}
	
	public List<Coupon> getCustomerCouponsByDate(@Future Date date,@Positive long cust_id) throws CouponsNotFoundException{
		List<Coupon> coupons = custRepository.findAllCustomerCouponsbyDate(date, cust_id);
		if(coupons.isEmpty()) {
			throw new CouponsNotFoundException(date);
		}
		return coupons;
	}
}
