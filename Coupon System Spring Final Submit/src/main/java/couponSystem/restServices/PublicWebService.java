package couponSystem.restServices;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import couponSystem.entities.Coupon;
import couponSystem.entities.Coupon_Type;
import couponSystem.exceptions.CouponNotFoundException;
import couponSystem.repositories.CoupRepository;

/**
 * This REST Service serve for public client use holds only getters.
 * @author Erik
 *
 */
@RestController
//@CrossOrigin(origins = "hyyp://locslhost:4200", allowCredentials = "true")
@RequestMapping("Public")
public class PublicWebService {

	
	@Autowired
	CoupRepository coupRepository;
	
	@GetMapping("/allCoupons")
	public List<Coupon> getAllCoupons(){
		System.err.println("Gotit");
		return coupRepository.findAll();
	}
	
	@GetMapping("/getCoupon/{couponId}")
	public Coupon getCouponById(@PathVariable("couponId") long coupId) throws CouponNotFoundException {
		Optional<Coupon> optionalCoup = coupRepository.findById(coupId);
		if (!optionalCoup.isPresent()) {
			throw new CouponNotFoundException(coupId);
		}
		return optionalCoup.get(); 
	}
	
	@GetMapping("/allCouponsByType/{type}")
	public List<Coupon> getCouponsByType(@PathVariable("type") Coupon_Type type) throws Exception {
		System.err.println("--------------------------" + type + "-----------------------------------------");
		Optional<List<Coupon>> optionalCoup = coupRepository.findAllByType(type);
		if (!optionalCoup.isPresent()) {
		}
		return optionalCoup.get(); 
	}

	@GetMapping("/allCouponsByPrice/{price}")
	public List<Coupon> getCouponsPrice(@PathVariable("price") double price) throws Exception {
		System.err.println("--------------------------" + price + "-----------------------------------------");
		Optional<List<Coupon>> optionalCoup = coupRepository.findAllByPrice(price);
		if (!optionalCoup.isPresent()) {
		}
		return optionalCoup.get();
	}
	
	@GetMapping("allCouponsByDate/{date}")
	public List<Coupon> getCouponByDate(@PathVariable("date") Date date) throws Exception{
		System.err.println("--------------------------" + date + "-----------------------------------------");
		Optional<List<Coupon>> optionalCoup = coupRepository.findAllByDate(date);
		if (!optionalCoup.isPresent()) {
		}
		return optionalCoup.get();
	}
}
