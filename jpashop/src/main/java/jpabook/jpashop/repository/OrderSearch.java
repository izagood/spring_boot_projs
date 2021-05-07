package jpabook.jpashop.repository;

import org.springframework.stereotype.Repository;

import jpabook.jpashop.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Repository
@Getter @Setter
public class OrderSearch {
	
	private String memberName;
	private OrderStatus orderStatus;
	
}
