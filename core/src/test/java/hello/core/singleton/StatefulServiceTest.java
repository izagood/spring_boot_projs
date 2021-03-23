package hello.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {
	@Test
	@DisplayName("")
	void stattefulServiceSingleton() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
		StatefulService statefulService1 = ac.getBean(StatefulService.class);
		StatefulService statefulService2 = ac.getBean(StatefulService.class);
		
		//ThreadA
		statefulService1.order("userA", 10000);
		
		
		//ThreadB
		statefulService2.order("userB", 20000);
		
		int price = statefulService1.getPrice();
		System.out.println("price = " + price);
	}
	
	static class TestConfig{
		@Bean
		public StatefulService statefulService() {
			return new StatefulService();
		}
	}
}
