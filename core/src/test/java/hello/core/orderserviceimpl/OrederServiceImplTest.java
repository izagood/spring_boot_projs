package hello.core.orderserviceimpl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.Order;
import hello.core.order.OrderServiceImpl;

public class OrederServiceImplTest {

	@Test
	void createOrder() {
		MemoryMemberRepository memberRepository = new MemoryMemberRepository();
		memberRepository.save(new Member(1L, "name", Grade.VIP));
		
		
		OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
		Order order = orderService.createOrder(1L, "itemA", 10000);
		
		Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
	}
	
}
