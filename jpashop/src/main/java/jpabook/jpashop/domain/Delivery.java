package jpabook.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Delivery {
	
	@Id @GeneratedValue
	@Column(name = "delivery_id")
	private Long id;
	
	@OneToOne(mappedBy = "delivery")
	private Order order;
	
	@Embedded
	private Address address;
	
	@Enumerated(EnumType.STRING) //Enum 타입 주의 ORDINAL가 기본인데 enum에 1,2 같은 숫자를 할당하는데 만약에 중간 부분에 새로운 enum이 삽입되면 문제가 생긴다. 그래서 STRING으로 사용
	private DeliveryStatus status; //READY, COMPLETE
}
