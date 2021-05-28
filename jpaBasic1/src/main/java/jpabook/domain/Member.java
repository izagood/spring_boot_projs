package jpabook.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Member {

	@Id @GeneratedValue
	@Column(name = "member_id")
	private Long id;
	
	@OneToMany
	@JoinColumn(name = "member")
	private List<Order> orders = new ArrayList<>();
	
	// 기간
	@Embedded
	private Period workPeriod;
	
	// 주소
	@Embedded
	private Address homeAddress;
	
	@ElementCollection
	@CollectionTable(name = "favorite_food", 
		joinColumns = @JoinColumn(name = "member_id")
	)
	@Column(name = "food_name") // String 하나이기 때문에 예외적으로 컬럼과 직접 매핑 가능
	private Set<String> favoriteFoods = new HashSet<>();
	
	@ElementCollection
	@CollectionTable(name = "address",
		joinColumns = @JoinColumn(name = "member_id")
	)
	private List<Address> addressHistory = new ArrayList<>();
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="city", 
				column=@Column(name = "work_citye")),
		@AttributeOverride(name="street", 
		column=@Column(name = "work_street")),
		@AttributeOverride(name="zipcode", 
		column=@Column(name = "work_zipcode"))
	})
	private Address workAddress;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Period getWorkPeriod() {
		return workPeriod;
	}

	public void setWorkPeriod(Period workPeriod) {
		this.workPeriod = workPeriod;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	
}
