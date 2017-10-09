package com.ybg.urule;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import com.bstek.urule.console.servlet.URuleServlet;

@Configuration
@ImportResource({"classpath:urulecontext.xml"})
public class URuleConfiguration {
	
	@Bean(name="uRuleServlet")
	public ServletRegistrationBean registerURuleServlet() {
		return new ServletRegistrationBean(new URuleServlet(), "/urule/*");
	}
}