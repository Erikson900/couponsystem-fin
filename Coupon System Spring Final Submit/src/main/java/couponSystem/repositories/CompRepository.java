package couponSystem.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import couponSystem.entities.Company;


@Repository
public interface CompRepository extends JpaRepository<Company, Long> {

	/**
	 *  Used for the Login
	 * @param comp_name
	 * @param password
	 * @return
	 */
	Optional<Company> findCompanyByCompNameAndPassword(String comp_name, String password);
	
	/**
	 *  Used for Checking existing Company name 
	 * @param comp_name
	 * @return
	 */
	Optional<Company> findCompanyByCompName(String comp_name);
	
	//---------------------------------------NOT IN USE----------------------------------------------------------------------
	/*
	@Query("SELECT c FROM Coupon c WHERE c.id = ?1 AND c.id IN (SELECT c.id FROM company_coupons comp WHERE comp.id =?2 )")
	Optional<Coupon> findCompanyCouponById(long coup_id, long comp_id);

	@Query("SELECT c FROM Coupon c WHERE c.id IN (SELECT c.id FROM company_coupons comp WHERE comp.id =?1 )")
	Optional<List<Coupon>> findAllCompanyCoupons(long comp_id);

	@Query("SELECT c FROM Coupon c WHERE c.price > ?1 AND c.id IN (SELECT c.id FROM company_coupons comp WHERE comp.id =?2 )")
	Optional<List<Coupon>> findAllCompanyCouponsbyPrice( double price ,long comp_id);

	@Query("SELECT c FROM Coupon c WHERE c.endDate > ?1 AND c.id IN (SELECT c.id FROM company_coupons comp WHERE comp.id =?2 )")
	Optional<List<Coupon>> findAllCompanyCouponsbyDate(Date endDate ,long comp_id);

	@Query("SELECT c FROM Coupon c WHERE c.couponType = ?1 AND c.id IN (SELECT c.id FROM company_coupons comp WHERE comp.id =?2 )")
	Optional<List<Coupon>>  findAllCompanyCouponsByType(Coupon_Type type ,long comp_id);
	*/
}
