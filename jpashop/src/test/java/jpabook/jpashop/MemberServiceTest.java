package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashops.service.MemberService;

@SpringBootTest
@Transactional
public class MemberServiceTest {
	
	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;
	
	@Test
	public void 회원가입() throws Exception {
		//given
		Member member = new Member();
		member.setName("Lee");
		
		//when
		Long saveId = memberService.join(member);
		
		
		//then
		Assertions.assertThat(member).isEqualTo(memberRepository.findONe(saveId));
	}
	
	@Test
	public void 중복_회원_예외() throws Exception {
		//given
		
		//when
		
		//then
	}
	
}
