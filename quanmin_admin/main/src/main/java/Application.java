import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import de.codecentric.boot.admin.config.EnableAdminServer;

/** 程序入口类
 * 
 * @author Deament
 * @date 2017/1/1 */
@EnableScheduling
@EnableAdminServer
@SpringBootApplication
@EnableAutoConfiguration(exclude = { org.activiti.spring.boot.SecurityAutoConfiguration.class, org.springframework.boot.actuate.autoconfigure.ManagementWebSecurityAutoConfiguration.class })
@ComponentScan(basePackages = { "org.activiti.rest.diagram", "com.ybg", "cn", "org.mybatis" }) 
public class Application {
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).web(true).run(args);
	}
}
