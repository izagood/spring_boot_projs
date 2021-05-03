package jpabook.jpashop.domain.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("M") // 안 해주면 클래스 명으로 들어감
@Getter @Setter
public class Movie extends Item {
	
	private String director;
	private String actor;
}
