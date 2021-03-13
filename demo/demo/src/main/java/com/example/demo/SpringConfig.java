package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.aop.TimeTraceAop;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;

@Configuration
public class SpringConfig {
	
//	private DataSource dataSource;
	
//	@PersistenceContext
//	private EntityManager em;
	
//	public SpringConfig(DataSource dataSource) {
//		this.dataSource = dataSource;
//	}
	
//	public SpringConfig(EntityManager em) {
//		this.em = em;
//	}
	
	@Autowired
	private final MemberRepository memberRepository;
	
	
	
	public SpringConfig(MemberRepository memberRepository) {
	this.memberRepository = memberRepository;
}

	@Bean
	public MemberService memberService() {
//		return new MemberService(memberRepository());
		return new MemberService(memberRepository);
	}
	
//	@Bean
//	public TimeTraceAop timeTraceAop() {
//		return new TimeTraceAop();
//	}
	
//	@Bean
//	public MemberRepository memberRepository() {
//		return new MemoryMemberRepository();
		// 이부분을 바꿔주면 바라보는 저장소를 변경할 수 있다.
//		return new JdbcMemberRepository(dataSource);
//		return new JdbcTemplateMemberRepository(dataSource);
//		return new JpaMemberRepository(em);
		
		
//	}
}
