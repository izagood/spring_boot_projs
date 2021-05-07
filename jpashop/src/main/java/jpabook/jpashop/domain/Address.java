package jpabook.jpashop.domain;

import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable // 내장 타입
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {
	
	private String city;
	private String street;
	private String zipcode;
	
//	protected Address() { // JPA 스펙상 생성 // 일반 생성 방지
//	}
	
	public Address(String city, String street, String zipcode) {
		super();
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}
	
}
