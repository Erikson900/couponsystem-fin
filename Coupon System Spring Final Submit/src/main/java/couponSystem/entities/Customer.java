package couponSystem.entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Customer Entity class contains the customer data members.
 * @author Erik
 *
 */

@Data

@NoArgsConstructor

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(max = 50)
	private String custName;

	@NotNull
	@Size(min = 4, max = 12)
	private String password;

	@JsonIgnore
	@ManyToMany( cascade=CascadeType.PERSIST)
	private Collection<Coupon> coupons;

	public Customer(String custName,String password, Collection<Coupon> coupons) {
			this.custName = custName;
			this.password = password;
			this.coupons = coupons;
		}
}
