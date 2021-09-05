package couponSystem.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * Coupon Entity class contains the coupon data members.
 * @author Erik
 *
 */

@Data

@NoArgsConstructor

@Entity
public class Coupon { 
	
	@ManyToOne
	private Company company;
	
	@ManyToMany(mappedBy = "coupons")
	private Collection<Customer> customer;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(max=75)
	private String title;
	
	@NotNull
	private Date startDate;
	
	@NotNull
	@Future
	private Date endDate;
	
	@Positive
	private int amount;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Coupon_Type couponType;
	
	private String message;
	
	@Positive
	private double price;
	
	@Valid
	private String image;
	
	public Coupon(String title,  Date startDate, Date endDate, int amount,Coupon_Type couponType,String massage, double price,
			String image) {
		super();
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.couponType = couponType;
		this.message= massage;
		this.price = price;
		this.image = image;
	}
	
}
