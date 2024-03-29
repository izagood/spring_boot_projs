package jpabook.jpashop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ItemController {
	
	private final ItemService itemService;
	
	@GetMapping("/items/new")
	public String createForm(Model model) {
		model.addAttribute("form", new BookForm());
		return "items/createItemForm";
	}
	
	@PostMapping("/items/new")
	public String create(BookForm bookForm) {
		
		Book form = new Book();
		form.setName(bookForm.getName());
		form.setPrice(bookForm.getPrice());
		form.setStockQuantity(bookForm.getStockQuantity());
		form.setAuthor(bookForm.getAuthor());
		form.setIsbn(bookForm.getIsbn());
		
		itemService.saveItem(form);
		return "redirect:/items";
	}
	
	@GetMapping("/items")
	public String list(Model model) {
		List<Item> items = itemService.findItems();
		model.addAttribute("items", items);
		return "items/itemList";
	}
	
	@GetMapping("/items/{itemId}/edit")
	public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
		Book item = (Book) itemService.findOne(itemId); // cast 실제로 사용하면 좋지 않음 예제 단순화
		
		BookForm form = new BookForm();
		form.setId(item.getId());
		form.setName(item.getName());
		form.setPrice(item.getPrice());
		form.setStockQuantity(item.getStockQuantity());
		form.setAuthor(item.getAuthor());
		form.setIsbn(item.getIsbn());
		
		model.addAttribute("form", form);
		return "items/updateItemForm";
	}
	
	@PostMapping("/items/{itemId}/edit")
	public String updateItem(@PathVariable("itemId") Long itemId, @ModelAttribute("form") BookForm form) {
		
//		어설프게 Entity를 만들지 않는다.
//		Book book = new Book();
//		book.setId(form.getId());
//		book.setName(form.getName());
//		book.setPrice(form.getPrice());
//		book.setStockQuantity(form.getStockQuantity());
//		book.setAuthor(form.getAuthor());
//		book.setIsbn(form.getIsbn());
//		itemService.saveItem(book);
		
		itemService.updateItem(itemId, form.getName(), form.getPrice(), form.getStockQuantity());
		
		return "redirect:/items";
	}
	
}
