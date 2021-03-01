package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MemoryMemberRepository;

public class MemberService {
	
	private final MemberRepository memberRepository = new MemoryMemberRepository();
	
	/**
	 * 회원 가입
	 * @param member
	 * @return
	 */
	public Long join(Member member) {
		
		validateDuplicateMember(member); //중복회원 검증
		memberRepository.save(member);
		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		// 중복 회원 안됨
		memberRepository.findByName(member.getName())
		.ifPresent(m -> {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		});
	}
	
	/**
	 * 전체 회원 조회
	 * @return
	 */
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}
	
	/**
	 * 아이디 하나 조회
	 * @param memberId
	 * @return
	 */
	public Optional<Member> findOne(Long memberId){
		return memberRepository.findById(memberId);
	}
	
}