package jpabook.jpashop;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {
	
	@PersistenceContext
	private EntityManager em; // 스프링부트가 알아서 생성해줌
	
	public Long save(Member member) {
		/* 원칙: 커맨드와 쿼리를 분리하라 */
		em.persist(member);
		return member.getId(); // member를 반환하지 않고 Id를 반환하는 이유가 위의 원칙에 따라
	}
	
	public Member find(Long id) {
		return em.find(Member.class, id);
	}
}
