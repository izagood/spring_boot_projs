package jpabook.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Category extends BaseEntity{

	@Id @GeneratedValue
	@Column(name = "category_id")
	private Long id;
	
	private String name;
	
	@OneToMany(mappedBy = "item")
	private List<Item> items = new ArrayList<>();
	
//	@ManyToMany
//	@JoinTable(name = "category_id",
//				joinColumns = @JoinColumn(name = "category_id"),
//				inverseJoinColumns = @JoinColumn(name = "item_id")
//	)
//	private List<Item> items = new ArrayList<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Category parent;
	
	@OneToMany(mappedBy = "parent")
	private List<Category> child = new ArrayList<>(); 
}
