package jpabook;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jpabook.domain.Order;
import jpabook.domain.OrderItem;

public class jpaMain {
	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();

		tx.begin();
		try {
//			Member member = new Member(200L, "member200");
//			em.persist(member);
			
//			Order order = new Order();
//			order.addOrderItem(new OrderItem());

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();

//		SpringApplication.run(JpaBasicApplication.class, args);
	}
}
