package hello.core.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor // final이 붙은 생성자를 만들어줌
public class OrderServiceImpl implements OrderService {

//	private final MemberRepository memberRepository = new MemoryMemberRepository();
	private final MemberRepository memberRepository;
//	private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//	private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
	private final DiscountPolicy discountPolicy;

//	public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//		super();
//		this.memberRepository = memberRepository;
//		this.discountPolicy = discountPolicy;
//	}

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {

		Member member = memberRepository.findById(memberId);
		int discountPrice = discountPolicy.discount(member, itemPrice);

		return new Order(memberId, itemName, itemPrice, discountPrice);
	}

	// 테스트 용도
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}

}
