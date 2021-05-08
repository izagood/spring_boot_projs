package jpabook.jpashop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Controller
@RequiredArgsConstructor
public class ItemController {
	
	private final ItemService itemService;
	
	@GetMapping("/item/new")
	public String createForm(Model model) {
		model.addAttribute("form", new BookForm());
		return "items/createItemForm";
	}
	
	@PostMapping("/item/new")
	public String create(BookForm bookForm) {
		
		Book book = new Book();
		book.setName(bookForm.getName());
		book.setPrice(bookForm.getPrice());
		book.setStockQuantity(bookForm.getStockQuantity());
		book.setAuthor(bookForm.getAuthor());
		book.setIsbn(bookForm.getIsbn());
		
		itemService.saveItem(book);
		return "redirect:/items";
	}
	
	@GetMapping("/items")
	public String list(Model model) {
		List<Item> items = itemService.findItems();
		model.addAttribute("items", items);
		return "items/itemList";
	}
	
	@GetMapping("item/{itemId}/edit")
	public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
		Book item = (Book) itemService.findOne(itemId); // cast 실제로 사용하면 좋지 않음 예제 단순화
		
		BookForm form = new BookForm();
		
		
		
	}
	
}
