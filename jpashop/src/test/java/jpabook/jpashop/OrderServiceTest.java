package jpabook.jpashop;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.service.OrderService;

@SpringBootTest
@Transactional
public class OrderServiceTest {
	
	@Autowired EntityManager em;
	@Autowired OrderService orderService;
	@Autowired OrderRepository orderRepository;
	
	@Test
	public void 상품주문() throws Exception {
		//given
		Member member = createMember();
		
		Book book = createBook("시골 JPA", 10000, 10);
		
		int orderCount = 2;
		
		//when
		Long orderId = orderService.Order(member.getId(), book.getId(), orderCount);
		
		//then
		Order getOrder = orderRepository.findOne(orderId);
		
		assertThat(getOrder.getStatus()).isEqualTo(OrderStatus.ORDER);
		assertThat(getOrder.getOrderItems().size()).isEqualTo(1);
		assertThat(getOrder.getTotalPrice()).isEqualTo(10000 * orderCount);
		assertThat(book.getStockQuantity()).isEqualTo(8);
	}

	
	
	@Test
	public void 상품주문_재고수량초과() throws Exception {
		//given
		Member member = createMember();
		
		Item item = createBook("시골 JPA", 10000, 10);
		
		//when
		int orderCount = 11;
		
		//then
		NotEnoughStockException assertThrows = Assertions.assertThrows(NotEnoughStockException.class, () -> orderService.Order(member.getId(), item.getId(), orderCount));
		assertThat(assertThrows.getMessage()).isEqualTo("need more stock");
	}
	
	@Test
	public void 주문취소() throws Exception {
		//given
		Member member = createMember();
		
		Item item = createBook("시골 JPA", 10000, 10);
		
		int orderCount = 2;
		
		Long orderId = orderService.Order(member.getId(), item.getId(), orderCount);
		//when
		orderService.cancelOrder(orderId);
		
		//then
		Order getOrder = orderRepository.findOne(orderId);
		
		assertThat(getOrder.getStatus()).isEqualTo(OrderStatus.CANCEL);
		assertThat(item.getStockQuantity()).isEqualTo(10);
	}
	
//	@Test
//	public void 상품주문_재고수량초과() throws Exception {
//		//given
//		
//		//when
//		
//		//then
//	}
	
	
	private Book createBook(String name, int price, int quantity) {
		Book book = new Book();
		book.setName(name);
		book.setPrice(price);
		book.setStockQuantity(quantity);
		em.persist(book);
		return book;
	}

	private Member createMember() {
		Member member = new Member();
		member.setName("회원1");
		member.setAddress(new Address("서울", "강가", "123-123"));
		em.persist(member);
		return member;
	}
	
	
	
}
