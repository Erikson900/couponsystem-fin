package couponSystem.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import couponSystem.entities.Company;
import couponSystem.entities.Coupon;
import couponSystem.entities.Coupon_Type;
import couponSystem.entities.Customer;

@Repository
public interface CoupRepository extends JpaRepository<Coupon, Long> {

	
	//----------------------------Public---------------------------------------------------------------
	Coupon findCouponByTitle(String title);
	
	@Query("SELECT c FROM Coupon c WHERE c.couponType = ?1")
	Optional<List<Coupon>> findAllByType( Coupon_Type couponType );
	
	@Query(value = "SELECT * FROM [Cupon Project].dbo.Coupons WHERE Price > ?1 and Price < ?2", nativeQuery = true)
	Optional<List<Coupon>> findAllByPrice2(double fromPrice,double maxPrice);
	
	@Query("SELECT c FROM Coupon c WHERE c.price > ?1")
	Optional<List<Coupon>> findAllByPrice(double price);
	
	@Query("SELECT c FROM Coupon c WHERE c.startDate > ?1")
	Optional<List<Coupon>>  findAllByDate(java.util.Date date);
	
	@Modifying 
	@Transactional
	@Query( value = "DELETE from Coupon WHERE end_Date < GETDATE()", nativeQuery = true )
	void removeExpieredCoupons();
	//----------------------------Company---------------------------------------------------------------
	@Query("SELECT c FROM Coupon c WHERE c.id = ?1 AND company_id = ?2")
	Optional<Coupon> findCompanyCouponById(long coup_id, long comp_id);
	
	@Query("SELECT c FROM Coupon c WHERE company_id = ?1")
	Optional<List<Coupon>> findAllByCompany(long comp_id);
	
	@Query("SELECT c FROM Coupon c WHERE c.couponType = ?1 AND company_id = ?2")
	Optional<List<Coupon>>  findAllCompanyCouponsByType(Coupon_Type type, long compId);
	
	@Query("SELECT c FROM Coupon c WHERE c.price > ?1 AND company_id = ?2")
	Optional<List<Coupon>> findAllCompanyCouponsByPrice(double price, long compId);
	
	@Query("SELECT c FROM Coupon c WHERE c.endDate > ?1 AND company_id = ?2")
	Optional<List<Coupon>> findAllCompanyCouponsByDate(java.util.Date date,long compId);
	
	@Modifying 
	@Transactional
	void deleteCouponByCompany(Company comp);
	//----------------------------Customer---------------------------------------------------------------
	 
	Optional<List<Coupon>> findAllByCustomer(Customer cust);

	
	
	
	
	
	
	
	
	
	
	
		
	
	
	
	
	
}
