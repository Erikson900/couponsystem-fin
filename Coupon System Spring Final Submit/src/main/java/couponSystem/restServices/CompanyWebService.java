package couponSystem.restServices;

import java.util.Date;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import couponSystem.entities.Coupon;
import couponSystem.entities.Coupon_Type;
import couponSystem.entities.User;
import couponSystem.exceptions.CouponNotFoundException;
import couponSystem.exceptions.OwnerException;
import couponSystem.services.CompService;

/**
 * This class receive requests from Company type client. 
 * @author Erik
 *
 */
@RestController
//@CrossOrigin(origins = "hyyp://locslhost:4200", allowCredentials = "true")
@RequestMapping("Company")
public class CompanyWebService {
	@Autowired
	CompService compService;
	
	/**
	 * retrieve the session object from the request with supposed user info.  
	 * @param req
	 * @return
	 */
	public User getLoggedUser(HttpServletRequest req) {
		return (User) req.getSession(false).getAttribute("user");	
	}
	
	//-----------------------------------------------Coupon REST CRUD-----------------------------------------------------------------
	
	@PostMapping("/createCoupon")
	public void createCoupon( @RequestBody Coupon coup, HttpServletRequest req) throws Exception {
		compService.createCoupon(coup, getLoggedUser(req).getId());
	}
	
	@DeleteMapping("/removeCoupon/{couponId}")
	public void deleteCoupon(@PathVariable("couponId") long couponId, HttpServletRequest req) throws CouponNotFoundException, OwnerException {
		compService.deleteCoupon(getLoggedUser(req).getId(), couponId);
	}
	
	@PutMapping("/updateCoupon")
	public void updateCoupon(@RequestBody Coupon coup,  HttpServletRequest req ) throws Exception {
		compService.updateCoupon(coup, getLoggedUser(req).getId());
	}

	//-----------------------------------------------Coupon REST GETTERS-----------------------------------------------------------------
	
	@GetMapping("/getCoupon/{couponId}")
	public Coupon getCouponById(@PathVariable("couponId") long coupId, HttpServletRequest req) throws CouponNotFoundException {
		return compService.getCouponById(coupId,getLoggedUser(req).getId());
	}
	
	@GetMapping("/allCompanyCoupons")
	public Collection<Coupon> getAllCoupons(HttpServletRequest req)  throws Exception {
		return compService.getCompanyCoupons(getLoggedUser(req).getId());
	}

	@GetMapping("/allCompanyCouponsByType/{type}")
	public Collection<Coupon> getCouponsByType(@PathVariable("type") Coupon_Type type, HttpServletRequest req ) throws Exception {
		return compService.getCompanyCouponsByType(type, getLoggedUser(req).getId());
	}

	@GetMapping("/allCompanyCouponsByPrice/{price}")
	public Collection<Coupon> getCouponsPrice(@PathVariable("price") double price, HttpServletRequest req ) throws Exception {
		return compService.getCompanyCouponsByPrice(price, getLoggedUser(req).getId());
	}

	@GetMapping("allCompanyCouponsByDate/{date}")
	public Collection<Coupon> getCouponByDate(@PathVariable("date") Date date, HttpServletRequest req) throws Exception{
		return compService.getAllCompanyCouponsByDate(date, getLoggedUser(req).getId());
	}
}
