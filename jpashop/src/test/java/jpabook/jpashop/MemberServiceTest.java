package jpabook.jpashop;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.service.MemberService;

@SpringBootTest
@Transactional
public class MemberServiceTest {
	
	@Autowired
	MemberService memberService;
	@Autowired
	MemberRepository memberRepository;
	
	@Test
//	@Rollback(false)
	public void 회원가입() throws Exception {
		//given
		Member member = new Member();
		member.setName("Lee");
		
		//when
		Long saveId = memberService.join(member);
		
		
		//then
		Assertions.assertThat(member).isEqualTo(memberRepository.findOne(saveId));
	}
	
	@Test
	public void 중복_회원_예외() throws Exception {
		//given
		Member member1 = new Member();
		member1.setName("kim");
		
		Member member2 = new Member();
		member2.setName("kim");
		//when
		memberService.join(member1);
//		try {
//			memberService.join(member2); // 예외가 발생해야 한다.
//		} catch (IllegalStateException e) {
//			return;
//		}
		
		//then
		IllegalStateException assertThrows = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
		assertThat(assertThrows.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//		fail("예외가 발생해야 한다.");
		
	}
	
}
