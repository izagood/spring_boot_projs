package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

	@Test
	public void lifeCycleTest() {
		ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
		NetworkClient client = ac.getBean(NetworkClient.class);
		ac.close(); // 보통 close()할 일이 없기 때문에 ConfigurableApplicationContext를 잘 쓰지 않지만 close()를 사용할때는 하위 클래스로 받아줘야한다.
	}
	
	@Configuration
	static class LifeCycleConfig{
		
		
//		@Bean(initMethod = "init", destroyMethod = "close")
		@Bean
		public NetworkClient networkClient() {
			NetworkClient networkClient = new NetworkClient();
			networkClient.setUrl("http://naver.com");
			return networkClient;
		}
	}
	
}
