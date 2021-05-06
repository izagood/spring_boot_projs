//package jpabook.jpashop;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//@SpringBootTest
//public class MemberRepository_backupTest {
//	
//	@Autowired MemberRepository_backup memberRepository;
//	
//	@Test
//	@Transactional
//	public void 테스트() {
//		//given
//		Member_backup member = new Member_backup();
//		member.setUsername("memberA");
//		//when
//		Long saveId = memberRepository.save(member);
//		Member_backup findMember = memberRepository.find(saveId);
//		//then
//		Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
//		Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
////		위와 같은 상황일때 java.lang.IllegalStateException: Failed to load ApplicationContext 에러가 발생한다.
////		Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'entityManagerFactory' defined in class path resource [org/springframework/boot/autoconfigure/orm/jpa/HibernateJpaConfiguration.class]: Invocation of init method failed; nested exception is org.hibernate.service.spi.ServiceException: Unable to create requested service [org.hibernate.engine.jdbc.env.spi.JdbcEnvironment]
////		최초 H2 생성하고 나서 현재 프로젝트에 맞는 DB 생성해줘야 함
//		Assertions.assertThat(findMember).isEqualTo(member);
//		
//		
//	}
//}
