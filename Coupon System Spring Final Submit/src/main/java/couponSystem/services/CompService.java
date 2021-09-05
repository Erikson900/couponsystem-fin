package couponSystem.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import couponSystem.entities.Company;
import couponSystem.entities.Coupon;
import couponSystem.entities.Coupon_Type;
import couponSystem.entities.User;
import couponSystem.exceptions.CouponExistException;
import couponSystem.exceptions.CouponNotFoundException;
import couponSystem.exceptions.CouponsNotFoundException;
import couponSystem.exceptions.OwnerException;
import couponSystem.exceptions.UniqueNameException;
import couponSystem.login.ClientType;
import couponSystem.repositories.CompRepository;
import couponSystem.repositories.CoupRepository;

/**
 * This service contains CRUD operations and Getters according to the requirements of the project.
 * @author Erik
 *
 */

@Service
@Validated
public class CompService {

	@Autowired
	CompRepository compRepository;

	@Autowired
	CoupRepository coupRepository;

	/**
	 * Company Login check.
	 * @param userName
	 * @param password
	 * @param clientType
	 * @return
	 */
	public User login(String userName, String password, ClientType clientType) {
		if (!userName.isEmpty() && !password.isEmpty() && userName != null && password != null) {
			Optional<Company> compLogging = compRepository.findCompanyByCompNameAndPassword(userName, password);
			if (compLogging != null) {
				System.out.println("Login Success");
				return new User(compLogging.get().getId(),userName, password, clientType);
			}
		} else {
			System.err.println("Login Faild");
		}
		return null;
	}

	//----------------------------------CRUD------------------------------------------------------------------
	@Modifying
	@Transactional
	public void createCoupon(@Valid Coupon coup,@Positive long comp_id) throws CouponExistException  {
		Company comp = compRepository.getOne(comp_id);
		List<Coupon> couponCheck = coupRepository.findAll();	
		for(Coupon c: couponCheck) {
			if(c.getTitle().equalsIgnoreCase(coup.getTitle())) {
				throw new CouponExistException(coup.getTitle());
			}
		}	
		comp.getCoupons().add(coup);
		coup.setCompany(comp);
		coupRepository.save(coup);
	}

	@Modifying
	@Transactional
	public void updateCoupon(@Valid Coupon coup,@Positive long comp_id) throws OwnerException, UniqueNameException {
		Optional<Coupon> optionalCoup = coupRepository.findCompanyCouponById(coup.getId(), comp_id);
		if (!optionalCoup.isPresent()) {
			throw new OwnerException(coup.getId());
		}else if(optionalCoup.get().getId() == coup.getId()  && optionalCoup.get().getTitle() == coup.getTitle()){	
			coupRepository.save(coup);	
		}else if (!optionalCoup.get().getTitle().equalsIgnoreCase(coup.getTitle()))  {
			throw new UniqueNameException();
		}	
	}
	
	@Modifying
	@Transactional
	public void deleteCoupon(@Positive long coup_id, @Positive long comp_id) throws OwnerException {				
			Optional<List<Coupon>> couponCheck = coupRepository.findAllByCompany(comp_id);
			if(!couponCheck.isPresent()) {
				throw new OwnerException(coup_id);
			}else if (couponCheck.get().contains(coupRepository.findById(coup_id).get())) {
				coupRepository.deleteById(coup_id);
			}
			
	}

	//----------------------------------GETTERS------------------------------------------------------------------
	
	public Coupon getCouponById( @Positive long coup_id, @Positive long comp_id) throws CouponNotFoundException {
		Optional<Coupon> optionalCoup = coupRepository.findCompanyCouponById(coup_id, comp_id);
		if (!optionalCoup.isPresent()) {
			throw new CouponNotFoundException(coup_id);
		}
		return optionalCoup.get();
	}

	public List<Coupon> getCompanyCoupons(@Positive long comp_id) throws CouponsNotFoundException {
		Optional<List<Coupon>> coupons = coupRepository.findAllByCompany(comp_id);
		if(!coupons.isPresent()) {
			throw new CouponsNotFoundException();
		}
		return coupons.get();
	}
	
	public List<Coupon> getCompanyCouponsByPrice(@Positive double price, @Positive long comp_id) throws CouponsNotFoundException {
		Optional<List<Coupon>> coupons = coupRepository.findAllCompanyCouponsByPrice(price, comp_id);
		if(!coupons.isPresent()) {
			throw new CouponsNotFoundException(price);
		}
		return coupons.get();
	}

	public List<Coupon> getCompanyCouponsByType(Coupon_Type type, @Positive long comp_id) throws CouponsNotFoundException {
		Optional<List<Coupon>> coupons = coupRepository.findAllCompanyCouponsByType(type, comp_id);
		if(!coupons.isPresent()) {
			throw new CouponsNotFoundException(type);
		}
		return coupons.get();
	}

	public List<Coupon> getAllCompanyCouponsByDate(@Future Date date, @Positive long comp_id) throws CouponsNotFoundException {
		Optional<List<Coupon>> coupons = coupRepository.findAllCompanyCouponsByDate(date, comp_id);
		if(!coupons.isPresent()) {
			throw new CouponsNotFoundException(date);
		}
		return coupons.get();
	}

}
