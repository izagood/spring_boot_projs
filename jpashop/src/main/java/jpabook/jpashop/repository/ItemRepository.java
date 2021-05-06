package jpabook.jpashop.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
	
	private final EntityManager em;
	
	public void save(Item item) {
		if(item.getId() == null) {
			em.persist(item); // 신규로 생성
		}else {
			em.merge(item); // update 비슷한거 // 이미 db에 등록된 것에 대한 업데이트 개념
		}
	}
	
	public Item findOne(Long id) {
		return em.find(Item.class, id);
	}
	
	public List<Item> findAll(){
		return em.createQuery("select i from item i", Item.class)
				.getResultList();
	}
}
