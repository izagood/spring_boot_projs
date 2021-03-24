package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
		excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)// 예제에 AppConfig에 Configuration이 설정되어있기 때문에 AppConfig가 등록되지 않도록 filter를 설정
public class AutoAppConfig {
	
}
