package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Member;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
	
	// findByName -> findBy 뒤으 Name을 보고 밑 처럼 쿼리를 짜준다.
	// JPQL select m from Member m where m.name = ?
	@Override
	Optional<Member> findByName(String name);
}
