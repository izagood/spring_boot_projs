package jpabook.jpashop.domain;

import javax.persistence.Embeddable;

import lombok.Getter;

@Embeddable // 내장 타입
@Getter
public class Address {
	
	private String city;
	private String street;
	private String zipcode;
	
	protected Address() { // JPA 스펙상 생성
	}
	
	public Address(String city, String street, String zipcode) {
		super();
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}
	
}
