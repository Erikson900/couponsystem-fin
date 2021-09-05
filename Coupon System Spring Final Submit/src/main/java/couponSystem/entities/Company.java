package couponSystem.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Company Entity class contains the company data members.
 * @author Erik
 *
 */

@Data

@NoArgsConstructor

@Entity
public class Company {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(max=50)
	private String compName;
	
	@NotNull
	@Size(min=4,max=12)
	private String password;
	
	@Email
	private String email;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	@JsonIgnore
	private List<Coupon> coupons;

	public Company(String compName,String password, String email, List<Coupon> coupons) {
		this.compName = compName;
		this.password = password;
		this.email = email;
		this.coupons = coupons;
	}
		
}
