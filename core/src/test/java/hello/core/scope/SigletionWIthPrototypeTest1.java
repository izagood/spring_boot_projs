package hello.core.scope;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SigletionWIthPrototypeTest1 {
	
	@Test
	void prototypeFind() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
		PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
		prototypeBean1.addCount();
		assertThat(prototypeBean1.getCount()).isEqualTo(1);
		
		PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
		prototypeBean2.addCount();
		assertThat(prototypeBean2.getCount()).isEqualTo(1);
	}
	
	@Test
	void singletonClientUsePrototype() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
		ClientBean clientBean1 = ac.getBean(ClientBean.class);
		int count1 = clientBean1.logic();
		assertThat(count1).isEqualTo(1);
		
		ClientBean clientBean2 = ac.getBean(ClientBean.class);
		int count2 = clientBean2.logic();
		assertThat(count2).isEqualTo(1);
//		assertThat(count2).isEqualTo(2);
	}
	/**
	 * ObjectProvider를 java에서 제공하는 provider로 대체해서 스프링 의존성을 제거함.
	 * @author mcnc
	 *
	 */
	static class ClientBean{
		
		private Provider<PrototypeBean> prototypeBeanProvider; // 필요할때 마다 요청하게 할 수 있음
//		private ObjectFactory<PrototypeBean> prototypeBeanFactory; 이것도 가능하지만 ObjectProvider가 편의 기능을 더 많이 제공한다.
		
		@Autowired // 생략가능
		public ClientBean(Provider<PrototypeBean> prototypeBeanProvider) {
			this.prototypeBeanProvider = prototypeBeanProvider;
		}
		
		public int logic() {
			PrototypeBean prototypeBean = prototypeBeanProvider.get(); // DL : Dependency LookUp
			prototypeBean.addCount();
			return prototypeBean.getCount();
		}
	}
	
	/**
	 * 스프링에 의존성있는 ObjectProvider 사용
	 * @author mcnc
	 *
	 */
//	static class ClientBean{
//		
//		private ObjectProvider<PrototypeBean> prototypeBeanProvider; // 필요할때 마다 요청하게 할 수 있음
////		private ObjectFactory<PrototypeBean> prototypeBeanFactory; 이것도 가능하지만 ObjectProvider가 편의 기능을 더 많이 제공한다.
//		
//		@Autowired // 생략가능
//		public ClientBean(ObjectProvider<PrototypeBean> prototypeBeanProvider) {
//			this.prototypeBeanProvider = prototypeBeanProvider;
//		}
//		
//		public int logic() {
//			PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
//			prototypeBean.addCount();
//			return prototypeBean.getCount();
//		}
//	}
	
	/**
	 * 이대로 사용하면 프로토타입 빈이 한번 주입되고 동일한 프로토타입이 계속 호출됨
	 * @author mcnc
	 *
	 */
//	@Scope("singleton") // 디폴트라 안 써줘도 됨
//	static class ClientBean{
//		private final PrototypeBean prototypeBean; // 생성시점에 주입
//		
//		@Autowired // 생략가능
//		public ClientBean(PrototypeBean prototypeBean) {
//			this.prototypeBean = prototypeBean;
//		}
//		
//		public int logic() {
//			prototypeBean.addCount();
//			return prototypeBean.getCount();
//		}
//	}
	
	@Scope("prototype")
	static class PrototypeBean{
		private int count = 0;
		
		public void addCount() {
			count++;
		}
		
		public int getCount() {
			return count;
		}
		
		@PostConstruct
		public void init() {
			System.out.println("PrototypeBean = " + this);
		}
		
		@PreDestroy
		public void destroy() {
			System.out.println("PrototypeBean.destroy");
		}
	}
}
