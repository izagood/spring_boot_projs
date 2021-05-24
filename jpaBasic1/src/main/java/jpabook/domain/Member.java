package jpabook.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Member extends BaseEntity {

	@Id @GeneratedValue
	@Column(name = "member_id")
	private Long id;
	
	private String name;
	private String city;
	private String street;
	private String zipcode;
	
	@OneToMany
	@JoinColumn(name = "member")
	private List<Order> orders = new ArrayList<>();
	
	
}
