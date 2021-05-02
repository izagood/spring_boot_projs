package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class MemberRepositoryTest {
	
	@Autowired MemberRepository memberRepository;
	
	@Test
	@Transactional
	public void 테스트() {
		//given
		Member member = new Member();
		member.setUsername("memberA");
		//when
		Long saveId = memberRepository.save(member);
		Member findMember = memberRepository.find(saveId);
		//then
		Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
		Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
//		위와 같은 상황일때 java.lang.IllegalStateException: Failed to load ApplicationContext 에러가 발생한다.
//		Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'entityManagerFactory' defined in class path resource [org/springframework/boot/autoconfigure/orm/jpa/HibernateJpaConfiguration.class]: Invocation of init method failed; nested exception is org.hibernate.service.spi.ServiceException: Unable to create requested service [org.hibernate.engine.jdbc.env.spi.JdbcEnvironment]
	}
}
