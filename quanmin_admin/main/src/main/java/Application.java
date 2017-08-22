import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {
		org.activiti.spring.boot.SecurityAutoConfiguration.class, org.springframework.boot.actuate.autoconfigure.ManagementWebSecurityAutoConfiguration.class })
@ComponentScan(basePackages = { "org.activiti.rest.diagram","com.qq", "com.baidu", "com.ybg", "cn", "weibo4j", "org.mybatis" }) // 扫描注解
public class Application {
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).web(true).run(args);
	}
}
