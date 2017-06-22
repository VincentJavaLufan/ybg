import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration    //这个注解可以根据你依赖的包自动生成相关配置
@ComponentScan(basePackages = { "com.qq", "com.baidu", "com.ybg", "cn", "weibo4j", "org.mybatis" }) // 扫描注解
public class App extends SpringBootServletInitializer
{  
    public static void main( String[] args )  
    {  
        SpringApplication.run(App.class, args);  
    }  

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // TODO Auto-generated method stub
        return builder.sources(App.class);
    }
} 
