package com.ybg.uflo;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import com.bstek.uflo.console.UfloServlet;

@Configuration
@ImportResource("classpath:uflocontext.xml")
public class UfloConfiguration {
	
	@Bean
	public ServletRegistrationBean buildUfloServlet() {
		return new ServletRegistrationBean(new UfloServlet(), "/uflo/*");
	}
}
