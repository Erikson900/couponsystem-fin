package couponSystem.restServices;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import couponSystem.entities.Coupon;
import couponSystem.entities.Coupon_Type;
import couponSystem.entities.User;
import couponSystem.exceptions.CouponNotFoundException;
import couponSystem.repositories.CoupRepository;
import couponSystem.services.CustService;;

/**
 * This class receive requests from Customer type client. 
 * @author Erik
 *
 */
@RestController
//@CrossOrigin(origins = "hyyp://locslhost:4200", allowCredentials = "true")
@RequestMapping("Customer")
public class CustomerWebService {

	@Autowired
	CustService custService;
	
	@Autowired
	CoupRepository coupReposetory;
	
	/**
	 * retrieve the session object from the request with supposed user info. 
	 * @param req
	 * @return
	 */
	public User getLoggedUser(HttpServletRequest req) {
		return (User) req.getSession(false).getAttribute("user");	
	}

	/**
	 * REST purchase coupon.
	 * @param req
	 * @param coupon
	 * @throws Exception
	 */
	@PostMapping("/purchase")
	public void purchaseCoupon(HttpServletRequest req, @RequestBody Coupon coupon) throws Exception {
		custService.purchaseCouponById(coupon, getLoggedUser(req).getId());
	}

	//-----------------------------------------------Coupon REST GETTERS-----------------------------------------------------------------
	
	@GetMapping("/getCoupon/{couponId}")
	public Coupon getCouponById(HttpServletRequest req, @PathVariable("couponId") long coupId) throws CouponNotFoundException {
		return custService.getCouponById(getLoggedUser(req).getId(),coupId);
	}
	
	@GetMapping("/allPerchasedCoupons")
	public List<Coupon> getAllPurchaedCoupons(HttpServletRequest req) throws Exception {		
		return custService.getCustomerCoupons(getLoggedUser(req).getId());
	}

	@GetMapping("/byType/{type}")
	public List<Coupon> getPurchasedCouponsByType(HttpServletRequest req, @PathVariable ("type") Coupon_Type type ) throws Exception {
		return custService.getCustomerCouponsByType(type, getLoggedUser(req).getId());
	}

	@GetMapping("/byPrice/{price}")
	public List<Coupon> getPurchaedCouponsByPrice(HttpServletRequest req, @PathVariable ("price") double price ) throws Exception {
		return custService.getCustomerCouponsByPrice(price, getLoggedUser(req).getId());
	}

	@GetMapping("/date/{date}")
	public List<Coupon> getPurchaedCouponsByDate(HttpServletRequest req, @PathVariable ("date") Date date ) throws Exception {
		return custService.getCustomerCouponsByDate(date, getLoggedUser(req).getId());
	}
}