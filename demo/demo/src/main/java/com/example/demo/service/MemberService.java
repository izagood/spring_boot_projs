package com.example.demo.service;

import java.lang.reflect.Member;

import com.example.demo.repository.MemberRepository;

public class MemberService {
	private final MemberRepository memberRepository = new MemberRepository();
	
	/**
	 *  회원가입 
	 */
	public Long join(Member member) {
		memberRepository.findByName(member.getName());
	}
	
}
