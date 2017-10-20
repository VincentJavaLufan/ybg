import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/*** @author https://gitee.com/YYDeament/88ybg
 * 
 * @date 2017/10/1 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.bstek", "com.ybg", "cn", "org.mybatis" }) // 扫描注解
public class App extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(App.class);
	}
}
