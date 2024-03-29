package com.example.jpaBasic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaBasicApplication {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		try {
			Member member = new Member(200L, "member200");
			em.persist(member);
			
			tx.commit();
		} catch(Exception e){
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
		
//		SpringApplication.run(JpaBasicApplication.class, args);
	}

}
