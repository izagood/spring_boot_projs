package jpabook.jpashop.domain.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("A") // 안 해주면 클래스 명으로 들어감
@Getter @Setter
public class Album extends Item {
	
	private String artist;
	private String etc;
	
}
