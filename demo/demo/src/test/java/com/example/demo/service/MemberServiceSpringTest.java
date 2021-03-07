package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;

@SpringBootTest
@Transactional
class MemberServiceSpringTest {
	
	
	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;
	
	@Test
	void 회원가입() {
		//given
		Member member = new Member();
		member.setName("spring1");
		
		//when
		Long saveId = memberService.join(member);

		
		//then
		Member findMember = memberService.findOne(saveId).get();
		assertThat(member.getName()).isEqualTo(findMember.getName());
	}
	
	@Test
	void 중복_회원_예외() {
		//given
		Member member1 = new Member();
		member1.setName("spring1");
		
		Member member2 = new Member();
		member2.setName("spring1");
		
		//when
		/*
		 * memberService.join(member1); try { memberService.join(member2); fail();
		 * }catch(IllegalStateException e) {
		 * assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); }
		 */
		
		memberService.join(member1);
		/*
		 * assertThrows(IllegalStateException.class, () -> memberService.join(member2));
		 */
		IllegalStateException assertThrows = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
		assertThat(assertThrows.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
		//then
	}

	@Test
	void 맴버모두찾기() {
		//given
		
		//when
		
		//then
	}

	@Test
	void 맴버한명찾기() {
		//givne
		
		//when
		
		//then
	}

}
