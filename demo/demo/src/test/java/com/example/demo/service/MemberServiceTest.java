package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemoryMemberRepository;

class MemberServiceTest {
	
	
	MemberService memberService;
	MemoryMemberRepository memoryMemberRepository;
	
	@BeforeEach
	void 초기설정() {
		memoryMemberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryMemberRepository);
	}
	
	@AfterEach
	void 클리어() {
		memoryMemberRepository.clearStore();
	}

	@Test
	void 회원가입() {
		//given
		Member member = new Member();
		member.setName("spring");
		
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
		member1.setName("spring");
		
		Member member2 = new Member();
		member2.setName("spring");
		
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
