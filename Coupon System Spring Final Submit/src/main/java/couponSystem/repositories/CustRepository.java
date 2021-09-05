package couponSystem.repositories;

import java.util.Optional;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import couponSystem.entities.Coupon;
import couponSystem.entities.Coupon_Type;
import couponSystem.entities.Customer;

@Repository
public interface CustRepository extends JpaRepository<Customer, Long> {
	
	Optional<Customer> findCustomerByCustNameAndPassword(String name,String password);
		
	Optional<Customer> findCustomerByCustName(String name);
	
	@Query("SELECT c FROM Coupon c WHERE c.id = ?1 AND c.id IN (SELECT c.id FROM c.customer cust WHERE cust.id =?2 )")
	Coupon findCustomerCouponById(long coup_id, long cust_id);

	@Query("SELECT c FROM Coupon c WHERE c.id IN (SELECT c.id FROM c.customer cust WHERE cust.id =?1 )")
	List<Coupon> findAllCustomerCoupons(long cust_id);

	@Query("SELECT c FROM Coupon c WHERE c.price > ?1 AND c.id IN (SELECT c.id FROM c.customer cust WHERE cust.id =?2 )")
	List<Coupon> findAllCustomerCouponsbyPrice( double price ,long cust_id);

	@Query("SELECT c FROM Coupon c WHERE c.startDate < ?1 AND ?1 < c.endDate AND c.id IN (SELECT c.id FROM c.customer cust WHERE cust.id =?2 )")
	List<Coupon> findAllCustomerCouponsbyDate(Date date ,long cust_id);

	@Query("SELECT c FROM Coupon c WHERE c.couponType = ?1 AND c.id IN (SELECT c.id FROM c.customer cust WHERE cust.id =?2 )")
	List<Coupon> findAllCustomerCouponsByType(Coupon_Type type ,long cust_id);
	
}
