package jpabook.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Delivery extends BaseEntity {
	
	@Id @GeneratedValue
	@Column(name ="delivery_id")
	private Long id;
	
	@OneToOne(mappedBy = "delivery")
	private Order order;
	
	private String city;
	private String street;
	private String zipcode;
	
	private DeliveryStatus status;

}
