package jpabook.jpashop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

	private final ItemRepository itemRepository;
	
	@Transactional
	public void saveItem(Item item) {
		itemRepository.save(item);
	}
	
	@Transactional
	public void updateItem(Long itemId, String name, int price, int stockQuantity) {
		Item findItem = itemRepository.findOne(itemId);
		findItem.setPrice(price);
		findItem.setName(name);
		findItem.setStockQuantity(stockQuantity);
		
	}
	
//	@Transactional
//	public Item updateItem(Long itemId, Book param) { // merge 동작방식과 같다.
//		Item findItem = itemRepository.findOne(itemId);
//		findItem.setPrice(param.getPrice());
//		findItem.setName(param.getName());
//		findItem.setStockQuantity(param.getStockQuantity());
//		return findItem;
//	}
	
	public List<Item> findItems(){
		return itemRepository.findAll();
	}
	
	public Item findOne(Long itemId) {
		return itemRepository.findOne(itemId);
	}

	
}
